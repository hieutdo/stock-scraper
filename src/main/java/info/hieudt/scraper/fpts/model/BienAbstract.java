package info.hieudt.scraper.fpts.model;

public abstract class BienAbstract {
    protected Double R;
    protected Double effspread;
    protected Double quospread;
    protected Double quospread2;
    protected Double quospread3;
    protected Double phanTramQuospread;
    protected Double amihud;
    protected Double amihudmoi;
    protected Double adAmihud;
    protected Double aminvest;
    protected Double depth;
    protected Double compositeLiq;
    protected Double highlow;

    protected Double roll;
    protected Double zeros;
    protected Double zeros2;
    protected Double phuongSai;
    protected Double phuongSaiQuospread;
    protected Double phuongSaiDepth;
    protected Double phuongSaiAmihud;

    protected Long tongKhoiLuongGiaoDich;
    protected Double tongGiaTriGiaoDich;
    protected Double trungBinhKhoiLuongGiaoDich;
    protected Double trungBinhGiaTriGiaoDich;

    protected Double nt;
    protected Integer tongSoNgayGiaoDich;

    public BienAbstract() {
    }

    public BienAbstract(Double R, Double effspread, Double quospread, Double phanTramQuospread, Double amihud, Double amihudmoi, Double adAmihud, Double aminvest, Double depth, Double compositeLiq, Double highlow) {
        this.R = R;
        this.effspread = effspread;
        this.quospread = quospread;
        this.phanTramQuospread = phanTramQuospread;
        this.amihud = amihud;
        this.amihudmoi = amihudmoi;
        this.adAmihud = adAmihud;
        this.aminvest = aminvest;
        this.depth = depth;
        this.compositeLiq = compositeLiq;
        this.highlow = highlow;
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

    public Double getAmihudmoi() {
        return amihudmoi;
    }

    public void setAmihudmoi(Double amihudmoi) {
        this.amihudmoi = amihudmoi;
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

    public Double getPhuongSaiQuospread() {
        return phuongSaiQuospread;
    }

    public void setPhuongSaiQuospread(Double phuongSaiQuospread) {
        this.phuongSaiQuospread = phuongSaiQuospread;
    }

    public Double getPhuongSaiDepth() {
        return phuongSaiDepth;
    }

    public void setPhuongSaiDepth(Double phuongSaiDepth) {
        this.phuongSaiDepth = phuongSaiDepth;
    }

    public Double getPhuongSaiAmihud() {
        return phuongSaiAmihud;
    }

    public void setPhuongSaiAmihud(Double phuongSaiAmihud) {
        this.phuongSaiAmihud = phuongSaiAmihud;
    }

    public Double getQuospread2() {
        return quospread2;
    }

    public void setQuospread2(Double quospread2) {
        this.quospread2 = quospread2;
    }

    public Double getQuospread3() {
        return quospread3;
    }

    public void setQuospread3(Double quospread3) {
        this.quospread3 = quospread3;
    }

}

