package utility;

import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

	/*
	public static void writeJsonToFile(String dvdNum, List<Movie> movies) throws IOException {
		FileWriter file = new FileWriter(FOLDER_JSON.concat("DVD_").concat(dvdNum));
		for
		file.write(obj.toJSONString());
	}*/
}
