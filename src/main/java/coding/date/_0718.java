package coding.date;

public class _0718 {

    public static void main(String[] args) {
        _0718 solution = new _0718();
        int n = 5;
        int[][] edges = new int[][]{{1,4},{2,4},{0,4},{0,3},{0,2},{2,3}};
        double[] succProb = new double[]{0.37,0.17,0.93,0.23,0.39,0.04};
        int start = 3,end = 4;
        System.out.println(solution.maxProbability(n,edges,succProb,start,end));
    }

    double res;
    int[][] edges;
    double[] succProb;
    boolean[] flag;
    int n;
    int start;
    int end;

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        this.n = n;
        this.edges = edges;
        this.succProb = succProb;
        this.start = start;
        this.end = end;
        flag = new boolean[edges.length];

        for (int i = 0; i < edges.length; i++) {
            if(!flag[i] && (edges[i][0] == start || edges[i][1] == start)) {
                double nowProb = succProb[i];
                int nowStart = edges[i][1] == start ? edges[i][0] : edges[i][1];
                dfs(nowStart,nowProb,i);
            }
        }
        return res;
    }

    private void dfs(int nowStart, double nowProb,int index) {
        if (nowStart == end) {
            res = Math.max(res, nowProb);
            return;
        }
        flag[index] = true;
        for (int i = 0; i < edges.length; i++) {
            if (!flag[i] && (edges[i][0] == nowStart || edges[i][1] == start)) {
                double tmpProb = succProb[i];
                int tmpStart = edges[i][1] == start ? edges[i][0] : edges[i][1];
                dfs(tmpStart, nowProb * tmpProb,i);
            }
        }
        flag[index] = false;
    }
}
