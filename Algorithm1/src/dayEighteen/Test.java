package dayEighteen;

import java.util.ArrayList;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		TreeNode b=new TreeNode(1);
		TreeNode c=new TreeNode(2);
		TreeNode d=new TreeNode(3);
		TreeNode e=new TreeNode(4);
		TreeNode f=new TreeNode(5);
		b.left=c;
		b.right=d;
		c.left=e;
		c.right=f;
		a.upsideDownBinaryTree(b);
		
	}
	public TreeNode upsideDownBinaryTree(TreeNode root)
	{
		if(root==null||root.left==null)
			return root;
		TreeNode l=root.left,r=root.right;
		TreeNode res=upsideDownBinaryTree(l);
		l.left=r;
		l.right=root;
		root.left=null;
		root.right=null;
		return res;
	}
    public int findMin(int[] nums) {
    	int l=0,r=nums.length-1;
    	int min=nums[0];
    	while(l<=r)
    	{
    		int m=l+(r-l)/2;
    		if(nums[l]<nums[m])
    		{
    			min=Math.min(nums[l], min);
    			l=m+1;
    		}
    		else if(nums[l]>nums[m])
    		{
    			min=Math.min(nums[m],min);
    			r=m-1;
    		}
    		else
    		{
    			l++;
    		}
    	}
    	min=Math.min(Math.min(nums[l], min),nums[r]);
    	return min;
    }
}
class MinStack {

    /** initialize your data structure here. */
	ArrayList<Integer> res=new ArrayList<>();
	ArrayList<Integer> min=new ArrayList<>();
    public MinStack() {
    }
    
    public void push(int x) {
        res.add(0, x);
        if(res.size()==1)
        	min.add(0,x);
        else
        {
        	min.add(0,Math.min(x, min.get(0)));
        }
    }
    
    public void pop() {
        res.remove(0);
        min.remove(0);
    }
    
    public int top() {
        return res.get(0);
    }
    
    public int getMin() {
        return min.get(0);
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
