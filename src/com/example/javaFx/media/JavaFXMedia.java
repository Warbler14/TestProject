package com.example.javaFx.media;
import java.io.File;

import com.example.util.PathUtil;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class JavaFXMedia extends Application{
		
	public static void main(String[] args){
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Media       – A media resource, containing information about the media, such as 
		//              its source, resolution, and metadata
		//MediaPlayer – The key component providing the controls for playing media
		//MediaView   – A Node object to support animation, translucency, and effects
		
		//Create and set the Scene.
		
		
		Scene scene = new Scene( new Group(), 700, 500);
		primaryStage.setTitle("JavaFX media play");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		String path = PathUtil.getAbsolutePath(this) + "/data/test01.mp4";
		
		Media media = new Media(new File(path).toURI().toString());
		
		//Create the player and set to play automatically.
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(Timeline.INDEFINITE);
		
		//Create the view and add it to the Scene.
		MediaView mediaView = new MediaView(mediaPlayer);
		((Group) scene.getRoot() ).getChildren().add(mediaView);
		
	}
	
}
