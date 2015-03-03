/* 
 */

package harmonyfx;

import javafx.animation.*;
import javafx.scene.*;
import javafx.util.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;


public class DisplayShelf extends CustomNode {

    public var content:Node[];
    public-init var spacing = 110;
    public-init var leftOffset = -50;
    public-init var rightOffset = 50;
    public-init var perspective = false;
    public-init var scaleSmall = 0.5;

    var left:Group = Group { };
    var center:Group = Group { };
    var right:Group = Group { };

    public var centerIndex = 0;
    
    override public function create():Node {
        var half = content.size()/2-1;

        left.content = content[0..half-2];
        center.content = content[half-1];
        center.effect = DropShadow {};
        right.content = content[half..content.size()-1];
        right.content = Sequences.<<reverse>>(right.content) as Node[];

        centerIndex = half-1;

        doLayout();
        return Group {
            content: [
                left,
                right,
                center
            ]
        }
    }
    
    /**
     * "Reparents" the node sequence newContent to its new parent Group
     * newParent, replacing any previous content,
     * after first removing them from their previous parent Group.
     */
    public function reparent(newContent:Node[], newParent:Group):Void {
        for (n in newContent) {
            if (n.parent instanceof Group) {
                delete n from (n.parent as Group).content;
            }
        }
        newParent.content = newContent;
    }

    public function shift(offset:Integer):Void {
        if(centerIndex <= 0 and offset > 0 ) {
            return;
        }
        
        if(centerIndex >= content.size()-1 and offset < 0) {
            return;
        }

        centerIndex -= offset;
        reparent(content[0..centerIndex-1], left);
        reparent([content[centerIndex]], center);
        reparent(Sequences.<<reverse>>(content[centerIndex+1..content.size()-1]) as Node[], right);
        doLayout();

        PlaylistView.Title = (content[centerIndex] as Album).albumtitle;
        for (i in [0..sizeof content-1]){
            var album = content[i] as Album;
            if (i == centerIndex)
                album.grip.visible = true
            else album.grip.visible = false;
        }
    }

    override function doLayout() {

        var startKeyframes:KeyFrame[];
        var endKeyframes:KeyFrame[];
        var duration = 1s;

        for(n in content) {
            var it = n as Album;
            insert KeyFrame { time: 0s values: [
                    n.translateX => n.translateX,
                    n.scaleX => n.scaleX,
                    n.scaleY => n.scaleY,
                    it.angle => it.angle
                    ] } into startKeyframes;
            //if (it.flipped) insert it.anim2.keyFrames[0] into startKeyframes;
        }

        for(n in left.content) {
            var it = n as Album;
            var newX = -left.content.size()*spacing +  spacing * indexof n + leftOffset;
            insert KeyFrame { time: duration values: [
                    n.translateX => newX tween Interpolator.EASEOUT,
                    n.scaleX => scaleSmall tween Interpolator.EASEOUT,
                    n.scaleY => scaleSmall tween Interpolator.EASEOUT,
                    it.angle => 45 tween Interpolator.EASEOUT
                ] } into endKeyframes;

            //if (it.flipped) insert it.anim2.keyFrames[1] into startKeyframes;
        }

        for(n in center.content) {
            var it = n as Album;
            insert KeyFrame { time: duration values: [
                    n.translateX => 0,
                    n.scaleX => 1.0,
                    n.scaleY => 1.0,
                    it.angle => 90
                ] } into endKeyframes;
        }

        for(n in right.content) {
            var it = n as Album;
            var newX = right.content.size()*spacing -spacing * indexof n + rightOffset;
            insert KeyFrame { time: duration values: [
                    n.translateX => newX tween Interpolator.EASEOUT,
                    n.scaleX => scaleSmall tween Interpolator.EASEOUT,
                    n.scaleY => scaleSmall tween Interpolator.EASEOUT,
                    it.angle => 135 tween Interpolator.EASEOUT
                ] } into endKeyframes;
            //if (it.flipped) insert it.anim2.keyFrames[1] into startKeyframes;
        }

        var anim = Timeline {
            keyFrames: [startKeyframes, endKeyframes]
        };
        anim.play();
    }

    public function shiftToCenter(item:Album):Void {
        for(n in left.content) {
            if(n == item) {
                var index = indexof n;
                var shiftAmount = left.content.size()-index;
                shift(shiftAmount);
                return;
            }
        }
        // flip
        for(n in center.content) {
            if(n == item) {
                return;
            }
        }
        for(n in right.content) {
            if(n == item) {
                var index = indexof n;
                var shiftAmount = -(right.content.size()-index);
                shift(shiftAmount);
                return;
            }
        }
    }


    override var onKeyPressed = function(e:KeyEvent):Void {
        if(e.code == KeyCode.VK_LEFT) {
            shift(1);
        }
        if(e.code == KeyCode.VK_RIGHT) {
            shift(-1);
        }
    }

    override var onMouseWheelMoved = function( e: MouseEvent ):Void {
        println("wr: {e.wheelRotation}");
        if (e.wheelRotation > 0){
            shift(1);
        }else if (e.wheelRotation < 0){
            shift(-1);
        }
    }
}

