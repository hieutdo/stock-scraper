package info.hieudt.scraper.fpts.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NamThang implements Serializable {
    private static final long serialVersionUID = -6585865277260902366L;

    private Integer nam;
    private Integer thang;

    public NamThang() {
    }

    public NamThang(Integer nam, Integer thang) {
        this.nam = nam;
        this.thang = thang;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getThang() {
        return thang;
    }

    public void setThang(Integer thang) {
        this.thang = thang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamThang)) return false;

        NamThang namThang = (NamThang) o;

        if (nam != null ? !nam.equals(namThang.nam) : namThang.nam != null) return false;
        if (thang != null ? !thang.equals(namThang.thang) : namThang.thang != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nam != null ? nam.hashCode() : 0;
        result = 31 * result + (thang != null ? thang.hashCode() : 0);
        return result;
    }
}
