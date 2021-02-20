package com.example.javaFx.basic;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RectangleTransitionMain extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500, Color.SILVER);
		stage.setTitle("JavaFX Scene Graph Demo");
		stage.setScene(scene);
		stage.show();
		
		
		Rectangle r = new Rectangle(0, 0, 250, 250);
		r.setFill(Color.STEELBLUE);
		root.getChildren().add(r);
		
		TranslateTransition translate= 
				new TranslateTransition(Duration.millis(750));
		translate.setToX(390);
		translate.setToY(190);
		//translate.setToZ(100);
		
		FillTransition fill = new FillTransition(Duration.millis(750));
		fill.setToValue(Color.GOLD);
		
		RotateTransition rotate =
				new RotateTransition(Duration.millis(750));
		rotate.setToAngle(360);
		
		ScaleTransition scale =
				new ScaleTransition(Duration.millis(750));
		scale.setToX(0.2);
		scale.setToY(0.2);
		
		ParallelTransition transition = 
				new ParallelTransition(r, translate, fill, rotate, scale );
		transition.setCycleCount(Timeline.INDEFINITE);
		transition.setAutoReverse(true);
		transition.play();
		
	}	
	
	
	public static void main( String[] args ){
		launch(args);
	}


}
