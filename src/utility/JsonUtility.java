package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dbentity.Film;
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
		//FOLDER_JSON = props.getProperty("FOLDER_JSON");
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
		return result;
	}
}
