package main;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainClassProvaIMDB {
	private static String url = "http://www.omdbapi.com/";
	private static String charset = "UTF-8";
	private static String title = "Game of Thrones";
	private static String season = "5";
	private static String episode = "5";

	public static void main(String[] args) throws MalformedURLException, IOException {

		String query = String.format("t=%s&season=%s&episode=%s", 
		     URLEncoder.encode(title, charset), 
		     URLEncoder.encode(season, charset),
		     URLEncoder.encode(episode, charset));
		
		System.out.println(url + "?" + query);

		URLConnection connection = new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();
	}

}
