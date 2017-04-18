package me.mylogo.pong.gameobject;

import me.mylogo.pong.PongCanvas;

import java.awt.event.KeyEvent;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public class PlayerBat extends Bat {

    public PlayerBat(PongCanvas canvas, Side side) {
        super(canvas, side);
    }

    @Override
    public void update(double elapsed) {
        double sign = 0;
        if (getSide() == Side.LEFT) {
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_W)) {
                sign = -1;
            }
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_S)) {
                sign = 1;
            }
        } else {
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_UP)) {
                sign = -1;
            }
            if (getCanvas().getKeyRegistry().isTyped(KeyEvent.VK_DOWN)) {
                sign = 1;
            }
        }
        if (sign != 0) {
            addY(sign * Y_SPEED * elapsed);
        }
    }

}
