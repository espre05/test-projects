/*
 * Song.fx
 *
 * Created on 18 Jun 09, 12:57:48
 */

package harmonyfx;
import harmonyfx.mp3tag.ID3Reader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaError;
import javafx.scene.image.Image;

/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class Song{
    public var album:String;
    public var title:String;
    public var artist:String;
    //public var duration:Duration = bind media.duration;
    public var albumArt:Image;

    public var url:String on replace{
        var id3 = ID3Reader{};
        id3.fileName = url.replaceAll("file:/", "");

        def albm = id3.getAlbum();
        album = if (albm != null ) albm else "";

        def ttle = id3.getSongTitle();
        title = if (ttle != "") ttle else{
            url.substring(url.lastIndexOf("/")+1).replaceAll(".mp3","");
        }
        artist = id3.getArtist();
        //def albmart:Image = id3.getPictures().firstElement() as Image;
        //if (albmart != null){
            // try to get it from the cloud
        //    albumArt = albmart;
        //}else{


    };

    var media:Media = null;
    public function getMedia():Media {
        if (media == null) {
            println("Loading song : {url}");
            media = Media {
                source: "{url.replaceAll(" ", "%20")}"
                onError: function(e:MediaError) {
                    println("got a media error {e}");
                }
            }
        }
        return media;
    }

    public function closeMedia():Void {
        if (media != null) {
            media.source = null;
            media = null;
        }
    }
}
