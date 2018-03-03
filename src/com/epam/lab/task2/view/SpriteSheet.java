package com.epam.lab.task2.view;

import java.awt.image.BufferedImage;

//get images from spritesheet
public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * 45) - 45, (row * 31) - 31, width, height);
        return img;
    }
}
