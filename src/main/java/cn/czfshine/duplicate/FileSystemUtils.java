package cn.czfshine.duplicate;

import cn.czfshine.duplicate.struct.DirContent;
import cn.czfshine.duplicate.struct.FileContent;
import cn.czfshine.duplicate.struct.Tree;
import cn.czfshine.duplicate.vfs.FileSystem;
import cn.czfshine.duplicate.vfs.VirtualDirs;
import cn.czfshine.duplicate.vfs.VirtualFiles;

/**
 * @author:czfshine
 * @date:2018/6/19 18:16
 */

public class FileSystemUtils {

    private static FileContent createFileNodeFromVirtualFile(VirtualFiles files){
        FileContent fileContent = new FileContent();
        fileContent.setSize(files.getSize());
        fileContent.setFilename(files.getFilepath());
        return fileContent;
    }

    private static DirContent createDirNodeFromVirtualDir(VirtualDirs dirs) throws Exception {
        DirContent dirContent =new DirContent();
        dirContent.setPath(dirs.getPathname());
        for(VirtualFiles files : dirs.getChildrenfile()){
            FileContent fileContent = createFileNodeFromVirtualFile(files);
            dirContent.addChildren(fileContent);
        }

        for(VirtualDirs dir :dirs.getChildrendirs()){
            DirContent content = createDirNodeFromVirtualDir(dir);
            dirContent.addChildren(content);
        }
        return dirContent;
    }

    public static Tree FileSystemToTree(FileSystem fileSystem) throws Exception {
        Tree tree = new Tree();
        tree.setRoot(createDirNodeFromVirtualDir(fileSystem.getRoot()));
        tree.setAllLevel();
        return tree;
    }
}
