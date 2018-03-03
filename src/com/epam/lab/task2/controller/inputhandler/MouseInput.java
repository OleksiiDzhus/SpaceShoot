package com.epam.lab.task2.controller.inputhandler;

import com.epam.lab.task2.consts.Constanta;
import com.epam.lab.task2.enums.CHOOSE;
import com.epam.lab.task2.controller.GameBuild;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //get mouse input for menu
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        enterGame(mx,my);

        exitGame(mx, my);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void enterGame(int xPos, int yPos){
        if (xPos >= Constanta.WIDTH / 2 + 190 && xPos <= Constanta.WIDTH + 300){
            if (yPos >= 200 && yPos < 260) {
                GameBuild.choose = CHOOSE.GAME;
            }
        }
    }

    private void exitGame(int xPos, int yPos){
        if (xPos >= Constanta.WIDTH / 2 + 190 && xPos <= Constanta.WIDTH + 300) {
            if (yPos >= 400 && yPos < 460) {
                System.exit(1);
            }
        }
    }
}
