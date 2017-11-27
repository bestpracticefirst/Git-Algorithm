package day64;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test=new Test();
		System.out.println(test.reverseWords("Let's take LeetCode contest"));
 	}
    public String reverseWords(String s) {
        if(s==null||s.length()==0)
        	return s;
        String[] words=s.split(" ");
        StringBuilder sb=new StringBuilder();
        for(String word:words)
        {
        	for(int i=word.length()-1;i>=0;i--)
        	{
        		sb.append(word.charAt(i));
        	}
        	sb.append(" ");
        }
        return sb.toString().trim();
    }
    public int nextGreaterElement(int n) {
    	if(n<11)
    		return -1;
    	char[] arr=String.valueOf(n).toCharArray();
		char currentMax=arr[arr.length-1];
		int swap2=arr.length-1;
    	for(int i=arr.length-2;i>=0;i--)
    	{
    		if(arr[i]>currentMax)
    		{
    			currentMax=arr[i];
    			swap2=i;
    		}
    		else if(arr[i]!=currentMax)
    		{
    			int swap1=i;

    			for(int j=arr.length-1;j>i;j--)
    			{
    				if(arr[j]>arr[swap1]&&arr[j]<currentMax)
    				{
    					currentMax=arr[j];
    					swap2=j;
    				}
    			}
    			char temp=arr[swap1];
    			arr[swap1]=arr[swap2];
    			arr[swap2]=temp;
    			int left=++i;
    			int right=arr.length-1;
    			while(left<right)
    			{
        			char temp1=arr[left];
        			arr[left]=arr[right];
        			arr[right]=temp1;
        			left++;
        			right--;
    			}
    			if(Double.valueOf(String.valueOf(arr))>Integer.MAX_VALUE)
    				return -1;
    			else
    				return Integer.valueOf(String.valueOf(arr));
    				
    		}
    		
    	}
    	return -1;
    }
    public int leastBricks(List<List<Integer>> wall) {
        if(wall==null||wall.size()==0||wall.get(0).size()==0)
        	return 0;
        HashMap<Integer, Integer> map=new HashMap<>();
        for(List<Integer> column:wall)
        {
        	int sum=0;
        	for(int i=0;i<column.size()-1;i++)
        	{
        		int wid=column.get(i);
        		sum+=wid;
        		if(map.containsKey(sum))
        		{
        			map.put(sum, map.get(sum)+1);
        		}
        		else
        		{
        			map.put(sum, 1);
        		}
        	}
        }
        int res=wall.size();
        for(int key:map.keySet())
        {
        	res=Math.min(res, wall.size()-map.get(key));
        }
        return res;
    }
    public String optimalDivision(int[] nums) {
        if(nums==null||nums.length==0)
        	return "";
        if(nums.length==1)
        	return String.valueOf(nums[0]);
        if(nums.length==2)
        	return String.valueOf(nums[0])+"/"+String.valueOf(nums[1]);
        StringBuilder sb=new StringBuilder();
        sb.append(nums[0]);
        sb.append("/(");
        sb.append(nums[1]);
        for(int i=2;i<nums.length;i++)
        {
        	sb.append("/");
        	sb.append(nums[i]);
        }
        sb.append(")");
        return sb.toString();
        
    }
}
