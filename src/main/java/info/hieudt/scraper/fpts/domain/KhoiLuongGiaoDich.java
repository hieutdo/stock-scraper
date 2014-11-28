package info.hieudt.scraper.fpts.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by hdo9 on 11/28/2014.
 */
@Embeddable
public class KhoiLuongGiaoDich {
	@Column(name = "klgdKhopLenh")
	private Integer khopLenh;

	@Column(name = "klgdThoaThuan")
	private Integer thoaThuan;

	@Column(name = "klgdTongCong")
	private Integer tongCong;

	public KhoiLuongGiaoDich() {}

	public KhoiLuongGiaoDich(Integer khopLenh, Integer thoaThuan, Integer tongCong) {
		this.khopLenh = khopLenh;
		this.thoaThuan = thoaThuan;
		this.tongCong = tongCong;
	}

	public Integer getKhopLenh() {
		return khopLenh;
	}

	public void setKhopLenh(Integer khopLenh) {
		this.khopLenh = khopLenh;
	}

	public Integer getThoaThuan() {
		return thoaThuan;
	}

	public void setThoaThuan(Integer thoaThuan) {
		this.thoaThuan = thoaThuan;
	}

	public Integer getTongCong() {
		return tongCong;
	}

	public void setTongCong(Integer tongCong) {
		this.tongCong = tongCong;
	}
}
