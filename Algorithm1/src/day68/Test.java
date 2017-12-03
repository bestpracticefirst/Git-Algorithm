package day68;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		String s="ABC";
		
		System.out.println(test.changeString(s));
	}
	public String changeString(String string)
	{
		return string.toLowerCase();
	}
    public String solveEquation(String equation) {
        if(equation==null||equation.length()==0)
        	return "No solution";
        String[] equations=equation.split("=");
        if(equations.length!=2)
        	return "No solution";
        String left=equations[0];
        String right=equations[1];
        char[] lefts=left.toCharArray();
        StringBuilder lb=new StringBuilder();
        lb.append(lefts[0]);
        for(int i=1;i<lefts.length;i++)
        {
        	if(lefts[i]=='-')
        	{
        		lb.append('+');
        	}
        	lb.append(lefts[i]);
        }
        left=lb.toString();
        char[] rights=right.toCharArray();
        StringBuilder rb=new StringBuilder();
        rb.append(rights[0]);
        for(int i=1;i<rights.length;i++)
        {
        	if(rights[i]=='-')
        	{
        		rb.append('+');
        	}
        	rb.append(rights[i]);
        }
        right=rb.toString();
        String[] leftS=left.split("\\+");
        String[] rightS=right.split("\\+");
        int lcoefficient=getCoefficient(leftS);
        int lvalue=getValue(leftS);
        int rcoefficient=getCoefficient(rightS);
        int rvalue=getValue(rightS);
        if(lcoefficient==rcoefficient)
        {
        	if(lvalue==rvalue)
        		return "Infinite solutions";
        	return "No solution";
        }
        else
        {
        	int temp=(rvalue-lvalue)/(lcoefficient-rcoefficient);
        	return "x="+temp;
        }
        
    }
    public double findMaxAverage(int[] nums, int k) {
        if(nums==null||k==0||nums.length<k)
        	return 0;
        double currentSum=0;
        for(int i=0;i<k;i++)
        {
        	currentSum+=nums[i];
        }
        double res=currentSum/k;
        for(int i=k;i<nums.length;i++)
        {
        	currentSum=currentSum-nums[i-k]+nums[i];
        	res=Math.max(res, currentSum/k);
        }
        return res;
        
    }
	private int getCoefficient(String[] S) {
		// TODO Auto-generated method stub
		int sum=0;
		for(String s:S)
		{
			if(s.equals("x"))
			{
				sum+=1;
			}
			else if(s.contains("x"))
			{
				if(s.length()==2&&s.charAt(0)=='-')
					sum--;
				else
				    sum+=Integer.valueOf(s.replace("x", ""));
				
			}
		}
		return sum;
	}
	private int getValue(String[] S) {
		// TODO Auto-generated method stub
		int sum=0;
		for(String s:S)
		{
			if(!s.contains("x"))
				sum+=Integer.valueOf(s);
		}
		return sum;
	}
	
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		Map<List<Integer>, Integer> dp = new HashMap<>();
		List<Integer> allZero = new ArrayList<>();
		for (int i = 0; i < needs.size(); i++) {
			allZero.add(0);
		}
		dp.put(allZero, 0);
		return dfs(needs, price, special, dp);
	}

	private int dfs(List<Integer> needs, List<Integer> price, List<List<Integer>> special,
			Map<List<Integer>, Integer> dp) {
		// TODO Auto-generated method stub
		if (dp.containsKey(needs))
			return dp.get(needs);
		int res = Integer.MAX_VALUE;
		for (List<Integer> spec : special) {
			List<Integer> arrayCopy = new ArrayList<>(needs);
			boolean valid = true;
			for (int i = 0; i < needs.size(); i++) {
				arrayCopy.set(i, needs.get(i) - spec.get(i));
				if (arrayCopy.get(i) < 0) {
					valid = false;
					break;
				}
			}
			if (valid) {
				res = Math.min(res, spec.get(needs.size()) + dfs(arrayCopy, price, special, dp));
			}
		}
		int noSpecial = 0;
		for (int i = 0; i < needs.size(); i++) {
			noSpecial += needs.get(i) * price.get(i);
		}
		res = Math.min(res, noSpecial);
		dp.put(needs, res);
		return res;
	}

	public List<Double> averageOfLevels1(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<Double> res = new ArrayList<>();
		if (root == null)
			return res;
		queue.add(root);
		while (!queue.isEmpty()) {
			double sum = 0;
			int length = queue.size();
			for (int i = 0; i < length; i++) {
				TreeNode current = queue.poll();
				sum += current.val;
				if (current.left != null)
					queue.add(current.left);
				if (current.right != null)
					queue.add(current.right);
			}
			sum = sum / length;
			res.add(sum);
		}
		return res;
	}

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<>();
		if (root == null)
			return res;
		HashMap<Integer, Integer> map = new HashMap<>();
		List<Double> list = new ArrayList<>();
		averageHelper(root, map, list, 0);
		for (int i = 0; i < list.size(); i++) {
			res.add(((double) list.get(i) / map.get(i)));
		}
		return res;
	}

	private void averageHelper(TreeNode root, HashMap<Integer, Integer> map, List<Double> list, int depth) {
		// TODO Auto-generated method stub
		if (root == null)
			return;
		if (map.containsKey(depth)) {
			map.put(depth, map.get(depth) + 1);
			list.set(depth, list.get(depth) + root.val);
		} else {
			map.put(depth, 1);
			list.add((double) root.val);
		}
		averageHelper(root.left, map, list, depth + 1);
		averageHelper(root.right, map, list, depth + 1);

	}

	public boolean judgeSquareSum(int c) {
		int sqrt = (int) Math.sqrt(c);
		if (sqrt * sqrt == c)
			return true;
		for (int i = 1; i < Math.sqrt(c); i++) {
			int temp = (int) Math.sqrt(c - i * i);
			if (i * i + temp * temp == c)
				return true;
		}
		return false;
	}

	public int maximumProduct(int[] nums) {
		if (nums == null || nums.length < 3)
			return 0;
		Arrays.sort(nums);
		return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3],
				nums[0] * nums[1] * nums[nums.length - 1]);
	}

	public int leastInterval(char[] tasks, int n) {
		if (tasks == null || tasks.length == 0)
			return 0;
		int[] count = new int[26];
		for (char task : tasks) {
			count[task - 'A']++;
		}
		Arrays.sort(count);
		int i = 25;
		while (count[i] == count[25])
			i--;
		return Math.max(tasks.length, (count[25] - 1) * (n + 1) + 25 - i);
	}

	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode t = new TreeNode(v);
			t.left = root;
			return t;
		}
		if (root == null)
			return root;
		dfsHelper(root, 1, v, d);
		return root;

	}

	private void dfsHelper(TreeNode root, int depth, int v, int d) {
		// TODO Auto-generated method stub
		if (depth == d - 1) {
			TreeNode left = new TreeNode(v);
			TreeNode right = new TreeNode(v);
			left.left = root.left;
			right.right = root.right;
			root.left = left;
			root.right = right;
			return;
		}
		if (root.left != null)
			dfsHelper(root.left, depth + 1, v, d);
		if (root.right != null)
			dfsHelper(root.right, depth + 1, v, d);
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