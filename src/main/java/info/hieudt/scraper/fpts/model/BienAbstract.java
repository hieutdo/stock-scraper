package info.hieudt.scraper.fpts.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class BienAbstract {
    protected Double R;
    protected Double effspread;
    protected Double quospread;
    protected Double quospread2;
    protected Double quospread3;
    protected Double phanTramQuospread;
    protected Double amihud;
    protected Double amihud2;
    protected Double amihud3;
    protected Double amihudmoi;
    protected Double adAmihud;
    protected Double aminvest;
    protected Double depth;
    protected Double depth2;
    protected Double depth3;
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

    @Transient
    protected List<BienTheoNgay> bienTheoNgayList;

    public BienAbstract() {
    }

    protected static Double geometricMean(List<Double> list) {
        int n = list.size();
        if (n == 0) return null;
        double gmLog = 0.0d;
        for (int i = 0; i < n; ++i) {
            if (list.get(i) == 0.0) {
                return 0.0;
            }
            gmLog += Math.log(list.get(i));
        }
        return Math.exp(gmLog / n);
    }

    public void calculateGeometricMean() {
        /*
        Cách 2: Trước khi tính trung bình nhân, em cộng từng giá trị của từng ngày với 1 trước (làm cho tất cả các giá trị,
        kể cả những ô có giá trị 0 lẫn những ô có giá trị khác 0), sau đó tính trung bình nhân bình thường.
        Vì thế lúc này trung bình nhân sẽ được tính cho tất cả các ngày trong tháng/quý. Và sau khi lấy căn N thì trừ lại cho 1.
        Ví dụ:
        Quospread2= = [(1+Quos ngày thứ 1 của quý/tháng) x (1+Quos ngày thứ 2 của quý/hoặc tháng) x …x (1+Quos ngày thứ N của quý/tháng)]^(1/N) - 1

        Cách 3: Tính như cách 1 em đã làm cho cô, nhưng chỉ khác là trong phép trung bình nhân lần chỉ lấy căn số thừa
        số trong phép nhân, nghĩa là những ngày mà Quos, Depth, Amihud bằng 0 thì không tính.
        Nghĩa là chỉ tính căn bậc của số ngày có tham gia trong phép tính trung bình.
         */
        List<Double> quospread2List = new ArrayList<>();
        List<Double> quospread3List = new ArrayList<>();

        List<Double> depth2List = new ArrayList<>();
        List<Double> depth3List = new ArrayList<>();

        List<Double> amihud2List = new ArrayList<>();
        List<Double> amihud3List = new ArrayList<>();

        bienTheoNgayList.forEach(bienTheoNgay -> {
            Double quospread = bienTheoNgay.getQuospread();
            Double depth = bienTheoNgay.getDepth();
            Double amihud = bienTheoNgay.getAmihud();

            if (quospread != null && quospread != 0) {
                quospread2List.add(quospread + 1);
                quospread3List.add(quospread);
            } else {
                quospread2List.add(1.0);
            }

            if (depth != null && depth != 0) {
                depth2List.add(depth + 1);
                depth3List.add(depth);
            } else {
                depth2List.add(1.0);
            }

            if (amihud != null && amihud != 0) {
                amihud2List.add(amihud + 1);
                amihud3List.add(amihud);
            } else {
                amihud2List.add(1.0);
            }
        });

        this.quospread2 = BienAbstract.geometricMean(quospread2List) - 1;
        this.depth2 = BienAbstract.geometricMean(depth2List) - 1;
        this.amihud2 = BienAbstract.geometricMean(amihud2List) - 1;

        this.quospread3 = BienAbstract.geometricMean(quospread3List);
        this.depth3 = BienAbstract.geometricMean(depth3List);
        this.amihud3 = BienAbstract.geometricMean(amihud3List);
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

    public Double getAmihud2() {
        return amihud2;
    }

    public void setAmihud2(Double amihud2) {
        this.amihud2 = amihud2;
    }

    public Double getAmihud3() {
        return amihud3;
    }

    public void setAmihud3(Double amihud3) {
        this.amihud3 = amihud3;
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

    public Double getDepth2() {
        return depth2;
    }

    public void setDepth2(Double depth2) {
        this.depth2 = depth2;
    }

    public Double getDepth3() {
        return depth3;
    }

    public void setDepth3(Double depth3) {
        this.depth3 = depth3;
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

    public List<BienTheoNgay> getBienTheoNgayList() {
        return bienTheoNgayList;
    }

    public void setBienTheoNgayList(List<BienTheoNgay> bienTheoNgayList) {
        this.bienTheoNgayList = bienTheoNgayList;
    }
}

