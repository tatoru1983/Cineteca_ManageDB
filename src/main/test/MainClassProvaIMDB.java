package main.test;

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
import utility.IMDBUtility;
import utility.JsonUtility;
import utility.PropertiesUtility;

public class MainClassProvaIMDB {
	private static Properties props;

	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		//IMDBUtility utility = new IMDBUtility();
		JsonUtility jsonUtility = new JsonUtility();
		InfoForJson a = new InfoForJson("1","Il grande botto","tt0242492");
		InfoForJson b = new InfoForJson("1","Io speriamo che me la cavo","tt0107225");
		InfoForJson c = new InfoForJson("1","La gente vuole ridere","tt4874670");
		InfoForJson d = new InfoForJson("1","Morte di un matematico napoletano","tt0104918");
		
		List<InfoForJson> listJson = new ArrayList<InfoForJson>();
		listJson.add(a);
		listJson.add(b);
		listJson.add(c);
		listJson.add(d);
		
		for(InfoForJson ifj : listJson) {
			//String url = utility.getUrl(ifj.getIdImdb());
			String url = IMDBUtility.getUrl(ifj.getIdImdb(), props);
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
