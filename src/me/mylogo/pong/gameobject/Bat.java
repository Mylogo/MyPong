package me.mylogo.pong.gameobject;

import me.mylogo.pong.PongCanvas;
import me.mylogo.pong.Vec2;

import java.awt.*;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public abstract class Bat extends GameObject {

    public static final double Y_SPEED = 200D;
    private Side side;
    private Dimension size;

    public Bat(PongCanvas canvas, Side side) {
        super(canvas);
        this.side = side;
        size = new Dimension(15, 100);
    }

    @Override
    public void paint(Graphics2D g) {
        Dimension d = getSize();
        Vec2 pos = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(
                (int) pos.getX(), (int) pos.getY(),
                (int) d.getWidth(), (int) d.getHeight()
        );
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

//    public double getY() {
//        return getLocation().getY();
//    }

    public void addY(double y) {
        getLocation().add(0, y);
    }

    public boolean intersectsWith(Ball ball) {
        Dimension size = getSize();
        Vec2 pos = getLocation();
        Vec2 ballPos = ball.getLocation();
        // simplify the ball into a point
        ballPos = new Vec2(ballPos.getX() + ball.getRadius(), ballPos.getY() + ball.getRadius());
        // it is inside the width
        if (ballPos.getX() >= pos.getX() && ballPos.getX() < pos.getX() + size.getWidth()) {
            // is it inside the height ..?
            if (ballPos.getY() >= pos.getY() && ballPos.getY() < pos.getY() + size.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public Side getSide() {
        return side;
    }

    public enum Side {
        LEFT,
        RIGHT
    }

}
