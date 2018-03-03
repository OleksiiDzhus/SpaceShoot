package com.epam.lab.task2.view;

import com.epam.lab.task2.view.SpriteSheet;
import com.epam.lab.task2.controller.GameBuild;

import java.awt.image.BufferedImage;

//get objects from spritesheet
public class Textures {
    public BufferedImage player;
    public BufferedImage blast;
    public BufferedImage enemy;

    private SpriteSheet spriteSheet = null;

    public Textures(GameBuild game) {
        spriteSheet = new SpriteSheet(game.getSpriteSheet());

        getTextures();
    }

    private void getTextures() {
        player = spriteSheet.grabImage(1, 1, 45, 31);
        blast = spriteSheet.grabImage(2, 1, 45, 31);
        enemy = spriteSheet.grabImage(3, 1, 45, 31);
    }
}

