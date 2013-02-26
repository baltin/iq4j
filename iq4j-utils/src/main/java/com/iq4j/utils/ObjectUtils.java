package com.iq4j.utils;

public class ObjectUtils {

	public static <T> boolean equals(T o1, T o2) {
		if(o1 == null) {
			return o2 == null;
		}
		else {
			return o1.equals(o2);
		}
	}
	
}
