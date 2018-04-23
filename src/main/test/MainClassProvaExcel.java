package main.test;

import java.io.IOException;

import utility.ExcelUtility;

public class MainClassProvaExcel {
	
	private static final String filePath = "C:\\Users\\totor\\eclipse-workspace\\Cineteca_ManageDB\\src\\cineteca.xlsx";

	public static void main(String[] args) throws IOException {
		ExcelUtility.readExcel(filePath);
	}
}
