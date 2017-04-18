package me.mylogo.pong;

import javax.swing.*;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public class PongFrame extends JFrame {

    private PongCanvas canvas;
    private GameThread thread;

    public PongFrame()  {
        super("Matrix");
        setSize(720, 480);
        (thread = new GameThread()).start();
        canvas = new PongCanvas(this);
        add(canvas);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public GameThread getThread() {
        return thread;
    }

}
