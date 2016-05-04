package com.yhl.cau.quick;

import com.yhl.cau.GenArray;

import java.util.Arrays;
import java.util.concurrent.*;

/**
* Created with IntelliJ IDEA.
* User: Administrator
* Date: 16-3-31
* Time: 下午3:08
* 线程池固定数目 很危险，当线程都用完而任务都等待时，程序将停止运行
*/
public class MulThreadCau implements Callable<int[]> {

    private int [] s;
    private static final int THREAD_COUNT = 4;
    private int partLength;
    ExecutorService service;

    MulThreadCau(int[] ss,int parLength ,ExecutorService service){
        this.s = ss;
        this.service = service;
        this.partLength = parLength;
    }

    @Override
    public int[] call() throws Exception {

        if(s.length>1 && s.length>partLength){
            int i = 0, j = s.length-1, x = s[0];
            while (i < j)
            {
                while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if(i < j) // 那肯定就是s[j]<x 所以跳出循环了 所以换位置
                    s[i++] = s[j];

                while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            int left[] = Arrays.copyOfRange(s,0,i+1);

            Future<int[]> fl = service.submit(new MulThreadCau(left,partLength,service)); // 递归调用

            int[] rr = null;
            Future<int[]> fr = null;
            if(i<s.length-1){
                int right[] = Arrays.copyOfRange(s,i+1,s.length);
                fr = service.submit(new MulThreadCau(right,partLength,service));
            }
            s = null; // 很重要，2亿数据时 不这样写会内存溢出
            int[] rl = fl.get();
            if(fr!=null){
                rr = fr.get();
            }
            if(rr==null){
                s = rl;
            }else{
                int[] result = Arrays.copyOfRange(rl, 0, rl.length + rr.length);
                System.arraycopy(rr, 0, result, rl.length,rr.length);
                s = result ;
            }
        }else{
            new QuickSort().quickSort(s,0,s.length-1);
        }
        return s;
    }

    void print(){
        synchronized (System.out){
            System.out.print("end <<"+Thread.currentThread().getName()+","+Thread.currentThread().getId()+","+s.length+">>");
            for(int i : s){
                System.out.print(i+"--");
            }
            System.out.println("<<");
        }
    }

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        //ExecutorService service = Executors.newFixedThreadPool(30);
        int[] ss = GenArray.gen();
        long total = 0;
        int loop = 1;
        for(int i=0;i<loop;i++){
            try{
                int partLength = ss.length/THREAD_COUNT+THREAD_COUNT;// 每一分区数据的最大数量;
                long start = System.currentTimeMillis();
                Future<int[]> f = service.submit(new MulThreadCau(ss,partLength,service));
                int[] r = f.get();
                total = total+(System.currentTimeMillis()-start);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("平均耗时："+total/loop+"ms,线程数："+((ThreadPoolExecutor)service).getLargestPoolSize());
        System.out.println("cpu 核心数："+Runtime.getRuntime().availableProcessors());
        service.shutdown();
    }

}
