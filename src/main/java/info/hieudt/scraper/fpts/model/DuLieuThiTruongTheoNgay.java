package info.hieudt.scraper.fpts.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DuLieuThiTruongTheoNgay {
    @Id
    @Type(type = "date")
    private Date ngay;

    private Double hoseIndex;
    private Double hoseTongGiaTriGiaoDich;
    private Double hoseTongKhoiLuongGiaoDich;
    private Double hoseR;

    private Double hnxIndex;
    private Double hnxTongGiaTriGiaoDich;
    private Double hnxTongKhoiLuongGiaoDich;
    private Double hnxR;

    private Double vn30Index;
    private Double vn30TongGiaTriGiaoDich;
    private Double vn30TongKhoiLuongGiaoDich;
    private Double vn30R;

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Double getHoseIndex() {
        return hoseIndex;
    }

    public void setHoseIndex(Double vnIndex) {
        this.hoseIndex = vnIndex;
    }

    public Double getHoseTongGiaTriGiaoDich() {
        return hoseTongGiaTriGiaoDich;
    }

    public void setHoseTongGiaTriGiaoDich(Double vnIndexGiaTriGiaoDich) {
        this.hoseTongGiaTriGiaoDich = vnIndexGiaTriGiaoDich;
    }

    public Double getHoseTongKhoiLuongGiaoDich() {
        return hoseTongKhoiLuongGiaoDich;
    }

    public void setHoseTongKhoiLuongGiaoDich(Double vnIndexKhoiLuongGiaoDich) {
        this.hoseTongKhoiLuongGiaoDich = vnIndexKhoiLuongGiaoDich;
    }

    public Double getHnxIndex() {
        return hnxIndex;
    }

    public void setHnxIndex(Double hnIndex) {
        this.hnxIndex = hnIndex;
    }

    public Double getHnxTongGiaTriGiaoDich() {
        return hnxTongGiaTriGiaoDich;
    }

    public void setHnxTongGiaTriGiaoDich(Double hnIndexGiaTriGiaoDich) {
        this.hnxTongGiaTriGiaoDich = hnIndexGiaTriGiaoDich;
    }

    public Double getHnxTongKhoiLuongGiaoDich() {
        return hnxTongKhoiLuongGiaoDich;
    }

    public void setHnxTongKhoiLuongGiaoDich(Double hnIndexKhoiLuongGiaoDich) {
        this.hnxTongKhoiLuongGiaoDich = hnIndexKhoiLuongGiaoDich;
    }

    public Double getVn30Index() {
        return vn30Index;
    }

    public void setVn30Index(Double vn30Index) {
        this.vn30Index = vn30Index;
    }

    public Double getVn30TongGiaTriGiaoDich() {
        return vn30TongGiaTriGiaoDich;
    }

    public void setVn30TongGiaTriGiaoDich(Double vn30IndexGiaTriGiaoDich) {
        this.vn30TongGiaTriGiaoDich = vn30IndexGiaTriGiaoDich;
    }

    public Double getVn30TongKhoiLuongGiaoDich() {
        return vn30TongKhoiLuongGiaoDich;
    }

    public void setVn30TongKhoiLuongGiaoDich(Double vn30IndexKhoiLuongGiaoDich) {
        this.vn30TongKhoiLuongGiaoDich = vn30IndexKhoiLuongGiaoDich;
    }

    public Double getHoseR() {
        return hoseR;
    }

    public void setHoseR(Double hoseR) {
        this.hoseR = hoseR;
    }

    public Double getHnxR() {
        return hnxR;
    }

    public void setHnxR(Double hnxR) {
        this.hnxR = hnxR;
    }

    public Double getVn30R() {
        return vn30R;
    }

    public void setVn30R(Double vn30R) {
        this.vn30R = vn30R;
    }

    @Override
    public String toString() {
        return "DuLieuThiTruongTheoNgay{" +
                "ngay=" + ngay +
                ", hoseIndex=" + hoseIndex +
                ", hoseTongGiaTriGiaoDich=" + hoseTongGiaTriGiaoDich +
                ", hoseTongKhoiLuongGiaoDich=" + hoseTongKhoiLuongGiaoDich +
                ", hoseR=" + hoseR +
                ", hnxIndex=" + hnxIndex +
                ", hnxTongGiaTriGiaoDich=" + hnxTongGiaTriGiaoDich +
                ", hnxTongKhoiLuongGiaoDich=" + hnxTongKhoiLuongGiaoDich +
                ", hnxR=" + hnxR +
                ", vn30Index=" + vn30Index +
                ", vn30TongGiaTriGiaoDich=" + vn30TongGiaTriGiaoDich +
                ", vn30TongKhoiLuongGiaoDich=" + vn30TongKhoiLuongGiaoDich +
                ", vn30R=" + vn30R +
                '}';
    }
}
