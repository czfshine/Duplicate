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
    public int update(int hash) {
        if(hash==0){
            return this.hash;
        }

        while(hash%2==0){
            hash>>=1;
        }
        this.hash=this.hash*hash;

        return this.hash;
    }
}
