package com.example.javaFx.basic;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * first javaFX program 
 * 2015.03.28
 * @author i n s u n g
 *
 */


public class AppMain01 extends Application {

	protected static int threadNum = 0;
	
	public AppMain01(){
		System.out.println( Thread.currentThread().getName() + ": AppMain() 호출 " + threadNum++); // <-- JavaFX Application Thread:
		
		
	}
	
	@Override
	public void init() throws Exception {
		//init() 메소드 javaFX-Launcher 스레드에서 실행된다.
		System.out.println( Thread.currentThread().getName() + ": init() 호출 " + threadNum++); // <-- JavaFX-Launcher:
		
	}
	
	
	
	//JavaFx Application Thread : 메인 클래스 기본 생성자, start() 및 stop() 실행 
	//javaFX 애플리케이션에서 윈도우( Stage ) 를 비롯한 UI 생성 및 수정 작업, 이벤트 처리 등은 모두 javaFX Application Thread가 관장
	//다른 스레드가  UI 에 접근하면 예외가 발생한다.
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.out.println( Thread.currentThread().getName() + ": start() 호출 "  + threadNum++); // <-- javaFX Application Thread
		
		//draw UI
		VBox root = new VBox();									//Parent 하위 객체인 VBox 생
		root.setPrefWidth(350);									//VBox의 폭 설정
		root.setPrefHeight(150);								//VBox의 높이 설정
		root.setAlignment(Pos.CENTER);  						//컨트롤을 중앙으로 정렬
		root.setSpacing(20); 									//컨트롤의 수직 간격
		
		Label label = new Label();								//Label 컨트롤 생성
		label.setText( "Hello , javaFX" );						//text 속성 설정
		label.setFont(  new Font(50) );							//font 속성 설정
		
		Button button = new Button();							//Button 컨트롤 생성
		button.setText("확인");									//text 속성 설정
		button.setOnAction(event->Platform.exit()); 			//클릭 이벤트 처리
		
		root.getChildren().add(label); 							//VBox의 자식으로 Label 컨트롤 추가
		root.getChildren().add(button);							//VBox의 자식으로 Button 컨트롤 추가
		
		Scene scene = new Scene(root);   				 		//VBox를 루트 컨테이너로 해서 Scene 생성
		
		primaryStage.setTitle("AppMain") ; 						//윈도우 제목 설정
		primaryStage.setScene(scene); 							//윈도우 장명 설정
	
		
		//show window
		primaryStage.show();	
	}
	
	
	
	
	@Override
	public void stop() throws Exception {
		System.out.println( Thread.currentThread().getName() + ": stop() 호출 "  + threadNum++);// <-- javaFX Application Thread
	}
	
	public static void main(String [] args){
		
		System.out.println( Thread.currentThread().getName() + ": main() 호출 " + threadNum++);
		launch(args);
	}

}
