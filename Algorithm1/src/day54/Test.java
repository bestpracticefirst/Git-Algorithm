package day54;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a= {{10,16},{2,8},{1,6},{7,12}};
		Test aTest=new Test();
		aTest.findMinArrowShots(a);
	}
    public int minMoves(int[] nums) {
    	if(nums==null||nums.length<2)
    		return 0;
    	int min=Integer.MAX_VALUE;
    	int sum=0;
    	for(int i=0;i<nums.length;i++)
    	{
    		sum+=nums[i];
    		min=Math.min(min, nums[i]);
    	}
    	return sum-min*nums.length;
    }
    public int findMinArrowShots(int[][] points) {
    	if(points==null||points.length==0)
    		return 0;
        Arrays.sort(points,new Comparator<int []>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])
					return o1[0]-o2[0];
				else
					return o1[1]-o2[1];
			}
		});
        int count=1;
        int currentEnd=points[0][1];
        int i=1;
        while(i<points.length)
        {
        	if(points[i][0]>currentEnd)
        	{
        		count++;
        		currentEnd=points[i][1];
        	}
        	else
        	{
        		currentEnd=Math.min(currentEnd, points[i][1]);
        	}
        	i++;
        }
        return count;
        
    }
    public String frequencySort(String s) {
        Map<Character, Integer> map=new HashMap<>();
        for(char c:s.toCharArray())
        {
        	if(map.containsKey(c))
        	{
        		map.put(c, map.get(c)+1);
        	}
        	else
        	{
        		map.put(c, 1);
        	}
        }
        List<Character>[] bucket=new List[s.length()+1];
        for(Character key:map.keySet())
        {
        	int frequency=map.get(key);
        	if(bucket[frequency]==null)
        		bucket[frequency]=new ArrayList<>();
        	bucket[frequency].add(key);
        }
        StringBuilder sb=new StringBuilder();
        for(int pos=bucket.length-1;pos>=0;pos--)
        {
        	if(bucket[pos]!=null)
        	{
        		for(char num:bucket[pos])
        		{
        			for(int i=0;i<map.get(num);i++)
        			{
        				sb.append(num);
        			}
        		}
        	}
        }
        return sb.toString();
        
    }
    public TreeNode deleteNode(TreeNode root, int key) {
    	if(root==null)
    		return root;
    	if(root.val==key)
    	{
    		return deleteNodeHelper(root);
    	}
    	if(root.val>key)
    	{
    		root.left=deleteNode(root.left, key);
    	}
    	else
    	{
    		root.right=deleteNode(root.right, key);
    	}
    	return root;
    }
    TreeNode deleteNodeHelper(TreeNode root)
    {
		if(root.left==null)
			return root.right;
		if(root.right==null)
			return root.left;
		TreeNode next=root.right;
		TreeNode pre = null;
		while(next.left!=null)
		{
			pre=next;
			next=next.left;
		}
        next.left = root.left;
        if(root.right != next) {
            pre.left = next.right;
            next.right = root.right;
        }
        return next;
    }
}

class Codec {
	StringBuilder data;
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		data = new StringBuilder();
		serializeHelper(root);
		return data.toString();
	}
	void serializeHelper(TreeNode root)
	{
		if(root==null)
			return;
		if(data.length()==0)
		{
			data.append(root.val);
		}
		else
		{
			data.append(",").append(root.val);
		}
		serializeHelper(root.left);
		serializeHelper(root.right);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] dataArray=data.split(",");
		if(data==null||data.length()==0)
			return null;
		int[] nums=new int[dataArray.length];
		for(int i=0;i<nums.length;i++)
		{
			nums[i]=Integer.valueOf(dataArray[i]);
		}
		return deserializeHelper(nums, 0, nums.length-1);
		
	}
	TreeNode deserializeHelper(int[] data,int start,int end)
	{
		if(start>end)
			return null;
		TreeNode root=new TreeNode(data[start]);
		if(start==end)
			return root;
		int breakPoint=-1;
		for(int i=start+1;i<=end;i++)
		{
			if(data[i]>data[start])
			{
				breakPoint=i;
				break;
			}
		}
		if(breakPoint!=-1)
		{
			root.left=(deserializeHelper(data, start+1, breakPoint-1));
			root.right=deserializeHelper(data, breakPoint, end);
		}
		else {
			root.left=deserializeHelper(data, start+1, end);
		}
		return root;
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