package com.example.robo;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.util.Util;

public class RobotMain {

	final static String SCS_STATUS_URL = "";

	public static void main(String[] args) {
		
		Runnable runnable = new Runnable() {

			public void run() {
				// task to run goes here
				new RobotMain().workSet();
				//System.out.println("Hello");
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.HOURS);
		
		/*
		RoboCronTrigger trigger = new RoboCronTrigger();
		trigger.start();
		*/
	}

	private void workSet() {
		int hanHour[] = { KeyEvent.VK_T, KeyEvent.VK_L };

		int hanMan[] = { KeyEvent.VK_A, KeyEvent.VK_U, KeyEvent.VK_D };

		// int keyHelloInput[] = { KeyEvent.VK_H, KeyEvent.VK_E, KeyEvent.VK_L,
		// KeyEvent.VK_L, KeyEvent.VK_O };

		try {
			Util util = new Util();

			Robot robot = new Robot();

			// 딜레이
			for (int count = 1, end = 5; count <= end; count++) {
				System.out.println("count : " + count);
				robot.delay(1000);
			}

			SimpleDateFormat sdFormat = new SimpleDateFormat("HH");
			Date nowDate = new Date();
			String houres = sdFormat.format(nowDate);

			System.out.println("hour : " + houres);

			if (houres != null && !houres.isEmpty()) {

				numberKeyInput(robot, houres);

			}

			// '시' 글자 입력
			for (int i = 0; i < hanHour.length; i++) {
				robot.keyPress(hanHour[i]);
				robot.keyRelease(hanHour[i]);
				robot.delay(200);
			}

			// 공백 입력
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);

			// StringBuffer buffer = util.sendPost(SCS_STATUS_URL, new
			// HashMap<String, String>());

			// System.out.println( buffer );

			try {
				// JSONObject json = new JSONObject( buffer.toString() );
				/*
				 * Iterator itr = json.keys(); while( itr.hasNext() ){ String
				 * key = (String)itr.next();
				 * 
				 * System.out.println( key ); }
				 */

				// String totalInfo = json.getString("totalInfo");

				String totalInfo = "useRate(6%), use(51), free(749)";

				System.out.println(totalInfo);

				System.out.println(totalInfo.indexOf("use(") + 4);

				String data = totalInfo.substring(totalInfo.indexOf("use(") + 4, totalInfo.length());

				System.out.println(data);

				data = data.substring(0, data.indexOf("),"));

				System.out.println("use count : " + data);

				// 접속량 출력
				if (data != null && !data.isEmpty()) {

					numberKeyInput(robot, data);

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// '명' 글자 입력
			for (int i = 0; i < hanMan.length; i++) {
				robot.keyPress(hanMan[i]);
				robot.keyRelease(hanMan[i]);
				robot.delay(200);
			}

			// 엔터 입력
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			// notepad 프로그램 활성화
			// Runtime.getRuntime().exec("notepad");

			/*
			 * // Hello글자 입력 for (int i = 0; i < keyHelloInput.length; i++) {
			 * robot.keyPress(keyHelloInput[i]);
			 * robot.keyRelease(keyHelloInput[i]); // 현재 해당 쓰레드를 200ms 동안
			 * sleep시킨다. robot.delay(200); }
			 */

			/*
			 * // 메모장에 입력한 글자를 모두 선택한다. robot.keyPress(KeyEvent.VK_CONTROL);
			 * robot.keyPress(KeyEvent.VK_A);
			 * robot.keyRelease(KeyEvent.VK_CONTROL);
			 * robot.keyRelease(KeyEvent.VK_A);
			 */

			/*
			 * // 현재 해당 쓰레드를 500ms 동안 sleep시킨다. robot.delay(500); // 마우스 오른쪽 버튼
			 * 클릭 robot.mousePress(InputEvent.BUTTON3_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON3_MASK);
			 */

		} catch (AWTException ae) {
			ae.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void numberKeyInput(final Robot robot, final String numString) {

		for (int idx = 0, end = numString.length(); idx < end; idx++) {

			String strNum = String.valueOf(numString.charAt(idx));
			int eventKey = 0;

			switch (strNum) {
			case "0":
				eventKey = KeyEvent.VK_0;
				break;
			case "1":
				eventKey = KeyEvent.VK_1;
				break;
			case "2":
				eventKey = KeyEvent.VK_2;
				break;
			case "3":
				eventKey = KeyEvent.VK_3;
				break;
			case "4":
				eventKey = KeyEvent.VK_4;
				break;
			case "5":
				eventKey = KeyEvent.VK_5;
				break;
			case "6":
				eventKey = KeyEvent.VK_6;
				break;
			case "7":
				eventKey = KeyEvent.VK_7;
				break;
			case "8":
				eventKey = KeyEvent.VK_8;
				break;
			case "9":
				eventKey = KeyEvent.VK_9;
				break;
			}

			robot.keyPress(eventKey);
			robot.keyRelease(eventKey);
			robot.delay(200);

		} // end for
	}

}
