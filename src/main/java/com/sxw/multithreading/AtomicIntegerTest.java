package com.sxw.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 测试,了解AtomicInteger功能
 *
 * 示例解决多线程操作某个自增数问题,两个线程同时对0自增,各1000次,结果应为2000
 *
 * @author shenxiangwei
 * @date 2021/4/14 11:38 上午
 */
public class AtomicIntegerTest {
    public volatile static AtomicInteger b = new AtomicInteger(0);

    //自增
    public static void increase() {
        b.incrementAndGet();
    }

    //AtomicInteger 是线程安全的原子操作类 自旋锁(轻量级锁) 底层 基于CAS（compare-and -swap）技术
    //即CPU 底层的 lock cmpxchg指令
    //存在aba问题,可以使用加版本号来解决
    public static void atomicIntegerTest(){
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                increase();
            }
            latch.countDown();
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                increase();
            }
            latch.countDown();
        });
        thread1.start();
        thread2.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(b.get());
    }

    public static void main(String[] args) {
        //AtomicInteger 测试
        atomicIntegerTest();

    }
}
