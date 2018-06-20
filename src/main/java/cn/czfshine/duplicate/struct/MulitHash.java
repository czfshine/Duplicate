package cn.czfshine.duplicate.struct;

/**
 * @author:czfshine
 * @date:2018/6/19 17:53
 */

public class MulitHash implements Hash {

    private int hash=1;
    @Override
    public int getHash() {
        return hash;
    }

    @Override
    public int update(Object hash) {

        Integer h=((Number)hash).intValue();
        if(h==0){
            return this.hash;
        }
        this.hash=this.hash*h;
        return this.hash;
    }
}
