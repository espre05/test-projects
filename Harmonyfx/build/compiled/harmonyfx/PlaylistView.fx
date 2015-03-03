/*
 * PlaylistView.fx
 *
 * Created on 15 Jun 09, 16:46:51
 */

package harmonyfx;
import javafx.scene.control.ListView;
//import javafx.scene.image.Image;
//import harmonyfx.mp3tag.ID3Reader;
import com.sun.javafx.scene.control.caspian.ListViewSkin;
import javafx.scene.control.TextBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.util.Math;
import javafx.util.Sequences;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public def searchbox:TextBox = TextBox {
        layoutX: 570
        layoutY: 45
        text: "";
        promptText: "Search Album"
        columns: 12
        selectOnFocus: true
        onKeyPressed: function( e: KeyEvent ):Void {
            searchbox.commit();
            if (searchbox.text != ""){
                filterAlbum(searchbox.text.toLowerCase().trim());
            }else {
                filterRemovedAlbum(searchbox.text.trim());
            }
            shelf.shift(-(sizeof Albums)*Math.random()/2);
        }
}

public var Title:String;
public var Albums:Album[] on replace{
};
public var removedAlbums:Album[];

//public var progressbar:ProgressBar = ProgressBar{
//    progress: -1;
//    layoutY: 450;
//    //layoutX: 200;
//    visible: true
//};

public var shelf:DisplayShelf = DisplayShelf {
    spacing: 25
    scaleSmall: 0.7
    leftOffset: -110
    rightOffset: 110
    perspective: true
    focusTraversable: true
    layoutY: 175
    layoutX: 225
    content: bind Albums;
}

public var playlist = Playlist{
    blocksMouse: true
    layoutY: 540
    layoutX: 250
};

public var currentPlayingSongIndex:Integer = 0;
public var nowPlayingList:Song[] on replace oldval[lo..hi]=newsongs{
    for (i in newsongs) playlist.addSong(i.title);
};

public var filelist:String[] on replace {
    getAlbum();
    def albums = Sequences.sort(Albums);
    Albums = albums as Album[];
    shelf.shift(-(sizeof Albums)/2); // shift to center
};

public function getAlbum(){
    for (file in filelist){
        var song = Song{url:file}
        
        if (sizeof Albums > 0){
            var idx = getIndexByID(song.album);
            //println("album: {song.album} idx: {idx}");
            if ( idx != -1){
                insert song into Albums[idx].songs;
            }else{
                // create new album
                var albm = Album{
                    angle: 45
                    albumtitle : song.album;
                    position: bind sizeof shelf.content
                    shelf: bind shelf
                    //image: song.albumArt;
                }
                insert song into albm.songs;
                insert albm into Albums;
            }
        }else{ // just add new albums, masih salah sih
            // create new album
            var albm = Album{
                angle: 45
                albumtitle : song.album;
                position:bind sizeof shelf.content
                shelf: bind shelf
                //image: song.albumArt;
            }
            insert song into albm.songs;
            insert albm into Albums;
        }
    }

}

public function filterAlbum (title:String) {
    filterRemovedAlbum(title);
    for (album in Albums){
        if (album.albumtitle.toLowerCase().indexOf(title) == -1){
            insert album into removedAlbums;
            delete album from Albums;
        }
    }
    // sort
    def albums = Sequences.sort(Albums);
    Albums = albums as Album[];
}

public function filterRemovedAlbum (title:String) {
    for (album in removedAlbums){
        if (album.albumtitle.toLowerCase().indexOf(title) > -1){
            insert album into Albums;
            delete album from removedAlbums;
        }
    }
}

function getIndexByID(id:String):Integer{
    for (i in [0..sizeof Albums-1]){
        if (Albums[i].albumtitle == id)
            return i;
    }
    return -1;
}