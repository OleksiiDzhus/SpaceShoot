package com.epam.lab.task2.controller;

import com.epam.lab.task2.model.PlayerEntity;
import com.epam.lab.task2.model.EnemyEntity;


//get collisions of bullet and enemy; enemy and player
public class Physics {
    public static boolean Collision(PlayerEntity entA, EnemyEntity entB) {
        return entA.getBounds().intersects(entB.getBounds());
    }

    public static boolean Collision(EnemyEntity entB, PlayerEntity entA) {
        return entB.getBounds().intersects(entA.getBounds());
    }
}
