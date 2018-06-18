package cn.czfshine.duplicate.vfs;

/**
 * @author:czfshine
 * @date:2018/6/17 19:02
 */

public class Nodes {

    private static void printOne(Node root,int level){
        System.out.println(root.toString());
        if(root.getChildren().size()>0)
        for(Object c:root.getChildren()){
            Node n=(Node) c;
            printOne(n,level+1);
        }
    }
    public static void printNodes(Node root){

        int level=0;
        printOne(root,level);
    }
}
