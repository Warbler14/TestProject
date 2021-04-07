package com.example.httpCall.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.example.httpCall.header.CustomHeader;

public class HttpUtil {

	public String socketConnect(final String url) {

		CustomHeader header = new CustomHeader(url);

		Socket socket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;

		String result = null;

		try {
			socket = new Socket(header.getIp(), header.getPort());

//			System.out.println("Socket connected : " +socket.isConnected());

			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			String[] headerArray = header.getHeaderArray();
			for (int idx = 0; idx < headerArray.length; idx++) {
				dos.writeBytes(headerArray[idx]);
			}
			dos.flush();

			// read the response
			StringBuilder sb = new StringBuilder(1024);

//			int i = 0;
//			while (i != -1) {
//				i = dis.read();
//				sb.append((char) i );
//			}

			byte [] buffer = new byte[100];
			while(true) {
				int length = dis.read(buffer);
				System.out.println(length);
				if( length<0 ) {
					break;
				}
				byte [] buffer2 = new byte[length];
				System.arraycopy(buffer, 0, buffer2, 0, length);
				sb.append(new String(buffer2));
			}

			result = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				if(dis != null) {
					dis.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}

			try {
				if(dos != null) {
					dos.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}


			try {
				if(socket != null) {
					socket.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}
}
