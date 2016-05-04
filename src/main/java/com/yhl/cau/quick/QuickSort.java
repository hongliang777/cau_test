package com.yhl.cau.quick;

import com.yhl.cau.GenArray;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-3-31
 * Time: 上午10:50
 快速排序
 */
public class QuickSort {

    /**
     * 快速排序
     * @param s 数组
     * @param l 左边界
     * @param r 右边界
     */
     public void quickSort(int s[], int l, int r)
    {
        if (l < r){
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j)
            {
                while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if(i < j) // 那肯定就是s[j]<x 所以跳出循环了 所以换位置
                    s[i++] = s[j]; // 等于s[i] = s[j] ；i++;

                while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quickSort(s, l, i - 1); // 递归调用
            quickSort(s, i + 1, r);
        }
    }

    public static void main(String[] args) {
        long total = 0;
        int loopcout = 1;
        QuickSort e = new QuickSort();
        for(int i=0;i<loopcout;i++){
            try{
                int[] ss = GenArray.gen(1024*1024*20);

                long start = System.currentTimeMillis();
                e.quickSort(ss,0,ss.length-1);
                total = total+(System.currentTimeMillis()-start);
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
        System.out.println("平均耗时：" + total / loopcout + "ms");


    }


}
