package com.yhl.cau.heap;

import com.yhl.cau.GenArray;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-4-5
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
public class HeapSort2 {

    public static void main(String[] args) {
        int[] array = GenArray.gen(1024*1024);
        long start = System.currentTimeMillis();
        heapSort(array);
        System.out.println("耗时：" + (System.currentTimeMillis()-start) + "ms");
        //PrintUtil.print(array);
    }

    public static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        buildMaxHeap(array);

        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            maxHeap(array, i, 0);
        }
    }

    public static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int half = array.length / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    private static void maxHeap(int[] array, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;
        if (left < heapSize && array[left] > array[index]) {
            largest = left;
        }

        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (index != largest) {
            swap(array, index, largest);

            maxHeap(array, heapSize, largest);
        }
    }
}
