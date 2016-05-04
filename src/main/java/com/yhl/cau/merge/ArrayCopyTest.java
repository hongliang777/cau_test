package com.yhl.cau.merge;

import com.yhl.cau.GenArray;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-6
 * Time: 下午2:25
 *
 * for clone copy 测试数据 http://my.oschina.net/u/816576/blog/368837
 */
public class ArrayCopyTest {
    public static void main(String[] args) {
        int[] a = GenArray.gen(1024*1024*50);
        int[] b = a.clone();

        long start = System.currentTimeMillis();
        MergeSort.mergeSort(a);
        System.out.println("for 耗时："+(System.currentTimeMillis()-start)+"ms");

        start = System.currentTimeMillis();
        MergeSort1.mergeSort(b);
        System.out.println("copy 耗时："+(System.currentTimeMillis()-start)+"ms");
    }

}
