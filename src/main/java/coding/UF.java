package coding;

import java.util.HashSet;
import java.util.Set;

class UF {
    // 连通分量个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录树的“重量”
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    private int find(int x) {
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }

    // 128. 最长连续序列
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        for(int num : nums) {
            if(!set.contains(num - 1)) {
                int currNum = num;
                int currLen = 1;
                while(set.contains(currNum + 1)) {
                    currNum = num + 1;
                    currLen = currLen + 1;
                }
                maxLen = Math.max(maxLen,currLen);
            }
        }
        return maxLen;
    }

    public static void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        UF uf = new UF(m*n + 1);
        int dummy = m * n;

        for(int i = 0;i < m;i++) {
            if(board[i][0] == 'O') {
                uf.union(i*n,dummy);
            }
            if(board[i][n-1] == 'O') {
                uf.union(i * n + n - 1,dummy);
            }
        }

        for(int j = 0;j < n;j++) {
            if(board[0][j] == 'O') {
                uf.union(j,dummy);
            }
            if(board[m-1][j] == 'O') {
                uf.union((m-1)*n + j,dummy);
            }
        }

        // 上下左右搜索
        int[][] d = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
        for(int i = 1;i < m - 1;i++) {
            for(int j = 1;j < n - 1;j++) {
                if(board[i][j] == 'O') {
                    for(int k = 0;k < 4;k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if(board[x][y] == 'O') {
                            uf.union(x*n+y,i*n+j);
                        }
                    }
                }
            }
        }

        // 所有不和dummy连接的0,全部要被替换
        for(int i = 1;i < m - 1;i++) {
            for(int j = 1;j < n - 1;j++) {
                if(!uf.connected(dummy,i*n+j)) {
                    board[i][j] = 'X';
                }
            }
        }

    }

    public static void main(String[] args) {
        String a = "John";
        String b = "Jon";
        System.out.println(a.compareTo(b));
    }
}