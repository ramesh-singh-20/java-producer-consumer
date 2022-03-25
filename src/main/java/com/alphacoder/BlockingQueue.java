package com.alphacoder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {
    private Queue<Integer> queue;
    private int maxSize;
    private final Lock lock= new ReentrantLock();
    private final Condition writeCondition= lock.newCondition();
    private final Condition readCondition= lock.newCondition();

    public BlockingQueue(){
        queue= new LinkedList<>();
        maxSize= 2;
    }


    public void produce(int element) {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                System.out.println("Wait for not full condition");
                writeCondition.await();
            }

            queue.offer(element);
            System.out.println("Message Produced:"+element);
            readCondition.signalAll();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Integer consume(){
        lock.lock();
        int res= 0;
        try {
            while (queue.size() == 0) {
                System.out.println("Wait for not empty condition.");
                readCondition.wait();
            }
            res= queue.poll();
            writeCondition.signalAll();
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return res;
    }
}
