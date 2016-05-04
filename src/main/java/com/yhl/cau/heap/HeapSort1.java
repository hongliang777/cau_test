package com.yhl.cau.heap;

import com.yhl.cau.GenArray;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-5
 * Time: 上午10:17
 堆排序 先建立最大最小堆，然后根据堆排序
 */
public class HeapSort1 {


    //建立最小堆
    void MakeMinHeap(int a[], int n)
    {
        for (int i = n / 2 - 1; i >= 0; i--)
            MinHeapFixdown(a, i, n);
    }

    //  从i节点开始调整,n为节点总数 从0开始计算 i节点的子节点为 2*i+1, 2*i+2
    void MinHeapFixdown(int a[], int i, int n)
    {
        int j, temp;

        temp = a[i];
        j = 2 * i + 1;
        while (j < n)
        {
            if (j + 1 < n && a[j + 1] < a[j]) //在左右孩子中找最小的
                j++;

            if (a[j] >= temp)
                break;

            a[i] = a[j];     //把较小的子结点往上移动,替换它的父结点
            i = j;
            j = 2 * i + 1;
        }
        a[i] = temp;
    }
    //在最小堆中删除数
    void MinHeapDeleteNumber(int a[], int n)
    {
        swap(a,a[0], a[n - 1]);
        MinHeapFixdown(a, 0, n - 1);
    }

    //  新加入i结点  其父结点为(i - 1) / 2
    void MinHeapFixup(int a[], int i){
        int j, temp;
        temp = a[i];
        j = (i - 1) / 2;      //父结点
        while (j >= 0 && i != 0){
            if (a[j] <= temp)
                break;
            a[i] = a[j];     //把较大的子结点往下移动,替换它的子结点
            i = j;
            j = (i - 1) / 2;
        }
        a[i] = temp;
    }

    //在最小堆中加入新的数据nNum
    void MinHeapAddNumber(int a[], int n, int nNum)
    {
        a[n] = nNum;
        MinHeapFixup(a, n);
    }

    void MinheapsortTodescendarray(int a[], int n)
    {
        for (int i = n - 1; i >= 1; i--)
        {
            swap(a,a[i], a[0]);
            MinHeapFixdown(a, 0, i);
        }
    }

    public void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    void MinHeapFixupShort(int a[], int i)
    {
        for (int j = (i - 1) / 2; (j >= 0 && i != 0)&& a[i] > a[j]; i = j, j = (i - 1) / 2)
            swap(a,a[i], a[j]);
    }

    public void sort(int[] a){
        for(int i=0;i<a.length;i++){
            MakeMinHeap(a, a.length - i - 1);
            swap(a, 0, a.length - i - 1);
        }
    }

    public static void main(String[] args) {

        int a[] = GenArray.gen(1024);

        HeapSort1 hs = new HeapSort1();
        long start = System.currentTimeMillis();
        hs.sort(a);
        System.out.println("耗时：" + (System.currentTimeMillis()-start) + "ms");

        //PrintUtil.print(a);

    }
}
