package com.yhl.thread;

/**
 * main就是用户线程
 */
public class TestMain4 extends Thread {
   
    public TestMain4() {
    }
    public void run() {
        for(int i = 1; i <= 50; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(i);
        }
    }

   
    public static void main(String [] args){
        TestMain4 test = new TestMain4();
        test.setDaemon(true);
        test.start();
        System.out.println("isDaemon = " + test.isDaemon());
        try {
            System.in.read(); // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束,如果test不是守护进程必须等到test运行完了以后才退出
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}