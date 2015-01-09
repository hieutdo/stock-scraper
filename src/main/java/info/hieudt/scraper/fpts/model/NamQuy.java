package info.hieudt.scraper.fpts.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NamQuy implements Serializable {
    private static final long serialVersionUID = 4657536397522671812L;

    private Integer nam;
    private Integer quy;

    public NamQuy() {
    }

    public NamQuy(Integer nam, Integer quy) {
        this.nam = nam;
        this.quy = quy;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getQuy() {
        return quy;
    }

    public void setQuy(Integer quy) {
        this.quy = quy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamQuy)) return false;

        NamQuy namQuy = (NamQuy) o;

        if (nam != null ? !nam.equals(namQuy.nam) : namQuy.nam != null) return false;
        if (quy != null ? !quy.equals(namQuy.quy) : namQuy.quy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nam != null ? nam.hashCode() : 0;
        result = 31 * result + (quy != null ? quy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NamQuy{" +
                "nam=" + nam +
                ", quy=" + quy +
                '}';
    }
}
