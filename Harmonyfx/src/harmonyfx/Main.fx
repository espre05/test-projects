/* 
 */
package harmonyfx;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
//import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.ext.swing.SwingComponent;
import harmonyfx.PlaylistView;
import harmonyfx.dndListener.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import harmonyfx.preferences.Library;
var stageDragInitialX:Number;
var stageDragInitialY:Number;

var inBrowser = "true".equals(FX.getArgument("isApplet") as String);
var draggable = AppletStageExtension.appletDragSupported;
var width = 700;
var height = 600;
var library = Library{}

var dragControl:ImageView = ImageView { 
        x: -5 y: 0
        image: Image { url: "{__DIR__}images/controlbar.png" }
        onMousePressed: function(e) {
            stageDragInitialX = e.screenX - stage.x;
            stageDragInitialY = e.screenY - stage.y;
        }
        onMouseDragged: function(e) {
            stage.x = e.screenX - stageDragInitialX;
            stage.y = e.screenY - stageDragInitialY;
        }
};

var statusbar:ImageView = ImageView{
    x: -5
    y: height - 22
    image: Image{ url:"{__DIR__}images/statusbar.png"}
    onMousePressed: function(e) {
        stageDragInitialX = e.screenX - stage.x;
        stageDragInitialY = e.screenY - stage.y;
    }
    
    onMouseDragged: function(e) {
        stage.x = e.screenX - stageDragInitialX;
        stage.y = e.screenY - stageDragInitialY;
    }
}

var dndlistener = DnDListener{}
var dndlisteneradapter = DnDListenerAdapter{
    dndListener: dndlistener;}

var background = Background{
    width: width; height: height
    layoutY: 75
    //layoutX: -10
};

var files:String[] = bind dndlisteneradapter.files on replace{
    insert files into PlaylistView.filelist;
};

def albumtitle:Text = CenteredText {
        layoutY: 400
        content: bind if (PlaylistView.Title != "") PlaylistView.Title
                    else "Unknown Album";
        visible: bind (sizeof PlaylistView.shelf.content > 0)
    }
    
def playerControl:PlayerControl = PlayerControl{
    layoutX: 10;
    layoutY: 35;
}

def buttonControl:ButtonControl = ButtonControl{
    layoutX: width - 50
    layoutY: 3
}

var content = Group{
    clip:Rectangle{
        width: width;
        height: height;
        arcWidth: 20
        arcHeight: 20
    }
    content:bind [
            background,
            dragControl,
            PlaylistView.searchbox,
            PlaylistView.shelf,
            albumtitle,
            buttonControl,
            PlaylistView.playlist,
            playerControl,
            statusbar
        ]
}

var scene:Scene = Scene {
        width: width, height: height
        fill: Color.TRANSPARENT;
        content: content
    };

public var stage:Stage = Stage {
    title: "Harmonyfx"
    visible: true
    resizable: true
    width: width, height: height
    style: StageStyle.TRANSPARENT
    scene: scene
}

class Background extends SwingComponent{
    public var background:  JPanel;
    //override var foreground = Color.BLACK;
    public override function createJComponent(){
        background = new JPanel();
        background.setBackground(java.awt.Color.BLACK);
        background.setPreferredSize(new Dimension(width, height));
        background.setDropTarget(new DropTarget(background,DnDConstants.ACTION_COPY_OR_MOVE,
            dndlistener, true));
        
        background.setRequestFocusEnabled(true);
        return background;
    }
}

class CenteredText extends Text {
        override var font = Font { size: 24 }
        override var textAlignment = TextAlignment.CENTER;
        override var textOrigin = TextOrigin.TOP;
        override var fill = Color.WHITE;
        override var translateX = bind (scene.width - boundsInLocal.width) / 2 ;
}

function run () {
    // load library
    
    stage.visible = true;
}

// Insert dragRect here to avoid possible cycle during initialization
//insert dragRect before dragControl.content[0];
//shelf.requestFocus();
//stage;
