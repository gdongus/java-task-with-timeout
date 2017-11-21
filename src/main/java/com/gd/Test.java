package com.gd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by gd on 02.10.2017.
 */
@Slf4j
public class Test {

    @SneakyThrows
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        final List<Callable<String>> callables = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            callables.add(new TimeoutTaskImpl());
        }

        final List<Future<String>> futures = executor.invokeAll(callables);

        for (Future<String> future : futures) {
            if (future.isDone()) {
                System.out.println("DONE: " + future.get());
            }
        }

        executor.shutdownNow();
    }
}
