
/**
 * @author Chris Ju
 */

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.*;
import java.io.Console;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import imagereader.IImageIO;

/**
 * Implement interface of ImplementImagIO
 * Including myRead and myWrite method
 */

public class ImplementImageIO implements IImageIO {

    /**
     * Read from bmp file to binary and convert to image
     * @param filePath bmp file path
     * @return result image
     */

    private static final int ZERO = 0;
    private static final int FIFTYFOUR = 54;
    private static final int TWENTYONE = 21;
    private static final int TWENTYFOUR = 24;
    private static final int SIXTEEN = 16;
    private static final int EIGHT = 8;
    private static final int TWENTY = 20;
    private static final int NINTENN = 19;
    private static final int EIGHTEEN = 18;
    private static final int TTF = 0xff;
    private static final int TWENTYFIVE = 25;
    private static final int TWENTYTHREE = 23;
    private static final int TWENTYTWO = 22;
    private static final int FOUR = 4;
    private static final int THIRTYFIVE = 35;
    private static final int THIRTYFOUR = 34;
    private static final int THIRTYSIX = 36;
    private static final int THIRTYSEVEN = 37;
    private static final int THREE = 3;

    public Image myRead(String filePath) {
        File file = new File(filePath);
        try {
            FileImageInputStream infile = new FileImageInputStream(file);

            // Creat and read Head Info
            byte headInfo[] = new byte[FIFTYFOUR];
            infile.read(headInfo, ZERO, FIFTYFOUR);

            // Read bmp Width
            int widthOfImage = (int) ((headInfo[TWENTYONE] & TTF) << TWENTYFOUR | (headInfo[TWENTY] & TTF) << SIXTEEN
                    | (headInfo[NINTENN] & TTF) << EIGHT | (headInfo[EIGHTEEN] & TTF));

            // Read bmp Height
            int heightOfImage = (int) ((headInfo[TWENTYFIVE] & TTF) << TWENTYFOUR
                    | (headInfo[TWENTYFOUR] & TTF) << SIXTEEN | (headInfo[TWENTYTHREE] & TTF) << EIGHT
                    | (headInfo[TWENTYTWO] & TTF));

            // Read bmp size        
            int sizeOfImage = (int) ((headInfo[THIRTYSEVEN] & TTF) << TWENTYFOUR
                    | (headInfo[THIRTYSIX] & TTF) << SIXTEEN | (headInfo[THIRTYFIVE] & TTF) << EIGHT
                    | (headInfo[THIRTYFOUR] & TTF));
            int sizeOfEmpty = sizeOfImage / heightOfImage - THREE * widthOfImage;
            sizeOfEmpty = (sizeOfEmpty == FOUR ? ZERO : sizeOfEmpty);

            // Read from down to top and from left to right
            byte allBytes[] = new byte[sizeOfImage];
            int arrayOfPix[] = new int[heightOfImage * widthOfImage];
            infile.read(allBytes, ZERO, sizeOfImage);
            int current = ZERO;

            // Because rgb in inverse, it should be read in inverse.
            for (int i = heightOfImage - 1; i >= ZERO; i--) {
                for (int j = ZERO; j < widthOfImage; j++) {
                    arrayOfPix[i * widthOfImage
                            + j] = (int) ((TTF) << TWENTYFOUR | (allBytes[current + 2] & TTF) << SIXTEEN
                                    | (allBytes[current + 1] & TTF) << EIGHT | (allBytes[current] & TTF));
                    current = current + THREE;
                }
                current = current + sizeOfEmpty;
            }
            infile.close();

            // Using java method to convert binary to image
            return Toolkit.getDefaultToolkit().createImage(
                    (ImageProducer) new MemoryImageSource(widthOfImage, heightOfImage, arrayOfPix, ZERO, widthOfImage));
        } catch (IOException e) {
            return null;
        }

    }

    /**
     * Write image to a new UI window
     * @param filePath result bmp file path
     * @return result image
     */
    public Image myWrite(Image image, String filePath) {
        try {
            File imgFile = new File(filePath + "bmp");

            // Using BufferedImage and Graphics2D
            BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graph = buffer.createGraphics();
            graph.drawImage(image, ZERO, ZERO, null);
            graph.dispose();
            ImageIO.write(buffer, "bmp", imgFile);
        } catch (Exception e) {

        } finally {
            return image;
        }

    }
}
