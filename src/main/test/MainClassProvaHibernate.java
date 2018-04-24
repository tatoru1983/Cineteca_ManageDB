package main.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.UsersDAO;
import dbentity.Users;
import utility.HibernateUtility;

public class MainClassProvaHibernate {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();
		UsersDAO usersDAO = new UsersDAO(session); 
		List<Users> users = usersDAO.getAll();
		for(Users user : users) {
			System.out.println(user.getUsername() + " - " + user.getPassword());
		}
		HibernateUtility.shutdown();
	}

}
