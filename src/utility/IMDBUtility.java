package utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entity.InfoForJson;
import entity.Movie;

public class IMDBUtility {
	
	private static String URL = "";
	private static String APIKEY = "";
	
	public static String getUrl(String id) {
		return URL.concat("/?i=").concat(id).concat("&apikey=").concat(APIKEY);
	}
	
	public static List<Movie> getMoviesFromInfos(List<InfoForJson> listJson, Properties props) throws IOException, ParseException{
		URL = props.getProperty("URL");
		APIKEY = props.getProperty("APIKEY");
		List<Movie> result = new ArrayList<Movie>();
		for(InfoForJson ifj : listJson) {
			String url = getUrl(ifj.getIdImdb());
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			InputStream response = connection.getInputStream();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(
			      new InputStreamReader(response, "UTF-8"));
			Movie movie = JsonUtility.getMovieFromJSON(jsonObject);
			movie.setTitleIta(ifj.getTitleIta());
			result.add(movie);
		}
		return result;
	}

}
