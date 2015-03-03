/*
 * Library.fx
 *
 * Created on 21 Jun 09, 9:56:40
 */

package harmonyfx.preferences;
import javafx.io.*;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import harmonyfx.Song;
import harmonyfx.PlaylistView;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class Library {
    var storage:Storage;
    var resource:Resource;

    postinit{
        var storeExist = checkStore();
        if (storeExist){
            // load songs
            PlaylistView.filelist = loadSongs();
        }

        FX.addShutdownAction(function():Void{saveIntoLibray (PlaylistView.filelist)});
    }
    
    public function loadSongs ():String[]{
        var songs:String[];
        /* res is a global variable and is the resource accessible by this application's storage. So
        the inputstream is being opened to res variable to read this application's existing data
         */

        var inp: InputStream = resource.openInputStream();
        //var currentLine: String ;
        //var countoflines: Number;

        try {
            var reader = new BufferedReader(
                        new InputStreamReader(inp));
            var song:String;
            try{
                while ((song = reader.readLine()) != null){
                    insert song into songs;
                }

            }catch(ex:IOException){
                println("error load library: {ex.getMessage()}");
            }

        } finally {
            inp.close();
        }
        return songs;
    }

    public function saveIntoLibray (songs:String[]) {
        var store_exists = checkStore();
        if (store_exists == true)
            loadSongs();

        var outp: java.io.OutputStream = resource.openOutputStream(true); // override old
        try{
        //Store data including newline character
        var newline:String = "\n";
            for (song in songs){
                outp.write(song.getBytes());
                outp.write(newline.getBytes());
            }
        }catch(e:IOException){
            println("save lib exception : {e.getMessage()}");
        }finally{
            outp.close();
        }
    }

    public function checkStore() : Boolean {

        var ret: Boolean = false;
        try {

            /* The following code creates the storage entry for the application
            if it's not there. If the storage entry by the same name already exists
             it just picks up and assigns it to storage variable */

            storage = Storage {
                source: "harmonyfxlibrary"
            };

            resource = storage.resource;
            ret = false;

        } catch (e : IOException ) {
            resource = storage.resource;
            ret = true;
        }
        ret = true;
    }
}
