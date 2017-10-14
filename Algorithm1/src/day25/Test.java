package day25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test h = new Test();
		int[] a= {0,1,2,4,5,7};
		h.summaryRanges(a);
	}

	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<String>();
		if (nums == null || nums.length == 0)
			return res;
		int pre = nums[0];
		StringBuilder s = new StringBuilder();
		s.append(pre).append("->");
		int currentTotal=1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - pre != 1) 
			{
				if(currentTotal==1)
				{
					s.delete(s.length()-2, s.length());
					res.add(s.toString());
					s=new StringBuilder();
					pre=nums[i];
					s.append(pre).append("->");
				}
				else
				{
					s.append(pre);
					res.add(s.toString());
					s=new StringBuilder();
					pre=nums[i];
					s.append(pre).append("->");
					currentTotal=1;
				}		
			}
			else {
				currentTotal++;
				pre = nums[i];
			}

		}
		if(currentTotal==1)
		{
			s.delete(s.length()-2, s.length());
			res.add(s.toString());
		}
		else
		{
			s.append(pre);
			res.add(s.toString());
		}	
		return res;
	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;

	}

	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int[] heights = { B, D, F, H };
		Arrays.sort(heights);
		int[] lengths = { A, C, E, G };
		Arrays.sort(lengths);
		return (lengths[2] - lengths[1]) * (heights[2] - heights[1]);
	}

	public int countNodes(TreeNode root) {
		if (root == null)
			return 0;

		int l = getLeft(root) + 1;
		int r = getRight(root) + 1;

		if (l == r) {
			return (2 << (l - 1)) - 1;
		} else {
			return countNodes(root.left) + countNodes(root.right) + 1;
		}
	}

	private int getLeft(TreeNode root) {
		int count = 0;
		while (root.left != null) {
			root = root.left;
			++count;
		}
		return count;
	}

	private int getRight(TreeNode root) {
		int count = 0;
		while (root.right != null) {
			root = root.right;
			++count;
		}
		return count;
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