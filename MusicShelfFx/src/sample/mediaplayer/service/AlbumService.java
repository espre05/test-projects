package sample.mediaplayer.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sample.mediaplayer.domain.Album;

public class AlbumService {

	private static String ALBUM_COVER_FNAME = "Cover.jpg";
	public static final String DEFAULT_ALBUM_ROOT = "C:/apps/workspace-eclipse_new/MusicShelfFx/AlbumRoot";
	private static final String AUDIO_FILE_EXT_PATTERN = "*.{mp3,avi}";
	private String albumRootPath;

	public AlbumService(String albumRootPath) {
		this.albumRootPath = albumRootPath;
	}
	public int findAlbumIndex(String barCodeNum){
		int returnIndex = 0;
		List<Album> list = null;
		try {
			list = getAlbumList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getTitle().contains(barCodeNum)){
				returnIndex = i; break;
			}
		}
		return returnIndex;
	}

	public List<Album> getAlbumList() throws Exception {
		List<Path> albumPathList = getFoldersFromPath(albumRootPath);
		List<Album> albumList = new ArrayList<Album>(albumPathList.size());
		for (Path path : albumPathList) {
			try{
			Album album = getAlbum(path);
			albumList.add(album);
			}catch(MediaException mex){
				System.out.println(mex.getMessage());
			}
		}

		return albumList;
	}

	private List<Path> getFoldersFromPath(String albumRootPath)
			throws Exception {
		File albumRootFolder = new File(albumRootPath); // Check if path exists
		if (false == albumRootFolder.exists()) {
			throw new Exception(
					"Folder does not exist :"
							+ albumRootPath
							+ "Hint: Check if album root folder exists AND pointing to correct location");
		}

		List<Path> albumPathList = new LinkedList<Path>();

		DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
			@Override
			public boolean accept(Path path) throws IOException {
				return (Files.isDirectory(path));
			}
		};

		Path dir = albumRootFolder.toPath();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,
				filter)) {
			for (Path entry : stream) {
				albumPathList.add(entry);
				System.out.println(entry.getFileName());
			}
		} catch (IOException x) {
			System.err.println(x);
		}
		return albumPathList;
	}

	private Album getAlbum(Path path) throws Exception {

		File coverImgFile = new File(path + "/" + ALBUM_COVER_FNAME);
		Album album = null;
		if (false == coverImgFile.exists()) {
			throw new MediaException("Album does not exist for folder: "
					+ path.toAbsolutePath());
		}
		// Add Cover
		String title = path.getFileName().toString();
		album = new Album(title);
		album.setAuthor(title);
		album.setCoverImage(coverImgFile.toURI());

		// Add songs
		addSongsToAlbum(path, album);
		return album;
	}

	private static void addSongsToAlbum(Path dir, Album album) throws Exception {
		try (DirectoryStream<Path> audioFiles = Files.newDirectoryStream(dir,
				AUDIO_FILE_EXT_PATTERN)) {
			for (Path entry : audioFiles) {
				// System.out.println(entry.toUri());
				album.addSongs(entry.toUri());
			}
		}
		if (album.getSongs().size() == 0) {
			throw new MediaException("This album does not have songs :"
					+ album.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		AlbumService service = new AlbumService(AlbumService.DEFAULT_ALBUM_ROOT);
		List<Album> album = service.getAlbumList();
		System.out.println(album.toString());
	}

}
