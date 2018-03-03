package com.epam.lab.task2.model;

import com.epam.lab.task2.view.Textures;

import java.awt.*;

//draw bullet from player ship
public class Bullet extends GameObjConstr implements PlayerEntity {
    private Textures texture;

    public Bullet(double xPos, double yPos, Textures texture) {
        super(xPos, yPos);
        this.texture = texture;

    }

    public void tickTack() {
        yPos -= 10;
    }

    public void render(Graphics g) {
        g.drawImage(texture.blast, (int) xPos, (int) yPos, null);
    }

    @Override
    public int getxPos() {
        return (int) xPos;
    }

    public int getyPos() {
        return (int) yPos;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) xPos, (int) yPos, 45, 31);
    }
}
