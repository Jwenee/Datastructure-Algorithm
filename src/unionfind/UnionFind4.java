package unionfind;

public class UnionFind4 implements UF {

//    子树指向父节点
    private int[] parent;
//    rank[i]表示以i为root的集合所表示的树的层数
    private int[] rank;

    public UnionFind4() {}
    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // 初始时每个节点指向自己
            rank[i] = 1; // 每个元素树深度为1
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
//        深度小的树指向深度大的树，避免增加较大的深度
//        小树指向大树，各自深度不变
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        } else {
//            两树深度相等，此时作为root的树深度+1
            rank[qRoot] = pRoot;
            rank[pRoot] += 1;
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
