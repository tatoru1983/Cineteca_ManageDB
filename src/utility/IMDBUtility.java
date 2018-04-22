package utility;

import java.io.IOException;
import java.util.Properties;

public class IMDBUtility {
	
	private static String URL = "";
	private static String CHARSET = "";
	private static String APIKEY = "";
	private static Properties props;
	
	static {
		try{
			props = PropertiesUtility.getPropValues();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		URL = props.getProperty("URL");
		CHARSET = props.getProperty("CHARSET");
		APIKEY = props.getProperty("APIKEY");
	};
	
	public static String getUrl(String id) {
		return URL.concat("/?i=").concat(id).concat("&apikey=").concat(APIKEY);
	}

}
