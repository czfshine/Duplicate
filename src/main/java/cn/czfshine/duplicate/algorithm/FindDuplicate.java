package cn.czfshine.duplicate.algorithm;

import cn.czfshine.duplicate.CSV;
import cn.czfshine.duplicate.struct.*;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

/**
 * @author:czfshine
 * @date:2018/6/19 14:56
 */

public class FindDuplicate {
    Tree root ;
    public  void findDuplicate(Tree tree){
        root = tree;
        placeDirInList();
        placeDirToHashMap();
        sortListByLevel(dirlist);
        for(Map.Entry<Integer, List<TreeNode>> list:dirhashmap.entrySet()){
           sortListByLevel(list.getValue());
        }
        findAllDuplicateGroup();
    }

    private List<TreeNode> dirlist;
    private  void placeDirInList(){
        List<TreeNode> allBranchNode = root.getAllBranchNode();
        dirlist=new ArrayList<>(allBranchNode);
    }

    private void sortListByLevel(List<TreeNode> dirlist){
        dirlist.sort((Levelable a,Levelable b)-> {

            if( a.getLevel() > b.getLevel()) return 1;
            else if(a.getLevel()==b.getLevel()){
                return 0;
            }else{
                return -1;
            }
        });
    }


    Map<Integer,List<TreeNode>> dirhashmap =new HashMap<>();

    private void putNodeToHashMAp(int hash,TreeNode node){
        if(!dirhashmap.containsKey(hash)){
            ArrayList<TreeNode> li = new ArrayList<>();
            dirhashmap.put(hash, li);
        }

        List<TreeNode> treeNodes = dirhashmap.get(hash);
        treeNodes.add(node);
    }

    private void placeDirToHashMap(){
        for(TreeNode node : dirlist){
            putNodeToHashMAp(node.hashCode(),node);
        }
    }

    private Set<Integer> compared=new HashSet<>();
    private void findAllDuplicateGroup(){

        int dirlen=dirlist.size();

        for(int i=0;i<dirlen;i++){
            TreeNode treeNode = dirlist.get(i);
            if(compared.contains(treeNode.hashCode())){
                continue;
            }
            compared.add(treeNode.hashCode());
            compareAllSameHashDir(treeNode.hashCode());
            System.out.println(treeNode.getLevel());
        }
    }

    private void compareAllSameHashDir(int hash){
        List<TreeNode> list = dirhashmap.get(hash);
        if(list.size()>10000){
            return;//todo
        }
        for(int i=0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size();j++){
                compareSameHashDir(list.get(i),list.get(j));
                //System.out.println("compare"+i+","+j);
            }
        }

    }

    private Map<Pair<TreeNode,TreeNode>,Boolean> samehash =new HashMap<>();
    private void compareSameHashDir(TreeNode a,TreeNode b){
        if(a.hashCode()!=b.hashCode()){
            return;
        }

        if(samehash.getOrDefault(new Pair<>(a,b),null)!=null){
           return;
        }
        if(samehash.getOrDefault(new Pair<>(b,a),null)!=null){
            return;
        }
        compareDir(a,b);

    }

    private Set<Pair<TreeNode,TreeNode>> sameNode=new HashSet<>();

    private Boolean compareDir(TreeNode a,TreeNode b){
        if(a.hashCode()!=b.hashCode()){
            return false;
        }

        if(((HasSize)a).getSize()!=((HasSize)b).getSize()){
            return false;
        }
        List<TreeNode> aChildren = a.getChildren();
        List<TreeNode> bChildren = b.getChildren();
        if(aChildren.size()!=bChildren.size()){
            return false;
        }
        sortListByLevel(aChildren);
        sortListByLevel(bChildren);
        for(int i=0;i<aChildren.size();i++){
            TreeNode anode = aChildren.get(i);
            TreeNode bnode = bChildren.get(i);
            if(compareDir(anode,bnode)==false){
                return false;
            }
        }
        if(a instanceof DirContent && b instanceof DirContent){
            sameNode.add(new Pair<>(a,b));
        }
        return true;
    }

    public void Test() throws IOException {
        System.out.println("Dir count:"+dirlist.size());
        System.out.println("Hash count"+dirhashmap.size());
        CSV csv = new CSV("hashdata.csv");
        for(Integer i: dirhashmap.keySet()) csv.write(i,dirhashmap.get(i).size());

        System.out.println("smae node count:"+sameNode.size());
        for(Pair<TreeNode,TreeNode> pair:sameNode){
            DirContent dirContent=(DirContent) pair.getKey();
            DirContent dirContent1=(DirContent) pair.getValue();
            System.out.println(dirContent.getPath()+"&&"+dirContent1.getPath());
        }
    }

}
