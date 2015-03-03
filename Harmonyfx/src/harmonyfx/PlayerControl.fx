/*
 * PlayerControl.fx
 *
 * Created on 20 Jun 09, 11:32:57
 */

package harmonyfx;
import javafx.scene.image.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.paint.LinearGradient;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
import javafx.scene.media.*;
import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import harmonyfx.PlaylistView;
import javafx.scene.Cursor;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class PlayerControl extends CustomNode{
    def playButtonImage = Image{ url:"{__DIR__}images/play.png"};
    def stopButtonImage = Image{ url:"{__DIR__}images/stop.png"};
    def pauseButtonImage = Image{ url:"{__DIR__}images/pause.png"};
    def nextButtonImage = Image{ url:"{__DIR__}images/next.png"};
    def prevButtonImage = Image{ url:"{__DIR__}images/prev.png"};

//    def repeatButtonImage = Image{ url:"{__DIR__}images/prev.png"};
//    def shuffleButtonImage = Image{ url:"{__DIR__}images/prev.png"};
//    def musicLoudImage = Image{ url:"{__DIR__}images/prev.png"};
//    def musicLowImage = Image{ url:"{__DIR__}images/prev.png"};
    
    var mediaplayer = MyMediaPlayer{}
    def glow:Glow = Glow { level: 0.5 }
    
    var playButton:ImageView = ImageView {
            image: bind 
                if (mediaplayer.status == MediaPlayer.PLAYING)
                    pauseButtonImage else playButtonImage;
            x: 0
            y: 0
            blocksMouse: true;
            onMouseClicked: function( e: MouseEvent ):Void {
                if (mediaplayer.status != MediaPlayer.PLAYING){
                    //playButton.image = pauseButtonImage;
                    mediaplayer.playCurrentSong();
                }else{
                    //playButton.image = playButtonImage;
                    mediaplayer.pause();
                }
            }

            onMouseEntered: function( e: MouseEvent ):Void {
                playButton.effect = glow
            }
            
            onMouseExited: function( e: MouseEvent ):Void {
                playButton.effect = null;
            }
    }

    var nextButton:ImageView = ImageView {
            image: nextButtonImage;
            x: 75
            y: 10
            //blocksMouse: true;
            onMouseClicked: function( e: MouseEvent ):Void {
                mediaplayer.playNext();
            }
            onMouseEntered: function( e: MouseEvent ):Void {
                nextButton.effect = glow
            }
            onMouseExited: function( e: MouseEvent ):Void {
                nextButton.effect = null;
            }
    }

    var prevButton:ImageView = ImageView {
            image: prevButtonImage;
            x: 55
            y: 10
            blocksMouse: true;
            onMouseClicked: function( e: MouseEvent ):Void {
                mediaplayer.playPrevious();
            }

            onMouseEntered: function( e: MouseEvent ):Void {
                prevButton.effect = glow
            }
            
            onMouseExited: function( e: MouseEvent ):Void {
                prevButton.effect = null;
            }
    }

    var stopButton:ImageView = ImageView {
            x: 35, y: 10
            image: stopButtonImage;
            blocksMouse: true;
            onMouseClicked: function( e: MouseEvent ):Void {
                mediaplayer.stop();
            }

            onMouseEntered: function( e: MouseEvent ):Void {
                stopButton.effect = glow
            }
            
            onMouseExited: function( e: MouseEvent ):Void {
                stopButton.effect = null;
            }
    }

    def seekbar = Rectangle {
            x: 150, y: 0
            width: 350, height: 10
            fill: null
            stroke: Color.BLACK
        }
        
    // the time control slider
    var time_control_length = 350.0;
    var time_control_xoff = 147;
    var currentTime = bind
        if (mediaplayer.media != null and
            mediaplayer.media.duration != null and
            mediaplayer.media.duration.toMillis() > 0 and
            mediaplayer.mediaPlayer.currentTime.toMillis() > 0)
        {
            time_control_length * mediaplayer.mediaPlayer.currentTime.toMillis() / mediaplayer.media.duration.toMillis()
        } else {
            0.0
        };

    var bufferedTime = bind
        if (mediaplayer.media != null and
            mediaplayer.media.duration != null and
            mediaplayer.media.duration.toMillis() > 0 and
            mediaplayer.mediaPlayer.bufferProgressTime.toMillis() > 0)
        {
            time_control_length * mediaplayer.mediaPlayer.bufferProgressTime.toMillis() / mediaplayer.media.duration.toMillis()
        } else {
            0.0
        };

    var time_control:ImageView = ImageView { id: "time_control"
        cursor: Cursor.HAND
        opacity: 1.0 visible: true
        x: bind currentTime + time_control_xoff  y: -2
        image: Image { url: "{__DIR__}images/bullet.png" },
        blocksMouse: true
        onMouseDragged: function(e) {
            if (mediaplayer.media != null and mediaplayer.media.duration > 0s) {
                var x = e.x;
                var available = mediaplayer.mediaPlayer.bufferProgressTime.toMillis() / mediaplayer.media.duration.toMillis();
                var xmin = time_control_xoff;
                var xmax = xmin + (available*time_control_length);
                if (x > xmin and x < xmax) {
                    var t = (x - xmin) / time_control_length * mediaplayer.media.duration.toMillis();
                    mediaplayer.mediaPlayer.currentTime = Duration.valueOf(t);
                }
            }
        }
    };

    var current_time_indicator = ImageView { id: "current_time_indicator"
        opacity: 1.0 visible: true
        translateX: 150
        translateY: 2
        image: Image { url: "{__DIR__}images/seek2.png" }
        clip: Rectangle { x: 0 y: 0 width: bind currentTime height: 20}
    };

    var buffering_indicator = Rectangle {
        fill: LinearGradient {
            startX: 0 startY: 0
            endX: 1 endY: 0
            stops: [
                Stop { offset: 0.9 color: Color.rgb(255, 255, 255, 0.1) }
                Stop { offset: 1.0 color: Color.TRANSPARENT }
            ]
        }
        translateX: 160
        translateY: 2
        width: bind bufferedTime
        height: 7
    };

    function getTimeString(dur:Duration):String {
        if (dur == null or dur < 0s) {
            return "0:00";
        } else {
            var minutes = dur.toMinutes() as Integer;
            var seconds = dur.toSeconds() mod 60 as Integer;
            return "{minutes}:{%02d seconds}";
        }
    }

    var current_time = Text {
        fill: Color.web("#0d9c0d")
        x: 130 y: 8 font: Font { size: 9 }
        content: bind getTimeString(mediaplayer.mediaPlayer.currentTime)
    };

    var total_time = Text {
        fill: Color.web("#c5c5c5")
        x: 505 y: 8 font: Font { size: 9 }
        content: bind getTimeString(mediaplayer.media.duration)
    };

    override function create () {
        return Group{
            content: [
                stopButton,
                prevButton, playButton, nextButton, seekbar,
                buffering_indicator,
                current_time_indicator,
                time_control,
                current_time,
                total_time,]
        }
    }
}

