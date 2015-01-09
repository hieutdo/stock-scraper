package info.hieudt.scraper.fpts.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class DuLieuThiTruongTheoThang {
    @EmbeddedId
    private NamThang namThang;

    private Double hoseR;
    private Double hnxR;
    private Double vn30R;

    public DuLieuThiTruongTheoThang() {
    }

    public DuLieuThiTruongTheoThang(Integer nam, Integer thang, Double hoseR, Double hnxR, Double vn30R) {
        this.namThang = new NamThang(nam, thang);
        this.hoseR = hoseR;
        this.hnxR = hnxR;
        this.vn30R = vn30R;
    }

    public NamThang getNamThang() {
        return namThang;
    }

    public void setNamThang(NamThang namThang) {
        this.namThang = namThang;
    }

    public Double getHoseR() {
        return hoseR;
    }

    public void setHoseR(Double hoseR) {
        this.hoseR = hoseR;
    }

    public Double getHnxR() {
        return hnxR;
    }

    public void setHnxR(Double hnxR) {
        this.hnxR = hnxR;
    }

    public Double getVn30R() {
        return vn30R;
    }

    public void setVn30R(Double vn30R) {
        this.vn30R = vn30R;
    }

    @Override
    public String toString() {
        return "DuLieuThiTruongTheoThang{" +
                "namThang=" + namThang +
                ", hoseR=" + hoseR +
                ", hnxR=" + hnxR +
                ", vn30R=" + vn30R +
                '}';
    }
}
