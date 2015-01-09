package info.hieudt.scraper.fpts.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DuLieuThiTruongTheoNam {
    @Id
    private Integer nam;
    private Double hoseR;
    private Double hnxR;
    private Double vn30R;

    public DuLieuThiTruongTheoNam() {
    }

    public DuLieuThiTruongTheoNam(Integer nam, Double hoseR, Double hnxR, Double vn30R) {
        this.nam = nam;
        this.hoseR = hoseR;
        this.hnxR = hnxR;
        this.vn30R = vn30R;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
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
        return "DuLieuThiTruongTheoNam{" +
                "nam=" + nam +
                ", hoseR=" + hoseR +
                ", hnxR=" + hnxR +
                ", vn30R=" + vn30R +
                '}';
    }
}
