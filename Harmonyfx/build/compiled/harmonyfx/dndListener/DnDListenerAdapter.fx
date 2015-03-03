/*
 * DnDListenerObserver.fx
 *
 * Created on 17 Jun 09, 23:41:27
 */

package harmonyfx.dndListener;
import java.util.Observer;
import java.util.Observable;

/**
 * @author Muhammad Hakim A <hakimrie[at]gmail.com>
 */

public class DnDListenerAdapter extends Observer{
    public var files:String[];
    public-init var dndListener:DnDListener on replace{
        dndListener.addObserver(this);
    }
    public override function update(observable:Observable, arg:Object){
        FX.deferAction(
            function():Void{
                files = dndListener.getFiles();
            });
    }
}