class MyMediaPlayer{
    var status = bind mediaPlayer.status;
    //public var curtime:Duration = bind mediaPlayer.currentTime;
    //var duration = bind mediaPlayer.media.duration;
    public var media = bind mediaPlayer.media;
    
    public function stopCurrentSong():Void {
        mediaPlayer.stop();
        mediaPlayer.media = null;
        if (sizeof PlaylistView.nowPlayingList > 0 and PlaylistView.currentPlayingSongIndex>-1) {
            PlaylistView.nowPlayingList[PlaylistView.currentPlayingSongIndex].closeMedia();
        }
    }

    public function playCurrentSong():Void {
        mediaPlayer.media = PlaylistView.nowPlayingList[PlaylistView.currentPlayingSongIndex].getMedia();
        if (not mediaPlayer.paused){
            if (mediaPlayer.media != null)
                mediaPlayer.play();
        }else{
            mediaPlayer.play();
        }
    }

    public function playNext () {
        PlaylistView.currentPlayingSongIndex++;
        def numsongs = bind sizeof PlaylistView.nowPlayingList;
        if (PlaylistView.currentPlayingSongIndex < numsongs)
            playCurrentSong()
        else{
            PlaylistView.currentPlayingSongIndex = 0;
            playCurrentSong();
        }
    }

    public function playPrevious () {
        if (PlaylistView.currentPlayingSongIndex > 0){
            PlaylistView.currentPlayingSongIndex--;
            playCurrentSong();
        }else{ // index = 0
            PlaylistView.currentPlayingSongIndex = sizeof PlaylistView.nowPlayingList - 1;
            playCurrentSong();
        }
    }

    public function pause() {
        if (not mediaPlayer.paused){
            mediaPlayer.pause();
        }
    }

    public function stop () {
        mediaPlayer.stop();
    }

    public var mediaPlayer:MediaPlayer = MediaPlayer {
        volume: 0.5
        autoPlay: false
        startTime: 60s
        //currentTime: bind curtime with inverse;
        onError: function(e:MediaError) {
            println("got a MediaPlayer error : {e.cause} {e}");
            stopCurrentSong();
        }
        onEndOfMedia: function() {
            println("reached end of media");
            stopCurrentSong();
            PlaylistView.currentPlayingSongIndex++;
            def numsongs = bind sizeof PlaylistView.nowPlayingList;
            println("cur: {PlaylistView.currentPlayingSongIndex} num: {numsongs}");
            if (PlaylistView.currentPlayingSongIndex < numsongs){
                playCurrentSong()
            }else{
                PlaylistView.currentPlayingSongIndex = 0;
                if (PlaylistView.playlist.repeat) playCurrentSong();
            }
        }
    };
}

//Stage {
//    title : "TEST"
//    scene: Scene {
//        fill: Color.BLACK
//        width: 500
//        height: 200
//        content: [PlayerControl{ translateY: 30}]
//    }
//}


