package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dbentity.Users;

public class UsersDAO {
	
	private Session session;
	
	public UsersDAO(Session session) {
		super();
		this.session = session;
	}
	
	@SuppressWarnings("deprecation")
	public List<Users> getAll(){
		Query<Users> query = session.createQuery("from Users");
		return query.list();
	}

}
