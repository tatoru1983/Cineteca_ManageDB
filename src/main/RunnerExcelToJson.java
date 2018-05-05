package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import dao.FilmDAO;
import dao.FilmRatingDAO;
import dbentity.Film;
import dbentity.FilmRating;
import entity.InfoForJson;
import entity.Movie;
import utility.ExcelUtility;
import utility.HibernateUtility;
import utility.IMDBUtility;
import utility.PropertiesUtility;

public class RunnerExcelToJson {
	
	private static Scanner in;
	private static Properties props;
	
	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("inserire il numero DVD");
		in = new Scanner(System.in);
		String input = in.nextLine();
		
		//DAO
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();
		FilmDAO filmDAO = new FilmDAO(session);
		FilmRatingDAO filmRatingDAO = new FilmRatingDAO(session);
		
		//Read rows from Excel
		List<InfoForJson> infos = ExcelUtility.readExcelByDvdNum(input, props);

		//JSON information
		List<Movie> movies = IMDBUtility.getMoviesFromInfos(infos, props);
		
		List<Film> films = new ArrayList<Film>();
		for(Movie movie : movies) {
			System.out.println(movie.getTitle() + " - " + movie.getTitleIta());
			//Transform to DB entities
			Film film = new Film(new Integer(input), movie);
			films.add(film);
		}
		
		for(Film film : films) {
			filmDAO.save(film);
			for(FilmRating filmRating : film.getFilmRating()) {
				filmRatingDAO.save(filmRating);
			}
		}
		
		session.close();
	}

}
