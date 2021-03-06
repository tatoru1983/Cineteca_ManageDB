package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dbentity.Film;
import dbentity.FilmRating;

public class FilmDAO {
	
	private Session session;
	
	public FilmDAO(Session session) {
		super();
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<Film> getAll(){
		TypedQuery<Film> query = session.createQuery("from Film");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Film> getFilmByDVD(Integer numDVD){
		FilmRatingDAO filmRatingDAO = new FilmRatingDAO(session);
		TypedQuery<Film> query = session.createQuery("from Film where dvd = ?");
		query.setParameter(0, numDVD);
		List<Film> listFilm = query.getResultList();
		for(Film film : listFilm) {
			List<FilmRating> filmRating = filmRatingDAO.getRatingsByFilm(film);
			film.setFilmRating(filmRating);
		}
		return listFilm;
	}
	
	public void deleteFilmByDVD(Integer numDVD){
		FilmRatingDAO filmRatingDAO = new FilmRatingDAO(session);
		TypedQuery<Film> query = session.createQuery("from Film where dvd = ?");
		query.setParameter(0, numDVD);
		List<Film> listFilm = query.getResultList();
		for(Film film : listFilm) {
			List<FilmRating> filmRating = filmRatingDAO.getRatingsByFilm(film);
			for(FilmRating rating : filmRating) {
				filmRatingDAO.delete(rating);
			}
			delete(film);
		}
	}

	@SuppressWarnings("unchecked")
	public Film getFilmById(String imdbID) {
		FilmRatingDAO filmRatingDAO = new FilmRatingDAO(session);
		TypedQuery<Film> query = session.createQuery("from Film f where f.imdbID = ?");
		query.setParameter(0, imdbID);
		Film film = query.getSingleResult();
		List<FilmRating> filmRating = filmRatingDAO.getRatingsByFilm(film);
		film.setFilmRating(filmRating);
		return film;
	}
	
	public void save(Film film) {
		 Transaction tx = null;
		 try {
		     tx = session.beginTransaction();
		     session.save(film);
		     tx.commit();
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
	}
	
	public void delete(Film film) {
		 Transaction tx = null;
		 try {
		     tx = session.beginTransaction();
		     session.delete(film);
		     tx.commit();
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
	}
	
	public void closeSession(){
		if(session !=null)
			session.close();
	}

}
