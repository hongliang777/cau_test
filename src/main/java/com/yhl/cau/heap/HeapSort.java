package com.yhl.cau.heap;

import com.yhl.cau.GenArray;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-5
 * Time: 上午11:49
 * To change this template use File | Settings | File Templates.
 */
public class HeapSort {

    int heapSize;
    int a[];

    public HeapSort(int[] a){
        this.a = a;
        this.heapSize = a.length;
    }

    public void makeMaxHeap(int[] a){
        for(int i=heapSize/2;i>=0;i--){
            max(i);
        }
    }

    public void max(int i){
        int l = 2*i+1;
        int r = 2*i+2;
        int largest = i;

        if(l<heapSize && a[l]>a[i]){
            largest = l;
        }

        if(r<heapSize && a[r]>a[largest]){
            largest = r;
        }

        if(largest!=i){
            swap(a,i,largest);
            max(largest);
        }
    }

    public void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void sort(int[] a){
        makeMaxHeap(a);
        for(int i=0;i<a.length;i++){
            swap(a, 0, heapSize-1);
            heapSize--;
            max(0);
        }
    }

    public static void main(String[] args) {

        int a[] = GenArray.gen(1024*1024);
        long start = System.currentTimeMillis();
        HeapSort hs = new HeapSort(a);
        hs.sort(a);
        System.out.println("耗时：" + (System.currentTimeMillis()-start) + "ms");
        //PrintUtil.print(a);

    }
}
