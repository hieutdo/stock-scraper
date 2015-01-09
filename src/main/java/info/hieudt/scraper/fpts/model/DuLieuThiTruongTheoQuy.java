package info.hieudt.scraper.fpts.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class DuLieuThiTruongTheoQuy {
    @EmbeddedId
    private NamQuy namQuy;

    private Double hoseR;
    private Double hnxR;
    private Double vn30R;

    public DuLieuThiTruongTheoQuy() {
    }

    public DuLieuThiTruongTheoQuy(Integer nam, Integer quy, Double hoseR, Double hnxR, Double vn30R) {
        this.namQuy = new NamQuy(nam, quy);
        this.hoseR = hoseR;
        this.hnxR = hnxR;
        this.vn30R = vn30R;
    }

    public NamQuy getNamQuy() {
        return namQuy;
    }

    public void setNamQuy(NamQuy namQuy) {
        this.namQuy = namQuy;
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
        return "DuLieuThiTruongTheoQuy{" +
                "namThang=" + namQuy +
                ", hoseR=" + hoseR +
                ", hnxR=" + hnxR +
                ", vn30R=" + vn30R +
                '}';
    }
}
