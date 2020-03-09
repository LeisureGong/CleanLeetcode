package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gonglei
 * @date 2020/3/5 11:06
 */
class Solutions {
	public static List<List<Integer>> threeSum(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		if(nums.length == 0){
			return result;
		}
		Arrays.sort(nums);
		for(int i = 0;i <nums.length;i++){
			if(i > 0 && nums[i] == nums[i-1])continue;
			int target = -nums[i];
			int j = i+1;//left pointer
			int k = nums.length - 1;//right pointer
			while(j < k){
				if(nums[j] + nums[k] == target){
					List<Integer> curr = new ArrayList<>();
					curr.add(nums[i]);
					curr.add(nums[j]);
					curr.add(nums[k]);
					result.add(curr);
					j++;k--;
					while(j<nums.length && nums[j] == nums[j - 1]) j++;
					while(k > j && nums[k] == nums[k+1]) k--;
				}else if(nums[j] + nums[k] > target){
					k--;
				}else{
					j++;
				}
			}
		}
		return result;
	}

	//四数之和
	public List<List<Integer>> fourSum(int[] nums,int target){
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);


		return null;
	}

	//合并两个有序数组
	public static void merge(int[] nums1,int m,int[] nums2,int n){
		//非空校验
		if(n == 0) return;
		if(m == 0) {
			System.arraycopy(nums2, 0, nums1, m, n);
			return;
		}
		int len = m + n - 1,i = m-1,j = n-1;
		while(i >= 0 && j >= 0){
			if(nums1[i] > nums2[j]){
				nums1[len--] = nums1[i--];
			}else{
				nums1[len--] = nums2[j--];
			}
		}
		while(j >= 0){
			nums1[len--] = nums2[j--];
		}
	}



	//最接近的三数之和
	public static int threeSumCloset(int[] nums,int target){
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];
		if(nums.length <= 3) return result;
		for(int i = 0;i < nums.length;i++){
			int j = i+1;//left pointer
			int k = nums.length - 1;//right pointer
			while(j < k){
				int sum = nums[j] + nums[k] + nums[i];
				if( Math.abs(result - target) > Math.abs(sum - target)){
					result = sum;
					// j++;k--;
					// while(j < nums.length && nums[j] == nums[j+1]) j++;
					// while(k < nums.length && nums[k] == nums[k-1]) k--;
				}

				if(sum > target){
					k--;
				}else if(sum < target){
					j++;
				}else{
					return sum;
				}
			}
		}
		return result;
	}

//	//合并区间
//	public int[][] merge(int[][] intervals){
//		int[][] result;
//		return null;
//	}
//
//	//组合总和
//	public List<List<Integer>> combinationSum(int[] candidates,int target){
//		return  null;
//	}


	public static void main(String... args){
//		System.out.println(Solutions.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
		System.out.println(Solutions.threeSumCloset(new int[]{1,2,4,8,16,32,64,128},82));
//		merge(new int[]{2,0},1,new int[]{1},1);
	}


	/**
	 *          .,:,,,                                        .::,,,::.
	 *        .::::,,;;,                                  .,;;:,,....:i:
	 *        :i,.::::,;i:.      ....,,:::::::::,....   .;i:,.  ......;i.
	 *        :;..:::;::::i;,,:::;:,,,,,,,,,,..,.,,:::iri:. .,:irsr:,.;i.
	 *        ;;..,::::;;;;ri,,,.                    ..,,:;s1s1ssrr;,.;r,
	 *        :;. ,::;ii;:,     . ...................     .;iirri;;;,,;i,
	 *        ,i. .;ri:.   ... ............................  .,,:;:,,,;i:
	 *        :s,.;r:... ....................................... .::;::s;
	 *        ,1r::. .............,,,.,,:,,........................,;iir;
	 *        ,s;...........     ..::.,;:,,.          ...............,;1s
	 *       :i,..,.              .,:,,::,.          .......... .......;1,
	 *      ir,....:rrssr;:,       ,,.,::.     .r5S9989398G95hr;. ....,.:s,
	 *     ;r,..,s9855513XHAG3i   .,,,,,,,.  ,S931,.,,.;s;s&BHHA8s.,..,..:r:
	 *    :r;..rGGh,  :SAG;;G@BS:.,,,,,,,,,.r83:      hHH1sXMBHHHM3..,,,,.ir.
	 *   ,si,.1GS,   sBMAAX&MBMB5,,,,,,:,,.:&8       3@HXHBMBHBBH#X,.,,,,,,rr
	 *   ;1:,,SH:   .A@&&B#&8H#BS,,,,,,,,,.,5XS,     3@MHABM&59M#As..,,,,:,is,
	 *  .rr,,,;9&1   hBHHBB&8AMGr,,,,,,,,,,,:h&&9s;   r9&BMHBHMB9:  . .,,,,;ri.
	 *  :1:....:5&XSi;r8BMBHHA9r:,......,,,,:ii19GG88899XHHH&GSr.      ...,:rs.
	 *  ;s.     .:sS8G8GG889hi.        ....,,:;:,.:irssrriii:,.        ...,,i1,
	 *  ;1,         ..,....,,isssi;,        .,,.                      ....,.i1,
	 *  ;h:               i9HHBMBBHAX9:         .                     ...,,,rs,
	 *  ,1i..            :A#MBBBBMHB##s                             ....,,,;si.
	 *  .r1,..        ,..;3BMBBBHBB#Bh.     ..                    ....,,,,,i1;
	 *   :h;..       .,..;,1XBMMMMBXs,.,, .. :: ,.               ....,,,,,,ss.
	 *    ih: ..    .;;;, ;;:s58A3i,..    ,. ,.:,,.             ...,,,,,:,s1,
	 *    .s1,....   .,;sh,  ,iSAXs;.    ,.  ,,.i85            ...,,,,,,:i1;
	 *     .rh: ...     rXG9XBBM#M#MHAX3hss13&&HHXr         .....,,,,,,,ih;
	 *      .s5: .....    i598X&&A&AAAAAA&XG851r:       ........,,,,:,,sh;
	 *      . ihr, ...  .         ..                    ........,,,,,;11:.
	 *         ,s1i. ...  ..,,,..,,,.,,.,,.,..       ........,,.,,.;s5i.
	 *          .:s1r,......................       ..............;shs,
	 *          . .:shr:.  ....                 ..............,ishs.
	 *              .,issr;,... ...........................,is1s;.
	 *                 .,is1si;:,....................,:;ir1sr;,
	 *                    ..:isssssrrii;::::::;;iirsssssr;:..
	 *                         .,::iiirsssssssssrri;;:.
	 */

}