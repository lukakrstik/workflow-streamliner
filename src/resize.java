package com.mkyong.io.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class resize {

    private static final int IMG_WIDTH = 720;
    private static final int IMG_HEIGHT = 480;

    public static void main(String[] args) throws IOException {

        Path sourceJpg = Paths.get("google.jpg");
        Path targetPng = Paths.get("resize.png");

        try (InputStream is = new FileInputStream(sourceJpg.toFile())) {
            resize(is, targetPng, IMG_WIDTH, IMG_HEIGHT);
        }
        Path sourcePng = Paths.get("resize.png");
        Path targetJpg = Paths.get("new.jpg");

        BufferedImage originalImage = ImageIO.read(sourcePng.toFile());

        // jpg needs BufferedImage.TYPE_INT_RGB
        // png needs BufferedImage.TYPE_INT_ARGB

        // create a blank, RGB, same width and height
        BufferedImage newBufferedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // draw a white background and puts the originalImage on it.
        newBufferedImage.createGraphics()
                .drawImage(originalImage,
                        0,
                        0,
                        Color.WHITE,
                        null);

        // save an image
        ImageIO.write(newBufferedImage, "jpg", targetJpg.toFile());

    }

    private static void resize(InputStream input, Path target,
                               int width, int height) throws IOException {

        BufferedImage originalImage = ImageIO.read(input);
        Image newResizedImage = originalImage
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);

        String s = target.getFileName().toString();
        String fileExtension = s.substring(s.lastIndexOf(".") + 1);

        // we want image in png format
        ImageIO.write(convertToBufferedImage(newResizedImage),
                fileExtension, target.toFile());

    }

    public static BufferedImage convertToBufferedImage(Image img) {

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();

        return bi;
    }
}