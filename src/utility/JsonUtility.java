package utility;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbentity.Film;
import dbentity.FilmRating;
import entity.Movie;
import entity.Rating;

public class JsonUtility {

	private static Properties props;
	//private static String FOLDER_JSON;
	
	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	};

	public static Movie getMovieFromJSON(JSONObject jsonObject) {
		Movie movie = new Movie();
		movie.setImdbID((String)jsonObject.get("imdbID"));
		movie.setTitle((String)jsonObject.get("Title"));
		movie.setYear((String)jsonObject.get("Year"));
		movie.setRated((String)jsonObject.get("Rated"));
		movie.setReleased((String)jsonObject.get("Released"));
		movie.setRuntime((String)jsonObject.get("Runtime"));
		movie.setGenre((String)jsonObject.get("Genre"));
		movie.setDirector((String)jsonObject.get("Director"));
		movie.setWriter((String)jsonObject.get("Writer"));
		movie.setActors((String)jsonObject.get("Actors"));
		movie.setPlot((String)jsonObject.get("Plot"));
		movie.setCountry((String)jsonObject.get("Country"));
		movie.setAwards((String)jsonObject.get("Awards"));
		movie.setPoster((String)jsonObject.get("Poster"));

		JSONArray ratings= (JSONArray) jsonObject.get("Ratings");
		if(ratings!=null) {
			for (Object o : ratings) {
				JSONObject rating = (JSONObject)o;
				movie.addRating(getRatingFromJSON(rating));
			}
		}
		return movie;
	}

	public static Rating getRatingFromJSON(JSONObject jsonObject) {
		Rating rating = new Rating();
		rating.setSource((String)jsonObject.get("Source"));
		rating.setValue((String)jsonObject.get("Value"));
		return rating;
	}

	public static void writeFileFromJSONArray(JSONArray array, String nameFile, String folder) throws IOException {
		//StringWriter out = new StringWriter();
		FileWriter file = new FileWriter( folder.concat("/").concat(nameFile).concat(".json"),false);
		StringWriter out = new StringWriter();
		array.writeJSONString(out);
		file.write(out.toString());
		file.close();
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJSONFromFilm(Film film) {
		JSONObject result = new JSONObject();
		result.put("TITLE_ITA",film.getTitleIta());
		result.put("TITLE",film.getTitle());
		result.put("YEAR",film.getYear());
		result.put("RATED",film.getRated());
		result.put("RELEASED",film.getReleased());
		result.put("RUNTIME",film.getRuntime());
		result.put("GENRE",film.getGenre());
		result.put("DIRECTOR",film.getDirector());
		result.put("WRITER",film.getWriter());
		result.put("ACTORS",film.getActors());
		result.put("PLOT",film.getPlot());
		result.put("COUNTRY",film.getCountry());
		result.put("AWARDS",film.getAwards());
		result.put("POSTER",film.getPoster());
		result.put("IMDBID",film.getImdbID());
		result.put("DVD",film.getDvd());
		List<FilmRating> ratings = film.getFilmRating();
		JSONArray arrayRatings = new JSONArray();
		for(FilmRating filmRating : ratings) {
			JSONObject obj = getJSONFromFilmRating(filmRating);
			arrayRatings.add(obj);
		}
		result.put("RATINGS", arrayRatings);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJSONFromFilmRating(FilmRating filmRating) {
		JSONObject result = new JSONObject();
		result.put("SOURCE",filmRating.getId().getSource());
		result.put("VALUE",filmRating.getValue());
		return result;
	}
	
	public static JSONArray file2Json(String fileName) throws IOException, ParseException {
		InputStream is = new FileInputStream(fileName);
        String jsonTxt = IOUtils.toString(is, "UTF-8");
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(jsonTxt);
	}
}
