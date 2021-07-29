package sort;

import base.ArrayGenerator;
import base.SortingHelper;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private QuickSort() {}

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;
//        int p = partition(arr, l, r);
//        int p = partition2(arr, l, r);
        int p = partition3(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

//    处理有序数组时非常差
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
//        循环不变量，arr[l+1, j] < v, arr[j+1, i] >= v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

//    改善处理有序数组，处理相同元素的数组性能差
    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r) {
//        生成[l, r]之间的随机索引
//        处理有序数组时，随机取值，避免每次取到最小值
//        避免频繁创建Random实例，可从外部传入
        int p = l + (new Random()).nextInt(r - l + 1);
        swap(arr, l, p);
//        循环不变量，arr[l+1, j] < v, arr[j+1, i] >= v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

//    双路快速排序，优化处理相同元素的数组
    private static <E extends Comparable<E>> int partition3(E[] arr, int l, int r) {
        int p = l + (new Random()).nextInt(r - l + 1);
        swap(arr, l, p);
//      arr[l+1, i-1] <=v; arr[j+1, r] >=v
//        当存在相同元素时，较平均分布在划分点两侧
        int i = l + 1, j = r;
        while (true) {
//            大于等于时中止
            while (i <= j && arr[i].compareTo(arr[l]) < 0)
                i++;
//            小于等于时中止
            while (j >= i && arr[j].compareTo(arr[l]) > 0)
                j--;
            if (i >= j) break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }


    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

//        SortingHelper.sortTest("MergeSort", arr3);
//        SortingHelper.sortTest("MergeSort2", arr4);
        SortingHelper.sortTest("MergeSort4", arr);
//        SortingHelper.sortTest("MergeSort4", arr5);
        SortingHelper.sortTest("QuickSort", arr2);

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("MergeSort4", arr);
        SortingHelper.sortTest("QuickSort", arr2);
    }
}
