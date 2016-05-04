package com.yhl.cau;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-5
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    public static void print(int a[]){
        for(int v : a){
            System.out.print(v + ",");
        }
        System.out.println("--");
    }

    public static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
