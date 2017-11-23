package day58;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest = new Test();
		
		System.out.println();
	}
	int max=0;
	int countNode=1;
	Integer pre=null;
	public int[] findMode(TreeNode root) {
		if(root==null)
			return new int[0];
		List<Integer> res=new ArrayList<>();
		dfsHelper(root,res);
		int[] resArray= new int[res.size()];
		for(int i=0;i<res.size();i++)
			resArray[i]=res.get(i);
		return resArray;
	}
	private void dfsHelper(TreeNode root, List<Integer> res) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		dfsHelper(root.left,res);
		if(pre!=null)
		{
			if(root.val==pre)
				countNode++;
			else
				countNode=1;
		}
		if(countNode>max)
		{
			res.clear();
			res.add(root.val);
			max=countNode;
		}
		else if(countNode==max)
		{
			res.add(root.val);
		}
		pre=root.val;
		dfsHelper(root.right, res);
		
	}

	public int[] findDiagonalOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return new int[0];
		int M = matrix.length;
		int N = matrix[0].length;
		int[] res = new int[M * N];
		boolean flag = true;
		int i = 0;
		int j = 0;
		for (int k = 0; k < res.length; k++) {
			res[k] = matrix[i][j];
			if (flag) {
				if (i - 1 >= 0 && j + 1 < N) {
					i = i - 1;
					j = j + 1;
				} else if (j + 1 < N) {
					j = j + 1;
					flag = false;
				} else {
					i = i + 1;
					flag = false;
				}
			} else {
				if (i + 1 < M && j - 1 >= 0) {
					i = i + 1;
					j = j - 1;
				} else if (i + 1 < M) {
					i = i + 1;
					flag = true;
				} else {
					j = j + 1;
					flag = true;
				}
			}
		}
		return res;
	}

	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0)
			return new int[0];
		int[] res = new int[nums1.length];
		Arrays.fill(res, -1);
		for (int i = 0; i < nums1.length; i++) {
			int currentMax = -1;
			boolean flag = false;
			for (int j = 0; j < nums2.length; j++) {
				if (nums2[j] == nums1[i]) {
					flag = true;
					continue;
				}
				if (flag) {
					if (nums2[j] > nums1[i]) {
						currentMax = nums2[j];
						break;
					}
				}

			}
			res[i] = currentMax;
		}
		return res;
	}

	public int[] constructRectangle(int area) {
		if (area == 0)
			return new int[] { 0, 0 };
		int W = (int) Math.sqrt(area);
		for (int i = W; W > 0; W--) {
			if (area % W == 0)
				return new int[] { area / W, W };
		}
		return new int[] { 0, 0 };
	}

	public int findPoisonedDuration(int[] timeSeries, int duration) {
		if (timeSeries == null || timeSeries.length == 0)
			return 0;
		if (timeSeries.length == 1)
			return duration;
		int res = duration;
		for (int i = 1; i < timeSeries.length; i++) {
			if (timeSeries[i] - timeSeries[i - 1] < duration)
				res += timeSeries[i] - timeSeries[i - 1];
			else
				res += duration;
		}
		return res;
	}

	int count = 0;

	public int findTargetSumWays(int[] nums, int S) {
		if (nums == null || nums.length == 0)
			return 0;
		dfsHelper(nums, S, 0);
		return count;
	}

	private void dfsHelper(int[] nums, int s, int start) {
		// TODO Auto-generated method stub
		if (start == nums.length - 1) {
			if (nums[start] == Math.abs(s)) {
				if (nums[start] == 0)
					count += 2;
				else
					count++;
			}
			return;
		}
		dfsHelper(nums, s - nums[start], start + 1);
		dfsHelper(nums, s + nums[start], start + 1);
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