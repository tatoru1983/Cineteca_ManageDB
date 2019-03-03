package utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import entity.InfoForJson;

public class DataUtility {
	
	public static BigDecimal getCurrentYear() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return BigDecimal.valueOf(year);
	}
	
	public static List<InfoForJson> getInfoByNum(List<InfoForJson> infosAll, String numDvd){
		List<InfoForJson> result = new ArrayList<InfoForJson>();
		for(InfoForJson info: infosAll){
			if(numDvd.equals(info.getIdDvd())){
				result.add(info);
			}
		}
		return result;
	}

}
