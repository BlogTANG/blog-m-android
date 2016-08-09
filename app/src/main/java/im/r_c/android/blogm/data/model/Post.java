package im.r_c.android.blogm.data.model;

import java.util.List;

/**
 * BlogM
 * Created by richard on 8/9/16.
 */

public final class Post {
    private String title;
    private String url;
    private String body;
    private List<String> categories;
    private List<String> tags;
    private String author;
    private String date;
    private String email;

    public Post() {
    }

    public Post(String title, String url, String body, List<String> categories, List<String> tags, String author, String date, String email) {
        this.title = title;
        this.url = url;
        this.body = body;
        this.categories = categories;
        this.tags = tags;
        this.author = author;
        this.date = date;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", body='" + body + '\'' +
                ", categories=" + categories +
                ", tags=" + tags +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (url != null ? !url.equals(post.url) : post.url != null) return false;
        if (body != null ? !body.equals(post.body) : post.body != null) return false;
        if (categories != null ? !categories.equals(post.categories) : post.categories != null)
            return false;
        if (tags != null ? !tags.equals(post.tags) : post.tags != null) return false;
        if (author != null ? !author.equals(post.author) : post.author != null) return false;
        if (date != null ? !date.equals(post.date) : post.date != null) return false;
        return email != null ? email.equals(post.email) : post.email == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
