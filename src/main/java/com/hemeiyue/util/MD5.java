package com.hemeiyue.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static void main(String[] args) {
		System.out.println(MD5encoder("12"));
		System.out.println(MD5encoder("12"));
	}
	

	public static String MD5encoder(String code) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(code.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new BigInteger(1, md.digest()).toString(16); 
	}
}
