package day41;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		int[] a= {2,3,8,9,27};
		aTest.largestDivisibleSubset(a);
	}
    public int getSum(int a, int b) {  
        while(b!=0)  
        {  
            int carry = a & b;  
            a = a ^ b;  
            b = carry << 1;  
        }  
        return a;  
    }
    public List<Integer> largestDivisibleSubset(int[] nums) {
    	List<Integer> res = new ArrayList<>();
    	if(nums==null||nums.length==0)
    		return res;
    	int[] dp=new int[nums.length];
    	Arrays.sort(nums);
    	Arrays.fill(dp, 1);
    	for(int i=0;i<nums.length;i++)
    	{
    		for(int j=i-1;j>=0;j--)
    		{
    			if(nums[i]%nums[j]==0)
    			{
    				dp[i]=Math.max(dp[i], dp[j]+1);
    			}
    		}
    	}
    	int maxIndex=0;
    	for(int i=0;i<dp.length;i++)
    	{
    		if(dp[maxIndex]<dp[i])
    		{
    			maxIndex=i;
    		}
    	}
    	int temp=nums[maxIndex];
    	int curDp=dp[maxIndex];
    	for(int i=maxIndex;i>=0;i--)
    	{
    		if(temp%nums[i]==0&&dp[i]==curDp)
    		{
    			res.add(nums[i]);
    			temp=nums[i];
    			curDp--;
    		}
    	}
    	return res;
    }
}
