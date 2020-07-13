package coding.date;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 频率最高的栈
 * @author gonglei
 * @date 2020/7/9
 */
class FreqStack {

	// 出现元素最大的次数
	int maxFreq;
	// 各个元素出先的次数
	Map<Integer, Integer> numFreq;
	//存储出现次数为i的所有元素
	Map<Integer, Deque<Integer>> group;
	public FreqStack() {
		maxFreq = 0;
		numFreq = new HashMap<>();
		group = new HashMap<>();
	}

	public void push(int x) {
		numFreq.put(x,numFreq.getOrDefault(x,0) + 1);
		if(numFreq.get(x) > maxFreq) {
			maxFreq = numFreq.get(x);
		}
		if(group.get(numFreq.get(x)) == null) {
			Deque<Integer> stack = new ArrayDeque<>();
			group.put(numFreq.get(x),stack);
		}
		group.get(numFreq.get(x)).push(x);
	}

	public int pop() {
		int x = group.get(maxFreq).peek();
		group.get(maxFreq).pop();
		numFreq.put(x,numFreq.getOrDefault(x,1)-1);
		if(group.get(maxFreq).size() == 0) {
			--maxFreq;
		}
		return x;
	}
}

/*
class FreqStack {
	public:
	int maxFreq;//存储出现元素最大的次数
	unordered_map<int, int> numFreq;//保留list中各个元素出现的次数
	unordered_map<int, stack<int>> group;//group[i]存储出现次数为i的所有元素
	FreqStack() {
		maxFreq = 0;//初始化出现元素最大的次数
	}
	void push(int x) {
		if (++numFreq[x] > maxFreq){//更新x出现次数
			maxFreq = numFreq[x];//更新出现元素最大的次数
		}
		group[numFreq[x]].push(x);//将x放入出现次数为numFreq[x]的栈中
	}

	int pop() {
		int x = group[maxFreq].top();//获取出现次数最多的栈的栈顶（即即将出栈的元素）
		group[maxFreq].pop();
		--numFreq[x];//更新出栈元素x的次数
		if (group[maxFreq].size() == 0){//如果出现次数为maxFreq的元素已经没有，则只能减小maxFreq
			--maxFreq;
		}
		return x;
	}
};
*/
