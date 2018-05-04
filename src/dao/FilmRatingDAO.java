package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dbentity.FilmRating;

public class FilmRatingDAO {
	
	private Session session;
	
	public FilmRatingDAO(Session session) {
		super();
		this.session = session;
	}
	
	@SuppressWarnings("deprecation")
	public List<FilmRating> getAll(){
		Query<FilmRating> query = session.createQuery("from FilmRating");
		return query.list();
	}
	
	public void save(FilmRating filmRating) {
		session.save(filmRating);
	}
}
