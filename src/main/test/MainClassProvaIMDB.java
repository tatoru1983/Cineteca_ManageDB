package main.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entity.InfoForJson;
import entity.Movie;
import utility.IMDBUtility;
import utility.JsonUtility;

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
	}

}
