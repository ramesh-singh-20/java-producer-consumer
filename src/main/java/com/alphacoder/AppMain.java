package com.alphacoder;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(3);
        ExecutorService service1= Executors.newFixedThreadPool(3);
        final BlockingQueue queue= new BlockingQueue();
        final Producer producer= new Producer(queue);
        final Consumer consumer= new Consumer(queue);

        for(int i=0; i< 5; i++) {
            service.submit(producer);
        }

        for(int i=0; i< 5; i++) {
           service1.submit(consumer);
        }

    }



}
