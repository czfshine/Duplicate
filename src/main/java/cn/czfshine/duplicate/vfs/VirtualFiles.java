package cn.czfshine.duplicate.vfs;

import java.io.Serializable;

/**
 * @author:czfshine
 * @date:2018/6/17 19:20
 */

public class VirtualFiles implements Serializable {
    private String filepath;
    private long size;

    private VirtualDirs parent;

    public VirtualFiles(String filepath, long size, VirtualDirs parent) {
        this.filepath = filepath;
        this.size = size;
        this.parent = parent;
    }

    public VirtualDirs getParent() {
        return parent;
    }

    public void setParent(VirtualDirs parent) {
        this.parent = parent;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
