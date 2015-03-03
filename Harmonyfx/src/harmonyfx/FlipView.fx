/*
 * FlipView.fx
 *
 * Created on 13 Jun 09, 10:49:45
 */

package harmonyfx;
import javafx.scene.*;
import javafx.animation.*;
import javafx.util.Math;
import javafx.scene.effect.PerspectiveTransform;

/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

/*
 * FlipView is a custom node which alternately displays two
 * child nodes, the front and the back, by flipping between
 * them with a 3-D transition.
 */
    // the animation to flip between front and back
var time = Math.PI/2;
var width = 200;
var height = 200;
var radius = width/2;
var back = height/10;
class FlipView extends CustomNode {
    // public variables for users of this class to set the front and back
    public var frontNode:Node;
    public var backNode:Node;

    var flipped = false;


    public var anim = Timeline {
        keyFrames: [
            at(0s) { time=> Math.PI/2 tween Interpolator.LINEAR},
            at(1s) { time=> -Math.PI/2 tween Interpolator.LINEAR},
            KeyFrame {
                time: 1.0s
                action: function() { flipped = not flipped; }
            }
        ]
    }

    override public function create():Node {
        return Group {
            content: [
                Group { content: backNode visible: bind (time<0) effect: bind getPT(time) },
                Group { content: frontNode  visible: bind (time>0) effect: bind getPT(time) },
            ]
        }
    }

    // Returns the actual perspective transform.
    // Calcualtes the transform by stretching the front and back
    // edges according to a sine and cosine curve multiplied by
    // the constants: radius and back
    function getPT(t:Number):PerspectiveTransform {
        return PerspectiveTransform {
            ulx: radius - Math.sin(t)*radius     uly: 0 - Math.cos(t)*back
            urx: radius + Math.sin(t)*radius     ury: 0 + Math.cos(t)*back
            lrx: radius + Math.sin(t)*radius     lry: height - Math.cos(t)*back
            llx: radius - Math.sin(t)*radius     lly: height + Math.cos(t)*back
        };
    }
}