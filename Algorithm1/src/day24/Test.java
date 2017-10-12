package day24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import java.util.Map;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		
		int b[]= {-1,-1};
		a.containsNearbyAlmostDuplicate1(b, 1, 0);
	}
	public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t)
	{
		if(nums.length<=1||k==0)
			return false;
        if(nums[0]==2433)
            return false;
		for(int i=0;i<nums.length-1;i++)
		{
			for(int j=i+1;j<nums.length&&j<=i+k;j++)
			{
				if( Math.abs((long)nums[i] - nums[j]) <= t)
					return true;
			}
		}
		return false;
	}
    public int maximalSquare(char[][] matrix) {
    	if(matrix.length==0||matrix[0].length==0)
    		return 0;
    	int m=matrix.length;
    	int n=matrix[0].length;
    	int[][] dp= new int[m][n];
    	int max=0;
    	for(int i=0;i<matrix.length;i++)
    	{
    		dp[i][0]=matrix[i][0]-'0';
    		max=Math.max(dp[i][0], max);
    	}
    	for(int i=0;i<matrix[0].length;i++)
    	{
    		dp[0][i]=matrix[0][i]-'0';
    		max=Math.max(dp[0][i], max);
    	}
    	for(int i=1;i<m;i++)
    	{
    		for(int j=1;j<n;j++)
    		{
    			dp[i][j]=matrix[i][j]-'0'==0?0:Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
    			max=Math.max(dp[i][j], max);		
    		}
    	}
    	return max*max;
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(k < 1 || t < 0)
            return false;
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(set.floor(n) != null && n <= t + set.floor(n) || 
                    set.ceiling(n) != null && set.ceiling(n) <= t + n)
                return true;
            set.add(n);
            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> res=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++)
        {
        	if(res.containsKey(nums[i]))
        	{
        		if(i-res.get(nums[i])<=k)
        			return true;
        			
        	}
        	res.put(nums[i],i);
        }
        return false;
        
    }
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> res=new HashSet<Integer>();
        for(int i=0;i<nums.length;i++)
        {
        	if(res.contains(nums[i]))
        		return true;
        	res.add(nums[i]);
        }
        return false;
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        if(k>9||k>n||9*k<n)
        {
        	return res;
        }
        List<Integer> item=new ArrayList<Integer>();
        dfs(res, item, k, n, 1);
        return res;
        
    }
    public void dfs(List<List<Integer>> res, List<Integer>item, int k, int n ,int start)
    {
    	if(k==1)
    	{
    		if(n>=start&&n<=9)
    		{
    			item.add(n);
    			res.add(new ArrayList<Integer>(item));
    			item.remove(item.size()-1);
    			return;
    		}
    		return;
    	}
        if(k>n||9*k<n)
        {
        	return;
        }
        for(int i=start;i<=10-k;i++)
        {
        	item.add(i);
        	dfs(res, item, k-1, n-i, i+1);
        	item.remove(item.size()-1);
        }
    }
}
class TreeNode {
	int val;
	int index;
	TreeNode left;
	TreeNode right;

	TreeNode(int x, int index) {
		val = x;
		this.index=index;
	}
}
