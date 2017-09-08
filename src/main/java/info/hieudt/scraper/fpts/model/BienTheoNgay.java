package info.hieudt.scraper.fpts.model;

import info.hieudt.scraper.fpts.domain.NgayMaCK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class BienTheoNgay {
    @EmbeddedId
    private NgayMaCK pk;

    private Double R;
    private Double effspread;
    private Double quospread;
    private Double phanTramQuospread;
    private Double amihud;
    private Double amihudmoi;
    private Double adAmihud;
    private Double aminvest;
    private Double depth;
    private Double compositeLiq;
    private Double highlow;

    @Transient
    private DuLieuCoPhieu coPhieu;

    @Transient
    private DuLieuCoPhieu coPhieuHomTruoc;

    @Transient
    private DuLieuCoPhieu coPhieuHomSau;

    public BienTheoNgay() {
    }

    public BienTheoNgay(DuLieuCoPhieu coPhieu, DuLieuCoPhieu coPhieuHomTruoc, DuLieuCoPhieu coPhieuHomSau) {
        this.coPhieu = coPhieu;
        this.coPhieuHomTruoc = coPhieuHomTruoc;
        this.coPhieuHomSau = coPhieuHomSau;
        this.pk = coPhieu.getPk();

        tinhR();
        tinhEffspread();
        tinhPhanTramQuospread();
        tinhQuospread();
        tinhAmihud();
        tinhAmihudMoi();
        tinhAdAmihud();
        tinhAminvest();
        tinhDepth();
        tinhCompositeLiq();
        tinhHighLow();
    }

    private Double ln(Double x) {
        return Math.log(x);
    }

    private Double abs(Double x) {
        return Math.abs(x);
    }

    private void tinhR() {
        /*
        R = (Pt – Pt-1)/Pt-1 hoặc ln(Pt/Pt-1) với Pt là giá đóng cửa
         */
        Double giaDongCua = coPhieu.getGiaDongCua();
        Double giaDongCuaHomTruoc = coPhieuHomTruoc.getGiaDongCua();

        if (giaDongCua != null && giaDongCuaHomTruoc != null) {
            this.R = ln(giaDongCua / giaDongCuaHomTruoc);
        }
    }

    private void tinhEffspread() {
        /*
        Ký hiệu: Effspread(2) (tính theo ngày, tháng, quý, năm)
        Effspread = 2 . |ln(Pk) - ln(Mk)| Hoặc Effspread = 2 . |Pk – Mk| / Mk
        Trong đó: Pk: Giá đóng cửa trong ngày
        Mk: (Giá chào bán + Giá hỏi mua)/2
        Theo tháng (quý, năm): tính trung bình giá trị Effspread các ngày trong tháng (quý, năm)
         */
        Double giaChaoBan = coPhieu.getGiaChaoBan();
        Double giaHoiMua = coPhieu.getGiaHoiMua();
        Double giaDongCua = coPhieu.getGiaDongCua();

        if (giaChaoBan != null && giaHoiMua != null && giaDongCua != null) {
            Double M = (giaChaoBan + giaHoiMua) / 2;
            this.effspread = 2 * abs(ln(giaDongCua) - ln(M));
        }
    }

    private void tinhQuospread() {
        // update lan 3: Quospread dc tinh giong phanTramQuospread
        if (this.phanTramQuospread != null && this.phanTramQuospread > 0) {
            this.quospread = this.phanTramQuospread;
        }

        /*
        Ký hiệu: Quospread(3) (tính theo ngày, tháng, quý, năm)
        Quospread = Giá chào bán – Giá hỏi mua
         */
        //Double giaChaoBan = coPhieu.getGiaChaoBan();
        //Double giaHoiMua = coPhieu.getGiaHoiMua();

        //if (giaChaoBan != null && giaHoiMua != null) {
        //    this.quospread = giaChaoBan - giaHoiMua;
        //}
    }

    private void tinhPhanTramQuospread() {
        /*
        Ký hiệu: %Quospread(4) (tính theo ngày, tháng, quý, năm)
        %Quospread(4) = 2 . (Giá chào bán – Giá hỏi mua)/(Giá chào bán + Giá hỏi mua)
        Theo tháng (quý, năm): tính trung bình giá trị %Quospread các ngày trong tháng (quý, năm)
         */
        Double giaChaoBan = coPhieu.getGiaChaoBan();
        Double giaHoiMua = coPhieu.getGiaHoiMua();

        if (giaChaoBan != null && giaHoiMua != null) {
            this.phanTramQuospread = 2 * (giaChaoBan - giaHoiMua) / (giaChaoBan + giaHoiMua);
        }
    }

    private void tinhAmihud() {
        /*
        Ký hiệu: Amihud(5) (tính theo ngày, tháng, quý, năm)
        Amihud(5) = 10^6 * |Rt|/Vt
        Trong đó: Vt = giá đóng cửa tại ngày t x khối lượng giao dịch lấy cột tổng cộng tại ngày t,
        Rt là tỷ suất sinh lợi cổ phiếu tại ngày t = (Giá đóng cửa ngày t  - giá đóng của ngày t-1)/Giá đóng cửa ngày t-1
        */
        Integer khoiLuongGiaoDichTongCong = coPhieu.getKhoiLuongGiaoDichTongCong();
        Double giaDongCua = coPhieu.getGiaDongCua();

        if (khoiLuongGiaoDichTongCong != null && giaDongCua != null && this.R != null) {
            Double V = giaDongCua * khoiLuongGiaoDichTongCong;
            if (V != 0) {
                this.amihud = Math.pow(10, 6) * abs(this.R) / V;
            }
        }
    }

    private void tinhAmihudMoi() {
        /*
        Ký hiệu: Amihudmoi(5) (tính theo ngày, tháng, quý, năm)
        Amihudmoi(5) = 106 . |Ri|/(giá đóng cửa x khối lượng giao dịch lấy cột tổng cộng)
        Theo tháng (quý, năm): tính trung bình giá trị Amihudmoi các ngày trong tháng (quý, năm)
        */
        Integer khoiLuongGiaoDichTongCong = coPhieu.getKhoiLuongGiaoDichTongCong();
        Double giaDongCua = coPhieu.getGiaDongCua();

        if (khoiLuongGiaoDichTongCong != null && khoiLuongGiaoDichTongCong != 0 && this.R != null && giaDongCua != null) {
            this.amihudmoi = Math.pow(10, 6) * abs(this.R) / (giaDongCua * khoiLuongGiaoDichTongCong);
        }
    }

    private void tinhAdAmihud() {
        /*
        Ký hiệu: AdAmihud(6) (tính theo ngày, tháng, quý, năm)
        AdAmihud(6) = 10^6 . ln(PH / PL) / Vi
        Hoặc: AdAmihud(6) = 10^6 . [(PH - PL) / PL] / Vi
        nếu Vi == 0 thì AdAmihud(6) = null
        Trong đó: PH: Giá cao nhất trong ngày
        PL: Giá thấp nhất trong ngày
        Theo tháng (quý, năm): tính trung bình giá trị AdAmihud các ngày trong tháng (quý, năm)
         */
        Double giaTriGiaoDichTongCong = coPhieu.getGiaTriGiaoDichTongCong();
        Double giaCaoNhat = coPhieu.getGiaCaoNhat();
        Double giaThapNhat = coPhieu.getGiaThapNhat();

        if (giaTriGiaoDichTongCong != null && giaTriGiaoDichTongCong != 0 && giaCaoNhat != null && giaThapNhat != null) {
            this.adAmihud = Math.pow(10, 6) * ln(giaCaoNhat / giaThapNhat) / giaTriGiaoDichTongCong;
        }
    }

    private void tinhAminvest() {
        /*
        Ký hiệu: Aminvest(7) (tính theo ngày, tháng, quý, năm)
        Aminvest(7) = Vi / |Ri|
        Nếu R == 0 thì Aminvest = null
        Theo tháng (quý, năm): tính trung bình giá trị Aminvest các ngày trong tháng (quý, năm)
         */
        Double giaTriGiaoDichTongCong = coPhieu.getGiaTriGiaoDichTongCong();

        if (giaTriGiaoDichTongCong != null && this.R != null && this.R != 0) {
            this.aminvest = giaTriGiaoDichTongCong / abs(this.R);
        }
    }

    private void tinhDepth() {
        /*
        Ký hiệu: Depth(8) (tính theo ngày, tháng, quý, năm)
        Depth(8) = Giá chào bán * Khối lượng chào bán + Giá hỏi mua * Khối lượng hỏi mua
        Theo tháng (quý, năm): tính trung bình giá trị Depth(8) các ngày trong tháng (quý, năm)
         */
        Double giaChaoBan = coPhieu.getGiaChaoBan();
        Double khoiLuongGiaChaoBan = coPhieu.getKhoiLuongGiaChaoBan();
        Double giaHoiMua = coPhieu.getGiaHoiMua();
        Double khoiLuongGiaHoiMua = coPhieu.getKhoiLuongGiaHoiMua();

        if (giaChaoBan != null && khoiLuongGiaChaoBan != null && giaHoiMua != null && khoiLuongGiaHoiMua != null) {
            this.depth = giaChaoBan * khoiLuongGiaChaoBan + giaHoiMua * khoiLuongGiaHoiMua;
        }
    }

    private void tinhCompositeLiq() {
        /*
        Ký hiệu: CompositeLiq(9) (tính theo ngày, tháng, quý, năm)
        CompositeLiq = %Quospread(4)/ Depth(8)
        Theo tháng (quý, năm): tính trung bình giá trị CompositeLiq các ngày trong tháng (quý, năm)
         */
        if (this.phanTramQuospread != null && this.depth != null && this.depth != 0) {
            this.compositeLiq = this.phanTramQuospread / this.depth;
        }
    }

    private void tinhHighLow() {
        if (coPhieuHomSau != null) {
            Double giaCaoNhat1 = coPhieu.getGiaCaoNhat();
            Double giaThapNhat1 = coPhieu.getGiaThapNhat();
            Double giaCaoNhat2 = coPhieuHomSau.getGiaCaoNhat();
            Double giaThapNhat2 = coPhieuHomSau.getGiaThapNhat();

            if (giaCaoNhat1 != null && giaCaoNhat2 != null & giaThapNhat1 != null && giaThapNhat2 != null) {
                // 1. Dieu chinh khi gia thay doi qua dem
                Double giaDongCua1 = coPhieu.getGiaDongCua();
                if (giaDongCua1 != null && (giaDongCua1 > giaCaoNhat2 || giaDongCua1 < giaThapNhat2)) {
                    if (giaThapNhat2 > giaDongCua1) {
                        Double X = giaThapNhat2 - giaDongCua1;
                        giaCaoNhat2 = giaCaoNhat2 - X;
                        giaThapNhat2 = giaThapNhat2 - X;
                    } else if (giaCaoNhat2 < giaDongCua1) {
                        Double Y = giaDongCua1 - giaCaoNhat2;
                        giaCaoNhat2 = giaCaoNhat2 + Y;
                        giaThapNhat2 = giaThapNhat2 + Y;
                    }
                }

                if (giaThapNhat1 != 0 && giaThapNhat2 != 0) {
                    Double beta = Math.pow(ln(giaCaoNhat1 / giaThapNhat1), 2) + Math.pow(ln(giaCaoNhat2 / giaThapNhat2), 2);
                    Double gamma = Math.pow(ln(Math.max(giaCaoNhat1, giaCaoNhat2) / Math.min(giaThapNhat1, giaThapNhat2)), 2);
                    Double alpha = ((Math.sqrt(2 * beta) - Math.sqrt(beta)) / (3 - 2 * Math.sqrt(2))) - Math.sqrt(gamma / (3 - 2 * Math.sqrt(2)));
                    this.highlow = 2 * (Math.pow(Math.E, alpha) - 1) / (1 + Math.pow(Math.E, alpha));

                    if (this.highlow < 0) {
                        this.highlow = 0.0;
                    }
                }
            }
        }
    }

    public NgayMaCK getPk() {
        return pk;
    }

    public void setPk(NgayMaCK pk) {
        this.pk = pk;
    }

    public Double getR() {
        return R;
    }

    public void setR(Double r) {
        this.R = r;
    }

    public Double getEffspread() {
        return effspread;
    }

    public void setEffspread(Double effspread) {
        this.effspread = effspread;
    }

    public Double getQuospread() {
        return quospread;
    }

    public void setQuospread(Double quospread) {
        this.quospread = quospread;
    }

    public Double getPhanTramQuospread() {
        return phanTramQuospread;
    }

    public void setPhanTramQuospread(Double phanTramQuospread) {
        this.phanTramQuospread = phanTramQuospread;
    }

    public Double getAmihud() {
        return amihud;
    }

    public void setAmihud(Double amihud) {
        this.amihud = amihud;
    }

    public Double getAmihudmoi() {
        return amihudmoi;
    }

    public void setAmihudmoi(Double amihudmoi) {
        this.amihudmoi = amihudmoi;
    }

    public Double getAdAmihud() {
        return adAmihud;
    }

    public void setAdAmihud(Double adAdmihud) {
        this.adAmihud = adAdmihud;
    }

    public Double getAminvest() {
        return aminvest;
    }

    public void setAminvest(Double aminvest) {
        this.aminvest = aminvest;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getCompositeLiq() {
        return compositeLiq;
    }

    public void setCompositeLiq(Double compositeLiq) {
        this.compositeLiq = compositeLiq;
    }

    public Double getHighlow() {
        return highlow;
    }

    public void setHighlow(Double highlow) {
        this.highlow = highlow;
    }

    public DuLieuCoPhieu getCoPhieu() {
        return coPhieu;
    }

    public void setCoPhieu(DuLieuCoPhieu coPhieu) {
        this.coPhieu = coPhieu;
    }

    public DuLieuCoPhieu getCoPhieuHomTruoc() {
        return coPhieuHomTruoc;
    }

    public void setCoPhieuHomTruoc(DuLieuCoPhieu coPhieuHomTruoc) {
        this.coPhieuHomTruoc = coPhieuHomTruoc;
    }

    public DuLieuCoPhieu getCoPhieuHomSau() {
        return coPhieuHomSau;
    }

    public void setCoPhieuHomSau(DuLieuCoPhieu coPhieuHomSau) {
        this.coPhieuHomSau = coPhieuHomSau;
    }

    @Override
    public String toString() {
        return "BienTheoNgay{" +
                "pk=" + pk +
                ", R=" + R +
                ", effspread=" + effspread +
                ", quospread=" + quospread +
                ", phanTramQuospread=" + phanTramQuospread +
                ", amihud=" + amihud +
                ", amihudmoi=" + amihudmoi +
                ", adAdmihud=" + adAmihud +
                ", aminvest=" + aminvest +
                ", depth=" + depth +
                ", compositeLiq=" + compositeLiq +
                ", highlow=" + highlow +
                ", coPhieu=" + coPhieu +
                ", coPhieuHomTruoc=" + coPhieuHomTruoc +
                ", coPhieuHomSau=" + coPhieuHomSau +
                '}';
    }
}
