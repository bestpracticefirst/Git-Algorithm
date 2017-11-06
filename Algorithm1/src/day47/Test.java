package day47;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		int[] A= {4,3,2,6};
		aTest.maxRotateFunction(A);
	}
    public int integerReplacement(int n) {
        if(n==Integer.MAX_VALUE)
        	return 32;
        int count=0;
        while(n>1)
        {
        	if(n%2==0)
        	{
        		n=n/2;
        		count++;
        	}
        	else if((n+1)%4==0&&n!=3)
        	{
        		n++;
        		count++;
        	}
        	else
        	{
        		n--;
        		count++;
        	}
        }
        return count;
    }
    public int maxRotateFunction(int[] A) {
    	if(A==null||A.length==0)
    		return 0;
    	int sum=0;
    	int len=A.length;
    	int F=0;
    	for(int i=0;i<len;i++)
    	{
    		F+=i*A[i];
    		sum+=A[i];
    	}
    	int max=F;
    	for(int i=len-1;i>0;i--)
    	{
    		F=F+sum-len*A[i];
    		max=Math.max(max, F);
    	}
    	return max;
    }
	public int longestSubstring(String s, int k) {
		int n=s.length();
		if(n<k)
			return 0;
		int[] counter=new int[26];
		boolean[] valid = new boolean[26];
		boolean fullvalid=true;
		for(int i=0;i<s.length();i++)
			counter[s.charAt(i)-'a']++;
		for(int i=0;i<26;i++)
		{
			if(counter[i]<k&&counter[i]>0)
			{
				valid[i]=false;
				fullvalid=false;
			}
			else
			{
				valid[i]=true;
			}
		}
		if(fullvalid)
			return s.length();
		int max=0;
		int lastStart=0;
		for(int i=0;i<s.length();i++)
		{
			if(valid[s.charAt(i)-'a']==false)
			{
				max=Math.max(longestSubstring(s.substring(lastStart, i), k),max);
				lastStart=i+1;
			}
		}
		max=Math.max(max, longestSubstring(s.substring(lastStart), k));
		return max;
		
	}

}
class Solution {
	int[] nums;
	HashMap<Integer, List<Integer>> index=new HashMap<>();
	Random random;
    public Solution(int[] nums) {
    	this.nums=nums;
    	random = new Random();
        for(int i=0;i<nums.length;i++)
        {
        	if(index.containsKey(nums[i]))
        	{
        		index.get(nums[i]).add(i);
        	}
        	else
        	{
        		List<Integer> item =new ArrayList<>();
        		item.add(i);
        		index.put(nums[i], item);
        	}
        }
        
    }
    
    public int pick(int target) {
        List<Integer> temp=index.get(target);
        int size= temp.size();
        if(size==1)
        	return temp.get(0);
        else
        {
        	return temp.get(random.nextInt(size));
        }
    }
}