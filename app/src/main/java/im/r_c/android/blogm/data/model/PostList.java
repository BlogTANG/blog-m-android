package im.r_c.android.blogm.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * BlogM
 * Created by richard on 8/9/16.
 */

public final class PostList {
    private List<Post> entries;
    @SerializedName("has_newer")
    private boolean hasNewer;
    @SerializedName("newer_url")
    private String newerUrl;
    @SerializedName("has_older")
    private boolean hasOlder;
    @SerializedName("older_url")
    private String olderUrl;

    public PostList() {
    }

    public PostList(List<Post> entries, boolean hasNewer, String newerUrl, boolean hasOlder, String olderUrl) {
        this.entries = entries;
        this.hasNewer = hasNewer;
        this.newerUrl = newerUrl;
        this.hasOlder = hasOlder;
        this.olderUrl = olderUrl;
    }

    public List<Post> getEntries() {
        return entries;
    }

    public void setEntries(List<Post> entries) {
        this.entries = entries;
    }

    public boolean hasNewer() {
        return hasNewer;
    }

    public void setHasNewer(boolean hasNewer) {
        this.hasNewer = hasNewer;
    }

    public String getNewerUrl() {
        return newerUrl;
    }

    public void setNewerUrl(String newerUrl) {
        this.newerUrl = newerUrl;
    }

    public boolean hasOlder() {
        return hasOlder;
    }

    public void setHasOlder(boolean hasOlder) {
        this.hasOlder = hasOlder;
    }

    public String getOlderUrl() {
        return olderUrl;
    }

    public void setOlderUrl(String olderUrl) {
        this.olderUrl = olderUrl;
    }

    @Override
    public String toString() {
        return "PostList{" +
                "entries=" + entries +
                ", hasNewer=" + hasNewer +
                ", newerUrl='" + newerUrl + '\'' +
                ", hasOlder=" + hasOlder +
                ", olderUrl='" + olderUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostList postList = (PostList) o;

        if (hasNewer != postList.hasNewer) return false;
        if (hasOlder != postList.hasOlder) return false;
        if (entries != null ? !entries.equals(postList.entries) : postList.entries != null)
            return false;
        if (newerUrl != null ? !newerUrl.equals(postList.newerUrl) : postList.newerUrl != null)
            return false;
        return olderUrl != null ? olderUrl.equals(postList.olderUrl) : postList.olderUrl == null;

    }

    @Override
    public int hashCode() {
        int result = entries != null ? entries.hashCode() : 0;
        result = 31 * result + (hasNewer ? 1 : 0);
        result = 31 * result + (newerUrl != null ? newerUrl.hashCode() : 0);
        result = 31 * result + (hasOlder ? 1 : 0);
        result = 31 * result + (olderUrl != null ? olderUrl.hashCode() : 0);
        return result;
    }
}
