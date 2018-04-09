package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entity.Movie;
import utility.IMDBUtility;
import utility.JsonUtility;

public class MainClassProvaIMDB {

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		IMDBUtility utility = new IMDBUtility();
		JsonUtility utilityJ = new JsonUtility();
		String url = utility.getUrl("tt0119396");
		System.out.println(url);
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		InputStream response = connection.getInputStream();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(
		      new InputStreamReader(response, "UTF-8"));
		Movie movie = utilityJ.getMovieFromJSON(jsonObject);
		System.out.println(movie.getTitle());
	}

}
