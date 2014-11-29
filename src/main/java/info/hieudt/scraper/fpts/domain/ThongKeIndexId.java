package info.hieudt.scraper.fpts.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
public class ThongKeIndexId implements Serializable {
    private static final long serialVersionUID = 7535866112230943065L;

    @Type(type = "date")
    private Date ngay;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SanGiaoDich sanGiaoDich;

    private Byte dot;

    public ThongKeIndexId() {
    }

    public ThongKeIndexId(String ngayStr, SanGiaoDich sanGiaoDich, Byte dot) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.ngay = dateFormat.parse(ngayStr);
        this.sanGiaoDich = sanGiaoDich;
        this.dot = dot;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public SanGiaoDich getSanGiaoDich() {
        return sanGiaoDich;
    }

    public void setSanGiaoDich(SanGiaoDich sanGiaoDich) {
        this.sanGiaoDich = sanGiaoDich;
    }

    public Byte getDot() {
        return dot;
    }

    public void setDot(Byte dot) {
        this.dot = dot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThongKeIndexId)) return false;

        ThongKeIndexId that = (ThongKeIndexId) o;

        if (dot != null ? !dot.equals(that.dot) : that.dot != null) return false;
        if (ngay != null ? !ngay.equals(that.ngay) : that.ngay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ngay != null ? ngay.hashCode() : 0;
        result = 31 * result + (dot != null ? dot.hashCode() : 0);
        return result;
    }
}
