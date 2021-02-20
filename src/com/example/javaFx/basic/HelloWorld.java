package com.example.javaFx.basic;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloWorld extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	Label lb_text;
	Button btn_click01;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//effect
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0);
		ds.setOffsetX(5.0);
		ds.setColor(Color.GRAY);
		
		Reflection reflection = new Reflection();
		reflection.setFraction(0.5);
		reflection.setTopOffset(-20);
		
		
		
		lb_text = new Label("Hello world");
		lb_text.setEffect(reflection);
		
		btn_click01 = new Button("Click this");		
		btn_click01.setEffect(ds);
		
		
		lb_text.getStyleClass().add("my_custom_label");
		
		btn_click01.setOnAction( new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				btn_click01.setText("!!!");
			}
		});
				
		
		
		VBox root = new VBox();
		
		root.getChildren().add(lb_text);
		root.getChildren().add(btn_click01);
		
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);		
		
		scene.getStylesheets().add("basic_style.css");
		
		primaryStage.show();
		
	}
}
