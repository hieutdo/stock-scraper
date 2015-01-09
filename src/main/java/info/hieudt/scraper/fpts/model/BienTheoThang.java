package info.hieudt.scraper.fpts.model;

import info.hieudt.scraper.fpts.domain.SanGiaoDich;
import info.hieudt.scraper.fpts.domain.ThangMaCK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class BienTheoThang {
    @EmbeddedId
    protected ThangMaCK pk;

    protected Double R;
    protected Double effspread;
    protected Double quospread;
    protected Double phanTramQuospread;
    protected Double amihud;
    protected Double adAmihud;
    protected Double aminvest;
    protected Double depth;
    protected Double compositeLiq;
    protected Double highlow;

    protected Double roll;
    protected Double zeros;
    protected Double zeros2;
    protected Double phuongSai;

    protected Long tongKhoiLuongGiaoDich;
    protected Double tongGiaTriGiaoDich;
    protected Double trungBinhKhoiLuongGiaoDich;
    protected Double trungBinhGiaTriGiaoDich;

    protected Double nt;
    protected Integer tongSoNgayGiaoDich;

    public BienTheoThang() {
    }

    public BienTheoThang(Integer nam, Integer thang, String maChungKhoan, SanGiaoDich sanGiaoDich, Double R, Double effspread, Double quospread, Double phanTramQuospread, Double amihud, Double adAmihud, Double aminvest, Double depth, Double compositeLiq, Double highlow) {
        this.pk = new ThangMaCK(nam, thang, maChungKhoan, sanGiaoDich);
        this.R = R;
        this.effspread = effspread;
        this.quospread = quospread;
        this.phanTramQuospread = phanTramQuospread;
        this.amihud = amihud;
        this.adAmihud = adAmihud;
        this.aminvest = aminvest;
        this.depth = depth;
        this.compositeLiq = compositeLiq;
        this.highlow = highlow;
    }

    public ThangMaCK getPk() {
        return pk;
    }

    public void setPk(ThangMaCK pk) {
        this.pk = pk;
    }

    public Double getR() {
        return R;
    }

    public void setR(Double r) {
        R = r;
    }

    public Double getEffspread() {
        return effspread;
    }

    public void setEffspread(Double effspread) {
        this.effspread = effspread;
    }

    public Double getQuospread() {
        return quospread;
    }

    public void setQuospread(Double quospread) {
        this.quospread = quospread;
    }

    public Double getPhanTramQuospread() {
        return phanTramQuospread;
    }

    public void setPhanTramQuospread(Double phanTramQuospread) {
        this.phanTramQuospread = phanTramQuospread;
    }

    public Double getAmihud() {
        return amihud;
    }

    public void setAmihud(Double amihud) {
        this.amihud = amihud;
    }

    public Double getAdAmihud() {
        return adAmihud;
    }

    public void setAdAmihud(Double adAmihud) {
        this.adAmihud = adAmihud;
    }

    public Double getAminvest() {
        return aminvest;
    }

    public void setAminvest(Double aminvest) {
        this.aminvest = aminvest;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getCompositeLiq() {
        return compositeLiq;
    }

    public void setCompositeLiq(Double compositeLiq) {
        this.compositeLiq = compositeLiq;
    }

    public Double getHighlow() {
        return highlow;
    }

    public void setHighlow(Double highlow) {
        this.highlow = highlow;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    public Double getZeros() {
        return zeros;
    }

    public void setZeros(Double zeros) {
        this.zeros = zeros;
    }

    public Double getZeros2() {
        return zeros2;
    }

    public void setZeros2(Double zeros2) {
        this.zeros2 = zeros2;
    }

    public Double getPhuongSai() {
        return phuongSai;
    }

    public void setPhuongSai(Double phuongSai) {
        this.phuongSai = phuongSai;
    }

    public Long getTongKhoiLuongGiaoDich() {
        return tongKhoiLuongGiaoDich;
    }

    public void setTongKhoiLuongGiaoDich(Long tongKhoiLuongGiaoDich) {
        this.tongKhoiLuongGiaoDich = tongKhoiLuongGiaoDich;
    }

    public Double getTongGiaTriGiaoDich() {
        return tongGiaTriGiaoDich;
    }

    public void setTongGiaTriGiaoDich(Double tongGiaTriGiaoDich) {
        this.tongGiaTriGiaoDich = tongGiaTriGiaoDich;
    }

    public Double getTrungBinhKhoiLuongGiaoDich() {
        return trungBinhKhoiLuongGiaoDich;
    }

    public void setTrungBinhKhoiLuongGiaoDich(Double trungBinhKhoiLuongGiaoDich) {
        this.trungBinhKhoiLuongGiaoDich = trungBinhKhoiLuongGiaoDich;
    }

    public Double getTrungBinhGiaTriGiaoDich() {
        return trungBinhGiaTriGiaoDich;
    }

    public void setTrungBinhGiaTriGiaoDich(Double trungBinhGiaTriGiaoDich) {
        this.trungBinhGiaTriGiaoDich = trungBinhGiaTriGiaoDich;
    }

    public Double getNt() {
        return nt;
    }

    public void setNt(Double nt) {
        this.nt = nt;
    }

    public Integer getTongSoNgayGiaoDich() {
        return tongSoNgayGiaoDich;
    }

    public void setTongSoNgayGiaoDich(Integer tongSoNgayGiaoDich) {
        this.tongSoNgayGiaoDich = tongSoNgayGiaoDich;
    }

    @Override
    public String toString() {
        return "BienTheoThang{" +
                "pk=" + pk +
                ", R=" + R +
                ", effspread=" + effspread +
                ", quospread=" + quospread +
                ", phanTramQuospread=" + phanTramQuospread +
                ", amihud=" + amihud +
                ", adAmihud=" + adAmihud +
                ", aminvest=" + aminvest +
                ", depth=" + depth +
                ", compositeLiq=" + compositeLiq +
                ", highlow=" + highlow +
                ", roll=" + roll +
                ", zeros=" + zeros +
                ", zeros2=" + zeros2 +
                ", phuongSai=" + phuongSai +
                ", tongKhoiLuongGiaoDich=" + tongKhoiLuongGiaoDich +
                ", tongGiaTriGiaoDich=" + tongGiaTriGiaoDich +
                ", trungBinhKhoiLuongGiaoDich=" + trungBinhKhoiLuongGiaoDich +
                ", trungBinhGiaTriGiaoDich=" + trungBinhGiaTriGiaoDich +
                '}';
    }
}

