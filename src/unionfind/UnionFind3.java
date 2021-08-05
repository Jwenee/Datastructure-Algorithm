package unionfind;

public class UnionFind3 implements UF {

//    子树指向父节点
    private int[] parent;
//    sz[i]表示以i为root的集合中元素个数
    private int[] sz;

    public UnionFind3() {}
    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // 初始时每个节点指向自己
            sz[i] = 1; // 每个元素独立一个集合
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

//    查看元素p和q是否所属一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

//    合并元素p和元素q所属的集合
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;
//        元素少的合并到元素多的集合上
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

//    查找元素p对应的集合编号, O(h)，h树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("P is out of bound.");

        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
