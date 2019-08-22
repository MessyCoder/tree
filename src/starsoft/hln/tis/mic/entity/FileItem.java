package starsoft.hln.tis.mic.entity;

import java.util.List;

public class FileItem {

    private String name;
    private String type = "unknown";
    private String lastModified;
    private String relativePath;
    private List<FileItem> sub;

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public  List<FileItem> getSub() {
        return sub;
    }

    public void setSub(List<FileItem> sub) {
        this.sub = sub;
    }
}
