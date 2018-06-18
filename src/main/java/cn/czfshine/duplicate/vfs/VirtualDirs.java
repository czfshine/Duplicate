package cn.czfshine.duplicate.vfs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:czfshine
 * @date:2018/6/17 19:22
 */

public class VirtualDirs implements Serializable {
    private List<VirtualFiles> childrenfile;
    private List<VirtualDirs> childrendirs;

    private String pathname;
    private long totalsize;
    private VirtualDirs parent;


    public VirtualDirs(String pathname,VirtualDirs parent) {
        this.setPathname(pathname);
        this.setParent(parent);
        setChildrendirs(new ArrayList<>());
        setChildrenfile(new ArrayList<>());
        setTotalsize(0);
    }

    public void addFile(VirtualFiles file){
        getChildrenfile().add(file);
        setTotalsize(getTotalsize() + file.getSize());
    }

    public void addDir(VirtualDirs dir){
        getChildrendirs().add(dir);
        setTotalsize(getTotalsize() + dir.getTotalsize());
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public List<VirtualFiles> getChildrenfile() {
        return childrenfile;
    }

    public void setChildrenfile(List<VirtualFiles> childrenfile) {
        this.childrenfile = childrenfile;
    }

    public List<VirtualDirs> getChildrendirs() {
        return childrendirs;
    }

    public void setChildrendirs(List<VirtualDirs> childrendirs) {
        this.childrendirs = childrendirs;
    }

    public long getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(long totalsize) {
        this.totalsize = totalsize;
    }

    public VirtualDirs getParent() {
        return parent;
    }

    public void setParent(VirtualDirs parent) {
        this.parent = parent;
    }
}
