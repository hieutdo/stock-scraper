package info.hieudt.scraper.fpts;

import info.hieudt.scraper.fpts.domain.Gia;
import info.hieudt.scraper.fpts.domain.GiaTriGiaoDich;
import info.hieudt.scraper.fpts.domain.KhoiLuongGiaoDich;
import info.hieudt.scraper.fpts.domain.NgayChungKhoanPK;
import info.hieudt.scraper.fpts.domain.ThongKeGia;
import org.hibernate.Query;
import org.hibernate.Session;
import info.hieudt.scraper.fpts.domain.ChungKhoan;
import info.hieudt.scraper.fpts.util.HibernateUtil;

import java.text.ParseException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

/*
			session.beginTransaction();

			ChungKhoan chungKhoan = new ChungKhoan("AAM");
			session.save(chungKhoan);

			NgayChungKhoanPK ngayChungKhoanPK = new NgayChungKhoanPK("27/11/2014", chungKhoan);
			Gia gia = new Gia(14.8, 15.8, 13.8, 14.7, 14.8, 14.9, 14.7, 0.0);
			KhoiLuongGiaoDich khoiLuongGiaoDich = new KhoiLuongGiaoDich(3830, 0, 3830);
			GiaTriGiaoDich giaTriGiaoDich = new GiaTriGiaoDich(57.0, 0.0, 57.0);

			ThongKeGia thongKeGia = new ThongKeGia(ngayChungKhoanPK, gia, khoiLuongGiaoDich, giaTriGiaoDich);
			session.save(thongKeGia);

			session.getTransaction().commit();
*/

			Query query = session.createQuery("from ChungKhoan ck where ck.maChungKhoan = :maChungKhoan");
			query.setParameter("maChungKhoan", "AAM");
			ChungKhoan chungKhoan = (ChungKhoan) query.uniqueResult();
			System.out.print(chungKhoan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
