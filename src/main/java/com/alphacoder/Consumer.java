package com.alphacoder;

public class Consumer implements Runnable{
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue){
        this.queue= queue;
    }
    @Override
    public void run() {
        System.out.println("Message Consumed: "+ queue.consume());
    }
}
