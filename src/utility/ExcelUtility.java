package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.InfoForJson;

public class ExcelUtility {
	
	private static XSSFWorkbook wb;
	
	private static final int DVD_NUM = 0;
	private static final int TITLE_ITA = 1;
	private static final int IMDB_ID = 2;
	private static String FILEPATH;

	@SuppressWarnings("deprecation")
	public static List<InfoForJson> readExcel(String filePath) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		List<InfoForJson> result = new ArrayList<InfoForJson>();
		wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			XSSFCell cellDvdNum = row.getCell(DVD_NUM);
			cellDvdNum.setCellType(Cell.CELL_TYPE_STRING);
			XSSFCell cellTitleIta = row.getCell(TITLE_ITA);
			cellTitleIta.setCellType(Cell.CELL_TYPE_STRING);
			XSSFCell cellImdbId = row.getCell(IMDB_ID);
			cellImdbId.setCellType(Cell.CELL_TYPE_STRING);
			
			InfoForJson info = new InfoForJson();
			info.setIdDvd(cellDvdNum.getStringCellValue());
			info.setTitleIta(cellTitleIta.getStringCellValue());
			info.setIdImdb(cellImdbId.getStringCellValue());
			
			result.add(info);
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static List<InfoForJson> readExcelByDvdNum(String dvdNum, Properties props) throws IOException {
		FILEPATH = props.getProperty("FILEPATH");
		InputStream ExcelFileToRead = new FileInputStream(FILEPATH);
		List<InfoForJson> result = new ArrayList<InfoForJson>();
		wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			
			if(row.getCell(DVD_NUM)==null)
				break;
			
			XSSFCell cellDvdNum = row.getCell(DVD_NUM);
			XSSFCell cellTitleIta = row.getCell(TITLE_ITA);
			cellTitleIta.setCellType(Cell.CELL_TYPE_STRING);
			XSSFCell cellImdbId = row.getCell(IMDB_ID);
			cellImdbId.setCellType(Cell.CELL_TYPE_STRING);
			
			InfoForJson info = new InfoForJson();
			info.setIdDvd(Integer.toString(new Double(cellDvdNum.getNumericCellValue()).intValue()));
			info.setTitleIta(cellTitleIta.getStringCellValue());
			info.setIdImdb(cellImdbId.getStringCellValue());
			if(dvdNum.equals(info.getIdDvd()))
				result.add(info);
		}
		return result;
	}

}
