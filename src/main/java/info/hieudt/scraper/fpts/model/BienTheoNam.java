package info.hieudt.scraper.fpts.model;

import info.hieudt.scraper.fpts.domain.NamMaCK;
import info.hieudt.scraper.fpts.domain.SanGiaoDich;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class BienTheoNam extends BienAbstract {
    @EmbeddedId
    protected NamMaCK pk;

    public BienTheoNam() {
    }

    public BienTheoNam(Integer nam, String maChungKhoan, SanGiaoDich sanGiaoDich, Double R, Double effspread, Double quospread, Double phanTramQuospread, Double amihud, Double amihudmoi, Double adAmihud, Double aminvest, Double depth, Double compositeLiq, Double highlow) {
        this.pk = new NamMaCK(nam, maChungKhoan, sanGiaoDich);
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

    public NamMaCK getPk() {
        return pk;
    }

    public void setPk(NamMaCK pk) {
        this.pk = pk;
    }
}

