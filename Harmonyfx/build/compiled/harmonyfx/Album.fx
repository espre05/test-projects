/* 
 */
package harmonyfx;

import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.animation.*;
import java.lang.Math;
import harmonyfx.listview.*;
import harmonyfx.PlaylistView;
import harmonyfx.RESTful.AlbumArt;
import java.lang.Comparable;

public class Album extends CustomNode, Comparable{
    public var position:Integer = 0;
    public var angle = 45.0;
    public var shelf:DisplayShelf;
    public var blockmouse = true;
    var art:AlbumArt = AlbumArt{};
    var image:Image = bind if (art.artImage != null) art.artImage else defaultArt;
        
    override var id = bind albumtitle;
    var defaultArt:Image = Image{ url: "{__DIR__}images/AlbumArt.png";}
    // album
    public var albumtitle:String ;
    public var songs:Song[] on replace oldval[lo..hi] = newval{
        for (s in newval){
            // check if song already exist
            def item = ListItem{song:s; listView:l}
            if (Exist(s.url)){
                // remove redundancy
                delete s from songs;
                continue;
            }
            // if not
            insert item into l.listItem;
        }
        if (image == null or image == defaultArt)
            art.getImage(songs[0].artist, songs[0].album);
//        println("sz song: {sizeof songs} = sz l.item: {sizeof l.listItem}");
//            if ( art.artImage != null){
//                image = art.artImage}
//            else image = defaultArt;
    };


    var time = Math.PI/2;
    def width = 250;
    def height = 300;
    def radius = width/2;
    def back = height/10;
    // flip
    public var frontNode:Node = Group{
        visible: bind (time > 0);
        cache: true
        content: [ ImageView {
                        image: bind image
                        opacity: bind if (image == defaultArt) 0.5 else 1;
                        blocksMouse: true
                    },
                    Rectangle {
                        width: image.width
                        height: image.height
                        fill: Color.TRANSPARENT
                        stroke: Color.BLACK
                        smooth: true
                    }]
        //blocksMouse: true
    };

    var l:ListView = ListView{
        layoutY: 4
        blocksMouse: true
//        listItem: for (i in [0..sizeof songs-1])
//            ListItem{listView: l; judul: "{songs[i].title}"
//        }
    };
    
    public var backNode:Node = Group{
        visible: bind (time < 0)
        cache: true
        content:bind [
                 Rectangle {
                    width: AlbumSongs.width
                    height: l.height
                    fill: Color.BLACK
                    stroke: Color.WHITE;
                    strokeWidth: 2
                    opacity: 0.5
                },
                l,
            ]
    }
    
    var animate = false;
    public var flipped = false;
    // end
    
    var lx = bind if (not animate) radius - Math.sin(Math.toRadians(angle))*radius - 1
            else radius - Math.sin(time)*radius;
    var rx = bind if (not animate) radius + Math.sin(Math.toRadians(angle))*radius + 1
            else radius + Math.sin(time)*radius;
    var uly = bind if (not animate) 0 - Math.cos(Math.toRadians(angle))*back
            else 0 - Math.cos(time)*back;
    var ury = bind if (not animate) 0 + Math.cos(Math.toRadians(angle))*back
            else Math.cos(time)*back;
    var lry = bind if (not animate) height + uly else height - Math.cos(time)*back;
    var lly = bind if (not animate) height + ury else height + Math.cos(time)*back;

    function Exist(f:String){
        for (item in l.listItem){
            if (item.song.url == f) return true;
        }
        return false;
    }

    public function playFlip():Void{
        if(flipped) {
            anim2.rate = 1.0;
            anim2.time = 0s;
            anim2.play();
        } else {
            anim.stop();
            anim.rate = 1.0;
            anim.time = 0s;
            anim.play();
        }
    }

    public var anim = Timeline {
        keyFrames: [
            at(0s) { time=> Math.PI/2 tween Interpolator.LINEAR;
                        this.scaleX => 1; this.scaleY => 1},
            KeyFrame {
                time: 1.0s
                values: [time=> -Math.PI/2 tween Interpolator.LINEAR,
                this.scaleX => 1.3 tween Interpolator.LINEAR,
                this.scaleY => 1.3 tween Interpolator.LINEAR]
                action: function() {
                    flipped = not flipped;
                    animate = false;
                    l.requestFocus();
                }
            }
        ]
    }

    public var anim2 = Timeline {
        keyFrames: [
            at(0s) { time=> -Math.PI/2 tween Interpolator.LINEAR;
            this.scaleX => this.scaleX; this.scaleY => this.scaleY},
            KeyFrame {
                time: 1.0s
                values: [time=> Math.PI/2 tween Interpolator.LINEAR,
                this.scaleX => 1 tween Interpolator.LINEAR,
                this.scaleY => 1 tween Interpolator.LINEAR]
                action: function() {
                    flipped = not flipped;
                    animate = false;
                }
            }
        ]
    }

    public var grip = Rectangle {
            x: bind if (flipped)  0 else rx-23
            y: bind lry-123
            width: 20, height: 20
            fill: Color.BLUE
            opacity: 0.5
            blocksMouse: true
            visible: false
            onMouseClicked: function( e: MouseEvent ):Void {
                animate = true;
                playFlip();
            }
            cursor: Cursor.HAND
    }


    override public function create():Node {
        return Group {
            content: [
                Group {
                    content: [ frontNode, backNode ]
                    
                    effect: bind PerspectiveTransform {
                        input: Reflection {
                            fraction: 0.5
                            topOffset: 0.0
                            topOpacity: 0.5
                            bottomOpacity: 0.0
                        }
                        ulx: lx     uly: uly
                        urx: rx     ury: ury
                        lrx: rx     lry: lry;//height + uly
                        llx: lx     lly: lly;//height + ury
                    }
                },
                Rectangle {
                    translateX: bind lx
                    width: bind rx-lx
                    height: height
                    fill: Color.TRANSPARENT
                    blocksMouse: bind blockmouse
                    onMouseClicked: function(e:MouseEvent) {
                        shelf.requestFocus();
                        for (album in shelf.content)
                            if (album != this){
                                (album as Album).blockmouse = true;
                            }
                        shelf.shiftToCenter(this);
                        this.blockmouse = false;
                        //grip.visible = true;
                        //PlaylistView.Title = albumtitle;
//                        if (e.clickCount == 2){
//                            animate = true;
//                            playFlip();
//                        }
                    };
                }, grip
            ]
        };
    }

    public override function compareTo(album:java.lang.Object):Integer {
        def albm = album as Album;
        return this.albumtitle.compareTo(albm.albumtitle);
    }
}



