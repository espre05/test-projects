import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.CommandListener;

public class PhotoFlipperMIDlet extends MIDlet implements CommandListener {

  // the main display
  Display display;

  // the canvas on which we will show the various events
  Canvas canvas;

  // the commands
  Command exitCommand;

  public PhotoFlipperMIDlet() {

    // get the current display
    display = Display.getDisplay(this);

    canvas = new PhotoCanvas();

    // create the commands and attach them to the UI
    exitCommand = new Command("Exit", Command.EXIT, 1);

    canvas.addCommand(exitCommand);
    canvas.setCommandListener(this);    
  }

  public void startApp() {
    display.setCurrent(canvas);
  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
  }

  public void commandAction(Command c, Displayable d) {
    // handle the exit command
    if (c == exitCommand) {
      destroyApp(true);
      notifyDestroyed();
      return;
    }
  }
}