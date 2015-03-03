/*
 * Playlist.fx
 *
 * Created on 01 Jul 09, 9:12:16
 */

package harmonyfx;
import javafx.scene.control.ListView;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.Bloom;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.util.Sequences;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class Playlist extends CustomNode{
    def shuffleButtonImage = Image{ url:"{__DIR__}images/shuffle.png"};
    def unshuffleButtonImage = Image{ url:"{__DIR__}images/sorted.png"};
    def repeatButtonImage = Image{ url:"{__DIR__}images/repeat.png"};
    def unrepeatButtonImage = Image{ url:"{__DIR__}images/unrepeat.png"};
    def showButtonImage = Image{ url:"{__DIR__}images/show.png"};
    def hideButtonImage = Image{ url:"{__DIR__}images/hide.png"};
    def clearButtonImage = Image{ url:"{__DIR__}images/clear.png"};
    def bloom:Bloom = Bloom { threshold: 0.5 }
    public var height = 150;
    public var shuffle:Boolean = false;
    public var repeat:Boolean = false;
    var showed:Boolean;
    
    var playlist:ListView = ListView{
        width:200; height:height;
        layoutX: 0
        layoutY: 45
//        clip: Rectangle {
//                x: 0, y: 0
//                width: 200, height: height
//                arcWidth: 10
//                arcHeight: 10
//        }

        onKeyPressed: function( e: KeyEvent ):Void {
            if (e.code == KeyCode.VK_DELETE){
                // delete selected item from
                // visual now playing list
                var index = playlist.selectedIndex;
                delete playlist.items[index];

                // also delete from logical playlist
                delete PlaylistView.nowPlayingList[index];
            }
        }
    }

    var shuffleButton:ImageView = ImageView {
            image: shuffleButtonImage;
            x:50
            //effect: bind if (shuffle) bloom else null;

            onMouseEntered: function( e: MouseEvent ):Void {
                shuffleButton.effect = bloom;
            }

            onMouseExited: function( e: MouseEvent ):Void {
                shuffleButton.effect = null;
            }

            onMouseClicked: function( e: MouseEvent ):Void {
                doShuffle();
                //shuffle = not shuffle;
            }
    }

    var repeatButton:ImageView = ImageView {
            image: repeatButtonImage;
            x:100
            effect: bind if (repeat) bloom else null;

            onMouseClicked: function( e: MouseEvent ):Void {
                repeat = not repeat;
            }
    }

    var showHideButton:ImageView = ImageView {
            image: bind if (showed) hideButtonImage else showButtonImage;
            x:150
            onMouseEntered: function( e: MouseEvent ):Void {
                showHideButton.effect = bloom;
            }

            onMouseExited: function( e: MouseEvent ):Void {
                showHideButton.effect = null;
            }

            onMouseClicked: function( e: MouseEvent ):Void {
                move();
                showed = not showed;
            }
    }

    var clearButton:ImageView = ImageView {
            image: clearButtonImage;
            x:0
            onMouseEntered: function( e: MouseEvent ):Void {
                clearButton.effect = bloom;
            }

            onMouseExited: function( e: MouseEvent ):Void {
                clearButton.effect = null;
            }

            onMouseClicked: function( e: MouseEvent ):Void {
                // delete playlist visual
                delete playlist.items;
                //playlist.layout();
                // delete playlist logical
                delete PlaylistView.nowPlayingList;
            }
    }

    public function addSong(song:String) {
        insert song into playlist.items;
    }

    public function move(){
        var to = layoutY;
        if (showed) to += height else to -= height;

        var anim = Timeline {
                keyFrames : [
                    at(0s){layoutY => layoutY;},
                    at(1s){layoutY => to tween Interpolator.LINEAR;}]
        };

        //anim.stop();
        anim.play();
    }

    public function doShuffle(){
        var newlist = Sequences.shuffle(PlaylistView.nowPlayingList) as harmonyfx.Song[];
        // update
        delete playlist.items;
        delete PlaylistView.nowPlayingList;
        insert newlist into PlaylistView.nowPlayingList;
    }

    override function create(){
        return Group{
            content: [shuffleButton, repeatButton, showHideButton, clearButton,
                        playlist]
        }
    }

}
