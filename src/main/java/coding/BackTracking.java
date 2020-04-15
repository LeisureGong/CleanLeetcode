package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gonglei
 * @date 2020/3/9 11:52
 */
public class BackTracking {

	//22括号生辰 回溯
	public static List<String> generateParenthesisUsingBackTrack(int n){
		List<String> result = new ArrayList<>();
		generateBackTracks(result,"",0,0,n);
		return result;
	}
	public static void generateBackTracks(List<String> result,String temp,int l,int r,int n){
		if(temp.length() == 2*n){
			result.add(temp);
			return;
		}
		if(l < n)
			generateBackTracks(result,temp+"(",l+1,r,n);
		if(r < l)
			generateBackTracks(result,temp+")",l,r+1,n);
	}

	//22括号生成 DFS
	public static List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		generateBackTrack(result,"",0,0,n);
		return result;
	}

	public static void generateBackTrack(List<String> result,String temp,int l,int r,int n){
		//剪枝
		if(l > n || r > n || r > l) return;
		//满足条件，保存结果
		if(l == n && r == n){
			result.add(temp);
		}
		generateBackTrack(result,temp+"(",l+1,r,n);
		generateBackTrack(result,temp+")",l,r+1,n);
		return;
	}

	//40.组合总和II
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		if(candidates.length <= 0 ) return Collections.emptyList();
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<>();
		combinationBackTrack(result,new ArrayList<>(),target,0,candidates);
		return result;
	}

	public static void combinationBackTrack(List<List<Integer>> result,List<Integer> temp,int target,int first,int[] candidates){
		if(target < 0) return;
		if(target == 0){
			if(!result.contains(temp))
				result.add(new ArrayList<>(temp));
		}else{
			for(int i = first;i < candidates.length;i++){
				temp.add(candidates[i]);
				combinationBackTrack(result,temp,target-candidates[i],i+1,candidates);
				temp.remove(temp.size() - 1);
			}
		}
	}

	//46全排列
	public List<List<Integer>> permute(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		Arrays.stream(nums).forEach(i -> temp.add(i));
		int n = nums.length;
		permuteBackTrack(n,temp,result,0);
		return result;
	}
	public void permuteBackTrack(int n,List<Integer> nums,List<List<Integer>> result,int first){
		//满足条件，保存结果
		if(first == n){
			result.add(new ArrayList<>(nums));
		}
		for(int i = first;i < n;i++){
			Collections.swap(nums,first,i);
			permuteBackTrack(n,nums,result,first+1);
			Collections.swap(nums,first,i);
		}
	}

	//77.组合
	public List<List<Integer>> combine(int n,int k){
		List<List<Integer>> result = new LinkedList<>();
		combineBacktrack(1,new LinkedList<Integer>(),result,n,k);
		return result;
	}

	public void combineBacktrack(int first, LinkedList<Integer> curr, List<List<Integer>> result,int n,int k){
		//结束条件
		if(curr.size() == k){
			result.add(new LinkedList<>(curr));
		}

		for(int i = first;i < n+1;i++){
			curr.add(i);
			combineBacktrack(i+1,curr,result,n,k);
			curr.removeLast();
		}
	}
	//79. 单词搜索
	static boolean flag = false;
	public static boolean exist(char[][] board, String word) {
		if(board.length == 0 || word.length() == 0) return false;
		for(int i = 0;i < board.length;i++){
			for(int j = 0;j < board[0].length;j++){
				//找到第一个字符，才开始回溯
				if(board[i][j] == word.charAt(0)){
					boolean[][] road = new boolean[board.length][board[0].length];
					wordBacktrack(board,word,i,j,0,road);
					if(flag) return true;
				}
			}
		}
		return false;
	}
	public static void wordBacktrack(char[][] board,String word,int i,int j,int len,boolean[][] road){
		if(flag) return;//剪枝，否则容易超时
		road[i][j] = true;
		//剪枝
		if(i < 0 || i >= board.length || j < 0 | j >= board[0].length) return;
		if(len == word.length()-1){
			flag = true;
			return;
		}
		//要过滤已经走过的路径
		//up
		if((i-1) >= 0 && board[i-1][j] == word.charAt(len+1) && !road[i-1][j]) wordBacktrack(board,word,i-1,j,len+1,road);
		//down
		if((i+1) < board.length && board[i+1][j] == word.charAt(len+1) && !road[i+1][j]) wordBacktrack(board,word,i+1,j,len+1,road);
		//left
		if((j-1) >= 0 && board[i][j-1] == word.charAt(len+1) && !road[i][j-1]) wordBacktrack(board,word,i,j-1,len+1,road);
		//right
		if((j+1) < board[0].length && board[i][j+1] == word.charAt(len+1) && !road[i][j+1]) wordBacktrack(board,word,i,j+1,len+1,road);
		road[i][j] = false;
	}


	public static void main(String... args){
		combinationSum(new int[]{10,1,2,7,6,1,5},8);
		char[][] board = {{'A','B','C','E'},
				{'S','F','E','S'},{'A','D','E','E'}};
		System.out.println(exist(board,"ABCESEEEFS"));
	}

}
