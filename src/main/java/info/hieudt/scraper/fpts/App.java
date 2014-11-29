package info.hieudt.scraper.fpts;

import info.hieudt.scraper.fpts.domain.*;
import org.hibernate.Query;
import org.hibernate.Session;
import info.hieudt.scraper.fpts.util.HibernateUtil;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            ChungKhoan chungKhoan = new ChungKhoan("AAM", SanGiaoDich.HOSE);
            session.save(chungKhoan);

            ThongKeGia thongKeGia = new ThongKeGia(
                    new ThongKeGiaId("27/11/2014", chungKhoan),
                    new Gia(14.8, 15.8, 13.8, 14.7, 14.8, 14.9, 14.7, 0.0),
                    new KhoiLuongGiaoDich(3830, 0, 3830),
                    new GiaTriGiaoDich(57.0, 0.0, 57.0)
            );
            session.save(thongKeGia);

//			Query query = session.createQuery("from ChungKhoan ck where ck.maChungKhoan = :maChungKhoan");
//			query.setParameter("maChungKhoan", "AAM");
//			chungKhoan = (ChungKhoan) query.uniqueResult();
//			System.out.print(chungKhoan);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
