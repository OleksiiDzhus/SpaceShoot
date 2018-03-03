package com.epam.lab.task2.model;

import com.epam.lab.task2.consts.Constanta;
import com.epam.lab.task2.controller.Controller;
import com.epam.lab.task2.controller.Physics;
import com.epam.lab.task2.view.Animation;
import com.epam.lab.task2.controller.GameBuild;
import com.epam.lab.task2.view.Textures;

import java.awt.*;
import java.util.Random;


public class Enemy extends GameObjConstr implements EnemyEntity {
    private Textures texture;
    private GameBuild game;
    private Random random = new Random();
    private Controller c;
    private Animation animation;

    private int speed = random.nextInt(6) + 1;

    public Enemy(double xPos, double yPos, Textures texture, Controller c, GameBuild game) {
        super(xPos, yPos);
        this.texture = texture;
        this.c = c;
        this.game = game;
        animation = new Animation(-10, texture.enemy);
    }

    public void tickTack() {
        yPos += speed;
        if (yPos > (Constanta.HEIGHT * Constanta.SCALE)) {
            yPos = 0;
            xPos = random.nextInt(Constanta.WIDTH * Constanta.SCALE);
        }

        for (int i = 0; i < game.ea.size(); i++) {
            PlayerEntity tempEnt = game.ea.get(i);
            handleMurders(tempEnt);
        }

        animation.runAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(texture.enemy, (int) xPos, (int) yPos, null);
    }

    public int getxPos() {
        return (int) xPos;
    }

    public int getyPos() {
        return (int) yPos;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) xPos, (int) yPos, 45, 31);
    }

    private void handleMurders(PlayerEntity playerEntity){
        if (Physics.Collision(this, playerEntity)) {
            c.removeEntity(playerEntity);
            c.removeEntity(this);
            game.setKilledEnemy(game.getKilledEnemy() + 1);
        }else {
            //nothing to handle
        }
    }
}
