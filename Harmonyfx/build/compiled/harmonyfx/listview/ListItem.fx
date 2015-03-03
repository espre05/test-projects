/* 

 */
package harmonyfx.listview;

import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextOrigin;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;
import harmonyfx.PlaylistView;
import harmonyfx.Song;
/**
 * View to display Item in Status List
 */

public def height = 30.0;

public class ListItem extends CustomNode {
    
    public var listView : ListView;
    public var song:Song;
    public var selected = false;
    
    var plusButton :ImageView = ImageView {
        layoutX: bind listView.itemWidth - 45
        layoutY: 3
        image:  Image { url: "{__DIR__}images/plus.png"; }
        opacity: 0.7
    };

    var musicImg = ImageView {
        image: Image { url: "{__DIR__}images/music.png"; }
        x: 3
        y: 5
        //preserveRatio: true
    };

    var title = Text {
        x: 55
        y: 6
        font: Font { name: "dialog" size: 13 }
        fill: Color.ORANGE
        content: song.title;
        wrappingWidth: bind listView.itemWidth - 80
        textOrigin: TextOrigin.TOP
    };
    
    var bgRect = Rectangle {
        x: 0
        width: bind listView.itemWidth - 10
        height: height - 3
        fill: Color.WHITE
        stroke: Color.BLUE
        strokeWidth: 1
        opacity: 0.2
        arcWidth: 7
        arcHeight: 7
    }

    var topLine = Line {
        startX: 0
        startY: bind (height - 1)
        endX: bind listView.itemWidth - 10
        endY: bind (height - 1)
        stroke: Color.GRAY
    };

    var bottomLine = Line {
        startX: 0
        startY: bind height
        endX: bind listView.itemWidth - 10
        endY: bind height
        stroke: Color.WHITE
    };
    
    override function create():Node {
        Group {
            content: [ bgRect, musicImg, title, plusButton, topLine, bottomLine]
        };
    }

//    override var onMouseEntered = function(e:MouseEvent){
//        bgRect.effect = Glow {
//                level: 1
//            }
//    }
//    override var onMouseExited = function(e:MouseEvent){
//        bgRect.effect = null;
//    }
    override var onMouseMoved = function(e : MouseEvent) {
        if (plusButton.boundsInParent.contains(e.x+50,e.y)){
            plusButton.opacity = 1;
            this.cursor = Cursor.HAND
        }else {
            plusButton.opacity = .5;
            this.cursor = Cursor.DEFAULT;
        }
    }

    override var onMousePressed = function(e : MouseEvent) {
        if (plusButton.boundsInParent.contains(e.x+50,e.y))
            addToPlayList(song);
    }

    function addToPlayList(s:Song){
        //insert s.title into PlaylistView.playlist.items;
        //PlaylistView.playlist.addSong(s.title);
        insert s into PlaylistView.nowPlayingList;
    }
}
