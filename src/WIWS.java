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
        /*for (int counter3 = 0; array1[counter3] != null; counter3++) {
            System.out.println(array1[counter3]);
        }*/
        System.out.println("================================================================");
        // Najkompliciranoto
        System.out.println("Image Compression...");
        float quality = 0.3f;
        float qualityThumb = 1f;
        String[] arrayComp = new String[50];
        String[] arrayThumb = new String[50];
        for (int counter2 = 0; array1[counter2] != null; counter2++) {
            File slika = new File(array1[counter2]);
            File compressedSlika = new File("Comp" + array1[counter2]);

            InputStream inputStream = new FileInputStream(slika);
            OutputStream outputStream = new FileOutputStream(compressedSlika);

            BufferedImage bufferSlika = ImageIO.read(inputStream);
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

            if (!writers.hasNext())
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

            arrayComp[counter2] = "Comp" + array1[counter2];
            System.out.println("File Name: " + arrayComp[counter2]);

            System.out.println("Compressed: Done " + (counter2 + 1));
            //========================================
            File slikaThumb = new File(array1[counter2]);
            File compressedSlikaThumb = new File("Thumb" + array1[counter2]);

            InputStream inputStreamThumb = new FileInputStream(slikaThumb);
            OutputStream outputStreamThumb = new FileOutputStream(compressedSlikaThumb);

            BufferedImage bufferSlikaThumb = ImageIO.read(inputStreamThumb);
            Iterator<ImageWriter> writersThumb = ImageIO.getImageWritersByFormatName("jpg");

            if (!writersThumb.hasNext())
                throw new IllegalStateException("Kraden Kod se razbira");

            ImageWriter writerThumb = (ImageWriter) writersThumb.next();
            ImageOutputStream imageOutputStreamThumb = ImageIO.createImageOutputStream(outputStreamThumb);
            writerThumb.setOutput(imageOutputStreamThumb);

            ImageWriteParam parametersThumb = writerThumb.getDefaultWriteParam();

            parametersThumb.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            parametersThumb.setCompressionQuality(qualityThumb);
            writerThumb.write(null, new IIOImage(bufferSlikaThumb, null, null), parametersThumb);

            inputStreamThumb.close();
            outputStreamThumb.close();
            imageOutputStreamThumb.close();
            writerThumb.dispose();

            arrayThumb[counter2] = "Thumb" + array1[counter2];
            System.out.println("File Name: " + arrayThumb[counter2]);

            System.out.println("Thumnails: Done " + (counter2 + 1));
        }
        System.out.println("================================================================");
        for (int counter3 = 0; arrayComp[counter3] != null && arrayThumb[counter3] != null; counter3++) {
            System.out.println("<div class=\"card m-2\" style=\"border: none;\">\n"+
                    "\t<a href=\"" + "https://github.com/lukakrstik/website-photo-pool/blob/master/images/" + arrayComp[counter3] + "\" data-fancybox=\"gallery\">\n"+
                    "\t\t<img class=\"card-img\" src= \"" + "https://github.com/lukakrstik/website-photo-pool/blob/master/images/" + arrayThumb[counter3] + "\">\n"+
                    "\t</a>\n"+
                    "</div>\n\n");
        }
        System.out.println("All URLs Generated, Pushed to Mainframe. All pussies fucked.");
    }
}