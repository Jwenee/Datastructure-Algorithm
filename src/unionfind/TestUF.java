package unionfind;

import java.util.Random;

public class TestUF {

    private static double test(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long start = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 100000;
        int m = 100000;

        UnionFind1 unionFind1 = new UnionFind1(size);
        UnionFind2 unionFind2 = new UnionFind2(size);
        UnionFind3 unionFind3 = new UnionFind3(size);
        UnionFind4 unionFind4 = new UnionFind4(size);
        UnionFind5 unionFind5 = new UnionFind5(size);
        UnionFind6 unionFind6 = new UnionFind6(size);

//        System.out.println("UnionFind1: " + test(unionFind1, m) + " s");
//        System.out.println("UnionFind2: " + test(unionFind2, m) + " s");
        System.out.println("UnionFind3: " + test(unionFind3, m) + " s");
        System.out.println("UnionFind4: " + test(unionFind4, m) + " s");
        System.out.println("UnionFind5: " + test(unionFind5, m) + " s");
        System.out.println("UnionFind6: " + test(unionFind6, m) + " s");
    }
}
