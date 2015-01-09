package info.hieudt.scraper.fpts.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class GiaoDichKhopLenh {
    @EmbeddedId
    private NgayMaCK pk;

    private Double khoiLuongCkDuocPhepSoHuu;
    private Double phanTramSoHuuNuocNgoai;
    private Double khoiLuongCkConDuocPhepMua;

    public GiaoDichKhopLenh() {
    }

    public GiaoDichKhopLenh(NgayMaCK pk, Double khoiLuongCkDuocPhepSoHuu, Double phanTramSoHuuNuocNgoai, Double khoiLuongCkConDuocPhepMua) {
        this.pk = pk;
        this.khoiLuongCkDuocPhepSoHuu = khoiLuongCkDuocPhepSoHuu;
        this.phanTramSoHuuNuocNgoai = phanTramSoHuuNuocNgoai;
        this.khoiLuongCkConDuocPhepMua = khoiLuongCkConDuocPhepMua;
    }

    public NgayMaCK getPk() {
        return pk;
    }

    public void setPk(NgayMaCK pk) {
        this.pk = pk;
    }

    public Double getKhoiLuongCkDuocPhepSoHuu() {
        return khoiLuongCkDuocPhepSoHuu;
    }

    public void setKhoiLuongCkDuocPhepSoHuu(Double khoiLuongCkDuocPhepSoHuu) {
        this.khoiLuongCkDuocPhepSoHuu = khoiLuongCkDuocPhepSoHuu;
    }

    public Double getPhanTramSoHuuNuocNgoai() {
        return phanTramSoHuuNuocNgoai;
    }

    public void setPhanTramSoHuuNuocNgoai(Double phanTramSoHuuNuocNgoai) {
        this.phanTramSoHuuNuocNgoai = phanTramSoHuuNuocNgoai;
    }

    public Double getKhoiLuongCkConDuocPhepMua() {
        return khoiLuongCkConDuocPhepMua;
    }

    public void setKhoiLuongCkConDuocPhepMua(Double khoiLuongCkConDuocPhepMua) {
        this.khoiLuongCkConDuocPhepMua = khoiLuongCkConDuocPhepMua;
    }

    @Override
    public String toString() {
        return "GiaoDichKhopLenh{" +
                "pk=" + pk +
                ", khoiLuongCkDuocPhepSoHuu=" + khoiLuongCkDuocPhepSoHuu +
                ", phanTramSoHuuNuocNgoai=" + phanTramSoHuuNuocNgoai +
                ", khoiLuongCkConDuocPhepMua=" + khoiLuongCkConDuocPhepMua +
                '}';
    }
}
