package info.hieudt.scraper.fpts.model;

import info.hieudt.scraper.fpts.domain.SanGiaoDich;
import info.hieudt.scraper.fpts.domain.ThangMaCK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class BienTheoThang extends BienAbstract {
    @EmbeddedId
    protected ThangMaCK pk;

    public BienTheoThang() {
    }

    public BienTheoThang(Integer nam, Integer thang, String maChungKhoan, SanGiaoDich sanGiaoDich, Double R, Double effspread, Double quospread, Double phanTramQuospread, Double amihud, Double amihudmoi, Double adAmihud, Double aminvest, Double depth, Double compositeLiq, Double highlow) {
        this.pk = new ThangMaCK(nam, thang, maChungKhoan, sanGiaoDich);
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

    public ThangMaCK getPk() {
        return pk;
    }

    public void setPk(ThangMaCK pk) {
        this.pk = pk;
    }
}

