package main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import entity.InfoForJson;
import entity.Movie;
import utility.ExcelUtility;
import utility.IMDBUtility;

public class RunnerExcelToJson {
	
	private static Scanner in;

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("inserire il numero DVD");
		in = new Scanner(System.in);
		String input = in.nextLine();
		List<InfoForJson> infos = ExcelUtility.readExcelByDvdNum(input);
		List<Movie> movies = IMDBUtility.getMoviesFromInfos(infos);
		for(Movie movie : movies) {
			System.out.println(movie.getTitle() + " - " + movie.getTitleIta());
		}
	}

}
