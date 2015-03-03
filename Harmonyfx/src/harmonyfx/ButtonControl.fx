/*
 * ButtonControl.fx
 *
 * Created on 29 Jun 09, 17:41:43
 */

package harmonyfx;
import javafx.scene.image.*;
import javafx.scene.CustomNode;
import javafx.scene.Group;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.Bloom;
/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class ButtonControl extends CustomNode {
    def closeButtonImage = Image{ url:"{__DIR__}images/close.png"};
    def minimizeButtonImage = Image{ url:"{__DIR__}images/min.png"};
    def maximizeButtonImage = Image{ url:"{__DIR__}images/maxbw.png"};

    var closeButton:ImageView = ImageView {
            image: closeButtonImage;
            x:32
            onMouseEntered: function( e: MouseEvent ):Void {
                closeButton.effect = Bloom {
                        threshold: 0.5
                    }
            }

             onMouseExited: function( e: MouseEvent ):Void {
                closeButton.effect = null;
            }

            onMouseClicked: function( e: MouseEvent ):Void {
                Main.stage.close();
            }
    }

    var minimizeButton:ImageView = ImageView {
            image: minimizeButtonImage;
            x:16
            onMouseEntered: function( e: MouseEvent ):Void {
                minimizeButton.effect = Bloom {
                        threshold: 0.5
                    }
            }

             onMouseExited: function( e: MouseEvent ):Void {
                minimizeButton.effect = null;
            }

            onMouseClicked: function( e: MouseEvent ):Void {
                Main.stage.iconified = true;
            }
    }

    var maximizeButton:ImageView = ImageView {
            image: maximizeButtonImage;

//            onMouseEntered: function( e: MouseEvent ):Void {
//                maximizeButton.effect = Bloom {
//                        threshold: 0.5
//                    }
//            }
//
//            onMouseExited: function( e: MouseEvent ):Void {
//                maximizeButton.effect = null;
//            }
//
//            onMouseClicked: function( e: MouseEvent ):Void {
//                stage.fullScreen = true;
//            }

    }

    override function create(){
        return Group{
            content: [maximizeButton, minimizeButton, closeButton]
        }
    }
}

//Stage {
//    title : "TEST"
//    scene: Scene {
//        //fill: Color.BLACK
//        width: 500
//        height: 200
//        content: [ButtonControl{ translateY: 30}]
//    }
//}