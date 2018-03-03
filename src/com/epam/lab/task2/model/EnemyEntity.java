package com.epam.lab.task2.model;

import java.awt.*;

//abstract entity of enemy object
public interface EnemyEntity {
    void tickTack();

    void render(Graphics g);

    Rectangle getBounds();

    int getxPos();

    int getyPos();
}
