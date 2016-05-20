package com.yhl.thread;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-5-12
 * Time: 下午4:29
 * 1 中断线程最好的，最受推荐的方式是，使用共享变量（shared variable）发出信号，告诉线程必须停止正在运行的任务。线程必须周期性的核查这一变量（尤其在冗余操作期间），然后有秩序地中止任务。
 * http://www.cnblogs.com/onlywujun/p/3565082.html
 *
  destroy stop 已禁用
 如需停止 可调用interrupt()
 但interrupt是不会中断一个正在运行的线程
 可以用阻塞触发，但是获取同步时不会触发，死锁不会触发

 1 停止有阻塞的线程，因为不能检查共享变量，使用interrupt配合异常
 2 停止无阻塞的可以while (!Thread.currentThread().isInterrupted())，比用共享变量简洁
 3

 */
public class StopTask {

    static Object o = new Object();
    static Object o1 = new Object();

    /**
     * 不会中断一个正在运行的线程
     */
    public static class InterruptTask extends Thread{

        private int ins;

        private long pre =  System.currentTimeMillis();

        public InterruptTask(int ins){
            this.ins = ins;
        }

        @Override
        public void run() {
            while(true){
                synchronized (o){
                    try {

                        if(System.currentTimeMillis() - pre >2000l){
                            System.out.println("DestroyTask"+ins+": interrupt");
                            this.interrupt();
                        }else{
                            System.out.println("DestroyTask"+ins+": "+new Date());
                            sleep(1000l);
                            pre = System.currentTimeMillis();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("DestroyTask"+ins+": interrupt catch");
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }


    /**
     * 正确使用interrupt 实际上 设置了标识位，循环检查
     */
    public static class InterruptTask1 extends Thread{

        private int ins;

        private long pre =  System.currentTimeMillis();

        public InterruptTask1(int ins){
            this.ins = ins;
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                synchronized (o){
                    try {

                        if(System.currentTimeMillis() - pre >2000l){
                            System.out.println("DestroyTask"+ins+": interrupt");
                            this.interrupt();
                        }else{
                            System.out.println("DestroyTask"+ins+": "+new Date());
                            pre = System.currentTimeMillis();
                            sleep(1000l);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("DestroyTask"+ins+": interrupt catch");
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }



    /**
     * 使用线程中断信号量终止线程
     */
    public static class InterruptTask2 extends Thread{

        private int ins;

        private long pre =  System.currentTimeMillis();

        volatile boolean stop = false;// 线程中断信号量


        public InterruptTask2(int ins){
            this.ins = ins;
        }

        @Override
        public void run() {
            while(!stop){
                synchronized (o){
                    try {

                        if(System.currentTimeMillis() - pre >2000l){
                            System.out.println("DestroyTask"+ins+": stop");
                            stop = true;
                        }else{
                            System.out.println("DestroyTask"+ins+": "+new Date());
                            pre = System.currentTimeMillis();
                            sleep(1000l);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    /**
     * 使用线程中断信号量终止线程
     */
    public static class InterruptTask3 extends Thread{

        private int ins;

        private long pre =  System.currentTimeMillis();

        volatile boolean stop = false;// 线程中断信号量


        public InterruptTask3(int ins){
            this.ins = ins;
        }

        @Override
        public void run() {
            try {
                synchronized (o) {
                    Thread.sleep(10);// 不会在这里死掉
                    synchronized (o1) {// 会锁在这里，虽然阻塞了，但不会抛异常
                        System.out.println(Thread.currentThread());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
//        new InterruptTask(1).start();
//        new InterruptTask(2).start();
//        new InterruptTask(3).start();
//        new InterruptTask(4).start();
//        new InterruptTask(5).start();
//        new InterruptTask(6).start();

//        new InterruptTask1(1).start();
//        new InterruptTask1(2).start();
//        new InterruptTask1(3).start();
//        new InterruptTask1(4).start();
//        new InterruptTask1(5).start();
//        new InterruptTask1(6).start();

//        new InterruptTask2(1).start();
//        new InterruptTask2(2).start();
//        new InterruptTask2(3).start();
//        new InterruptTask2(4).start();
//        new InterruptTask2(5).start();
//        new InterruptTask2(6).start();

        try {
            InterruptTask3 thread1 = new InterruptTask3(1);
            InterruptTask3 thread2 = new InterruptTask3(1);
            System.out.println("Starting thread...");
            thread1.start();
            thread2.start();
            Thread.sleep(3000);
            System.out.println("Interrupting thread...");
            thread1.interrupt();
            thread2.interrupt();
            Thread.sleep(3000);
            System.out.println("Stopping application...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //dt.stop();
    }
}
