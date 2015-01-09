package info.hieudt.scraper.fpts.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Embeddable
public class ThangMaCK implements Serializable {
    private static final long serialVersionUID = 2780347533324100157L;

    private Integer nam;
    private Integer thang;

    @Column(length = 10)
    private String maChungKhoan;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SanGiaoDich sanGiaoDich;

    public ThangMaCK() {
    }

    public ThangMaCK(Integer nam, Integer thang, String maChungKhoan, SanGiaoDich sanGiaoDich) {
        this.nam = nam;
        this.thang = thang;
        this.maChungKhoan = maChungKhoan;
        this.sanGiaoDich = sanGiaoDich;
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
        if (!(o instanceof ThangMaCK)) return false;

        ThangMaCK thangMaCK = (ThangMaCK) o;

        if (maChungKhoan != null ? !maChungKhoan.equals(thangMaCK.maChungKhoan) : thangMaCK.maChungKhoan != null) return false;
        if (nam != null ? !nam.equals(thangMaCK.nam) : thangMaCK.nam != null) return false;
        if (sanGiaoDich != thangMaCK.sanGiaoDich) return false;
        if (thang != null ? !thang.equals(thangMaCK.thang) : thangMaCK.thang != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nam != null ? nam.hashCode() : 0;
        result = 31 * result + (thang != null ? thang.hashCode() : 0);
        result = 31 * result + (maChungKhoan != null ? maChungKhoan.hashCode() : 0);
        result = 31 * result + (sanGiaoDich != null ? sanGiaoDich.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ThangMaCK{" +
                "nam=" + nam +
                ", thang=" + thang +
                ", maChungKhoan='" + maChungKhoan + '\'' +
                ", sanGiaoDich=" + sanGiaoDich +
                '}';
    }
}
