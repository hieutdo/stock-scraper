package info.hieudt.scraper.fpts;

import org.hibernate.Query;
import org.hibernate.Session;
import info.hieudt.scraper.fpts.domain.ChungKhoan;
import info.hieudt.scraper.fpts.util.HibernateUtil;

import java.text.ParseException;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws ParseException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

/*
		ChungKhoan chungKhoan = new ChungKhoan("AAM");
		session.save(chungKhoan);

		ThongKeGia thongKeGia = new ThongKeGia();
		thongKeGia.setPk(new NgayChungKhoanPK("27/11/2014", chungKhoan));
		// gia
		thongKeGia.setGiaThamChieu(14.8);
		thongKeGia.setGiaTran(15.8);
		thongKeGia.setGiaSan(13.8);
		thongKeGia.setGiaMoCua(14.7);
		thongKeGia.setGiaDongCua(14.8);
		thongKeGia.setGiaCaoNhat(14.9);
		thongKeGia.setGiaThapNhat(14.7);
		thongKeGia.setGiaTrungBinh(0.0);
		// khoi luong giao dich
		thongKeGia.setKlgdKhopLenh(3830);
		thongKeGia.setKlgdThoaThuan(0);
		thongKeGia.setKlgdTongCong(3830);
		// gia tri giao dich
		thongKeGia.setGtgdKhopLenh(57.0);
		thongKeGia.setGtgdThoaThuan(0.0);
		thongKeGia.setGtgdTongCong(57.0);

		session.save(thongKeGia);
*/

		Query query = session.createQuery("from ChungKhoan ck where ck.maChungKhoan = :maChungKhoan");
		query.setParameter("maChungKhoan", "AAM");

//		List chungKhoanList = query.list();
		ChungKhoan chungKhoan = (ChungKhoan) query.uniqueResult();

		session.getTransaction().commit();

		System.exit(0);
	}
}
