package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import dbentity.Film;
import entity.InfoForJson;
import entity.Movie;
import utility.ExcelUtility;
import utility.IMDBUtility;
import utility.PropertiesUtility;

public class RunnerExcelToJson {
	
	private static Scanner in;
	private static Properties props;
	
	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("inserire il numero DVD");
		in = new Scanner(System.in);
		String input = in.nextLine();
		
		//Read rows from Excel
		List<InfoForJson> infos = ExcelUtility.readExcelByDvdNum(input, props);

		//JSON information
		List<Movie> movies = IMDBUtility.getMoviesFromInfos(infos, props);
		
		List<Film> films = new ArrayList<Film>();
		for(Movie movie : movies) {
			System.out.println(movie.getTitle() + " - " + movie.getTitleIta());
			//Transform to DB entities
		}
		
		
	}

}
