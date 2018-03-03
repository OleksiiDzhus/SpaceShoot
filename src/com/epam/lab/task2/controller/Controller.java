package com.epam.lab.task2.controller;

import com.epam.lab.task2.consts.Constanta;
import com.epam.lab.task2.model.Enemy;
import com.epam.lab.task2.model.EnemyEntity;
import com.epam.lab.task2.model.PlayerEntity;
import com.epam.lab.task2.view.Textures;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;


public class Controller {
    private LinkedList<PlayerEntity> e = new LinkedList<PlayerEntity>();
    private LinkedList<EnemyEntity> eb = new LinkedList<EnemyEntity>();

    private PlayerEntity playerEntity;
    private EnemyEntity enemyEntity;
    private Textures texture;
    private Random random = new Random();

    private GameBuild game;

    public Controller(Textures texture, GameBuild game) {
        this.texture = texture;
        this.game = game;
    }

    //remove killed enemies
    public void tickTack() {
        for (int i = 0; i < e.size(); i++) {
            playerEntity = e.get(i);
            if (playerEntity.getyPos() < 0) {
                removeEntity(playerEntity);
            }
            playerEntity.tickTack();
        }

        for (int i = 0; i < eb.size(); i++) {
            enemyEntity = eb.get(i);

            enemyEntity.tickTack();
        }
    }

    //draw objects of player and enemies
    public void render(Graphics g) {
        for (int i = 0; i < e.size(); i++) {
            playerEntity = e.get(i);
            playerEntity.render(g);
        }

        for (int i = 0; i < eb.size(); i++) {
            enemyEntity = eb.get(i);
            enemyEntity.render(g);
        }
    }

    //creat random number of enemies due to actions of player
    public void createEnemy(int countEnemy) {
        for (int i = 0; i < countEnemy; i++) {
            addEntity(new Enemy(random.nextInt(Constanta.WIDTH * Constanta.SCALE), -10, texture, this, game));
        }
    }

    public void addEntity(PlayerEntity block) {
        e.add(block);
    }

    public void removeEntity(PlayerEntity block) {
        e.remove(block);
    }

    public void addEntity(EnemyEntity block) {
        eb.add(block);
    }

    public void removeEntity(EnemyEntity block) {
        eb.remove(block);
    }

    public LinkedList<PlayerEntity> getE() {
        return e;
    }

    public LinkedList<EnemyEntity> getEb() {
        return eb;
    }
}
