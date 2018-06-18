package cn.czfshine.duplicate.vfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:czfshine
 * @date:2018/6/17 18:47
 */

public class Node<K extends Comparable> {

    /*
        new Node().add(2)
                   .add(new Node().add(3)
                                    .add(4))
                   .add(5);

     */

    private List<Node<K>> children;
    private K key;

    public Node(K k){
        this();
        key = k;
    }
    public Node(){
        children=new ArrayList<>();
    }

    public Node<K> add(Node child){
        children.add(child);
        return this;
    }
    public Node<K> add(K k){
        children.add(new Node(k));
        return this;
    }

    public List<Node<K>> getChildren(){
        return children;
    }


    public K getKey() {

        return key;
    }

}
