package com.epam.lab.task2.view;

import com.epam.lab.task2.consts.Constanta;

import java.awt.*;
import java.awt.Graphics2D;

//draw start menu for game
public class Menu {
    public Rectangle playButton = new Rectangle(Constanta.WIDTH / 2 + 190, 200, 110, 60);
    public Rectangle helpButton = new Rectangle(Constanta.WIDTH / 2 + 190, 300, 110, 60);
    public Rectangle quitButton = new Rectangle(Constanta.WIDTH / 2 + 190, 400, 110, 60);


    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("arial", Font.BOLD, 60);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("SpaceShooter", Constanta.WIDTH / 2 + 60, 150);

        Font font1 = new Font("arial", Font.BOLD, 45);
        g.setFont(font1);
        g.drawString("Play", playButton.x + 10, playButton.y + 50);
        g2d.draw(playButton);
        g2d.drawString("Help", helpButton.x + 10, helpButton.y + 50);
        g2d.draw(helpButton);
        g2d.drawString("Quit", quitButton.x + 10, quitButton.y + 50);
        g2d.draw(quitButton);
    }
}
