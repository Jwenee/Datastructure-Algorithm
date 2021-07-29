package sort;

import base.ArrayGenerator;
import base.SortingHelper;

import java.util.Arrays;
import java.util.Random;

// 三路快速排序
public class QuickSort2 {

    private QuickSort2() {}

    public static <E extends Comparable<E>> void sort(E[] arr) {
        Random rnd = new Random();
        sort(arr, 0, arr.length - 1, rnd);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random random) {
        if (l >= r) return;
        int p = l + random.nextInt(r - l + 1);
        swap(arr, l, p);

//        arr[l + 1, lt] < v, arr[lt + 1, i - 1] == v, arr[gt, r] > v
        int lt = l, i = l + 1, gt = r + 1;
        while (i < gt) {
            if (arr[i].compareTo(arr[l]) < 0) {
                lt++;
                swap(arr, i, lt);
                i++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                gt--;
                swap(arr, i, gt);
            } else {
//                arr[i] == arr[l]
                i++;
            }
        }
        swap(arr, l, lt);
        // arr[l, lt - 1] < v, arr[lt, gt - 1] == v, arr[gt, r] > v
        sort(arr, l, lt - 1, random);
        sort(arr, gt, r, random);
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("QuickSort2", arr);

        arr = ArrayGenerator.generateOrderedArray(n);
        SortingHelper.sortTest("QuickSort2", arr);
    }
}
