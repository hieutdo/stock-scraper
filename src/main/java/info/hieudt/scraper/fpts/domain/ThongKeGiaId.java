package info.hieudt.scraper.fpts.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
public class ThongKeGiaId implements Serializable {
    private static final long serialVersionUID = -1702548745396373333L;

    @Type(type = "date")
    private Date ngay;

    @ManyToOne
    @JoinColumn(name = "idChungKhoan")
    private ChungKhoan chungKhoan;

    public ThongKeGiaId() {
    }

    public ThongKeGiaId(String ngayStr, ChungKhoan chungKhoan) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.ngay = dateFormat.parse(ngayStr);
        this.chungKhoan = chungKhoan;
    }

    public ChungKhoan getChungKhoan() {
        return chungKhoan;
    }

    public void setChungKhoan(ChungKhoan chungKhoan) {
        this.chungKhoan = chungKhoan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThongKeGiaId)) return false;

        ThongKeGiaId that = (ThongKeGiaId) o;

        if (chungKhoan != null ? !chungKhoan.equals(that.chungKhoan) : that.chungKhoan != null) return false;
        if (ngay != null ? !ngay.equals(that.ngay) : that.ngay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ngay != null ? ngay.hashCode() : 0;
        result = 31 * result + (chungKhoan != null ? chungKhoan.hashCode() : 0);
        return result;
    }
}
