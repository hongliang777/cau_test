package com.yhl.cau;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-1
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public class GenArray {

    public static int[] gen(){
        int[] ss = new int[1024*1024*20];
        int i = 0;
        Random random = new Random();
        while (i<ss.length){
            ss[i] = (random.nextInt(Integer.MAX_VALUE));
            i++;
        }
        return ss;
    }

    public static int[] gen(int size){
        int[] ss = new int[size];
        int i = 0;
        Random random = new Random();
        while (i<ss.length){
            ss[i] = (random.nextInt(10000));
            i++;
        }
        return ss;
    }
}
