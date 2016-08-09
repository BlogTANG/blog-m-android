package im.r_c.android.blogm.data.model;

import java.util.List;

/**
 * BlogM
 * Created by richard on 8/9/16.
 */

public final class Archive {
    private String name;
    private String type;
    private List<Post> entries;

    public Archive() {
    }

    public Archive(String name, String type, List<Post> entries) {
        this.name = name;
        this.type = type;
        this.entries = entries;
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

    public List<Post> getEntries() {
        return entries;
    }

    public void setEntries(List<Post> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", entries=" + entries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Archive archive = (Archive) o;

        if (name != null ? !name.equals(archive.name) : archive.name != null) return false;
        if (type != null ? !type.equals(archive.type) : archive.type != null) return false;
        return entries != null ? entries.equals(archive.entries) : archive.entries == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (entries != null ? entries.hashCode() : 0);
        return result;
    }
}
