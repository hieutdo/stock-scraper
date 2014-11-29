package info.hieudt.scraper.fpts.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ThongKeIndex {
    @EmbeddedId
    private ThongKeIndexId pk = new ThongKeIndexId();
    private Double chiSoIndex;
    private Double tongGiaTriGiaoDich;
    private Double tongKhoiLuongGiaoDich;

    public ThongKeIndex() {}

    public ThongKeIndex(ThongKeIndexId pk, Double chiSoIndex, Double tongGiaTriGiaoDich, Double tongKhoiLuongGiaoDich) {
        this.pk = pk;
        this.chiSoIndex = chiSoIndex;
        this.tongGiaTriGiaoDich = tongGiaTriGiaoDich;
        this.tongKhoiLuongGiaoDich = tongKhoiLuongGiaoDich;
    }

    public ThongKeIndexId getPk() {
        return pk;
    }

    public void setPk(ThongKeIndexId pk) {
        this.pk = pk;
    }

    public Double getChiSoIndex() {
        return chiSoIndex;
    }

    public void setChiSoIndex(Double chiSoIndex) {
        this.chiSoIndex = chiSoIndex;
    }

    public Double getTongGiaTriGiaoDich() {
        return tongGiaTriGiaoDich;
    }

    public void setTongGiaTriGiaoDich(Double tongGiaTriGiaoDich) {
        this.tongGiaTriGiaoDich = tongGiaTriGiaoDich;
    }

    public Double getTongKhoiLuongGiaoDich() {
        return tongKhoiLuongGiaoDich;
    }

    public void setTongKhoiLuongGiaoDich(Double tongKhoiLuongGiaoDich) {
        this.tongKhoiLuongGiaoDich = tongKhoiLuongGiaoDich;
    }
}
