package im.r_c.android.blogm.data.model;

/**
 * BlogM
 * Created by richard on 8/9/16.
 */

public final class CustomPage {
    private String title;
    private String body;
    private String author;
    private String email;

    public CustomPage() {
    }

    public CustomPage(String title, String body, String author, String email) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        return "CustomPage{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author='" + author + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomPage customPage = (CustomPage) o;

        if (title != null ? !title.equals(customPage.title) : customPage.title != null) return false;
        if (body != null ? !body.equals(customPage.body) : customPage.body != null) return false;
        if (author != null ? !author.equals(customPage.author) : customPage.author != null)
            return false;
        return email != null ? email.equals(customPage.email) : customPage.email == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
