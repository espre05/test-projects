package sample.mediaplayer.domain;

import java.net.URI;

public class Song {
	String title;
	private URI songURI;
	public Song(String title, URI songURI) {
		this.title = title;
		this.songURI = songURI;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public URI getSongURI() {
		return songURI;
	}
	public void setSongURI(URI songURI) {
		this.songURI = songURI;
	}
	@Override
	public String toString() {
		return title;
	}
	
	
	

}
