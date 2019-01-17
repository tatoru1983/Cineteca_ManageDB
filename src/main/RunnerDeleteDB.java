package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.FilmDAO;
import utility.HibernateUtility;

public class RunnerDeleteDB {

	public static void main(String[] args) {
		Session session = null;
		try {
			Integer input = new Integer("253");
			
			//DAO
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			FilmDAO filmDAO = new FilmDAO(session);
			
			filmDAO.deleteFilmByDVD(input);
			
			System.out.println("Deleted!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
				System.out.println("Terminated!");
			}
			HibernateUtility.shutdown();
			System.exit(0);
		}
	}

}
