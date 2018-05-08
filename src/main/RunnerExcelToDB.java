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

public class RunnerExcelToDB {

	private static Scanner in;
	private static Properties props;

	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) {
		Session session = null;
		String input = "";
		List<String> inputList = getListFromRange(185, 200);
		try {

			//DAO
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			FilmDAO filmDAO = new FilmDAO(session);
			FilmRatingDAO filmRatingDAO = new FilmRatingDAO(session);


			for(String numDVD : inputList) {
				input = numDVD;

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
				System.out.println("DVD "+input+" inserted!");
			}
			System.out.println("Inserted all! OK");
		}catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("Errore DVD numero: "+input);
		}finally {
			if (session != null) {
				session.close();
				System.out.println("Terminated!");
			}
			HibernateUtility.shutdown();
			System.exit(0);
		}
	}

	private static List<String> getListFromRange(int from, int to) {
		List<String> result = new ArrayList<String>();
		int i=from;
		while(i<=to) {
			String num = Integer.toString(i);
			result.add(num);
			i++;
		}
		return result;
	}

}
