package com.example.httpCall.core;

public class ByteUtil {

	//  byte[] byteArray = ByteBuffer.allocate(4).putInt(value).array();
	public static byte[] intToByteArray(int value) {
		byte[] byteArray = new byte[4];
		byteArray[0] = (byte)(value >> 24);
		byteArray[1] = (byte)(value >> 16);
		byteArray[2] = (byte)(value >> 8);
		byteArray[3] = (byte)(value);
		return byteArray;
	}

}
