package cn.czfshine.duplicate.struct;

/**
 * @author:czfshine
 * @date:2018/6/19 17:02
 */

public class FileContent extends TreeNode implements HasSize {

    private String filename;

    private long size;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
