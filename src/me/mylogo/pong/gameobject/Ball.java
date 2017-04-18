package me.mylogo.pong.gameobject;

import me.mylogo.pong.PongCanvas;
import me.mylogo.pong.Vec2;

import java.awt.*;
import java.util.Random;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 * Will be 5% of the screen height
 */
public class Ball extends GameObject {

    private final int MIN_X_SPEED = 130, MAX_X_SPEED = 240, MIN_Y_SPEED = 280, MAX_Y_SPEED = 750;
    private double vX, vY;

    public Ball(PongCanvas canvas) {
        super(canvas);
        vX = 130;
        vY = 400;
    }

    @Override
    public void paint(Graphics2D g) {
        Vec2 pos = getLocation();
        g.setColor(Color.WHITE);
        g.fillOval(
                (int) pos.getX(), (int) pos.getY(),
                (int) getRadius(), (int) getRadius()
        );
    }

    public double getRadius() {
        return 15;
    }

    @Override
    public void update(double elapsed) {
        Vec2 pos = getLocation();
        double newY = pos.getY() + getvY() * elapsed;
        double newX = pos.getX() + getvX() * elapsed;
        if (newY < 0) {
            newY = getRadius() / 2;
            setvY(-getvY());
        } else if (newY > getCanvas().getHeight()) {
            setvY(-getvY());
            newY = getCanvas().getHeight() - Math.ceil(getRadius() / 2);
        }
        boolean ballOutside = false;
        if (newX <= 0) {
            ballOutside = true;
        } else if (newX >= getCanvas().getWidth() - getRadius()) {
            ballOutside = true;
        }
        if (ballOutside) {
            getCanvas().ballIsOutside();
        } else {
            // if it is going to the left side
            if (getvX() < 0) {
                if (getCanvas().getLeftBat().intersectsWith(this)) {
                    setvX(-getvX());
                }
            } else {
                // definitely going to the right side
                if (getCanvas().getRightBat().intersectsWith(this)) {
                    setvX(-getvX());
                }
            }
        }
        pos.setX(newX);
        pos.setY(newY);
    }

    public double getvX() {
        return vX;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }

    public void randomVelocity() {
        Random rand = new Random();
        setvX( (rand.nextInt(MAX_X_SPEED - MIN_X_SPEED) + MIN_X_SPEED) * (Math.random() < 0.5 ? 1 : -1));
        setvY( (rand.nextInt(MAX_Y_SPEED - MIN_Y_SPEED) + MIN_Y_SPEED) * (Math.random() < 0.5 ? 1 : -1));
    }

}
