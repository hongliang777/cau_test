package com.yhl.cau.quick;

import com.yhl.cau.GenArray;
import com.yhl.cau.Util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-3-31
 * Time: 上午10:50
 快速排序
 */
public class QuickSort1 {

    /**
     * 快速排序
     * @param s 数组
     * @param left 左边界
     * @param right 右边界
     */
     public void quickSort(int s[], int left, int right)
    {
        if (left < right){
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = left, j = right, midValue = s[left];
            while (i < j){
                while(i < j && s[j] >= midValue) // 从右向左找第一个小于x的数
                    j--;
                if(i < j){ // 那肯定就是s[j]<x 所以跳出循环了 所以换位置
                    s[i] = s[j];
                    i++;
                }

                while(i < j && s[i] < midValue) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j){
                    s[j] = s[i];
                    j--;
                }
            }
            s[i] = midValue;
            quickSort(s, left, i - 1); // 递归调用
            quickSort(s, i + 1, right);
        }
    }

    public static void main(String[] args) {
        long total = 0;
        int loopcout = 1;
        QuickSort1 e = new QuickSort1();
        for(int i=0;i<loopcout;i++){
            try{
                int[] ss = GenArray.gen(20);

                long start = System.currentTimeMillis();
                e.quickSort(ss,0,ss.length-1);
                total = total+(System.currentTimeMillis()-start);
                Util.print(ss);
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
        System.out.println("平均耗时：" + total / loopcout + "ms");


    }


}
