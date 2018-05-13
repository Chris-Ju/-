
/**
 * @author Chris Ju
 */

import imagereader.Runner;


/**
 * Runner class, to test
 */
public final class ImageReaderRunner {
    public static void main(String[] args) {
        ImplementImageIO io = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        Runner.run(io, processor);
    }

    private ImageReaderRunner() {}
}
