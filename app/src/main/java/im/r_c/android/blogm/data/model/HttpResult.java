package im.r_c.android.blogm.data.model;

/**
 * BlogM
 * Created by richard on 8/9/16.
 */

public final class HttpResult<T> {
    private boolean ok;
    private T page;
    private Site site;

    public HttpResult() {
    }

    public HttpResult(boolean ok, T page, Site site) {
        this.ok = ok;
        this.page = page;
        this.site = site;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public T getPage() {
        return page;
    }

    public void setPage(T page) {
        this.page = page;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "ok=" + ok +
                ", page=" + page +
                ", site=" + site +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpResult<?> that = (HttpResult<?>) o;

        if (ok != that.ok) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        return site != null ? site.equals(that.site) : that.site == null;

    }

    @Override
    public int hashCode() {
        int result = (ok ? 1 : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        return result;
    }
}
