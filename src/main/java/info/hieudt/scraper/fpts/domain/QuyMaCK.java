package info.hieudt.scraper.fpts.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class QuyMaCK implements Serializable {
    private static final long serialVersionUID = 693713525453080237L;

    private Integer nam;
    private Integer quy;

    @Column(length = 10)
    private String maChungKhoan;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SanGiaoDich sanGiaoDich;

    public QuyMaCK() {
    }

    public QuyMaCK(Integer nam, Integer quy, String maChungKhoan, SanGiaoDich sanGiaoDich) {
        this.nam = nam;
        this.quy = quy;
        this.maChungKhoan = maChungKhoan;
        this.sanGiaoDich = sanGiaoDich;
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

    public void setQuy(Integer thang) {
        this.quy = thang;
    }

    public String getMaChungKhoan() {
        return maChungKhoan;
    }

    public void setMaChungKhoan(String maChungKhoan) {
        this.maChungKhoan = maChungKhoan;
    }

    public SanGiaoDich getSanGiaoDich() {
        return sanGiaoDich;
    }

    public void setSanGiaoDich(SanGiaoDich sanGiaoDich) {
        this.sanGiaoDich = sanGiaoDich;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuyMaCK)) return false;

        QuyMaCK thangMaCK = (QuyMaCK) o;

        if (maChungKhoan != null ? !maChungKhoan.equals(thangMaCK.maChungKhoan) : thangMaCK.maChungKhoan != null) return false;
        if (nam != null ? !nam.equals(thangMaCK.nam) : thangMaCK.nam != null) return false;
        if (sanGiaoDich != thangMaCK.sanGiaoDich) return false;
        if (quy != null ? !quy.equals(thangMaCK.quy) : thangMaCK.quy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nam != null ? nam.hashCode() : 0;
        result = 31 * result + (quy != null ? quy.hashCode() : 0);
        result = 31 * result + (maChungKhoan != null ? maChungKhoan.hashCode() : 0);
        result = 31 * result + (sanGiaoDich != null ? sanGiaoDich.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuyMaCK{" +
                "nam=" + nam +
                ", quy=" + quy +
                ", maChungKhoan='" + maChungKhoan + '\'' +
                ", sanGiaoDich=" + sanGiaoDich +
                '}';
    }
}
