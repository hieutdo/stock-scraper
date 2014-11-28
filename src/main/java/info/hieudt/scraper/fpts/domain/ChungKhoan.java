package info.hieudt.scraper.fpts.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdo9 on 11/28/2014.
 */
@Entity
@Table
public class ChungKhoan implements Serializable {
	private static final long serialVersionUID = -1549252751720459350L;

	@Id
	@GeneratedValue
	private Integer idChungKhoan;

	@Column(unique = true, length = 5)
	private String maChungKhoan;

	@OneToMany(mappedBy = "pk.chungKhoan", cascade = CascadeType.ALL)
	private List<ThongKeGia> thongKeGia = new ArrayList<ThongKeGia>();

	public ChungKhoan() {}

	public ChungKhoan(String maChungKhoan) {
		this.maChungKhoan = maChungKhoan;
	}

	public Integer getIdChungKhoan() {
		return idChungKhoan;
	}

	public void setIdChungKhoan(Integer idChungKhoan) {
		this.idChungKhoan = idChungKhoan;
	}

	public String getMaChungKhoan() {
		return maChungKhoan;
	}

	public void setMaChungKhoan(String maChungKhoan) {
		this.maChungKhoan = maChungKhoan;
	}

	public List<ThongKeGia> getThongKeGia() {
		return thongKeGia;
	}

	public void setThongKeGia(List<ThongKeGia> thongKeGia) {
		this.thongKeGia = thongKeGia;
	}
}
