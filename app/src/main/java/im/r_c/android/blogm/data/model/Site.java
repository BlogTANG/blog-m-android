package im.r_c.android.blogm.data.model;

/**
 * BlogM
 * Created by richard on 8/9/16.
 */

public final class Site {
    private String title;
    private String subtitle;
    private String author;
    private String email;

    public Site() {
    }

    public Site(String title, String subtitle, String author, String email) {
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Site{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", author='" + author + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;

        if (title != null ? !title.equals(site.title) : site.title != null) return false;
        if (subtitle != null ? !subtitle.equals(site.subtitle) : site.subtitle != null)
            return false;
        if (author != null ? !author.equals(site.author) : site.author != null) return false;
        return email != null ? email.equals(site.email) : site.email == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
