package com.example.httpCall.header;

public class CustomHeader {

	private String[] headerArray = null;

	private String ip = null;
	private Integer port = null;

	public CustomHeader(final String url) {
		String str = url.substring(url.indexOf("//") + 2, url.length());
		this.ip = str.substring(0, str.indexOf(":"));
		this.port = Integer.valueOf(str.substring(str.indexOf(":") + 1, str.indexOf("/")));

		headerArray = new String[] {
			"GET "+url+" HTTP/1.1\r\n",
			"Host: " + this.ip + ":" + this.port+ "\r\n",
			"Connection: close\r\n",
			"Cache-Control: no-cache\r\n",
//			"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36\r\n",
//			"Postman-Token: 3e7e1fb1-b148-35ef-9f16-87e772593101\r\n",
			"Accept: */*\r\n",
//			"Accept-Encoding: gzip, deflate\r\n",
			"Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\r\n\r\n"
		};
	}

	public String[] getHeaderArray() {
		return headerArray;
	}

	public void setHeaderArray(String[] headerArray) {
		this.headerArray = headerArray;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
