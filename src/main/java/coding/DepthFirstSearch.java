package coding;

public class DepthFirstSearch {

    //695. Max Area of Island
    //遍历过的标志位
    private static int visited[][];
    private static int nodeNum = 0;
    private static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int maxAreaOfIsland(int[][] grid) {
        int res = 0;//save the result
        visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++)
                visited[i][j] = 0;
        }
        for(int i = 0;i < grid.length;i++){
            for(int j = 0;j < grid[0].length;j++){
                if(grid[i][j] == 1 && visited[i][j] == 0){
                    nodeNum = 0;
                    maxAreaOfIslandDFS(i,j,grid);
                    res = Math.max(nodeNum,res);
                }
            }
        }
        return res;
    }

    public static void  maxAreaOfIslandDFS(int x,int y,int[][] grid){
        visited[x][y] = 1;
        nodeNum++;
        for(int i = 0;i < direction.length;i++){
            int tx = x + direction[i][0];
            int ty = y+ direction[i][1];
            //越界跳过，遇到障碍或访问过就跳过，符合条件则继续遍历
            if(tx >= 0 && ty>=0 && tx < grid.length && ty < grid[0].length
                    && grid[tx][ty] > 0 && visited[tx][ty] == 0){
                maxAreaOfIslandDFS(tx,ty,grid);
            }
        }
    }

    public static void main(String... args){
        int[][] maze = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        };
        System.out.println(maxAreaOfIsland(maze));
    }






//    //深度优先搜索，从点(x,y)开始
//    public void dfs(int x,int y,int[][] grid){
//        nodeNum = 0;
//        dfsTrace(x,y,grid);
//    }
//
//    public static void dfsTrace(int x,int y,int[][] grid){
//        visited[x][y] = 1;
//        nodeNum++;
//        for(int i = 0;i < direction.length;i++){
//            int tx = x + direction[i][0];
//            int ty = y+ direction[i][1];
//            //越界跳过，遇到障碍或访问过就跳过，符合条件则继续遍历
//            if(tx >= 0 && ty>=0 && tx < grid.length && ty < grid[0].length
//                && grid[tx][ty] > 0 && visited[x][y] == 0){
//                dfsTrace(tx,ty,grid);
//            }
//        }
//    }
}
