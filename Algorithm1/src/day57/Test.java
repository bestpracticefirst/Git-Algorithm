package day57;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest = new Test();
		int[] a= {4,6,7,7};
		System.out.println(aTest.findSubsequences(a));
	}
    public List<List<Integer>> findSubsequences1(int[] nums) {
        Set<List<Integer>> res= new HashSet<List<Integer>>();
        List<Integer> holder = new ArrayList<Integer>();
        findSequence(res, holder, 0, nums);
        List result = new ArrayList(res);
        return result;
    }

   public void findSequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
       if (holder.size() >= 2) {
           res.add(new ArrayList(holder));
       }
       for (int i = index; i < nums.length; i++) {
           if(holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
               holder.add(nums[i]);
               findSequence(res, holder, i + 1, nums);
               holder.remove(holder.size() - 1);
           }
       }
   }
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res=new HashSet<>();
        if(nums==null||nums.length<2)
        	return new ArrayList(res);
        for(int i=0;i<nums.length;i++)
        {
        	List<Integer> item=new ArrayList<>();
        	item.add(nums[i]);
        	dfs(nums,res,item,i);
        }
        return new ArrayList(res);
    }
    private void dfs(int[] nums, Set<List<Integer>> res, List<Integer> item, int start) {
		// TODO Auto-generated method stub
		if(item.size()>1)
			res.add(new ArrayList<Integer>(item));
		for(int i=start+1;i<nums.length;i++)
		{
			if(nums[i]>=item.get(item.size()-1))
			{
				item.add(nums[i]);
				dfs(nums, res, item,i);
				item.remove(item.size()-1);
			}
		}
	}
	public boolean PredictTheWinner(int[] nums) {
    	int n=nums.length;
    	int[][] dp=new int[n][n];
    	for(int i=0;i<n;i++)
    	{
    		dp[i][i]=nums[i];
    	}
    	for(int len=1;len<n;len++)
    	{
    		for(int i=0;i<n-len;i++)
    		{
    			int j=i+len;
    			dp[i][j]=Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
    		}
    	}
    	return dp[0][n-1]>=0;
    }
	public int findMaxConsecutiveOnes(int[] nums) {
        int max=0;
        int currentMax=0;
        for(int i=0;i<nums.length;i++)
        {
        	if(nums[i]==1)
        	{
        		currentMax++;
        		max=Math.max(max, currentMax);
        	}
        	else
        	{
        		currentMax=0;
        	}
        }
        return max;
    }
    public String licenseKeyFormatting(String S, int K) {
    	String s=S.replaceAll("-", "").toUpperCase();
        int count=s.length();
    	int mod=count%K;
    	if(mod==0)
    		mod=K;
    	boolean flag=true;
    	StringBuilder  res=new StringBuilder();
    	for(int i=0;i<count;i++)
    	{
    		if(mod==0)
    		{
    			res.append("-");
    			mod=K;
    		}
    		mod--;
    		res.append(s.charAt(i));
    	}
    	return res.toString();
    		
    }
    public int magicalString(int n) {
    	if(n==0)
    		return 0;
    	if(n<4)
    		return 1;
    	StringBuilder s=new StringBuilder();
    	s.append("122");
    	int i=2;
    	while(s.length()<n)
    	{
    		int count=Integer.valueOf(s.substring(i,i+1));
    		int value=Integer.valueOf(s.substring(s.length()-1));
    		value= value==1?2:1;
    		for(int j=0;j<count;j++)
    		{
    			s.append(value);
    		}
    		i++;
    	}
    	int count=0;
    	String temp=s.toString();
    	for(int j=0;j<n;j++)
    	{
    		if(temp.charAt(j)=='1')
    			count++;
    	}
    	return count;
    }
	 public int largestPalindrome1(int n) {
		    // 边界处理
		    if(n==1){
		        return 9; // 单位数是回文数
		    }
		    int maxnumber = (int)Math.pow(10,n)-1;

		    for(int i=maxnumber;i>maxnumber/10;i--){ // 保证了数值递减
		        long num = toLong(i);

		        for(long j=maxnumber;j*j>=num;j--){
		            if(num%j==0){ // num已是回文数，只要mod为0就找到了结果
		                return (int)(num%1337);
		            }
		        }
		    }
		    return 0;
		}
	public int largestPalindrome(int n) {
		if(n==0)
			return 0;
		if(n==1)
			return 9;
		int upbound=(int) (Math.pow(10, n)-1);
		for(int i=upbound;i>upbound/10;i--)
		{
			long num=toLong(i);
			for(long j=upbound;j*j>=num;j--)
			{
				if(num%j==0)
					return (int) (num%1337);
			}
		}
		return 0;
	}
	public long toLong(int i)
	{
		StringBuffer s=new StringBuffer();
		s=s.append(i).reverse();
		return Long.valueOf(i+s.toString());
	}
}
