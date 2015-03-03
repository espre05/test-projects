/* 
 * 
 */
package harmonyfx.listview;

import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import javafx.util.Math;
import javafx.scene.input.MouseEvent;
/**
 * @author Muhammad Hakim A
 */

public class ListView extends CustomNode {
    
    public var listItem : ListItem[] on replace old[lo..hi]=n{
        value = ListItem.height*sizeof listItem;
    };
    public var height = 200.0;
    public var itemWidth = AlbumSongs.width;
    
    public var selectedListItem : ListItem;
    var value:Number;
    var max = bind Math.max((listView.boundsInParent.height - height), 0);
    override var focusTraversable = true;
    

    var listView:VBox = VBox {
        spacing: 0
        translateX: 2
        content: bind listItem
    };

    override var onKeyPressed = function(e) {
        println("key pressed max: {max} v+l = {value+listView.layoutY}");
        if(e.code == KeyCode.VK_UP) {
            if (value+listView.layoutY-height > 0){
                listView.layoutY -= ListItem.height;
            }
        } else if(e.code == KeyCode.VK_DOWN) {
            if (listView.layoutY < 0){
                listView.layoutY += ListItem.height;
            }
        }
    };

    override var onMouseWheelMoved = function(e:MouseEvent){
        if (e.wheelRotation> 0 and value+listView.layoutY-height > 0){
            listView.layoutY -= ListItem.height;
        }else if (e.wheelRotation < 0 and listView.layoutY < 0){
            listView.layoutY += ListItem.height;
        }
    };

    var sy;
    override var onMousePressed = function(e:MouseEvent){
        sy = e.y - translateY
    }

    override var onMouseDragged = function(e:MouseEvent){
        var ty = e.y - sy;
        if (Math.abs(ty) > 5)
            if (ty > 0 and listView.layoutY < 0){
                listView.layoutY += ListItem.height;
            }
            else if (ty < 0 and value+listView.layoutY-height > 0 ){
                listView.layoutY -= ListItem.height;
            }
    }
    
    override function create() : Node {
        
        Group {
            content: [
                Group {
                    content: [ listView ]
                    // Clip for ListView
                    clip: Rectangle {
                        y: 2
                        width: bind AlbumSongs.width - 10
                        height: bind height - 2
                    }
                }
            ]
        }
    }
}
