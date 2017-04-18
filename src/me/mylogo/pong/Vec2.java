package me.mylogo.pong;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public class Vec2 {

    private double x, y;

    public Vec2() {
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }
}
