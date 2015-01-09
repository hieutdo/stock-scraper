package info.hieudt.scraper.fpts.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChungKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idChungKhoan;

    @Column(unique = true, length = 10)
    private String maChungKhoan;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SanGiaoDich sanGiaoDich;

//    @OneToMany(mappedBy = "pk.chungKhoan", cascade = CascadeType.ALL)
//    private List<ThongKeGia> thongKeGia = new ArrayList<>();
//
//    @OneToMany(mappedBy = "pk.chungKhoan", cascade = CascadeType.ALL)
//    private List<ThongKeDatLenh> thongKeDatLenh = new ArrayList<>();

    public ChungKhoan() {
    }

    public ChungKhoan(String maChungKhoan, SanGiaoDich sanGiaoDich) {
        this.maChungKhoan = maChungKhoan;
        this.sanGiaoDich = sanGiaoDich;
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

    public SanGiaoDich getSanGiaoDich() {
        return sanGiaoDich;
    }

    public void setSanGiaoDich(SanGiaoDich sanGiaoDich) {
        this.sanGiaoDich = sanGiaoDich;
    }

//    public List<ThongKeGia> getThongKeGia() {
//        return thongKeGia;
//    }
//
//    public void setThongKeGia(List<ThongKeGia> thongKeGia) {
//        this.thongKeGia = thongKeGia;
//    }
//
//    public List<ThongKeDatLenh> getThongKeDatLenh() {
//        return thongKeDatLenh;
//    }
//
//    public void setThongKeDatLenh(List<ThongKeDatLenh> thongKeDatLenh) {
//        this.thongKeDatLenh = thongKeDatLenh;
//    }

    @Override
    public String toString() {
        return "ChungKhoan{" +
                "idChungKhoan=" + idChungKhoan +
                ", maChungKhoan='" + maChungKhoan + '\'' +
                ", sanGiaoDich=" + sanGiaoDich +
//                ", thongKeGia=" + thongKeGia +
//                ", thongKeDatLenh=" + thongKeDatLenh +
                '}';
    }
}
