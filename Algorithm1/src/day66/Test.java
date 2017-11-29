package day66;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Test test=new Test();
		int[] a= {2, 6, 4, 8, 10, 9, 15};
		System.out.println(test.findUnsortedSubarray(a));
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