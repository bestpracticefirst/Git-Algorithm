package day50;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		int[][] A= {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
		System.out.println(aTest.pacificAtlantic(A));
	}
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]>res=new ArrayList<>();
        if(matrix==null||matrix.length==0||matrix[0].length==0)
        	return res;
        boolean[][] reachPacific=new boolean[matrix.length][matrix[0].length];
        boolean[][] reachAtlantic=new boolean[matrix.length][matrix[0].length];
        for(int i=0;i<matrix[0].length;i++)
        {
        	reachValid(matrix,reachPacific,0,i);
        }
        for(int i=0;i<matrix.length;i++)
        {
        	reachValid(matrix,reachPacific,i,0);
        }
        for(int i=0;i<matrix[0].length;i++)
        {
        	reachValid(matrix,reachAtlantic,matrix.length-1,i);
        }
        for(int i=0;i<matrix.length;i++)
        {
        	reachValid(matrix,reachAtlantic,i,matrix[0].length-1);
        }
        for(int i=0;i<matrix.length;i++)
        {
        	for(int j=0;j<matrix[0].length;j++)
        	{
        		if(reachAtlantic[i][j]&&reachPacific[i][j])
        		{
        			res.add(new int[] {i,j});
        		}
        	}
        }
        return res;
    }
    public void reachValid(int[][] matrix,boolean[][] reachPacific,int row,int column)
    {
    	if(reachPacific[row][column])
    		return;
    	reachPacific[row][column]=true;
    	if(row>0&&matrix[row][column]<=matrix[row-1][column])
    		reachValid(matrix, reachPacific,row-1,column);
    	if(row<matrix.length-1&&matrix[row][column]<=matrix[row+1][column])
    		reachValid(matrix, reachPacific,row+1,column);
    	if(column>0&&matrix[row][column]<=matrix[row][column-1])
    		reachValid(matrix, reachPacific,row,column-1);
    	if(column<matrix[0].length-1&&matrix[row][column]<=matrix[row][column+1])
    		reachValid(matrix, reachPacific,row,column+1);
    	
    }
	public boolean canPartition(int[] nums) {
		if(nums==null||nums.length==0)
			return true;
		int sum=0;
		for(int num:nums)
			sum+=num;
		if(sum%2==1)
			return false;
		sum/=2;
		boolean[] dp=new boolean[sum+1];
		dp[0]=true;
		for(int i=0;i<nums.length;i++)
		{
			for(int j=sum;j>=nums[i];j--)
			{
				dp[j]=dp[j]||dp[j-nums[i]];
			}
		}
		return dp[sum];
	}
    public String addStrings(String num1, String num2) {
        if((num1==null||num1.length()==0)&&(num2==null||num2.length()==0))
        	return "0";
        if(num1==null||num1.length()==0)
        	return num2;
        if(num2==null||num2.length()==0)
        	return num1;
    	int cur=0;
    	if(num1.length()>num2.length())
    	{
    		String temp=num1;
    		num1=num2;
    		num2=temp;
    	}
    	int i=0;
    	StringBuilder s=new StringBuilder();
    	int len1=num1.length()-1;
    	int len2=num2.length()-1;
    	for(;i<num1.length();i++)
    	{
    		int total=num1.charAt(len1-i)-'0'+num2.charAt(len2-i)-'0'+cur;
    		cur=total/10;
    		total=total%10;
    		s.append(total);
    	}
    	for(;i<num2.length();i++)
    	{
    		int total=num2.charAt(len2-i)-'0'+cur;
    		cur=total/10;
    		total=total%10;
    		s.append(total);
    	}
        if(cur==1)
        	s.append(1);
        return s.reverse().toString();
    }
    public int thirdMax(int[] nums) {
    	if(nums==null||nums.length==0)
    		return 0;
    	HashSet<Integer> set=new HashSet<>();
    	for(int i=0;i<nums.length;i++)
    		set.add(nums[i]);
    	PriorityQueue<Integer> p = new PriorityQueue<Integer>();
    	Iterator<Integer> it=set.iterator();
    	while(it.hasNext())
    	{
    		p.add(it.next());
    		if(p.size()>3)
    			p.poll();
    	}
    	while(p.size()<3)
    	{
    		if(p.size()==1)
    			return p.poll();
    		p.poll();
    	}

    	return p.poll();

    }
	public int numberOfArithmeticSlices(int[] A) {
    	if(A.length<3)
    		return 0;
    	boolean[] isArithmetic=new boolean[A.length];
    	for(int i=2;i<A.length;i++)
    	{
    		if(A[i]-A[i-1]==A[i-1]-A[i-2])
    			isArithmetic[i]=true;
    	}
    	int[] countArithmetic=new int[A.length];
    	for(int i=2;i<A.length;i++)
    	{
    			countArithmetic[i]=countArithmetic[i-1];
    			int temp=i;
    			while(isArithmetic[temp])
    			{
    				countArithmetic[i]++;
    				temp--;
    			}
    	}
    	return countArithmetic[A.length-1];
    }

}
