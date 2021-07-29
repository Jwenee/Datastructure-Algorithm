package recursion;

public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }
//    calculate sum [left, n)
    private static int sum(int[] arr, int left) {
        if (left == arr.length) return 0;
        return arr[left] + sum(arr, left + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(nums));
    }
}
