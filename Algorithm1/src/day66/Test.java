package day66;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		Test test=new Test();
		int[] p1 = {0,0}, p2 = {1,1}, p3 = {1,0}, p4 = {0,1};
		System.out.println(test.validSquare(p1, p2, p3, p4));
	}
    public int findLHS(int[] nums) {
    	int currentMax=0;
    	Map<Integer, Integer>map = new HashMap<>();
    	for(int num:nums)
    	{
    		if(map.containsKey(num))
    		{
    			map.put(num, map.get(num)+1);
    		}
    		else
    		{
    			map.put(num, 1);
    		}
    		int count=map.get(num);
    		if(map.containsKey(num-1))
    		{
    			currentMax=Math.max(currentMax,map.get(num-1)+count);
    		}
    		if(map.containsKey(num+1))
    		{
    			currentMax=Math.max(currentMax,map.get(num+1)+count);
    		}
    	}
    	return currentMax;
    }
    public double dist(int[] p1, int[] p2) {
        return Math.pow((p2[0] - p1[0]), 2) + Math.pow((p2[1] - p1[1]), 2);
    }
    
    public boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        return dist(p1, p2) != 0 && dist(p1, p2) == dist(p2, p3) && dist(p2, p3) == dist(p3, p4) && dist(p3, p4) == dist(p4, p1) && dist(p1, p3) == dist(p2, p4);
    }
    
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return check(p1, p2, p3, p4) || check(p1, p3, p2, p4) || check(p1, p2, p4, p3);
    }
	public int minDistance(String word1, String word2) {
        if(word1==null||word2==null)
        	return 0;
        if(word1.length()==0)
        	return word2.length();
        if(word2.length()==0)
        	return word1.length();
        int m=word1.length();
        int n=word2.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<=m;i++)
        {
        	dp[i][0]=i;
        }
        for(int i=0;i<=n;i++)
        {
        	dp[0][i]=i;
        }
        for(int i=1;i<=m;i++)
        {
        	for(int j=1;j<=n;j++)
        	{
        		if(word1.charAt(i-1)==word2.charAt(j-1))
        		{
        			dp[i][j]=dp[i-1][j-1];
        		}
        		else
        		{
        			dp[i][j]=Math.min(dp[i-1][j-1]+2, Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
        		}
        	}
        }
        return dp[m][n];
    }
    public int findUnsortedSubarray(int[] nums) {
    	if(nums==null||nums.length==0)
    		return 0;
    	int min=Integer.MAX_VALUE;
    	int currentStart=nums.length-1;
    	for(int i=nums.length-1;i>=0;i--)
    	{
    		if(nums[i]<=min)
    		{
    			min=nums[i];
    		}
    		else
    		{
    			currentStart=i;
    		}
    	}

    	int max=Integer.MIN_VALUE;
    	int currentEnd=0;
    	for(int i=0;i<nums.length;i++)
    	{
    		if(nums[i]>=max)
    		{
    			max=nums[i];
    		}
    		else
    		{
    			currentEnd=i;
    		}
    	}
    	if(currentEnd<=currentStart)
    		return 0;
    	return currentEnd-currentStart+1;
    }
    public int distributeCandies(int[] candies) {
    	if(candies==null||candies.length==0)
    		return 0;
    	Arrays.sort(candies);
    	int count=1;
    	for(int i=1;i<candies.length;i++)
    	{
    		if(candies[i]!=candies[i-1])
    			count++;
    		if(count>=candies.length/2)
    			return candies.length/2;
    	}
    	return count;
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
    	StringBuilder sb=new StringBuilder();
    	toString(s,sb);
    	StringBuilder tb=new StringBuilder();
    	toString(t,tb);
    	return sb.toString().indexOf(tb.toString())>=0;

    }
    public void toString(TreeNode node, StringBuilder res) {
        if (node == null) {
            res.append(",#");
        } else {
            res.append("," + node.val);
            toString(node.left, res);
            toString(node.right, res);
        }
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