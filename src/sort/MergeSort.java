package sort;

import base.ArrayGenerator;
import base.SortingHelper;

import java.util.Arrays;

// 自顶向下的归并排序
public class MergeSort {

    private MergeSort() {}

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        sort2(arr, l, mid);
        sort2(arr, mid + 1, r);
//        优化，如果mid小于mid+1，则两个区间已经有序，无需merge
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r);
    }

    public static <E extends Comparable<E>> void sort3(E[] arr) {
        sort3(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r) {
        if (l >= r) return;
//      当数据量小时，插入排序效率较高
//        if (r - l <= 15) {
//            InsertionSort.sort3(arr, l, r);
//            return;
//        }
        int mid = l + (r - l) / 2;
        sort3(arr, l, mid);
        sort3(arr, mid + 1, r);
//        优化，如果mid小于mid+1，则两个区间已经有序，无需merge
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r);
    }

    public static <E extends Comparable<E>> void sort4(E[] arr) {
//        内存优化
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort4(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void sort4(E[] arr, int l, int r, E[] temp) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        sort4(arr, l, mid, temp);
        sort4(arr, mid + 1, r, temp);
//        优化，如果mid小于mid+1，则两个区间已经有序，无需merge
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge2(arr, l, mid, r, temp);
    }

//    合并两个有序的区间[l, mid], [mid+1, r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;

//        每轮循环为arr[k]赋值
        for (int k = l; k <= r; k++) {
//            左半部分越界，元素已用完，直接取右半部分
            if (i > mid) {
                arr[k] = temp[j - l];
                j++;
//            右半部分越界，元素已用完，直接取左半部分
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
//            左部比右部小
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];
                i++;
//            右部比左部小
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }

    //    合并两个有序的区间[l, mid], [mid+1, r]，内存优化
    private static <E extends Comparable<E>> void merge2(E[] arr, int l, int mid, int r, E[] temp) {
//        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i = l, j = mid + 1;

//        每轮循环为arr[k]赋值
        for (int k = l; k <= r; k++) {
//            左半部分越界，元素已用完，直接取右半部分
            if (i > mid) {
                arr[k] = temp[j];
                j++;
//            右半部分越界，元素已用完，直接取左半部分
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
//            左部比右部小
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
//            右部比左部小
            } else {
                arr[k] = temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

//        SortingHelper.sortTest("SelectionSort", arr);
//        SortingHelper.sortTest("InsertionSort2", arr2);
        SortingHelper.sortTest("MergeSort", arr3);
        SortingHelper.sortTest("MergeSort2", arr4);
        SortingHelper.sortTest("MergeSort4", arr5);
    }
}
