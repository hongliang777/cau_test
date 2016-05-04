package com.yhl.cau.bfprt;

import com.yhl.cau.Util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-7
 * Time: 下午4:03
 通常，我们需要在一大堆数中求前K大的数，或者求前K小的。比如在搜索引擎中求当天用户点击次数排名
 前10000的热词；在文本特征选择中求IF-IDF值按从大到小排名前K个的等等问题，都涉及到一个核心问
 题，即TOP-K问题。

 在BFPTR算法中，仅仅是改变了快速排序Partion中的pivot值的选取，在快速排序中，我们始终选择第一个元
 素或者最后一个元素作为pivot，而在BFPTR算法中，每次选择五分中位数的中位数作为pivot，这样做的目的
 就是使得划分比较合理，从而避免了最坏情况的发生。算法步骤如下

 （1）将输入数组的个元素划分为组，每组5个元素，且至多只有一个组由剩下的个元素组成。
 （2）寻找个组中每一个组的中位数，首先对每组的元素进行插入排序，然后从排序过的序列中选出中位数。
 （3）对于（2）中找出的个中位数，递归进行步骤（1）和（2），直到只剩下一个数即为这个元素
 的中位数，找到中位数后并找到对应的下标。
 （4）进行Partion划分过程，Partion划分中的pivot元素下标为。
 （5）进行高低区判断即可。
 */
public class BFPRT {

    //插入排序
    static void insertSort(int a[], int l, int r){
        for(int i = l + 1; i <= r; i++){
            if(a[i - 1] > a[i]){
                int t = a[i];
                int j = i;
                while(j > l && a[j - 1] > t){
                    a[j] = a[j - 1];
                    j--;
                }
                a[j] = t;
            }
        }
    }

    //寻找中位数的中位数
    int findMid(int a[], int l, int r){
        if(l == r) return a[l];
        int i = 0;
        int n = 0;
        for(i = l; i < r - 5; i += 5){
            insertSort(a, i, i + 4);
            n = i - l;
            Util.swap(a, a[l + n / 5], a[i + 2]);
        }

        //处理剩余元素
        int num = r - i + 1;
        if(num > 0){
            insertSort(a, i, i + num - 1);
            n = i - l;
            Util.swap(a, a[l + n / 5], a[i + num / 2]);
        }
        n /= 5;
        if(n == l) return a[l];
        return findMid(a, l, l + n);
    }

    //寻找中位数的所在位置
    int FindId(int a[], int l, int r, int num)
    {
        for(int i = l; i <= r; i++)
            if(a[i] == num)
                return i;
        return -1;
    }

    //进行划分过程
    int partion(int a[], int l, int r, int p)
    {
        Util.swap(a, a[p], a[l]);
        int i = l;
        int j = r;
        int pivot = a[l];
        while(i < j)
        {
            while(a[j] >= pivot && i < j)
                j--;
            a[i] = a[j];
            while(a[i] <= pivot && i < j)
                i++;
            a[j] = a[i];
        }
        a[i] = pivot;
        return i;
    }

    int bfptr(int a[], int l, int r, int k)
    {
        int num = findMid(a, l, r);    //寻找中位数的中位数
        int p =  FindId(a, l, r, num); //找到中位数的中位数对应的id
        int i = partion(a, l, r, p);

        int m = i - l + 1;
        if(m == k) return a[i];
        if(m > k)  return bfptr(a, l, i - 1, k);
        return bfptr(a, i + 1, r, k - m);
    }


    public static void main(String[] args) {
        int a [] = {87,6847,250,8561,5185,3894,2131,1090,7381,4374,7414,6368,8592,3909,576,6330,6420,8252,2685,1612};
        BFPRT b = new BFPRT();
        b.bfptr(a,0,a.length-1,1);
        Util.print(a);
    }
}
