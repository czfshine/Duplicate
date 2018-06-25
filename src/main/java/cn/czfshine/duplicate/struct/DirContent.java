package cn.czfshine.duplicate.struct;

/**
 * @author:czfshine
 * @date:2018/6/19 17:51
 */

public class DirContent extends TreeNode implements HasSize {

    MulitHash mulitHash;
    //XorHash xorHash;

    private String path;
    private int getHash(){
       return  mulitHash.getHash()+(int)size;
    }
    public int getHashCode() {
        if(mulitHash!=null){
            return getHash();
        }
        mulitHash=new MulitHash();
        //xorHash=new XorHash();

        for(TreeNode node:getChildren()){
            if(node instanceof  FileContent){
                FileContent fileContent = (FileContent)node;
                mulitHash.update((int)fileContent.getSize());
                //xorHash.update(fileContent.getSize());
            }
            if(node instanceof  DirContent){
                DirContent dirContent = (DirContent)node;
                mulitHash.update(dirContent.getHashCode());
                //xorHash.update(dirContent.getHashCode());
            }
        }
        return getHash() ;
    }
    private long size;
    @Override
    public void addChildren(TreeNode node) throws Exception {
        if(node instanceof HasSize ){
            size=((HasSize)node).getSize()+size;
        }
        super.addChildren(node);
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public void setSize(long size) {
        this.size=size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
