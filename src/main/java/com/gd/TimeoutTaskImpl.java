package com.gd;

/**
 * Created by gd on 02.10.2017.
 */
public class TimeoutTaskImpl extends TimeoutTask<String> {

    @Override
    protected String taskWithTimeout() throws InterruptedException {
        Thread.sleep(2000);
        return "result";
    }
}
