package main.test;

import java.security.NoSuchAlgorithmException;

import utility.MD5Utility;

public class MainClassProvaMD5 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String input = "SANSAL8386";
		String encrypt = MD5Utility.encrypt(input);
		//String decrypt = MD5Utility.decrypt(input);
		System.out.println(encrypt);
		//System.out.println(decrypt);
	}

}
