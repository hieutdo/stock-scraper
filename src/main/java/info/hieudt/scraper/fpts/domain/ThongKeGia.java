package info.hieudt.scraper.fpts.domain;

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

	private Double giaThamChieu;
	private Double giaTran;
	private Double giaSan;
	private Double giaMoCua;
	private Double giaDongCua;
	private Double giaCaoNhat;
	private Double giaThapNhat;
	private Double giaTrungBinh;

	private Integer klgdKhopLenh;
	private Integer klgdThoaThuan;
	private Integer klgdTongCong;

	private Double gtgdKhopLenh;
	private Double gtgdThoaThuan;
	private Double gtgdTongCong;

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

	public Double getGiaThamChieu() {
		return giaThamChieu;
	}

	public void setGiaThamChieu(Double giaThamChieu) {
		this.giaThamChieu = giaThamChieu;
	}

	public Double getGiaTran() {
		return giaTran;
	}

	public void setGiaTran(Double giaTran) {
		this.giaTran = giaTran;
	}

	public Double getGiaSan() {
		return giaSan;
	}

	public void setGiaSan(Double giaSan) {
		this.giaSan = giaSan;
	}

	public Double getGiaMoCua() {
		return giaMoCua;
	}

	public void setGiaMoCua(Double giaMoCua) {
		this.giaMoCua = giaMoCua;
	}

	public Double getGiaDongCua() {
		return giaDongCua;
	}

	public void setGiaDongCua(Double giaDongCua) {
		this.giaDongCua = giaDongCua;
	}

	public Double getGiaCaoNhat() {
		return giaCaoNhat;
	}

	public void setGiaCaoNhat(Double giaCaoNhat) {
		this.giaCaoNhat = giaCaoNhat;
	}

	public Double getGiaThapNhat() {
		return giaThapNhat;
	}

	public void setGiaThapNhat(Double giaThapNhat) {
		this.giaThapNhat = giaThapNhat;
	}

	public Double getGiaTrungBinh() {
		return giaTrungBinh;
	}

	public void setGiaTrungBinh(Double giaTrungBinh) {
		this.giaTrungBinh = giaTrungBinh;
	}

	public Integer getKlgdKhopLenh() {
		return klgdKhopLenh;
	}

	public void setKlgdKhopLenh(Integer klgdKhopLenh) {
		this.klgdKhopLenh = klgdKhopLenh;
	}

	public Integer getKlgdThoaThuan() {
		return klgdThoaThuan;
	}

	public void setKlgdThoaThuan(Integer klgdThoaThuan) {
		this.klgdThoaThuan = klgdThoaThuan;
	}

	public Integer getKlgdTongCong() {
		return klgdTongCong;
	}

	public void setKlgdTongCong(Integer klgdTongCong) {
		this.klgdTongCong = klgdTongCong;
	}

	public Double getGtgdKhopLenh() {
		return gtgdKhopLenh;
	}

	public void setGtgdKhopLenh(Double gtgdKhopLenh) {
		this.gtgdKhopLenh = gtgdKhopLenh;
	}

	public Double getGtgdThoaThuan() {
		return gtgdThoaThuan;
	}

	public void setGtgdThoaThuan(Double gtgdThoaThuan) {
		this.gtgdThoaThuan = gtgdThoaThuan;
	}

	public Double getGtgdTongCong() {
		return gtgdTongCong;
	}

	public void setGtgdTongCong(Double gtgdTongCong) {
		this.gtgdTongCong = gtgdTongCong;
	}
}
