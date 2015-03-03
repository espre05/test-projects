package sample.mediaplayer.ui;

/**
 * Copyright (c) 2008, 2012 Premkumar N  and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.mediaplayer.domain.Album;
import sample.mediaplayer.service.AlbumService;
import sample.mediaplayer.ui.MediaPlayerComponent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * A display shelf of images using the PerspectiveTransform effect.
 *
 */
public class CLCMPlayer extends Application {
    private static final double WIDTH = 700, HEIGHT = 300;
    DisplayShelf displayShelf = null;
    MediaPlayerComponent mplayerComponent = null;
    public enum BarcodeReadState{BEGIN,END,UNDEF};
    BarcodeReadState bcReadState = BarcodeReadState.UNDEF;
    StringBuilder barcode = new StringBuilder(30);
    Stopwatch timer = new Stopwatch();
    AlbumService albumService  = null;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    
    private synchronized void handleBarCodeScan(KeyEvent keyEvent){
    	Character ch = new Character(keyEvent.getCharacter().charAt(0));

        if(ch == Character.LINE_SEPARATOR){	
    	System.out.println("Enter pressed "+ keyEvent.getCode());
        }
        if('~' == ch.charValue() && bcReadState == BarcodeReadState.UNDEF){
        	System.out.println("---------- Scan begins"+ ch);
        	bcReadState = BarcodeReadState.BEGIN;
        	executorService.execute((new Runnable() {
        	    public void run() {
        	    	try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        	        System.out.println("Barcode after 1 sec: " + barcode.toString());
        	        bcReadState = BarcodeReadState.END;
        	        bcReadState = BarcodeReadState.UNDEF;
        	        final int newAlbumIndex = albumService.findAlbumIndex(barcode.toString());
        	        System.out.println("newAlbumIndex: " + newAlbumIndex);
		        	        Platform.runLater(new Runnable(){
		        	        	 public void run() {
		        	        		 displayShelf.changeFocusToAlbum(newAlbumIndex);
		        	        	 }
		        	        });
        	        barcode.delete(0, barcode.length());
        	    }
        	}));
        }
        
        if(Character.isDigit(ch) && bcReadState == BarcodeReadState.BEGIN){
        	barcode.append(ch);
        	System.out.printf("digit pressed %s: barcode = %s ",ch, barcode.toString());
        }

    }

    private void init(Stage primaryStage) {
    	primaryStage.setTitle("CLC Media Player");
    	//Pane root = new Pane();
    	BorderPane rootBorderPane = new BorderPane();
        primaryStage.setResizable(true);
        Scene sceneWithPane = new Scene(rootBorderPane);
        primaryStage.setScene(sceneWithPane);
        
        
        final EventHandler<KeyEvent> keyEventHandler =
                new EventHandler<KeyEvent>() {
                    public void handle(final KeyEvent keyEvent) {
                    	handleBarCodeScan(keyEvent);
                    };
        		};
                sceneWithPane.setOnKeyTyped(keyEventHandler);

        
        // Get album detais
        albumService  = new AlbumService(AlbumService.DEFAULT_ALBUM_ROOT);
        List<Album> albumList = null;
		try {
			albumList = albumService.getAlbumList();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
         // Add Album displayshelf
        
		try {
			displayShelf = new DisplayShelf(albumService);
		} catch (Exception e) {
			e.printStackTrace();
		}
        displayShelf.setPrefSize(WIDTH, HEIGHT);
        rootBorderPane.setCenter(displayShelf);
        sceneWithPane.getStylesheets().add(this.getClass().getResource("mediaplayer.css").toExternalForm());

        //Add media player component
        mplayerComponent = new MediaPlayerComponent(albumList.get(0)); // with first album
        displayShelf.addAlbumChangeListener(mplayerComponent);
        rootBorderPane.setBottom(mplayerComponent);
    }


    public double getSampleWidth() { return 495; }

    public double getSampleHeight() { return 300; }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}