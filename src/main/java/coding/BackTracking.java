package coding;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gonglei
 * @date 2020/3/9 11:52
 */
public class BackTracking {

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




}
