package me.mylogo.pong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis Heckmann on 14.04.17
 * Copyright (c) 2017 Dennis Heckmann
 */
public class GameThread extends Thread {

    private final Object LOCK = new Object();
    private List<Updateable> toUpdate;
    private long lastTime;

    public GameThread() {
        toUpdate = new ArrayList<>();
    }

    public void run() {
        while (true) {
            long now = System.currentTimeMillis();
            double elapsed = ((double) (now - lastTime)) / 1000D;
            synchronized (LOCK) {
                toUpdate.forEach(updateable -> updateable.update(elapsed));
            }
            lastTime = now;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Updateable up) {
        synchronized (LOCK) {
            toUpdate.add(up);
        }
    }

    public void remove(Updateable up) {
        synchronized (LOCK) {
            toUpdate.remove(up);
        }
    }



}
