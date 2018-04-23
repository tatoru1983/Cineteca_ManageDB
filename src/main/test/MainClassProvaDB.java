package main.test;

import java.io.IOException;

import utility.DatabaseUtility;

public class MainClassProvaDB {

	public static void main(String[] args) throws IOException {
		DatabaseUtility.connectJDBCToAWSEC2();
	}

}
