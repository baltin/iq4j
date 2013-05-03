package com.iq4j.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectUtils {

	public static <T> boolean equals(T o1, T o2) {
		if(o1 == null) {
			return o2 == null;
		}
		else {
			return o1.equals(o2);
		}
	}
	
	/**
	 * Returns a copy of the object, or null if the object cannot be serialized.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T copy(T original) {
		Object obj = null;
		
		try {
			// Write the object out to a byte array
			FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(fbos);
			out.writeObject(original);
			out.flush();
			out.close();

			// Retrieve an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
			obj = in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return (T) obj;
	}

	
}
