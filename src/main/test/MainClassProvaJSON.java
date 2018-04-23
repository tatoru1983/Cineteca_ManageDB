package main.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entity.Movie;
import utility.JsonUtility;

public class MainClassProvaJSON {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		String filePath = "C:\\Users\\salvatore.aprano\\workspace-oxygen\\Cineteca_ManageDB\\src\\json\\DVD8.JSON";
		JSONArray a = (JSONArray) parser.parse(new FileReader(filePath));

		for (Object o : a)
		{
			JSONObject m = (JSONObject) o;

			Movie movie = JsonUtility.getMovieFromJSON(m);
			System.out.println(movie.getImdbID() + " - " + movie.getTitle() + " - " + movie.getCountry() + " - " + movie.getYear());
		}
	}
}
