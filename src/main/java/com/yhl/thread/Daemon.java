package com.yhl.thread;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-5-12
 * Time: 上午11:02
 * 1 设置为守护线程后，只要没有用户线程运行Jvm就会退出。main方法也是用户线程
 * 比如gc就是守护线程。
 * 2 守护线程创建的子线程 依然是守护线程
 */
public class Daemon {

    public static class DaemonTask extends Thread{

        public DaemonTask(){
           setDaemon(true);
        }

        @Override
        public void run() {
            while (true){
                try {
                    sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DaemonTask: "+new Date());
            }
        }
    }

    public static class NoDaemonTask extends Thread{

        public NoDaemonTask(){
        }

        @Override
        public void run() {
            try {
                sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("NoDaemonTask: "+new Date());
        }
    }

    public static void main(String[] args) {
        new DaemonTask().start();
        new NoDaemonTask().start();
    }
}
