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

    public static boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for(String eq : equations) {
            if(eq.charAt(1) == '=') {
                char a = eq.charAt(0);
                char b = eq.charAt(3);
                uf.union(a - 'a',b - 'a');
            }
        }

        for(String eq : equations) {
            if(eq.charAt(1) == '!') {
                char a = eq.charAt(0);
                char b = eq.charAt(3);
                if(uf.connected(a - 'a',b - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(equationsPossible(new String[]{"a==b","b!=a"}));
    }
}