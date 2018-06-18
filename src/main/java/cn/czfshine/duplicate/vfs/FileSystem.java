package cn.czfshine.duplicate.vfs;

import java.io.*;

/**
 * @author:czfshine
 * @date:2018/6/17 19:19
 */

public class FileSystem implements  Serializable{
    private VirtualDirs root;

    public FileSystem(String path){
        root=new VirtualDirs(path,null);
    }

    public VirtualDirs getRoot() {
        return root;
    }

    public FileSystem setRoot(VirtualDirs root) {
        this.root = root;
        return null;
    }

    public void dumpFile(String path) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
        out.writeObject(root);
        out.close();
    }

    public static FileSystem loadFromFile(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream infile =new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
        Object o= infile.readObject();
        VirtualDirs root=(VirtualDirs)o;
        FileSystem fileSystem = new FileSystem(root.getPathname());
        fileSystem.setRoot(root);
        return fileSystem;
    }

    private void printDir(VirtualDirs dir){
        System.out.println("======The dir["+dir.getPathname()+"] content:");
        System.out.println("dirs:");
        for(VirtualDirs dirs :dir.getChildrendirs()){
            System.out.println(dirs.getPathname());
        }
        System.out.println("files:");
        for(VirtualFiles files :dir.getChildrenfile()){
            System.out.println(files.getFilepath()+"\t"+files.getSize());
        }
        System.out.println("subdirs content:");

        for (VirtualDirs dirs:dir.getChildrendirs()){
            printDir(dirs);
        }
    }
    public void showTree(){
        printDir(root);
    }
}
