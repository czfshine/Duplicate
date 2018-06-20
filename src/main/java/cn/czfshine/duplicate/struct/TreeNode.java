package cn.czfshine.duplicate.struct;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author:czfshine
 * @date:2018/6/19 14:34
 */

public abstract class TreeNode implements Levelable {

    private List<TreeNode> children;
    private TreeNode parent;


    /*
    public  TreeNode(TreeNode parent,NodeContent content){
        this(content);
        this.parent=parent;
    }*/

    public TreeNode(){
        children = new ArrayList<>();
    }

    public void addChildren(TreeNode node) throws Exception {
        if(parent!=null){
            throw new Exception("You Must Add All Children Before Has Parent!Because We Count Total Size On Add Child." );
        }
        if(children.contains(node))
            return;
        node.setParent(this);
        children.add(node);
    }

    public Boolean isRoot(){
        return parent==null;
    }


    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent(){
        return this.parent;
    }
    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    private int level;
    @Override
    public void setLevel(int level) {
        this.level =level;
    }

    @Override
    public int getLevel() {
        return level;
    }
}
