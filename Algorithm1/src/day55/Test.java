package day55;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a= {3,1,6,3, 4, 2};
		Test aTest=new Test();
		aTest.find132pattern(a);
	}
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int s3 = Integer.MIN_VALUE;
        for(int i=nums.length-1;i>=0;i--){
            if(s3>nums[i]) 
            	return true;
            while(!stack.isEmpty()&&nums[i]>stack.peek()){
                s3 = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }

    public int findContentChildren(int[] g, int[] s) {
    	if(g==null||g.length==0||s==null||s.length==0)
    		return 0;
    	Arrays.sort(g);
    	Arrays.sort(s);
    	int children=0;
    	for(int cookie=0;children<g.length&&cookie<s.length;cookie++)
    	{
    		if(g[children]<=s[cookie])
    			children++;
    	}
    	return children;
    	
    }
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if(A==null||A.length==0)
        	return 0;
        Map<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<A.length;i++)
        {
        	for(int j=0;j<B.length;j++)
        	{
        		int sum=A[i]+B[j];
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
        int res=0;
        for(int i=0;i<C.length;i++)
        {
        	for(int j=0;j<D.length;j++)
        	{
        		int sum=-(C[i]+D[j]);
        		if(map.containsKey(sum))
        		{
        			res+=map.get(sum);
        		}		
        	}
        }
        return res;
    }
}
