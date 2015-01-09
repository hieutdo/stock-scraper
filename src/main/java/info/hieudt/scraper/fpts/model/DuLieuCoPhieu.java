package info.hieudt.scraper.fpts.model;

import info.hieudt.scraper.fpts.domain.NgayMaCK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class DuLieuCoPhieu {
    @EmbeddedId
    private NgayMaCK pk;

    private Double giaDongCua;
    private Double giaCaoNhat;
    private Double giaThapNhat;
    private Integer khoiLuongGiaoDichKhopLenh;
    private Integer khoiLuongGiaoDichTongCong;
    private Double giaTriGiaoDichKhopLenh;
    private Double giaTriGiaoDichTongCong;
    private Double giaHoiMua;
    private Double khoiLuongGiaHoiMua;
    private Double giaChaoBan;
    private Double khoiLuongGiaChaoBan;
    private Double soHuuNuocNgoai;

    public NgayMaCK getPk() {
        return pk;
    }

    public void setPk(NgayMaCK pk) {
        this.pk = pk;
    }

    public Double getGiaDongCua() {
        return giaDongCua;
    }

    public void setGiaDongCua(Double giaDongCua) {
        this.giaDongCua = giaDongCua;
    }

    public Double getGiaCaoNhat() {
        return giaCaoNhat;
    }

    public void setGiaCaoNhat(Double giaCaoNhat) {
        this.giaCaoNhat = giaCaoNhat;
    }

    public Double getGiaThapNhat() {
        return giaThapNhat;
    }

    public void setGiaThapNhat(Double giaThapNhat) {
        this.giaThapNhat = giaThapNhat;
    }

    public Integer getKhoiLuongGiaoDichKhopLenh() {
        return khoiLuongGiaoDichKhopLenh;
    }

    public void setKhoiLuongGiaoDichKhopLenh(Integer khoiLuongGiaoDichKhopLenh) {
        this.khoiLuongGiaoDichKhopLenh = khoiLuongGiaoDichKhopLenh;
    }

    public Integer getKhoiLuongGiaoDichTongCong() {
        return khoiLuongGiaoDichTongCong;
    }

    public void setKhoiLuongGiaoDichTongCong(Integer khoiLuongGiaoDichTongCong) {
        this.khoiLuongGiaoDichTongCong = khoiLuongGiaoDichTongCong;
    }

    public Double getGiaTriGiaoDichKhopLenh() {
        return giaTriGiaoDichKhopLenh;
    }

    public void setGiaTriGiaoDichKhopLenh(Double giaTriGiaoDichKhopLenh) {
        this.giaTriGiaoDichKhopLenh = giaTriGiaoDichKhopLenh;
    }

    public Double getGiaTriGiaoDichTongCong() {
        return giaTriGiaoDichTongCong;
    }

    public void setGiaTriGiaoDichTongCong(Double giaTriGiaoDichTongCong) {
        this.giaTriGiaoDichTongCong = giaTriGiaoDichTongCong;
    }

    public Double getGiaHoiMua() {
        return giaHoiMua;
    }

    public void setGiaHoiMua(Double giaHoiMua) {
        this.giaHoiMua = giaHoiMua;
    }

    public Double getKhoiLuongGiaHoiMua() {
        return khoiLuongGiaHoiMua;
    }

    public void setKhoiLuongGiaHoiMua(Double khoiLuongGiaHoiMua) {
        this.khoiLuongGiaHoiMua = khoiLuongGiaHoiMua;
    }

    public Double getGiaChaoBan() {
        return giaChaoBan;
    }

    public void setGiaChaoBan(Double giaChaoBan) {
        this.giaChaoBan = giaChaoBan;
    }

    public Double getKhoiLuongGiaChaoBan() {
        return khoiLuongGiaChaoBan;
    }

    public void setKhoiLuongGiaChaoBan(Double khoiLuongGiaChaoBan) {
        this.khoiLuongGiaChaoBan = khoiLuongGiaChaoBan;
    }

    public Double getSoHuuNuocNgoai() {
        return soHuuNuocNgoai;
    }

    public void setSoHuuNuocNgoai(Double soHuuNuocNgoai) {
        this.soHuuNuocNgoai = soHuuNuocNgoai;
    }
}
