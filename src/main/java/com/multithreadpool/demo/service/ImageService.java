package com.multithreadpool.demo.service;

import java.util.concurrent.Future;

public interface ImageService {
    public Future<String> asynctmb(int i);
}
