package com.epam.lab.task2.model;

import com.epam.lab.task2.controller.Controller;
import com.epam.lab.task2.controller.Physics;
import com.epam.lab.task2.view.Animation;
import com.epam.lab.task2.controller.GameBuild;
import com.epam.lab.task2.view.Textures;

import java.awt.*;

//draw player
public class Player extends GameObjConstr implements PlayerEntity {
    private double velX = 0;
    private double velY = 0;

    private Animation animation;
    private GameBuild game;
    private Controller controller;
    private Textures texture;

    public Player(double xPos, double yPos, Textures texture, GameBuild game, Controller controller) {
        super(xPos, yPos);
        this.texture = texture;
        this.game = game;
        this.controller = controller;
        animation = new Animation(0, texture.player);
    }

    public void tickTack() {
        xPos += velX;
        yPos += velY;
        if (xPos <= 0) {
            xPos = 0;
        }
        if (xPos > 925) {
            xPos = 925;
        }
        if (yPos <= 0) {
            yPos = 0;
        }
        if (yPos > 700) {
            yPos = 700;
        }

        for (int i = 0; i < game.eb.size(); i++) {
            EnemyEntity tempEnt = game.eb.get(i);
            clashHandle(tempEnt);
        }
    }

    public void render(Graphics g) {
        g.drawImage(texture.player, (int) xPos, (int) yPos, null);
    }

    public int getxPos() {
        return (int) xPos;
    }

    public int getyPos() {
        return (int) yPos;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) xPos, (int) yPos, 45, 31);
    }

    private void exitGame() {
        if (GameBuild.health <= 0)
            System.exit(1);
    }

    private void substractHealth() {
        GameBuild.health -= 10;
    }

    private void clashHandle(EnemyEntity enemyEntity){
        if (Physics.Collision(this, enemyEntity)) {
            controller.removeEntity(enemyEntity);
            substractHealth();
            game.setKilledEnemy(game.getKilledEnemy() + 1);
            exitGame();
        }else {
            //nothing to handle
        }
    }

    private void stayInBounds(){

    }
}
