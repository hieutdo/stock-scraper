package info.hieudt.scraper.fpts.domain;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hdo9 on 11/28/2014.
 */
@Entity
@Table
public class ThongKeGia implements Serializable {
	private static final long serialVersionUID = 810352505590404497L;

	@EmbeddedId
	private NgayChungKhoanPK pk = new NgayChungKhoanPK();

	@Embedded
	private Gia gia;

	@Embedded
	private KhoiLuongGiaoDich khoiLuongGiaoDich;

	@Embedded
	private GiaTriGiaoDich giaTriGiaoDich;

	public ThongKeGia() {}

	public ThongKeGia(NgayChungKhoanPK pk, Gia gia, KhoiLuongGiaoDich khoiLuongGiaoDich, GiaTriGiaoDich giaTriGiaoDich) {
		this.pk = pk;
		this.gia = gia;
		this.khoiLuongGiaoDich = khoiLuongGiaoDich;
		this.giaTriGiaoDich = giaTriGiaoDich;
	}

	public NgayChungKhoanPK getPk() {
		return pk;
	}

	public void setPk(NgayChungKhoanPK pk) {
		this.pk = pk;
	}

	@Transient
	public Date getNgay() {
		return pk.getNgay();
	}

	public void setNgay(Date ngay) {
		pk.setNgay(ngay);
	}

	@Transient
	public ChungKhoan getChungKhoan() {
		return pk.getChungKhoan();
	}

	public void setChungKhoan(ChungKhoan chungKhoan) {
		pk.setChungKhoan(chungKhoan);
	}

	public Gia getGia() {
		return gia;
	}

	public void setGia(Gia gia) {
		this.gia = gia;
	}

	public KhoiLuongGiaoDich getKhoiLuongGiaoDich() {
		return khoiLuongGiaoDich;
	}

	public void setKhoiLuongGiaoDich(KhoiLuongGiaoDich khoiLuongGiaoDich) {
		this.khoiLuongGiaoDich = khoiLuongGiaoDich;
	}

	public GiaTriGiaoDich getGiaTriGiaoDich() {
		return giaTriGiaoDich;
	}

	public void setGiaTriGiaoDich(GiaTriGiaoDich giaTriGiaoDich) {
		this.giaTriGiaoDich = giaTriGiaoDich;
	}
}
