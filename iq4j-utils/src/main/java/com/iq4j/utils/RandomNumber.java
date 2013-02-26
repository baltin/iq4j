package com.iq4j.utils;

public class RandomNumber {

	public static int generateInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public static int generateInt(int max) {
		return generateInt(0, max);
	}
	
	public static long generateLong(long min, long max) {
		return min + (long)(Math.random() * ((max - min) + 1));
	}
	
	public static long generateLong(long max) {
		return generateLong(0, max);
	}
	
}
