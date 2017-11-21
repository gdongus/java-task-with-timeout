package com.gd;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by gd on 02.10.2017.
 */
@Slf4j
public abstract class TimeoutTask<V> implements Callable<V> {

    public V call() {
        final Timer timer = new Timer(true);
        final Thread currentThread = Thread.currentThread();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentThread != null && currentThread.isAlive()) {
                    currentThread.interrupt();
                }
            }
        }, 2);
        try {
            return taskWithTimeout();
        } catch (InterruptedException ie) {
            return null;
        } finally {
            timer.cancel();
            timer.purge();
        }
    }


    protected abstract V taskWithTimeout() throws InterruptedException;
}
