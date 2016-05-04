package com.yhl.cau.merge;

import com.yhl.cau.GenArray;

import java.util.Arrays;

/**
 *  会额外占用空间 但是非常快 最慢也是n(nlogn)
 */
public class MergeSort1 {

    public static void main(String[] args) {
			int[] array = GenArray.gen(1024);
            long start = System.currentTimeMillis();
			mergeSort(array);
            System.out.println("耗时："+(System.currentTimeMillis()-start)+"ms");
            //Util.print(array);
    }

		public static void mergeSort(int[] array) {
			sortArray(array, 0, array.length - 1);
		}

		private static void sortArray(int[] array, int start, int end) {
			// 单个元素不需要排序,直接返回
			if (start == end) {
				return;
			}

			int sortSize = end - start + 1;
			int seperate;
			if (sortSize % 2 == 0) {
				seperate = start + sortSize / 2 - 1;
			} else {
				seperate = start + sortSize / 2;
			}

			sortArray(array, start, seperate);
			sortArray(array, seperate + 1, end);

			mergeArray(array, start, seperate, end);
		}

		private static void mergeArray(int[] array, int start, int seperate, int end) {
			int totalSize = end - start + 1;
			int size1 = seperate - start + 1;
			int size2 = end - seperate;

			int[] array1 = Arrays.copyOfRange(array,start,start+size1);
			int[] array2 = Arrays.copyOfRange(array,seperate+1,seperate+1+size2);

//			for (int i = 0; i < size1; i++) {
//				array1[i] = array[start + i];
//			}
//
//			for (int i = 0; i < size2; i++) {
//				array2[i] = array[seperate + 1 + i];
//			}
//            System.arraycopy(array[seperate + 1]);

			int mergeCount = 0;
			int index1 = 0;
			int index2 = 0;

			while (mergeCount < totalSize) {
				// 先检查有没有其中的一个数组已经处理完
				if (index1 == size1) {
					for (int i = index2; i < size2; i++) {
						array[start + mergeCount] = array2[i];
                        mergeCount++;
                        index2++;
					}
//                    System.arraycopy(array2,index2,array,start+mergeCount,size2-index2);
//                    mergeCount++;
//                    index2++;

				} else if (index2 == size2) {
					for (int i = index1; i < size1; i++) {
						array[start + mergeCount] = array1[i];
						mergeCount++;
                      index1++;
					}
//                    System.arraycopy(array1,index1,array,start+mergeCount,size1-index1);
//                    mergeCount++;
//                    index1++;
				} else {
					int value1 = array1[index1];
					int value2 = array2[index2];

					if (value1 == value2) {
						array[start + mergeCount] = value1;
						array[start + mergeCount + 1] = value1;
						mergeCount += 2;
						index1++;
						index2++;
					} else if (value1 < value2) {
						array[start + mergeCount] = value1;
						mergeCount++;
						index1++;
					} else if (value1 > value2) {
						array[start + mergeCount] = value2;
						mergeCount++;
						index2++;
					}
				}
			}
		}
	}