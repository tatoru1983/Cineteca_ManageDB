package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
<<<<<<< HEAD
import java.net.URLEncoder;
import java.util.Map;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> e57a80b9ac45dbee3f25d5329ee499f346c232d4

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entity.InfoForJson;
import entity.Movie;
import utility.IMDBUtility;
import utility.JsonUtility;

<<<<<<< HEAD
		String query = String.format("t=%s&season=%s&episode=%s", 
		     URLEncoder.encode(title, charset), 
		     URLEncoder.encode(season, charset),
		     URLEncoder.encode(episode, charset));
		
		System.out.println(url + "?" + query+"&apikey=1e3dd8f8");

		URLConnection connection = new URL(url + "?" + query+"&apikey=1e3dd8f8").openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(response, Map.class);
=======
public class MainClassProvaIMDB {

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		IMDBUtility utility = new IMDBUtility();
		JsonUtility jsonUtility = new JsonUtility();
		InfoForJson a = new InfoForJson("1","Jackie Brown","tt0119396");
		InfoForJson b = new InfoForJson("1","Le iene","tt0105236");
		InfoForJson c = new InfoForJson("1","Pulp fiction","tt0110912");
		
		List<InfoForJson> listJson = new ArrayList<InfoForJson>();
		listJson.add(a);
		listJson.add(b);
		listJson.add(c);
		
		for(InfoForJson ifj : listJson) {
			String url = utility.getUrl(ifj.getIdImdb());
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			InputStream response = connection.getInputStream();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(
			      new InputStreamReader(response, "UTF-8"));
			Movie movie = jsonUtility.getMovieFromJSON(jsonObject);
			movie.setTitleIta(ifj.getTitleIta());
			System.out.println(movie.getTitle() + " - " + movie.getTitleIta());
		}
>>>>>>> e57a80b9ac45dbee3f25d5329ee499f346c232d4
	}

}
