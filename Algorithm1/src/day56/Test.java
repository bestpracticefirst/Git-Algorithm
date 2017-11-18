package day56;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="aaaa";
		String s2="aaaa";
		System.out.println(s1==s2);
	}
    public boolean repeatedSubstringPattern(String s) {
        if(s==null||s.length()<2)
        	return false;
        int lenth=s.length();
        for(int i=1;i<=s.length()/2;i++)
        {
        	if(lenth%i==0)
        	{
        		String temp1=s.substring(0, i);
        		String temp2=s.replaceAll(temp1, "");
        		if(temp2.length()==0)
        			return true;
        	}
        }
        return false;
    }
    public int hammingDistance(int x, int y) {
        int count=0;
        while(x!=0||y!=0)
        {
        	count+=(x&1)^(y&1);
        	x=x>>1;
            y=y>>1;
        }
        return count;
    }
    public int minMoves2(int[] nums) {
    	if(nums==null||nums.length<2)
    		return 0;
    	Arrays.sort(nums);
    	int a=nums[nums.length/2];
        int  res=0;
    	for(int i=0;i<nums.length;i++)
    	{
    		res+=Math.abs(nums[i]-a);
    	}
    	return res;
    }
    Map<Integer, Boolean> map;
    boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if(sum < desiredTotal) return false;
        if(desiredTotal <= 0) return true;
        
        map = new HashMap();
        used = new boolean[maxChoosableInteger+1];
        return helper(desiredTotal);
    }
    
    public boolean helper(int desiredTotal){
        if(desiredTotal <= 0) return false;
        int key = format(used);
        if(!map.containsKey(key)){
    // try every unchosen number as next step
            for(int i=1; i<used.length; i++){
                if(!used[i]){
                    used[i] = true;
     // check whether this lead to a win (i.e. the other player lose)
                    if(!helper(desiredTotal-i)){
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }
   
// transfer boolean[] to an Integer 
    public int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }
    public int findSubstringInWraproundString(String p) {
    	int[] count=new int[26];
    	int maxCurLenth=0;
    	for(int i=0;i<p.length();i++)
    	{
    		if(i>0&&(p.charAt(i)-p.charAt(i-1)==1||p.charAt(i-1)-p.charAt(i)==25))
    			maxCurLenth++;
    		else
    			maxCurLenth=1;
    		count[p.charAt(i)-'a']=Math.max(count[p.charAt(i)-'a'], maxCurLenth);
    	}
    	int sum=0;
    	for(int i=0;i<26;i++)
    	{
    		sum+=count[i];
    	}
    	return sum;
    }
    public boolean makesquare(int[] nums) {
    	if(nums==null||nums.length<4)
    		return false;
    	int sum=0;
    	for(int num:nums)
    		sum+=num;
    	if(sum%4!=0)
    		return false;
    	return dfs(nums, new int[4], 0, sum/4);
    }
    public boolean dfs(int[]nums, int[]sums,int index, int target)
    {
    	if(index==nums.length&&sums[0]==target&&sums[1]==target&&sums[2]==target)
    		return true;
    	else if(index==nums.length)
    		return false;
    	for(int i=0;i<sums.length;i++)
    	{
    		if(sums[i]+nums[index]>target)
    			continue;
    		sums[i]+=nums[index];
    		if(dfs(nums, sums, index+1, target))
    			return true;
    		sums[i]-=nums[index];
    	}
    	return false;	
    }
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp=new int[m+1][n+1];
        for(String s:strs)
        {
        	int[] count=count(s);
        	for(int i=m;i>=count[0];i--)
        	{
        		for(int j=n;j>=count[1];j--)
        		{
        			dp[i][j]=Math.max(dp[i][j], 1+dp[i-count[0]][j-count[1]]);
        		}
        	}
        }
        return dp[m][n];
        
    }
    public int[] count(String str)
    {
    	int[] res=new int[2];
    	for(int i=0;i<str.length();i++)
    	{
    		res[str.charAt(i)-'0']++;
    	}
    	return res;
    }
    public int findRadius(int[] houses, int[] heaters) {
    	Arrays.sort(houses);
    	Arrays.sort(heaters);
    	int res=0;
    	for(int house:houses)
    	{
    		int left=0,right=heaters.length-1;
    		boolean flag=true;
    		while(left+1<right)
    		{
    			int mid=left+(right-left)/2;
    			if(heaters[mid]==house)
    			{
    				flag=false;
    				break;
    			}
    			if(heaters[mid]>house)
    				right=mid;
    			else
    				left=mid;   				
    		}
    		if(flag)
    		{
    			res=Math.max(res, Math.min(Math.abs(house-heaters[left]), Math.abs(house-heaters[right])));
    		}
    	}
    	return res;
    }
    public int findComplement(int num) {
    	int i=31;
    	int lead=(num>>i)&1;
    	while(lead==0&&i>=0)
    	{
    		i--;
    		lead=(num>>i)&1;
    	}
    	int res=0;
    	while(i>=0)
    	{
    		res+=(((num>>i)&1)^1)<<i;
    		i--;
    	}
    	return res;
    }
    public int totalHammingDistance(int[] nums) {
    	int total=0,n=nums.length;
    	for(int i=0;i<32;i++)
    	{
    		int bitcount=0;
    		for(int j=0;j<nums.length;j++)
    		{
    			bitcount+=((nums[j]>>i)&1);
    		}
    		total+=bitcount*(n-bitcount);
    	}
    	return total;
    }
}
