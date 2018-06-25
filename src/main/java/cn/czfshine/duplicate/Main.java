package cn.czfshine.duplicate;

import cn.czfshine.duplicate.algorithm.FindDuplicate;
import cn.czfshine.duplicate.struct.Tree;
import cn.czfshine.duplicate.vfs.FileSystem;

import java.io.IOException;

/**
 * @author:czfshine
 * @date:2018/6/19 18:14
 */

public class Main {
    public static void main(String[] args) throws Exception {
        //FileSystem fileSystem = FileSystem.loadFromFile("dumpfile1529550318663.dat");
        FileSystem fileSystem = FileList.createList("C:\\");
        System.out.println("open ok");
        Tree tree = FileSystemUtils.FileSystemToTree(fileSystem);
        System.out.println("to tree ok");
        FindDuplicate findDuplicate = new FindDuplicate();
        findDuplicate.findDuplicate(tree);
        System.out.println("find ok");
        findDuplicate.Test();
        //System.in.read();
    }
}
