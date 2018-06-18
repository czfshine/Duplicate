package cn.czfshine.duplicate;

import cn.czfshine.duplicate.vfs.FileSystem;
import cn.czfshine.duplicate.vfs.VirtualDirs;
import cn.czfshine.duplicate.vfs.VirtualFiles;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Files.walkFileTree;

/**
 * @author:czfshine
 * @date:2018/6/17 16:51
 */

public class FileList {

    static class  FileVisitorImpl
            implements FileVisitor<Path> {


        private VirtualDirs curdir;
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            VirtualDirs c = new VirtualDirs(dir.toString(), curdir);
            curdir.addDir(c);
            curdir=c;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            VirtualFiles f = new VirtualFiles(file.toString(),attrs.size(),curdir);
            curdir.addFile(f);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            curdir=curdir.getParent();
            return FileVisitResult.CONTINUE;
        }

        public void setCurdir(VirtualDirs curdir) {
            this.curdir = curdir;
        }
    }

    public static  FileList createList(String pathstr) throws IOException {
        final int[] count = {0};
        FileSystem fileSystem = new FileSystem(pathstr);
        VirtualDirs root =fileSystem.getRoot();
        Path path =Paths.get(pathstr);

        FileVisitorImpl pathFileVisitor = new FileVisitorImpl();
        pathFileVisitor.setCurdir(root);
        Files.walkFileTree(path,pathFileVisitor );

        fileSystem.dumpFile("dumpfile.dat");
        //fileSystem.showTree();
        return null;
    }
}
