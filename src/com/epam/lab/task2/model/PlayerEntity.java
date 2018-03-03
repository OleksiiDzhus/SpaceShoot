package com.epam.lab.task2.model;

import java.awt.*;

// abstract entity of player object
public interface PlayerEntity {
    void tickTack();

    void render(Graphics g);

    Rectangle getBounds();

    int getxPos();

    int getyPos();
}
