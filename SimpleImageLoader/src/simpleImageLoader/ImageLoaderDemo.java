// This is semi-cool.
package simpleImageLoader;
/*
 * Adapted from this book:
 * http://proquest.safaribooksonline.com.proxy.libraries.uc.edu/1565924843/ch09-12080
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;

// Changed ApplicationFrame to Frame because ApplicationFrame was not recognized
//public class ImageLoaderDemo extends ApplicationFrame {
public class ImageLoaderDemo extends Frame {
  private Image mImage;

  public static void main(String[] args) throws Exception {
    String url = "http://nicholsoncomputerconsulting.com/images/balloons.jpg";
    if (args.length > 0) url = args[0];
    new ImageLoaderDemo(new URL(url));
  }

  public ImageLoaderDemo(URL url) {
    super("RightSizer v1.0");
    mImage = Toolkit.getDefaultToolkit().getImage(url);
    rightSize();
  }

  private void rightSize() {
    int width = mImage.getWidth(this);
    int height = mImage.getHeight(this);
    if (width == -1 || height == -1) return;
    addNotify();
    Insets insets = getInsets();
    setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
//  Commented out center() because it wasn't recognized
//  center();
    setVisible(true);
  }

  public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
    if ((infoflags & ImageObserver.ERROR) != 0) {
      System.out.println("Error loading image!");
      System.exit(-1);
    }
    if ((infoflags & ImageObserver.WIDTH) != 0 &&
        (infoflags & ImageObserver.HEIGHT) != 0)
      rightSize();
    if ((infoflags & ImageObserver.SOMEBITS) != 0)
      repaint();
    if ((infoflags & ImageObserver.ALLBITS) != 0) {
      rightSize();
      repaint();
      return false;
    }
    return true;
  }

  public void update(Graphics g) {
    paint(g);
  }
  public void paint(Graphics g) {
    Insets insets = getInsets();
    g.drawImage(mImage, insets.left, insets.top, this);
  }
}

