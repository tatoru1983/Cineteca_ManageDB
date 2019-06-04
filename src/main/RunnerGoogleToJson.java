package main;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import dbentity.Film;
import entity.InfoForJson;
import entity.Movie;
import utility.DataUtility;
import utility.GoogleUtility;
import utility.IMDBUtility;
import utility.JsonUtility;
import utility.PropertiesUtility;

public class RunnerGoogleToJson {

	private static Scanner in;
	private static Properties props;

	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) throws IOException, GeneralSecurityException, ParseException {
		String folder = args[0];
		int lastDvd = 0;
		String fileNameJson = "movies";
		
		//file con l'ultimo DVD inserito
		JSONArray array = JsonUtility.file2Json(folder.concat("/").concat(fileNameJson).concat(".json"));
		for(int i=0; i<array.size(); i++) {
			JSONObject json = (JSONObject)array.get(i);
			lastDvd = Integer.parseInt(json.get("DVD").toString());
		}
		/*String nameTxtLastDvd = "LAST_DVD.txt";
		File txtLastDvd = new File(folder.concat(nameTxtLastDvd));
		if(txtLastDvd!=null && txtLastDvd.exists() && txtLastDvd.length()>0) {
			try (BufferedReader reader = new BufferedReader(new FileReader(txtLastDvd))) {
			    while (true) {
			        String line = reader.readLine();
			        if (line == null) {
			           break;
			        }
			    	lastDvd = Integer.parseInt(line);
			    }
			}
		}*/

		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = props.getProperty("spreadsheetIdCineteca");
		final String sheetName = props.getProperty("sheetNameCineteca");
		
		String cell = sheetName.concat("!J1");
		int maxDvd = GoogleUtility.getMaxDvd(spreadsheetId, cell, HTTP_TRANSPORT);
		
		String input = "";
		List<String> inputList = getListFromRange(lastDvd+1, maxDvd);
		System.out.println("From "+(lastDvd+1)+" to "+maxDvd);
		try {

			String range = sheetName.concat("!A1:C2000");
			//Read rows from Google Drive
			List<InfoForJson> infosAll = GoogleUtility.readExcel(spreadsheetId, range, HTTP_TRANSPORT);

			List<Film> films = new ArrayList<Film>();

			for(String numDVD : inputList) {
				input = numDVD;

				//Read rows from Excel
				List<InfoForJson> infos = DataUtility.getInfoByNum(infosAll, input);

				//JSON information
				List<Movie> movies = IMDBUtility.getMoviesFromInfos(infos, props);
				for(Movie movie : movies) {
					//Transform to DB entities
					Film film = new Film(new Integer(input), movie);
					films.add(film);
				}

				/*for(Film film : films) {
					System.out.println(film.toInsertString());
					for(FilmRating filmRating : film.getFilmRating()) {
						System.out.println(filmRating.toInsertString());
					}
				}*/
			}
			for(Film film : films) {
				//1 JSON tutti i film
				JSONObject jsonFilm = JsonUtility.getJSONFromFilm(film);
				array.add(jsonFilm);
			}
			JsonUtility.writeFileFromJSONArray(array, fileNameJson, folder);
			System.out.println("Finito!");
		}catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("Errore DVD numero: "+input);
		}finally {
			System.exit(0);
		}
	}

	private static List<String> getListFromRange(int from, int to) {
		List<String> result = new ArrayList<String>();
		int i=from;
		while(i<=to) {
			String num = Integer.toString(i);
			result.add(num);
			i++;
		}
		return result;
	}

}
