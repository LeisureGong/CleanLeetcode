package coding.date;

import java.util.Arrays;

public class _0917 {

    public static void main(String[] args) {
        _0917 solution = new _0917();
        int[][] data = new int[][] {
                {2,1},{3,1},{4,2},{1,4}
        };

        System.out.println(Arrays.toString(solution.findRedundantDirectedConnection(data)));
    }

    // Union Find
    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            if (connected(edge[1],edge[0])) {
                return edge;
            } else {
                parent[edge[1]] = edge[0];
            }
        }

        return new int[] {-1, -1};
    }

    private int[] parent;

    private int find(int x) {
        while(parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
}


