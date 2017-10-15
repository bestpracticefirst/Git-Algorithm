package day27;

import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		int[][] b= {{1,1}};
		a.searchMatrix(b, 0);

	}
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0||matrix[0].length==0)
        	return false;
        int rowLeft=0, rowRight=matrix[0].length-1, columnTop = 0, columnBottom=matrix.length-1;
        while(rowLeft<=rowRight&&columnTop<=columnBottom)
        {
        	if(matrix[columnBottom][rowLeft]==target||matrix[columnTop][rowRight]==target)
        		return true;
            while(matrix[columnBottom][rowLeft]<target)
            {
            	rowLeft++;
            	if(rowLeft>rowRight)
            		return false;
            }
            while(matrix[columnTop][rowRight]>target)
            {
            	rowRight--;
            	if(rowLeft>rowRight)
            		return false;
            }
            while(matrix[columnBottom][rowLeft]>target)
            {
            	columnBottom--;
            	if(columnTop>columnBottom)
            		return false;
            }
            while(matrix[columnTop][rowRight]<target)
            {
            	columnTop++;
            	if(columnTop>columnBottom)
            		return false;
            }
        }
        return false;
        	
    }
	public int[] productExceptSelf(int[] nums) {
        int[] output = nums.clone();
        
        int p = 1;
        for(int i = 0; i < nums.length; i++){
            output[i] = p;
            p = p * nums[i];
        }
        //记录左边的乘积
        p = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            int tmp = nums[i];
            nums[i] = p;
            p = p * tmp;
        }
        //记录左边的乘积
        for(int i = 0; i < nums.length; i++){
            output[i]  *= nums[i];
        }
        return output;
    }
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root;
        return left != null ? left : right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val>q.val)
        {
        	TreeNode temp=p;
        	p=q;
        	q=temp;
        }
        if(root==null||root==p||root==q||(p.val<root.val&&q.val>root.val))
        	return root;
        if(q.val<root.val)
        	return lowestCommonAncestor(root.left, p, q);
        else
        	return lowestCommonAncestor(root.right, p, q);
    }
    
	int kth = 0;
	int res = 0;

	public int kthSmallest(TreeNode root, int k) {
		kth = k;
		dfs(root);
		return res;
	}

	public void dfs(TreeNode root) {
		if (root == null || kth == 0)
			return;
		dfs(root.left);
		if (kth == 0)
			return;
		kth--;
		if (kth == 0) {
			res = root.val;
			return;
		}
		dfs(root.right);

	}

	public boolean isPowerOfTwo(int n) {
		if (n < 1)
			return false;
		while (n != 1) {
			if (n % 2 != 0)
				return false;
			n = n / 2;
		}
		return true;
	}

	public boolean isPalindrome(ListNode head) {
		if(head==null||head.next==null)
			return true;
		ListNode temp = head;
		int count=0;
		while(temp!=null)
		{
			count++;
			temp=temp.next;
		}
		boolean flag=false;
		if(count%2==1)
			flag=true;
		count=count/2;
		temp=head;
		Stack<ListNode> res=new Stack<>();
		while(count!=0)
		{
			res.add(temp);
			temp=temp.next;
			count--;
		}
		if(flag)
		{
			temp=temp.next;
		}
		while(temp!=null)
		{
			ListNode temp2=res.pop();
			if(temp2.val!=temp.val)
				return false;
			temp=temp.next;
		}
		return true;
	}

}

class MyQueue {
	Stack<Integer> res = new Stack<Integer>();

	/** Initialize your data structure here. */
	public MyQueue() {

	}

	/** Push element x to the back of queue. */
	public void push(int x) {
		res.push(x);
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
		int result = res.get(0);
		res.remove(0);
		return result;
	}

	/** Get the front element. */
	public int peek() {
		return res.get(0);
	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
		return res.isEmpty();
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

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
