package com.example.bsepprojekat.util;

import java.util.Base64;

public class Base64Utility {
	// Helper function for encoding bytes to a string
	public static String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	// Helper function for decoding a base64 string to bytes
	public static byte[] decode(String base64Data) {
		return Base64.getDecoder().decode(base64Data);
	}
}
