package com.alphacoder;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    private BlockingQueue queue;
    private AtomicInteger sequence= new AtomicInteger(0);

    public Producer(BlockingQueue queue){
        this.queue= queue;
    }
    @Override
    public void run() {
        queue.produce(sequence.incrementAndGet());
    }

    public void produce(){

    }
}
