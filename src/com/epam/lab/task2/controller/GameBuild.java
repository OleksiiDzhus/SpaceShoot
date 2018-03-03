package com.epam.lab.task2.controller;

import com.epam.lab.task2.consts.Constanta;
import com.epam.lab.task2.controller.inputhandler.KeyInput;
import com.epam.lab.task2.controller.inputhandler.MouseInput;
import com.epam.lab.task2.enums.CHOOSE;
import com.epam.lab.task2.model.Bullet;
import com.epam.lab.task2.model.EnemyEntity;
import com.epam.lab.task2.model.Player;
import com.epam.lab.task2.model.PlayerEntity;
import com.epam.lab.task2.view.BufferedImageLoader;
import com.epam.lab.task2.view.Menu;
import com.epam.lab.task2.view.Textures;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static com.epam.lab.task2.consts.Constanta.*;

public class GameBuild extends Canvas implements Runnable {
    private static Logger LOG = Logger.getLogger(GameBuild.class);

    private static int updates = 0;
    private static int frames = 0;

    private boolean isRunning = false;
    private Thread thread;
    private BufferedImage image = new BufferedImage(Constanta.WIDTH, Constanta.HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private BufferStrategy strategy;
    private boolean isShooting = false;

    private Player p;
    private Controller c;
    private Textures texture;

    private int countEnemy = 3;
    private int killedEnemy = 0;

    public LinkedList<PlayerEntity> ea;
    public LinkedList<EnemyEntity> eb;
    public static CHOOSE choose = CHOOSE.MENU;
    private com.epam.lab.task2.view.Menu menu;
    public static int health = 100 * 2;

    //build main frame of a game
    public GameBuild() {
        this.setPreferredSize(new Dimension(Constanta.WIDTH * SCALE, Constanta.HEIGHT * SCALE));
        this.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(GAME_TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.start();
    }

    private synchronized void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    //initialize game
    private void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteSheet = loader.loadImage("res/sprite_sheet.png");
        background = loader.loadImage("res/background.png");
        this.requestFocus();

        texture = new Textures(this);
        c = new Controller(texture, this);
        p = new Player(480, 600, texture, this, c);
        menu = new Menu();

        ea = c.getE();
        eb = c.getEb();

        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        c.createEnemy(countEnemy);

    }

    //run game and get ticks per second and FPS
    @Override
    public void run() {
        init();
        long previousTime = System.nanoTime();
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (isRunning) {
            long thisTime = System.nanoTime();
            delta += (thisTime - previousTime) / TIME_PER_TICK;
            previousTime = thisTime;
            if (delta >= 1) {
                tickTack();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                LOG.info(String.format("%s Ticks, %s FPS", updates, frames));
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tickTack() {
        if (choose == CHOOSE.GAME) {
            p.tickTack();
            c.tickTack();
        }

        if (killedEnemy >= countEnemy) {
            countEnemy += 2;
            killedEnemy = 0;
            c.createEnemy(countEnemy);
        }
    }

    //render and rerender all objects
    private void render() {
        strategy = this.getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = strategy.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background, 0, 0, null);

        if (choose == CHOOSE.GAME) {
            p.render(g);
            c.render(g);

            g.setColor(Color.gray);
            g.fillRect(5, 5, 200, 20);

            g.setColor(Color.cyan);
            g.fillRect(5, 5, health, 20);

            g.setColor(Color.white);
            g.drawRect(5, 5, 200, 20);
        } else if (choose == CHOOSE.MENU) {
            menu.render(g);
        }
        g.dispose();
        strategy.show();
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    //move player by pressing keys
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (choose == CHOOSE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                p.setVelX(5);
            } else if (key == KeyEvent.VK_LEFT) {
                p.setVelX(-5);
            } else if (key == KeyEvent.VK_UP) {
                p.setVelY(-5);
            } else if (key == KeyEvent.VK_DOWN) {
                p.setVelY(5);
            } else if (key == KeyEvent.VK_SPACE && !isShooting) {
                isShooting = true;
                c.addEntity(new Bullet((double) p.getxPos(), (double) p.getyPos(), texture));
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_UP) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_SPACE) {
            isShooting = false;
        }
    }

    public int getKilledEnemy() {
        return killedEnemy;
    }

    public void setKilledEnemy(int killedEnemy) {
        this.killedEnemy = killedEnemy;
    }

}
