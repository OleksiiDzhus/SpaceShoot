package com.epam.lab.task2.controller.inputhandler;

import com.epam.lab.task2.controller.GameBuild;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    public GameBuild game;

    public KeyInput(GameBuild game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }
}
