package cn.czfshine.duplicate.struct;

import javax.xml.soap.Node;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:czfshine
 * @date:2018/6/19 14:57
 */

public class Tree {
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    public interface Traversaler {
        void ArrivalsNode(TreeNode node);
    }

    private int curtraverlevel=0;
    private void preOrder(TreeNode root,Traversaler callback){
        curtraverlevel++;
        if(root!=null){
            callback.ArrivalsNode(root);
        }

        if(root.getChildren().size()>0){

            List<TreeNode> children = root.getChildren();
            for(TreeNode child:children){
                preOrder(child,callback);
            }
        }
        curtraverlevel--;
    }

    public void PreOrderTraversal(Traversaler callback){
        curtraverlevel=0;
        preOrder(root,callback);
    }

    public List<TreeNode> getAllBranchNode(){
        List<TreeNode> res=new LinkedList<>();
        class IsBranchNodeTraversal implements  Traversaler{

            @Override
            public void ArrivalsNode(TreeNode node) {
                if(node.getChildren().size()>0){
                    res.add(node);
                }
            }
        }

        this.PreOrderTraversal(new IsBranchNodeTraversal());
        return res;
    }

    public void setAllLevel(){
        class IsBranchNodeTraversal implements  Traversaler{

            @Override
            public void ArrivalsNode(TreeNode node) {
               node.setLevel(curtraverlevel);
            }
        }

        this.PreOrderTraversal(new IsBranchNodeTraversal());
    }
}
