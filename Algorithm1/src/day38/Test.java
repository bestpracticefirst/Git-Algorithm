package day38;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest = new Test();
		int[] a = { 1, 1, -2, 6 };
		System.out.println(aTest.countBits(9));
	}
    public int[] countBits(int num) {
        int[] count =new int[num+1];
        int pow=1;
        for(int i=1,j=0;i<=num;i++)
        {
        	if(i==pow)
        	{
        		j=0;
        		pow=pow*2;
        	}
        	count[i]=count[j]+1;
        	j++;
        }
        return count;
    }
	private int[] dfs(TreeNode node) {
		int result[] = { 0, 0 };
		if (node != null) {
			int left[] = dfs(node.left);
			int right[] = dfs(node.right);
			result[1] = left[0] + right[0];
			result[0] = Math.max(result[1], node.val + left[1] + right[1]);
		}

		return result;
	}
	

	public int rob1(TreeNode root) {
		return dfs(root)[0];
	}

	public int rob(TreeNode root) {
		if (root == null)
			return 0;
		int left = 0, right = 0;
		if (root.left != null)
			left = rob(root.left.left) + rob(root.left.right);
		if (root.right != null)
			right = rob(root.right.left) + rob(root.right.right);
		return Math.max(root.val + left + right, rob(root.left) + rob(root.right));
	}

	public boolean increasingTriplet1(int[] nums) {
		if(nums.length<3)
			return false;
		int min=nums[0];
		int mid=Integer.MAX_VALUE;
		for(int i=1;i<nums.length;i++)
		{
			if(nums[i]<=min)
				min=nums[i];
			else if(nums[i]<=mid)
				mid=nums[i];
			else
				return true;
		}
		return false;
	}

	public boolean increasingTriplet(int[] nums) {
		if (nums.length < 3)
			return false;
		int temp1 = nums[0], temp2 = Integer.MAX_VALUE, temp3 = Integer.MAX_VALUE;
		boolean flag2 = true;
		for (int i = 1; i < nums.length; i++) {
			if (flag2) {
				if (nums[i] < temp1) {
					temp1 = nums[i];
					continue;
				} else if (nums[i] > temp1) {
					temp2 = nums[i];
					flag2 = false;
					continue;
				}
				continue;
			}
			if (nums[i] > temp2) {
				return true;
			} else if (nums[i] < temp2 && nums[i] > temp1) {
				temp2 = nums[i];
			} else if (nums[i] < temp3) {
				temp3 = nums[i];
			} else {
				temp1 = temp3;
				temp2 = nums[i];
				temp3 = Integer.MAX_VALUE;
			}
		}
		return false;

	}

	public boolean isValidSerialization(String preorder) {
		String[] strs = preorder.split(",");
		int cnull = 0, cnum = 0, i = 0;
		for (; i < strs.length; i++) {
			if (strs[i].equals("#"))
				cnull++;
			else
				cnum++;
			if (cnull == cnum + 1)
				break;
		}
		return cnum + 1 == cnull && i == strs.length - 1;
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
