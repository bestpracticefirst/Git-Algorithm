package day35;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a= {
		            {3, 0, 1, 4, 2},
		            {5, 6, 3, 2, 1},
		            {1, 2, 0, 1, 5},
		            {4, 1, 0, 1, 7},
		            {1, 0, 3, 0, 5}};
		NumMatrix b=new NumMatrix(a);
	}
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> leaves = new ArrayList<>();
        if(n <= 1) {
            return Collections.singletonList(0);
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();     // list of edges to  Ajacency Lists
        
        for(int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        for(int i = 0; i < n; i++) {
            if(graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        while(n>2)
        {
        	n-=leaves.size();
        	List<Integer> newLeaves=new ArrayList<>();
        	for(int leaf:leaves)
        	{
        		for(int newLeaf:graph.get(leaf))
        		{
        			graph.get(leaf).remove(newLeaf);
        			graph.get(newLeaf).remove(leaf);
        			if(graph.get(newLeaf).size()==1)
        				newLeaves.add(newLeaf);
        		}
        	}
        	leaves=newLeaves;
        }
        return leaves;
    }
	public int maxProfit(int[] prices) {
		int[] buy=new int[prices.length];
		int[] sell=new int[prices.length];
		int[] rest=new int[prices.length];
		if(prices.length<2)
			return 0;
		buy[0]=-prices[0];
		sell[0]=0;
		rest[0]=0;
		for(int i=1;i<prices.length;i++)
		{
			buy[i]=Math.max(buy[i-1], rest[i-1]-prices[i]);
			sell[i]=Math.max(sell[i-1], buy[i-1]+prices[i]);
			rest[i]=Math.max(rest[i-1], Math.max(buy[i-1], sell[i-1]));
		}
		return sell[sell.length-1];
	}
	
}
class NumArray {
	class SegmentTreeNode
	{
		int start,end;
		SegmentTreeNode left,right;
		int sum;
		public SegmentTreeNode(int start,int end)
		{
			this.start=start;
			this.end=end;
			this.left=null;
			this.right=null;
			this.sum=0;
		}
	}
	SegmentTreeNode root=null;
    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);
    }
    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
    	if(start>end)
    		return null;
    	else
    	{
    		SegmentTreeNode node=new SegmentTreeNode(start, end);
    		if(start==end)
    		{
    			node.sum=nums[start];
    		}
    		else
    		{
    			int mid= start+(end-start)/2;
    			node.left=buildTree(nums, start, mid);
    			node.right=buildTree(nums, mid+1, end);
    			node.sum=node.left.sum+node.right.sum;
    		}
        	return node;
    	}
    }
    public void update(int i, int val) {
    	updateTree(root, i, val);
    }
    public void updateTree(SegmentTreeNode root,int i,int val)
    {
    	if(root.start==root.end)
    		root.sum=val;
    	else
    	{
    		int mid= root.start+(root.end-root.start)/2;
    		if(i<=mid)
    		{
    			updateTree(root.left, i, val);
    		}
    		else
    		{
    			updateTree(root.right, i, val);
    		}
    		root.sum=root.left.sum+root.right.sum;
    	}
    }
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    public int sumRange(SegmentTreeNode root, int start, int end) {
    	if(root.start==start&&root.end==end)
    	{
    		return root.sum;
    	}
    	else
    	{
    		int mid=root.start+(root.end-root.start)/2;
    		if(end<=mid)
    		{
    			return sumRange(root.left, start, end);
    		}
    		else if(start>mid)
    		{
    			return sumRange(root.right, start, end);
    		}
    		else
    		{
    			return sumRange(root.left, start, mid)+sumRange(root.right, mid+1, end);
    		}
    	}
    }
}
class NumMatrix {
	int[][] sum;
    public NumMatrix(int[][] matrix) {
    	if(matrix.length==0||matrix[0].length==0||matrix==null)
    		return;
        sum=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++)
        {
        	for(int j=0;j<matrix[0].length;j++)
        	{
        		if(j==0)
        		{
        			sum[i][j]=matrix[i][j];
        		}
        		else
        		{
        			sum[i][j]=matrix[i][j]+sum[i][j-1];
        		}
        	}
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
    	if(sum==null)
    		return 0;
        int sumRegion=0;
        for(int i=row1;i<=row2;i++)
        {
        	if(col1==0)
        	{
        		sumRegion+=sum[i][col2];
        	}
        	else
        	{
        		sumRegion+=sum[i][col2]-sum[i][col1-1];
        	}
        }
        return sumRegion;
    }
}