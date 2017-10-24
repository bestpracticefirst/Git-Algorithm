package day34;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		int[] a= {1,3,6,7,9,4,10,5,6};
		aTest.lengthOfLIS(a);
	}
	
    public int lengthOfLIS(int[] nums) {
    	if(nums.length<2)
    		return nums.length;
    	int[] Longest=new int[nums.length];
    	Longest[0]=1;
    	int max=0;
    	for(int i=1;i<nums.length;i++)
    	{
    		for(int j=0;j<i;j++)
    		{
    			if(nums[j]<nums[i])
    				Longest[i]=Math.max(Longest[i], Longest[j]);
    		}
    		Longest[i]+=1;
    		max=Math.max(max, Longest[i]);
    	}
    	return max;
    	
    }
    public String getHint(String secret, String guess) {
        if(secret.length()!=guess.length())
        	return null;
        int bull=0;
        int[] map=new int[10];
        int[] map1=new int[10];
        for(int i=0;i<secret.length();i++)
        {
        	if(secret.charAt(i)==guess.charAt(i))
        		bull++;
        	else
        	{
        		map[secret.charAt(i)-'0']++;
        		map1[guess.charAt(i)-'0']++;
        	}
        }
        int cows=0;
        for(int i=0;i<10;i++)
        {
        	cows+=Math.min(map[i], map1[i]);
        }
        String s=bull+"A"+cows+"B";
        return s;
    }
}
class NumArray {
    int[] sums;  
    
    public NumArray(int[] nums) {  
        sums = new int[nums.length];  
        System.arraycopy(nums, 0, sums, 0, nums.length);  
        for(int i=1; i<sums.length; i++) {  
            sums[i] += sums[i-1];   
        }  
    }  
  
    public int sumRange(int i, int j) {  
        if(i>j || i<0 || j<0 || j>=sums.length) return 0;  
        return i==0 ? sums[j] : (sums[j] - sums[i-1]);  
    }  
}
