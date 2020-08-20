package coding.date;

import java.util.Arrays;

/**
 * @author gonglei
 * @date 2020/8/20
 */
public class _0820 {

	public static void main(String[] args) {
		_0820 solution = new _0820();
		char[][] chs = new char[][]{
				{'E', 'E', 'E', 'E', 'E'},
				{'E', 'E', 'M', 'E', 'E'},
				{'E', 'E', 'E', 'E', 'E'},
				{'E', 'E', 'E', 'E', 'E'}
		};

		char[][] res = solution.updateBoard(chs,new int[]{3,0});
		for (char[] re : res)
			System.out.println(Arrays.toString(re));
	}

	char[][] board;
	int[][] dirs = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	int cols,rows;
	boolean[][] flags;
	public char[][] updateBoard(char[][] board, int[] click) {

		this.board = board;
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}

		this.rows = board.length;
		this.cols = board[0].length;
		flags = new boolean[rows][cols];
		dfs(click[0],click[1]);
		return board;
	}

	private void dfs(int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols || flags[row][col]) return;

		flags[row][col] = true;

		// 计算周围雷的个数
		int cnt = 0;
		for (int[] dir : dirs) {
			int i = dir[0] + row;
			int j = dir[1] + col;
			if (i >= 0 && i < rows && j >= 0 && j < cols) {
				if (board[i][j] == 'M') {
					++cnt;
				}
			}
		}
		board[row][col] = (cnt == 0) ? 'B' : (char) ( cnt + '0');

		for (int[] dir : dirs) {
			int i = dir[0] + row;
			int j = dir[1] + col;
			if (i >= 0 && i < rows && j >= 0 && j < cols) {
				if (board[i][j] != 'M' && !flags[i][j] && cnt == 0) {
					dfs(i,j);
				}
			}
		}
	}
}
