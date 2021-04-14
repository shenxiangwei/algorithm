package com.sxw.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程交替输出问题
 * 使用两个线程输出A1B2C3D4
 * create by shenxiangwei on 2021/4/13 下午 10:01
 */
public class SwapPrintQuestion {

    public static final char[] number = "1234567".toCharArray();
    public static final char[] letter = "ABCDEFG".toCharArray();

    /**
     * 使用 reentrantLock解决
     */
    public static void solutionOne(){

        Lock lock = new ReentrantLock();

        //两个队列
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        //保证一个线程先执行，即先输出1还是A
        //两种用法
        /**
         * 1、某一线程在开始运行前等待n个线程执行完毕。
         * 将 CountDownLatch 的计数器初始化为n ：new CountDownLatch(n)，每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，
         * 当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
         *
         * 2、实现多个线程开始执行任务的最大并行性。
         * 注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。
         * 做法是初始化一个共享的 CountDownLatch 对象，将其计数器初始化为 1 ：new CountDownLatch(1)，
         * 多个线程在开始执行任务前首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。
         *
         *  ------->此处用的是第一种用法
         */
        CountDownLatch latch = new CountDownLatch(1);

        Thread t1 = new Thread(()->{
            try {
                //等待
                latch.await();
                lock.lock();
                for (char a : number){
                    System.out.print(a);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                lock.lock();
                for (char a : letter){
                    System.out.print(a);
                    latch.countDown();
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        solutionOne();
    }
}
