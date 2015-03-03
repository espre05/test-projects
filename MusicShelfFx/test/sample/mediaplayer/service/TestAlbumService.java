package sample.mediaplayer.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sample.mediaplayer.domain.Album;

public class TestAlbumService {
	
	AlbumService albumservice;
	@Before
	public void setup(){
		albumservice = new AlbumService(AlbumService.DEFAULT_ALBUM_ROOT);
	}
	
	@Test
	public void testGetAlbumList() throws Exception{
		List<Album> album  = albumservice.getAlbumList();
		Assert.assertTrue("Should contain 9 albums", 9 == album.size());
		
	}

}
