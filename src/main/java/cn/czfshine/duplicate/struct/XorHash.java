package cn.czfshine.duplicate.struct;

/**
 * @author:czfshine
 * @date:2018/6/19 22:00
 */

public class XorHash implements Hash {

    private Byte[] intbyte;

    public XorHash(){
        intbyte=new Byte[]{0, 0, 0, 0};
    }

    private void Xor(byte[] bytetwo){
        for(int i=0;i<3;i++){
            intbyte[i]=((Number) (intbyte[i]^bytetwo[i])).byteValue();
        }
    }
    @Override
    public int getHash() {
        return  intbyte[3] & 0xFF |
                (intbyte[2] & 0xFF) << 8 |
                (intbyte[1] & 0xFF) << 16 |
                (intbyte[0] & 0xFF) << 24;
    }

    @Override
    public int update(Object hash) {
        int  number=((Number)hash).intValue();
        byte[] bytes = {
                (byte) ((number >> 24) & 0xFF),
                (byte) ((number >> 16) & 0xFF),
                (byte) ((number >> 8) & 0xFF),
                (byte) (number & 0xFF)
        };
        Xor(bytes);
        return getHash();
    }
}
