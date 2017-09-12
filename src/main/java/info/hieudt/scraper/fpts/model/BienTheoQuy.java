package info.hieudt.scraper.fpts.model;

import info.hieudt.scraper.fpts.domain.QuyMaCK;
import info.hieudt.scraper.fpts.domain.SanGiaoDich;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class BienTheoQuy extends BienAbstract {
    @EmbeddedId
    protected QuyMaCK pk;

    public BienTheoQuy() {
    }

    public BienTheoQuy(Integer nam, Integer quy, String maChungKhoan, SanGiaoDich sanGiaoDich, Double R, Double effspread, Double quospread, Double phanTramQuospread, Double amihud, Double amihudmoi, Double adAmihud, Double aminvest, Double depth, Double compositeLiq, Double highlow) {
        this.pk = new QuyMaCK(nam, quy, maChungKhoan, sanGiaoDich);
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

    public QuyMaCK getPk() {
        return pk;
    }

    public void setPk(QuyMaCK pk) {
        this.pk = pk;
    }
}

