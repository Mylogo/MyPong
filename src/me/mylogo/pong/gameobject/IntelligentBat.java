package me.mylogo.pong.gameobject;

import me.mylogo.pong.PongCanvas;
import me.mylogo.pong.Vec2;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public class IntelligentBat extends Bat {

    private double customSpeed;

    public IntelligentBat(PongCanvas canvas, Side side, double customSpeed) {
        super(canvas, side);
        this.customSpeed = customSpeed;
    }

    public IntelligentBat(PongCanvas canvas, Side side) {
        this(canvas, side, Y_SPEED);
    }

    @Override
    public void update(double elapsed) {
        if (getSide() == Side.LEFT) {
            if (getCanvas().getBall().getvX() < 0)
                goToBall(elapsed);
        } else {
            if (getCanvas().getBall().getvX() > 0) {
                goToBall(elapsed);
            }
        }
    }

    private void goToBall(double elapsed) {
        Vec2 ballPos = getCanvas().getBall().getLocation();
        Vec2 pos = getLocation();
        ballPos = new Vec2(
                ballPos.getX() + getCanvas().getBall().getRadius() / 2,
                ballPos.getY() + getCanvas().getBall().getRadius() / 2
        );
        pos = new Vec2(
                pos.getX() + getSize().getWidth() / 2,
                pos.getY() + getSize().getHeight() / 2
        );
        double direction = ballPos.getY() - pos.getY();
        if (direction != 0) {
            direction = Math.abs(direction) / direction;
        }
        addY(direction * elapsed * customSpeed);
    }

}
