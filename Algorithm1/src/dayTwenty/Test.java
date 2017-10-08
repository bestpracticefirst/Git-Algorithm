package dayTwenty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		int n=Integer.MIN_VALUE;
		System.out.println(a.isHappy(2));
	}
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer>res=new ArrayList<Integer>();
    	dfs(root,res,0);
    	return res;
    }
    public boolean isHappy(int n) {
    	HashMap<Integer,Integer> number=new HashMap<>();
    	number.put(n, 0);
        while(n!=1)
        {
        	int res=0;
        	while(n!=0)
        	{
        		res+=(n%10)*(n%10);
        		n=n/10;
        	}
        	if(number.containsKey(res))
        		return false;
        	else
        	{
        		number.put(res, 1);
        	}
        	n=res;
        }
        return true;
    }
    public void dfs(TreeNode root, List<Integer> res,int index)
    {
    	if(root==null)
    		return;
    	if(res.size()==index)
    		res.add(index, root.val);
    	dfs(root.right,res,index+1);
    	dfs(root.left,res,index+1);
    }
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        if(nums==null||nums.length==0)
        	return 0;
        if(nums.length==1)
        	return nums[0];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0], nums[1]);
        for(int i=2;i<nums.length;i++)
        {
        	dp[i]=Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res += (n & 1);
            n = n >> 1;
        }
        return res;
    }
	public int compareVersion(String version1, String version2) {
		if (version1.equals(version2))
			return 0;
		String[] res1 = version1.split("\\.");
		String[] res2 = version2.split("\\.");
		int length = res1.length > res2.length ? res1.length : res2.length;
		int[] temp1 = new int[length];
		int[] temp2 = new int[length];
		for (int i = 0; i < res1.length; i++)
			temp1[i] = Integer.valueOf(res1[i]);
		for (int i = 0; i < res2.length; i++)
			temp2[i] = Integer.valueOf(res2[i]);
		for (int i = 0; i < length; i++) {
			if (temp1[i] > temp2[i])
				return 1;
			else if (temp1[i] < temp2[i])
				return -1;
		}
		return 0;
	}
}

class BSTIterator {
    Stack<TreeNode> stack = new Stack();
    public BSTIterator(TreeNode root) {
        pullAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmp = stack.pop();
        pullAll(tmp.right);
        return tmp.val;
        
    }
    public void pullAll(TreeNode root){
        for(;root!=null;stack.push(root),root = root.left);
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