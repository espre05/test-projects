/*
 * Mp3TagReader.fx
 *
 * Created on 18 Jun 09, 7:57:56
 */

package harmonyfx.mp3tag;
import java.io.File;
import org.cmc.music.myid3.*;
import org.cmc.music.metadata.*;
import java.util.Vector;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class ID3Reader{
    var file:File;
    def myid3 = new MyID3();
    var metadataset:MusicMetadataSet; // read metadata
    var metadata:IMusicMetadata;
    public var fileName:String on replace{
        if (fileName != null and fileName != ""){
            file = new File(fileName);
            metadataset = myid3.read(file);
            metadata = metadataset.getSimplified();
        }
    };

    public function getAlbum(){ return metadata.getAlbum(); }
    public function getArtist(){    return metadata.getArtist();    }
    public function getBand () { return metadata.getBand(); }
    public function getComments () {  }
    public function getComposer () {  }
    public function getConductor () {  }
    public function getDiscNumber () {  }

    public function getDurationSeconds () { 
        return metadata.getDurationSeconds();
    }

    public function getEncodedBy () {  }
    public function getEncoderSettings () {  }
    public function getEngineer () {  }
    public function getFeaturingList () {  }

    public function getFileType () {
        return metadata.getFileType();
    }

    public function getGenreID () {  }

    public function getGenreName () {
        return metadata.getGenreName();
    }

    public function getIsAcapella () {  }
    public function getIsCompilation () {  }
    public function getIsSoundtrack () {  }
    public function getLyricist () {  }

    public function getMediaType () {
        return metadata.getMediaType();
    }

    public function getMetadataName () {  }

    public function getMixArtist () {
        return metadata.getMixArtist();
    }

    public function getPartOfSetCount () {  }
    public function getPartOfSetIndex () {  }

    public function getPictures ():Vector {
        return metadata.getPictures();
    }

    public function getProducer () { 
        return metadata.getProducer();
    }

    public function getPublisher() {  
        return metadata.getPublisher();
    }

    public function getRawValues () {  }

    public function getSongTitle () {
        return metadata.getSongTitle();
    }

    public function getTrackCount () {  }
    public function getTrackNumberDescription(){}
    public function getTrackNumberFormatted () {  }

    public function getTrackNumberNumeric () {
        return metadata.getTrackNumberNumeric();
    }

    public function getUnknownUserTextValues () {  }

    public function getYear () {  
        return metadata.getYear();
    }
}

public function run () {
    var id = ID3Reader{fileName:"D:/Multimedia/Audio/mix/Kokoro_Instrumental.mp3"}
    println("title: {id.getSongTitle()}");
    id.fileName = "F:/13 B.T.mp3";
    println("title: {id.getSongTitle()}");
}