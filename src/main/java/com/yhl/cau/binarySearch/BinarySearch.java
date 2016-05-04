package com.yhl.cau.binarySearch;

import com.yhl.cau.GenArray;
import com.yhl.cau.merge.MergeSort1;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-7
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearch {

    public static int loop(int[] a,int target){
        int start = 0;
        int end = a.length-1;
        while (start <= end){
            int mid = (start + end) >>>1;//右移 /2
            if(a[mid]==target){
                return mid;
            }else if(a[mid] > target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }


    public static int recurse(int[] a ,int l,int r,int target){

        if(target<a[l] || target>a[r] || l>r)
            return -1;
        int mid = (l + r) >>>1;//右移 /2
        if(a[mid]==target){
            return mid;
        }else if(a[mid] > target){
            r = mid - 1;
        }else{
            l = mid + 1;
        }
        return recurse(a,l,r,target);
    }


    public static void main(String[] args) {
        int[] a = GenArray.gen(1024*1024*20);
        MergeSort1.mergeSort(a);


        long start = System.currentTimeMillis();
        int index = BinarySearch.loop(a,a[90]);
        System.out.println("loop耗时：" + (System.currentTimeMillis()-start) + "ms");

        start = System.currentTimeMillis();
        int index1 = BinarySearch.recurse(a,0,a.length-1,a[90]);
        System.out.println("耗时：" + (System.currentTimeMillis()-start) + "ms");

    }
}
