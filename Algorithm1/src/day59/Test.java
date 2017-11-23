package day59;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest = new Test();
		TreeNode a=new TreeNode(2);
		TreeNode b=new TreeNode(5);
		TreeNode c=new TreeNode(-5);
		b.left=a;
		b.right=c;
		System.out.println(aTest.findFrequentTreeSum(b));
	}
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if(root==null)
        	return res;
        int deep=0;
        HashMap<Integer, Integer>map=new HashMap<>();
        deep=dfsLargestHelper(root,map,deep);
        for(int i=1;i<=deep;i++)
        {
        	res.add(map.get(i));
        }
        return res;
    }
	private int dfsLargestHelper(TreeNode root, HashMap<Integer, Integer> map, int deep) {
		// TODO Auto-generated method stub
		if(root==null)
		    return deep;
		deep++;
		if(map.containsKey(deep))
		{
			map.put(deep, Math.max(root.val, map.get(deep)));
		}
		else
		{
			map.put(deep, root.val);
		}
		return Math.max(dfsLargestHelper(root.left, map, deep), dfsLargestHelper(root.right, map, deep));
		
	}
	int leftMost=Integer.MIN_VALUE;
	int deep=0;
    public int findBottomLeftValue(TreeNode root) {
        dfsLeftHelper(root,0);
        return leftMost;
    }
    
    private void dfsLeftHelper(TreeNode root, int currentDeep) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		currentDeep=currentDeep+1;
		if(currentDeep>deep)
		{
			deep=currentDeep;
			leftMost=root.val;
		}
		dfsLeftHelper(root.left, currentDeep);
		dfsLeftHelper(root.right, currentDeep);
	}
	public int[] findFrequentTreeSum(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        HashMap<Integer, Integer> count=new HashMap<>();
        dfsHelper(root,res,count);
        int[] find=new int[res.size()];
        for(int i=0;i<find.length;i++)
        {
        	find[i]=res.get(i);
        }
        return find;
    }
    
	private int dfsHelper(TreeNode root, List<Integer> res, HashMap<Integer, Integer> count) {
		// TODO Auto-generated method stub
		if(root==null)
			return 0;
		int val=root.val+dfsHelper(root.left, res, count)+dfsHelper(root.right, res, count);
		int countTime=0;
		int currentMax=1;
		if(res.size()!=0)
			currentMax=count.get(res.get(0));
		if(count.containsKey(val))
		{
			countTime=count.get(val)+1;
			count.put(val, countTime);
		}
		else
		{
			countTime=1;
			count.put(val, countTime);
		}
		if(res.size()==0)
		{
			res.add(val);
		}
		else
		{

			if(currentMax<countTime)
			{
				res.clear();
				res.add(val);
			}
			else if(currentMax==countTime)
			{
				res.add(val);
			}
		}
		return val;
	}
	public boolean checkPerfectNumber(int num) {
		if (num <= 1)
			return false;
		int res = 1;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				res += i;
				res += num / i;
				if (res > num)
					return false;
			}
		}
		if (res < num)
			return false;
		return true;
	}

	public String[] findRelativeRanks(int[] nums) {
		int[][] pair = new int[nums.length][2];
		for (int i = 0; i < nums.length; i++) {
			pair[i][0] = nums[i];
			pair[i][1] = i;
		}
		Arrays.sort(pair, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o2[0] - o1[0];
			}
		});
		String[] result = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				result[pair[i][1]] = "Gold Medal";
			} else if (i == 1) {
				result[pair[i][1]] = "Silver Medal";
			} else if (i == 2) {
				result[pair[i][1]] = "Bronze Medal";
			} else {
				result[pair[i][1]] = (i + 1) + "";
			}
		}
		return result;
	}

	public String convertToBase7(int num) {
		boolean flag = num < 0 ? true : false;
		if (num == 0)
			return "0";
		StringBuilder sb = new StringBuilder();
		num = Math.abs(num);
		while (num > 0) {
			sb.append(num % 7);
			num = num / 7;
		}
		return flag ? "-" + sb.reverse().toString() : sb.reverse().toString();
	}

	public int[] nextGreaterElements1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return nums;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int size = nums.length;
		int[] rst = new int[size];
		Arrays.fill(rst, -1);
		for (int i = 0; i < size * 2; ++i) {
			int num = nums[i % size];
			while (!stack.isEmpty() && nums[stack.peek()] < num) {
				rst[stack.pop()] = num;
			}
			if (i < size) {
				stack.push(i);
			}
		}
		return rst;
	}

	public int[] nextGreaterElements(int[] nums) {
		if (nums == null || nums.length == 0)
			return new int[0];
		int len = nums.length;
		int[] res = new int[len];
		for (int i = 0; i < nums.length; i++) {
			int temp = -1;
			for (int j = i + 1; j % len != i; j++) {
				if (nums[j % len] > nums[i]) {
					temp = nums[j % len];
					break;
				}
			}
			res[i] = temp;
		}
		return res;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}