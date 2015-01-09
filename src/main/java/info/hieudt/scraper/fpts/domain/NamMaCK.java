package info.hieudt.scraper.fpts.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class NamMaCK implements Serializable {
    private static final long serialVersionUID = 8243447358483086813L;

    private Integer nam;

    @Column(length = 10)
    private String maChungKhoan;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SanGiaoDich sanGiaoDich;

    public NamMaCK() {
    }

    public NamMaCK(Integer nam, String maChungKhoan, SanGiaoDich sanGiaoDich) {
        this.nam = nam;
        this.maChungKhoan = maChungKhoan;
        this.sanGiaoDich = sanGiaoDich;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
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
        if (!(o instanceof NamMaCK)) return false;

        NamMaCK namMaCK = (NamMaCK) o;

        if (maChungKhoan != null ? !maChungKhoan.equals(namMaCK.maChungKhoan) : namMaCK.maChungKhoan != null) return false;
        if (nam != null ? !nam.equals(namMaCK.nam) : namMaCK.nam != null) return false;
        if (sanGiaoDich != namMaCK.sanGiaoDich) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nam != null ? nam.hashCode() : 0;
        result = 31 * result + (maChungKhoan != null ? maChungKhoan.hashCode() : 0);
        result = 31 * result + (sanGiaoDich != null ? sanGiaoDich.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NamMaCK{" +
                "nam=" + nam +
                ", maChungKhoan='" + maChungKhoan + '\'' +
                ", sanGiaoDich=" + sanGiaoDich +
                '}';
    }
}
