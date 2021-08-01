package binary;

public class BinarySearch {

    private BinarySearch() {}

//    非递归实现二分查找
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        int l = 0, r = data.length - 1;
//        不变量[l, r]
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) == 0) return mid;
            if (data[mid].compareTo(target) < 0) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    public static <E extends Comparable<E>> int search2(E[] data, E target) {
        //        不变量[l, r)
        int l = 0, r = data.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) == 0) return mid;
            if (data[mid].compareTo(target) < 0) l = mid + 1; // [mid + 1, r)
            else r = mid; // [l, mid)
        }
        return -1;
    }

//    求大于target的最小索引
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        //        不变量[l, r)
        int l = 0, r = data.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid].compareTo(target) <= 0) l = mid + 1;
            else r = mid;
        }
        return l;
    }

//    > target, 返回最小值索引，== target，返回最大索引
    public static <E extends Comparable<E>> int ceil(E[] data, E target) {
        int u = upper(data, target);
        if (u - 1 >= 0 && data[u - 1].compareTo(target) == 0) return u - 1;
        return u;
    }

    public static <E extends Comparable<E>> int searchR(E[] data, E target) {
        return searchR(data, 0, data.length - 1, target);
    }

//    < target的最大值索引
    public static <E extends Comparable<E>> int lower(E[] data, E target) {
        int l = - 1, r = data.length - 1;
//        在data[l, r]中寻找解
        while (l < r) {
//            int mid = l + (r - l) / 2; // 当l和r相邻时，向下取整，产生死循环
            int mid = l + (r - l + 1) / 2; // 向上取整
            if (data[mid].compareTo(target) < 0)
                l = mid;
            else r = mid - 1;
        }
        return l;
    }

//    递归实现二分查找
    private static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;

        if (data[mid].compareTo(target) == 0) return mid;
        if (data[mid].compareTo(target) < 0)
            return searchR(data, mid + 1, r, target);
        return searchR(data, l, mid - 1, target);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
//        for (int i = 0; i < 6; i++) {
//            int p = BinarySearch.upper(arr, i);
//            System.out.println(p);
//        }
//        for (int i = 0; i <= 6; i++) {
//            int p = BinarySearch.ceil(arr, i);
//            System.out.print(p + " ");
//        }
        for (int i = 0; i <= 6; i++) {
            int p = BinarySearch.lower(arr, i);
            System.out.print(p + " ");
        }
    }
}
