package info.hieudt.scraper.fpts.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ThongKeDatLenh {
    @EmbeddedId
    private NgayMaCK pk;

    private Double lenhMua;
    private Double lenhBan;

    private Double khoiLuongMua;
    private Double khoiLuongBan;

    private Double giaMuaTotNhat;
    private Double khoiLuongMuaTotNhat;
    private Double giaBanTotNhat;
    private Double khoiLuongBanTotNhat;

    public ThongKeDatLenh() {
    }

    public ThongKeDatLenh(NgayMaCK pk, Double lenhMua, Double lenhBan, Double khoiLuongMua, Double khoiLuongBan, Double giaMuaTotNhat, Double khoiLuongMuaTotNhat, Double giaBanTotNhat, Double khoiLuongBanTotNhat) {
        this.pk = pk;
        this.lenhMua = lenhMua;
        this.lenhBan = lenhBan;
        this.khoiLuongMua = khoiLuongMua;
        this.khoiLuongBan = khoiLuongBan;
        this.giaMuaTotNhat = giaMuaTotNhat;
        this.khoiLuongMuaTotNhat = khoiLuongMuaTotNhat;
        this.giaBanTotNhat = giaBanTotNhat;
        this.khoiLuongBanTotNhat = khoiLuongBanTotNhat;
    }

    public NgayMaCK getPk() {
        return pk;
    }

    public void setPk(NgayMaCK pk) {
        this.pk = pk;
    }

    public Double getLenhMua() {
        return lenhMua;
    }

    public void setLenhMua(Double lenhMua) {
        this.lenhMua = lenhMua;
    }

    public Double getLenhBan() {
        return lenhBan;
    }

    public void setLenhBan(Double lenhBan) {
        this.lenhBan = lenhBan;
    }

    public Double getKhoiLuongMua() {
        return khoiLuongMua;
    }

    public void setKhoiLuongMua(Double khoiLuongMua) {
        this.khoiLuongMua = khoiLuongMua;
    }

    public Double getKhoiLuongBan() {
        return khoiLuongBan;
    }

    public void setKhoiLuongBan(Double khoiLuongBan) {
        this.khoiLuongBan = khoiLuongBan;
    }

    public Double getGiaMuaTotNhat() {
        return giaMuaTotNhat;
    }

    public void setGiaMuaTotNhat(Double giaMuaTotNhat) {
        this.giaMuaTotNhat = giaMuaTotNhat;
    }

    public Double getKhoiLuongMuaTotNhat() {
        return khoiLuongMuaTotNhat;
    }

    public void setKhoiLuongMuaTotNhat(Double khoiLuongMuaTotNhat) {
        this.khoiLuongMuaTotNhat = khoiLuongMuaTotNhat;
    }

    public Double getGiaBanTotNhat() {
        return giaBanTotNhat;
    }

    public void setGiaBanTotNhat(Double giaBanTotNhat) {
        this.giaBanTotNhat = giaBanTotNhat;
    }

    public Double getKhoiLuongBanTotNhat() {
        return khoiLuongBanTotNhat;
    }

    public void setKhoiLuongBanTotNhat(Double khoiLuongBanTotNhat) {
        this.khoiLuongBanTotNhat = khoiLuongBanTotNhat;
    }

    @Override
    public String toString() {
        return "ThongKeDatLenh{" +
                "pk=" + pk +
                ", lenhMua=" + lenhMua +
                ", lenhBan=" + lenhBan +
                ", khoiLuongMua=" + khoiLuongMua +
                ", khoiLuongBan=" + khoiLuongBan +
                ", giaMuaTotNhat=" + giaMuaTotNhat +
                ", khoiLuongMuaTotNhat=" + khoiLuongMuaTotNhat +
                ", giaBanTotNhat=" + giaBanTotNhat +
                ", khoiLuongBanTotNhat=" + khoiLuongBanTotNhat +
                '}';
    }
}
