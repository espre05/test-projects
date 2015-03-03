/*
 * AlbumArt.fx
 *
 * Created on 23 Jun 09, 11:53:30
 */

package harmonyfx.RESTful;
import javafx.scene.image.Image;
import javafx.io.http.*;
import javafx.data.xml.QName;
import java.lang.Exception;
import javafx.data.pull.PullParser;
//import java.net.URI;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class AlbumArt {
    public var artImage:Image;

    public function getImage (artist:String,album:String) {
        def artst = artist.replaceAll(" ", "%20");
        def albm = album.replaceAll(" ", "%20");
        def url= "http://ws.audioscrobbler.com/2.0/?method=album.search&album={albm}&api_key=1d819e1201e75b96724a818c85e7f730";

        var p:PullParser;
        var h:HttpRequest;
        h = HttpRequest{
                location: url
                onException: function(exception: Exception) {
                    print("exception: {exception.getMessage()}");
                }

                onInput: function(input) {
                    if (album != ""){
                        try{
                            println("album");
                            var attval : String;
                            p = PullParser {
                                documentType: PullParser.XML
                                input: input
                                onEvent: function(event) {
                                    if (event.type == PullParser.START_ELEMENT){
                                        if (event.qname.name == "image"){
                                              var qAttr : QName = QName {name : "size"};
                                              attval = event.getAttributeValue(qAttr);
                                        }
                                    }else
                                    if (event.type == PullParser.END_ELEMENT){
                                        if (event.qname.name == "image"){
                                              //var qAttr : QName = QName {name : "size"};
                                              //var attval : String = event.getAttributeValue(qAttr);
                                              //println("size: {attval}");
                                              //println("text: {event.text}");
                                              if (attval == "small"){
                                                  //println("small: {event.text}");
                                              }else if (attval == "medium"){
                                                  //println("medium: {event.text}");
                                              }else if (attval == "large"){
                                                  println("large: {event.text}");
                                                  if (event.text != "" and artImage.url == "")
                                                  artImage = Image{
                                                        url: event.text;
                                                        width: 200
                                                        height: 200
                                                        preserveRatio: true
    //                                                  placeholder: Image {
    //                                                        url: "{__DIR__}AlbumArt.png"
    //                                                  }
                                                  }
                                              }else if (attval == "extralarge" and artImage.url == ""){
                                                  println("extralarge: {event.text}");
                                                  if (event.text != "")
                                                  artImage = Image{
                                                      url: event.text;
    //                                                  placeholder: Image {
    //                                                        url: "{__DIR__}AlbumArt.png"
    //                                                  }
                                                  }
                                              }
                                        }
                                    }
                                }
                            };

                            p.parse();
                            p.input.close();
                        }catch(exc:Exception){
                            println("msg: {exc.getMessage()}");
                        }finally{
                            p.input.close();
                        }
                    }
                }
                
                onDone: function() {
                    println("DONE!\nartImageurl: {artImage.url}");
                }
        };

        h.start();
    }
}

//function run () {
//    AlbumArt{}.getImage("Linkin%20Park", "Meteora");
//}