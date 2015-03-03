/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package sample.mediaplayer.ui;

import java.util.List;

import sample.mediaplayer.domain.Album;
import sample.mediaplayer.domain.Song;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerComponent extends BorderPane implements ChangeListener<Album>{
    ListView<Song> songListView = null; //song list
    Album album;
    MediaPlayer mediaPlayer; // the core player - not UI
    MediaControl mediaControl; // UI controlls for play/pause/timeline/volume/loop
    public MediaPlayerComponent(Album album){
        setId("mediaPlayerComponent");
        setNewAlbum(album);
    }
    
	//Album has changed. -> Song List changes,control links to fist song in album 
    @Override
	public void changed(
			ObservableValue<? extends Album> paramObservableValue,
			Album paramT1, Album newAlbum) {
		this.stop();
		this.setNewAlbum(newAlbum);
		
	}
    
  
    @Override
    protected void layoutChildren() {
    	super.layoutChildren();
    }
    public void stop(){
		if(mediaPlayer!= null)
		mediaPlayer.stop();    	
    }

    //Album has changed. -> Song List changes,control links to fist song in album 
    public void setNewAlbum(Album newAlbum) {
		stop();
		songListView = new ListView<Song>();
		this.album = newAlbum;
		   List<Song> songList = album.getSongs();
	        ObservableList<Song> observableSongTitleList = FXCollections.observableList(songList);

	        songListView.setItems(observableSongTitleList); // new songs updated in songlist view
	        songListView.getSelectionModel().select(0);
	        
	        //Get Media
	        String path = songListView.getSelectionModel().getSelectedItem().getSongURI().toString(); //Default play first song in songlist
	        Media media  = new Media(path);
	        
	        //Create media player
	         mediaPlayer = new javafx.scene.media.MediaPlayer(media);
	        mediaPlayer.setAutoPlay(true);
	        
	        //Create and add media control
	        mediaControl = new MediaControl(mediaPlayer);
	        this.setBottom(mediaControl);

	        songListView.setPrefWidth(200);
	        songListView.setPrefHeight(200);
	        this.setCenter(songListView);
	        songListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {

				@Override
				public void changed(
						ObservableValue<? extends Song> paramObservableValue,
						Song paramT1, Song newSong) {
		        	Media newMedia  = new Media(newSong.getSongURI().toString());
		        	mediaPlayer.stop(); //Song changed - so pause teh cur
	        		mediaPlayer = new javafx.scene.media.MediaPlayer(newMedia); //set the newMedia song in MediaPlayer
					mediaControl = new MediaControl(mediaPlayer); //Re initialize media control
					mediaPlayer.play(); //mediaControl.togglePlayPause(); // start playing
			        setBottom(mediaControl);

				}
			});
	}

    public static void main(String[] args) {
        Application.launch(args);
    }
    
}