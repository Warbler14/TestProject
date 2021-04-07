package com.example.httpCall;

import com.example.httpCall.core.HttpUtil;

public class HttpCallMain {
	public static void main(String[] args) {
		HttpUtil httpUtil = new HttpUtil();

		//오류발생 테스트 필요
		String result = httpUtil.socketConnect("https://postman-echo.com/get?foo1=bar1&foo2=bar2");

		System.out.println( result );
	}
}
