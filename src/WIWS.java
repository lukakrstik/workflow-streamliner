import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;

public class WIWS {
    public static void main(String[] args) throws IOException {
        // Load na site file names na originalnite sliki vo niza
        System.out.println("Reading Text File...");
        File kur = new File("kur");
        FileReader read = new FileReader(kur);
        BufferedReader buffer = new BufferedReader(read);
        String line = buffer.readLine();
        String[] array1 = new String[50];
        int counter1 = 0;
        while (line != null) {
            array1[counter1] = line;
            line = buffer.readLine();
            counter1++;
            System.out.println("Done " + counter1);
        }
        //System.out.println(array1[1]);
        /*for (int counter2 = 0; array1[counter2] != null; counter2++) {
            System.out.println(array1[counter2]);
        }*/
        System.out.println("================================================================");
        // Najkompliciranoto
        System.out.println("Image Compression...");
        float quality = 0.2f;
        for (int counter2 = 0; array1[counter2] != null; counter2++) {
            File slika = new File(array1[counter2]);
            File compressedSlika = new File(array1[counter2] + "Thumb.jpg");

            InputStream inputStream = new FileInputStream(slika);
            OutputStream outputStream = new FileOutputStream(compressedSlika);

            BufferedImage bufferSlika = ImageIO.read(inputStream);
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

            if(!writers.hasNext())
                throw new IllegalStateException("Kraden Kod se razbira");

            ImageWriter writer = (ImageWriter) writers.next();
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            writer.setOutput(imageOutputStream);

            ImageWriteParam parameters = writer.getDefaultWriteParam();

            parameters.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            parameters.setCompressionQuality(quality);
            writer.write(null, new IIOImage(bufferSlika, null, null), parameters);

            inputStream.close();
            outputStream.close();
            imageOutputStream.close();
            writer.dispose();

            System.out.println("Done "+ (counter2+1));
        }

    }
}
