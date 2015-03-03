import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class PhotoCanvas extends Canvas {

  private boolean scroll = false;
  private int current_displayed_image = 0;

  // our library of images
  private Image[] images;

  private int pressX, releaseX, dragX = 0;

  public PhotoCanvas() {
    // load up the images
    loadImages();
  }

  protected void paint(Graphics g) {

    // clear the screen
    g.setGrayScale(255);
    g.fillRect(0, 0, getWidth(), getHeight());

    // are we scrolling?
    if (scroll) {
      g.drawImage(
        images[current_displayed_image],
        -dragX, 0,
        Graphics.LEFT | Graphics.TOP);
      scroll = false;
      return;
    }

    // we are moving images
    if (pressX < releaseX) { // move right
      current_displayed_image++;
      if (current_displayed_image == images.length) {
        current_displayed_image = 0;
      }
    }

    if (pressX > releaseX) { // move left
      current_displayed_image--;
      if (current_displayed_image < 0) {
        current_displayed_image = (images.length - 1);
      }
    }

    // draw the new moved image
    g.drawImage(
      images[current_displayed_image],
      0, 0,
      Graphics.LEFT | Graphics.TOP);
  }

  // store the location of the start of the touch
  protected void pointerPressed(int x, int y) {
    pressX = x;
  }

  // where did we release and have we moved far enough to justify new image?
  protected void pointerReleased(int x, int y) {
    if (scroll) {
      return;
    }
    releaseX = x;
    if (Math.abs(releaseX - pressX) > 10) {
      repaint();
    }
  }

  // did we drag the pointer just to scroll the image?
  protected void pointerDragged(int x, int y) {
    int deltaX = pressX - x;
    if (Math.abs(deltaX) <= 10) {
      scrollImage(deltaX);
    }
  }

  // handles scrolling
  private void scrollImage(int deltaX) {

    int imageWidth = images[current_displayed_image].getWidth();
    if (imageWidth > getWidth()) {
      dragX += deltaX;
      if (dragX < 0) {
        dragX = 0;
      } else if (dragX + getWidth() > imageWidth) {
        dragX = imageWidth - getWidth();
      }
    }
    scroll = true;
    repaint();
  }

  // loads up the images at start
  private void loadImages() {

    images = new Image[3];

    try {
      images[0] = Image.createImage("/cow1.jpg");

      images[1] = Image.createImage("/cow2.jpg");

      images[2] = Image.createImage("/cow3.jpg");

    } catch (IOException ex) {
      ex.printStackTrace();
    }


  }
}
