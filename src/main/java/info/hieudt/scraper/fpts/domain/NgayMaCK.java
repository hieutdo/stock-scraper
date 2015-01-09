package info.hieudt.scraper.fpts.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
public class NgayMaCK implements Serializable {
    private static final long serialVersionUID = -1702548745396373333L;

    @Type(type = "date")
    private Date ngay;

    @Column(length = 10)
    private String maChungKhoan;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SanGiaoDich sanGiaoDich;

    public NgayMaCK() {
    }

    public NgayMaCK(NgayMaCK other) {
        this.sanGiaoDich = other.sanGiaoDich;
        this.maChungKhoan = other.maChungKhoan;
        this.ngay = other.ngay;
    }

    public NgayMaCK(String ngayStr, String maChungKhoan, SanGiaoDich sanGiaoDich) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.ngay = dateFormat.parse(ngayStr);
        this.maChungKhoan = maChungKhoan;
        this.sanGiaoDich = sanGiaoDich;
    }

    public String getMaChungKhoan() {
        return maChungKhoan;
    }

    public void setMaChungKhoan(String chungKhoan) {
        this.maChungKhoan = chungKhoan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
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
        if (!(o instanceof NgayMaCK)) return false;

        NgayMaCK that = (NgayMaCK) o;

        if (maChungKhoan != null ? !maChungKhoan.equals(that.maChungKhoan) : that.maChungKhoan != null) return false;
        if (ngay != null ? !ngay.equals(that.ngay) : that.ngay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ngay != null ? ngay.hashCode() : 0;
        result = 31 * result + (maChungKhoan != null ? maChungKhoan.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NgayMaCK{" +
                "ngay=" + ngay +
                ", maChungKhoan='" + maChungKhoan + '\'' +
                ", sanGiaoDich=" + sanGiaoDich +
                '}';
    }
}
