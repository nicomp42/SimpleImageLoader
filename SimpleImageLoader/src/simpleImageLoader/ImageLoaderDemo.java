// This is semi-cool.
package simpleImageLoader;
/*
* Adapted from this book:
* http://proquest.safaribooksonline.com.proxy.libraries.uc.edu/1565924843/ch09-12080
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Changed ApplicationFrame to Frame because ApplicationFrame was not recognized
//public class ImageLoaderDemo extends ApplicationFrame {
public class ImageLoaderDemo extends JFrame {
    private Image mImage;
    
    public static void main(String[] args) throws Exception {
        String url = "http://nicholsoncomputerconsulting.com/images/balloons.jpg";
        if (args.length > 0) url = args[0];
        new ImageLoaderDemo(new URL(url));
    }
    
    public ImageLoaderDemo(URL url) {
        super("RightSizer v1.0");
        
        // Set absolute layout
        setLayout(null);
        
        // Set the image
        mImage = Toolkit.getDefaultToolkit().getImage(url);
        ImageIcon imageIcon = new ImageIcon(mImage);
        
        // Convert to BufferedImage
        BufferedImage bimage = new BufferedImage(
        	    imageIcon.getIconWidth(),
        	    imageIcon.getIconHeight(),
        	    BufferedImage.TYPE_INT_RGB);
    	Graphics g = bimage.createGraphics();
    	imageIcon.paintIcon(null, g, 0,0);
    	
    	// Filter out all red in image
    	Graphics2D graphics = bimage.createGraphics();
    	for(int x=0;x<bimage.getWidth();x++) {
    		for(int y=0;y<bimage.getHeight();y++) {
    			Color color = new Color(bimage.getRGB(x, y));
    			int rgb = new Color(0, color.getGreen(), color.getBlue()).getRGB();
    			Color newColor = new Color(rgb);
    			graphics.setPaint(newColor);
    	    	graphics.fillRect(x, y, 1, 1);
    	    	System.out.println(x+"\t"+y+"\t"+color.toString());
    		}
    	}
        
        // Replace image with filtered image
        mImage = bimage;
        imageIcon = new ImageIcon(mImage);
        
        // JLabel image container
        JLabel imageContainer = new JLabel();
        imageContainer.setIcon(imageIcon);
        imageContainer.setIconTextGap(0);
        imageContainer.setBorder(null);
        imageContainer.setText(null);
        Insets insets = getInsets();
        Dimension size = imageContainer.getPreferredSize();
        imageContainer.setBounds(25 + insets.left, 5 + insets.top, imageIcon.getImage().getWidth(null), imageIcon.getImage().getHeight(null));
        add(imageContainer);
        
        int width = imageContainer.getIcon().getIconWidth();
        int height = imageContainer.getIcon().getIconHeight();
        if (width == -1 || height == -1) return;
        
        setSize(new Dimension(1024,768));
        setVisible(true);
    }
}