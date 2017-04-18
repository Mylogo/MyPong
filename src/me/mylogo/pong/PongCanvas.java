package me.mylogo.pong;

import me.mylogo.pong.gameobject.Ball;
import me.mylogo.pong.gameobject.Bat;
import me.mylogo.pong.gameobject.IntelligentBat;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public class PongCanvas extends Canvas implements Updateable, KeyListener {

    private int leftPoints, rightPoints;
    private KeyRegistry keyRegistry;
    private PongFrame frame;
    private Ball ball;
    private Bat leftBat, rightBat;

    public PongCanvas(PongFrame frame) {
        this.frame = frame;
        this.keyRegistry = new KeyRegistry();
        setSize(frame.getSize());
        initBats();
        ball = new Ball(this);
        ball.setLocation(new Vec2(getWidth() / 2, getHeight() / 2));
        ball.randomVelocity();
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        frame.getThread().add(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        ball.paint(g2);
        leftBat.paint(g2);
        rightBat.paint(g2);
        drawPoints(g2);
    }

    private void drawPoints(Graphics2D g2) {
        g2.drawString(String.valueOf(leftPoints), getWidth() / 2 - 50, 20);
        g2.drawString(String.valueOf(rightPoints), getWidth() / 2 + 40, 20);
    }

    private void initBats() {
        leftBat = new IntelligentBat(this, Bat.Side.LEFT, 260);
        rightBat = new IntelligentBat(this, Bat.Side.RIGHT, 260);
        leftBat.setLocation(new Vec2(15, getHeight() / 2 - leftBat.getSize().getHeight() / 2));
        rightBat.setLocation(new Vec2(getWidth() - 15 - rightBat.getSize().getWidth(), leftBat.getSize().getHeight() / 2));
    }

    @Override
    public void update(double elapsed) {
        requestFocus();
        leftBat.update(elapsed);
        rightBat.update(elapsed);
        ball.update(elapsed);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nothing because it does not interact with arrow keys
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("PRESSED");
//        keyRegistry.setTyped(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("RELEASED");
//        keyRegistry.setTyped(e.getKeyCode(), false);
    }

    public KeyRegistry getKeyRegistry() {
        return keyRegistry;
    }

    public Bat getLeftBat() {
        return leftBat;
    }

    public Bat getRightBat() {
        return rightBat;
    }

    public Ball getBall() {
        return ball;
    }

    public void ballIsOutside() {
        if (ball.getLocation().getX() < getWidth() / 2) {
            // it is on the left side
            rightPoints++;
        } else {
            //it is on the right side
            leftPoints++;
        }
        ball.setLocation(new Vec2(getWidth() / 2, getHeight() / 2));
        ball.randomVelocity();
    }

}
