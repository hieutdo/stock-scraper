package info.hieudt.scraper.fpts.domain;

import javax.persistence.Embeddable;

/**
 * Created by hdo9 on 11/28/2014.
 */
@Embeddable
public class Gia {
	private Double giaThamChieu;
	private Double giaTran;
	private Double giaSan;
	private Double giaMoCua;
	private Double giaDongCua;
	private Double giaCaoNhat;
	private Double giaThapNhat;
	private Double giaTrungBinh;

	public Gia() {}

	public Gia(Double giaThamChieu, Double giaTran, Double giaSan, Double giaMoCua, Double giaDongCua,
			   Double giaCaoNhat, Double giaThapNhat, Double giaTrungBinh) {
		this.giaThamChieu = giaThamChieu;
		this.giaTran = giaTran;
		this.giaSan = giaSan;
		this.giaMoCua = giaMoCua;
		this.giaDongCua = giaDongCua;
		this.giaCaoNhat = giaCaoNhat;
		this.giaThapNhat = giaThapNhat;
		this.giaTrungBinh = giaTrungBinh;
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
}
