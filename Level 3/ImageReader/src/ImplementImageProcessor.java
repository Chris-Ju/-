
/**
 * @author Chris Ju
 */

import java.awt.*;
import java.io.*;
import java.awt.image.*;
import imagereader.IImageProcessor;

/**
 * Implement interface of ImageProcessor
 * Including showChanelR showChanelG showChanelB and showGray method
 */
public class ImplementImageProcessor implements IImageProcessor {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;


    public Image showChanelR(Image sourceImage) {
        ColorFilter redFilter = new ColorFilter(ONE);
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        return toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), redFilter));
    }

    public Image showChanelG(Image sourceImage) {
        ColorFilter greenFilter = new ColorFilter(TWO);
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        return toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), greenFilter));
    }

    public Image showChanelB(Image sourceImage) {
        ColorFilter blueFilter = new ColorFilter(THREE);
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        return toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), blueFilter));
    }

    public Image showGray(Image sourceImage) {
        ColorFilter grayFilter = new ColorFilter(FOUR);
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        return toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));
    }

}

/**
 * Deal RGB with given Color extends RGBImageFilter
 */

class ColorFilter extends RGBImageFilter {

    // Way
    private int colorNum;

    private static final int RED = 0xff0000;
    private static final int GREEN = 0xff00;
    private static final int BLUE = 0xff;
    private static final double REDM = 0.299;
    private static final double GREENM = 0.587;
    private static final double BLUEM = 0.114;
    private static final int SIXTEEN = 16;
    private static final int EIGHT = 8;
    private static final int OPO = 0xff000000;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    // Construction method
    public ColorFilter(int c) {
        colorNum = c;
        canFilterIndexColorModel = true;
    }

    /**
     * Overwrite filterRGB
     * @param x  target position x, not using in this method
     * @param y  target position y, not using in this method
     * @param rgb color
     * @return   the willing color stored by int 
     */
    public int filterRGB(int x, int y, int rgb) {
        if (colorNum == ONE) {
            return (rgb & RED) + (rgb & OPO);
        } else if (colorNum == TWO) {
            return (rgb & GREEN) + (rgb & OPO);
        } else if (colorNum == THREE) {
            return (rgb & BLUE) + (rgb & OPO);
        } else {
            int grayRGB = (int) (((rgb & RED) >> SIXTEEN) * REDM + ((rgb & GREEN) >> EIGHT) * GREENM + (rgb & BLUE) * BLUEM);
            return (rgb & OPO) + (grayRGB << SIXTEEN) + (grayRGB << EIGHT) + grayRGB;
        }
    }
}