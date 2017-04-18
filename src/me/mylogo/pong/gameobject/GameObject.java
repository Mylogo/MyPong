package me.mylogo.pong.gameobject;

import me.mylogo.pong.PongCanvas;
import me.mylogo.pong.Updateable;
import me.mylogo.pong.Vec2;

import java.awt.*;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public abstract class GameObject implements Updateable {

    private PongCanvas canvas;
    private Vec2 location;

    public GameObject(PongCanvas canvas) {
        this.canvas = canvas;
        location = new Vec2();
    }

    public abstract void paint(Graphics2D g);

    public Vec2 getLocation() {
        return location;
    }

    public void setLocation(Vec2 location) {
        this.location = location;
    }

    public PongCanvas getCanvas() {
        return canvas;
    }
}
