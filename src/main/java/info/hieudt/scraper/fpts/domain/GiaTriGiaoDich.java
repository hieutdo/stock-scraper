package info.hieudt.scraper.fpts.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GiaTriGiaoDich {
    @Column(name = "gtgdKhopLenh")
    private Double khopLenh;

    @Column(name = "gtgdThoaThuan")
    private Double thoaThuan;

    @Column(name = "gtgdTongCong")
    private Double tongCong;

    public GiaTriGiaoDich() {
    }

    public GiaTriGiaoDich(Double khopLenh, Double thoaThuan, Double tongCong) {
        this.khopLenh = khopLenh;
        this.thoaThuan = thoaThuan;
        this.tongCong = tongCong;
    }

    public Double getKhopLenh() {
        return khopLenh;
    }

    public void setKhopLenh(Double khopLenh) {
        this.khopLenh = khopLenh;
    }

    public Double getThoaThuan() {
        return thoaThuan;
    }

    public void setThoaThuan(Double thoaThuan) {
        this.thoaThuan = thoaThuan;
    }

    public Double getTongCong() {
        return tongCong;
    }

    public void setTongCong(Double tongCong) {
        this.tongCong = tongCong;
    }
}
