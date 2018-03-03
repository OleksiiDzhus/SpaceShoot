package com.epam.lab.task2.view;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoader {
    private static  final Logger LOG = Logger.getLogger(BufferedImageLoader.class);
    private BufferedImage image;

    // get and load image by path
    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return image;
    }

}
