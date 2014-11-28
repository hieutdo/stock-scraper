package info.hieudt.scraper.fpts.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by hdo9 on 11/28/2014.
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
