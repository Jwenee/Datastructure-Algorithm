package tree;

public class TestST {

    public static void main(String[] args) {
        Integer[] arr = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> integerSegmentTree = new SegmentTree<>(arr, (a, b) -> a + b);
        System.out.println(integerSegmentTree);

        Integer query = integerSegmentTree.query(0, 2);
        System.out.println(query);
        query = integerSegmentTree.query(2, 5);
        System.out.println(query);
        query = integerSegmentTree.query(0, 5);
        System.out.println(query);

        integerSegmentTree.set(2, 9);
        System.out.println(integerSegmentTree);
        query = integerSegmentTree.query(0, 5);
        System.out.println(query);
    }
}
