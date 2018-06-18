package cn.czfshine.duplicate.vfs;

/**
 * @author:czfshine
 * @date:2018/6/17 18:56
 */

public class LongNode extends Node<Long> {
    public LongNode(Long aLong) {
        super(aLong);
    }
    public LongNode(){
        super();
    }

    public String toString(){
        return getKey().toString();
    }

    @Override
    public Node<Long> add(Node child) {
        return super.add(child);
    }

    @Override
    public Node<Long> add(Long aLong) {
        return super.add(aLong);
    }
}
