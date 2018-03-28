package utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Movie;
import entity.Rating;

public class JsonUtility {
	
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
		for (Object o : ratings) {
			JSONObject rating = (JSONObject)o;
			movie.addRating(getRatingFromJSON(rating));
		}	
		return movie;
	}
	
	public static Rating getRatingFromJSON(JSONObject jsonObject) {
		Rating rating = new Rating();
		rating.setSource((String)jsonObject.get("Source"));
		rating.setValue((String)jsonObject.get("Value"));
		return rating;
	}
}
