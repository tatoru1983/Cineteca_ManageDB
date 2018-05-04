package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dbentity.Film;

public class FilmDAO {
	
	private Session session;
	
	public FilmDAO(Session session) {
		super();
		this.session = session;
	}
	
	@SuppressWarnings("deprecation")
	public List<Film> getAll(){
		Query<Film> query = session.createQuery("from Film");
		return query.list();
	}
	
	public void save(Film film) {
		session.save(film);
	}

}
