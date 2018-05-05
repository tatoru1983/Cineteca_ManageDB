package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dbentity.Film;
import dbentity.FilmRating;

public class FilmRatingDAO {
	
	private Session session;
	
	public FilmRatingDAO(Session session) {
		super();
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<FilmRating> getAll(){
		TypedQuery<FilmRating> query = session.createQuery("from FilmRating");
		return query.getResultList();
	}
	
	public void save(FilmRating filmRating) {
		Transaction tx = null;
		 try {
		     tx = session.beginTransaction();
		     session.save(filmRating);
		     tx.commit();
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
	}
	
	@SuppressWarnings("unchecked")
	public List<FilmRating> getRatingsByFilm(Film film){
		TypedQuery<FilmRating> query = session.createQuery("from FilmRating fr where fr.id.imdbID = ?");
		query.setParameter(0, film.getImdbID());
		return query.getResultList();
	}
	
	public void closeSession(){
		if(session !=null)
			session.close();
	}
}
