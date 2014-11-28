package info.hieudt.scraper.fpts.domain;

import org.hibernate.annotations.Type;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hdo9 on 11/28/2014.
 */
public class NgayChungKhoanPK implements Serializable {
	private static final long serialVersionUID = -1233587661749721106L;

	@Type(type = "date")
	private Date ngay;

	@ManyToOne
	@JoinColumn(name = "idChungKhoan")
	private ChungKhoan chungKhoan;

	public NgayChungKhoanPK() {
	}

	public NgayChungKhoanPK(String ngayStr, ChungKhoan chungKhoan) throws ParseException {
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
		if (o == null || getClass() != o.getClass()) return false;

		NgayChungKhoanPK that = (NgayChungKhoanPK) o;

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
