package info.hieudt.scraper.fpts.model;

import java.util.Date;

public class ChungKhoanTheoNgay {
    private String sanGiaoDich;
    private String maChungKhoan;
    private Date ngay;

    public ChungKhoanTheoNgay() {
    }

    public ChungKhoanTheoNgay(String sanGiaoDich, String maChungKhoan, Date ngay) {
        this.sanGiaoDich = sanGiaoDich;
        this.maChungKhoan = maChungKhoan;
        this.ngay = ngay;
    }

    public String getSanGiaoDich() {
        return sanGiaoDich;
    }

    public void setSanGiaoDich(String sanGiaoDich) {
        this.sanGiaoDich = sanGiaoDich;
    }

    public String getMaChungKhoan() {
        return maChungKhoan;
    }

    public void setMaChungKhoan(String maChungKhoan) {
        this.maChungKhoan = maChungKhoan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "ChungKhoanTheoNgay{" +
                "sanGiaoDich=" + sanGiaoDich +
                ", maChungKhoan='" + maChungKhoan + '\'' +
                ", ngay=" + ngay +
                '}';
    }
}
