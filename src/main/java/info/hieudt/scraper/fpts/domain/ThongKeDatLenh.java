package info.hieudt.scraper.fpts.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ThongKeDatLenh {
    @EmbeddedId
    private ThongKeGiaId pk;

    private Integer lenhMua;
    private Integer lenhBan;

    private Integer khoiLuongMua;
    private Integer khoiLuongBan;

    public ThongKeDatLenh() {
    }

    public ThongKeDatLenh(ThongKeGiaId pk, Integer lenhMua, Integer khoiLuongMua, Integer lenhBan, Integer khoiLuongBan) {
        this.pk = pk;
        this.lenhMua = lenhMua;
        this.khoiLuongMua = khoiLuongMua;
        this.lenhBan = lenhBan;
        this.khoiLuongBan = khoiLuongBan;
    }

    public ThongKeGiaId getPk() {
        return pk;
    }

    public void setPk(ThongKeGiaId pk) {
        this.pk = pk;
    }

    public Integer getLenhMua() {
        return lenhMua;
    }

    public void setLenhMua(Integer lenhMua) {
        this.lenhMua = lenhMua;
    }

    public Integer getKhoiLuongMua() {
        return khoiLuongMua;
    }

    public void setKhoiLuongMua(Integer khoiLuongMua) {
        this.khoiLuongMua = khoiLuongMua;
    }

    public Integer getLenhBan() {
        return lenhBan;
    }

    public void setLenhBan(Integer lenhBan) {
        this.lenhBan = lenhBan;
    }

    public Integer getKhoiLuongBan() {
        return khoiLuongBan;
    }

    public void setKhoiLuongBan(Integer khoiLuongBan) {
        this.khoiLuongBan = khoiLuongBan;
    }
}
