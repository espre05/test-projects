package sample.mediaplayer.domain;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URI;

public class Album {

	public Album(String title) {
		this.title = title;
	}
	private String title;
	private String author;
	private URI coverImage;
	private List<Song> songs = new ArrayList<Song>(15);
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String value) {
		this.author = value;
	}
	public URI getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(URI coverImage) {
		this.coverImage = coverImage;
	}
	public List<Song> getSongs() {
		return songs;
	}

	public void addSongs(URI songURI){
		String songTitle  = new File(songURI).getName();
		Song newSong  = new Song(songTitle, songURI);
		songs.add(newSong);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Album [title=").append(title).append(", author=")
				.append(author).append(", coverImage=").append(coverImage)
				.append(", songs=").append(songs).append("]");
		return builder.toString();
	}
	
}
