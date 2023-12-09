package com.example;

import java.util.concurrent.Future;

public interface AsyncService {
    void asyncTask() throws InterruptedException;

    Future<String> asyncWithReturn(String name) throws InterruptedException;
}
