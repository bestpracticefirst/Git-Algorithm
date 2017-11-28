package day65;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 1, 2, 1 };
		Test test = new Test();
		test.subarraySum1(a, 3);
	}
    public boolean checkInclusion(String s1, String s2) {
    	if((s1==null&&s2==null)||s1.length()>s2.length())
    		return false;
    	if(s1.equals(" ")||s1.equals(s2))
    		return true;
    	int[] count=new int[26];
    	for(int i=0;i<s1.length();i++)
    	{
    		count[s1.charAt(i)-'a']++;
    		count[s2.charAt(i)-'a']--;
    	}
    	if(allZero(count))
    		return true;
    	for(int i=s1.length();i<s2.length();i++)
    	{
    		count[s2.charAt(i)]++;
    		count[s2.charAt(i-s1.length())]--;
    		if(allZero(count))
    			return true;
    	}
    	return false;
    }
    private boolean allZero(int[] count) {
		// TODO Auto-generated method stub
		for(int i=0;i<count.length;i++)
		{
			if(count[i]!=0)
				return false;
		}
		return true;
	}
	public int[][] matrixReshape(int[][] nums, int r, int c) {
    	if(nums==null||nums.length==0||nums[0].length==0)
    		return nums;
    	int m=nums.length;
    	int n=nums[0].length;
    	if(m*n!=r*c||m==r)
    		return nums;
    	int[][] res=new int[r][c];
    	for(int i=0;i<m;i++)
    	{
    		for(int j=0;j<n;j++)
    		{
    			int number=i*n+j;
    			res[number/c][number%c]=nums[i][j];
    		}
    	}
    	return res;
    }
    public int findTilt(TreeNode root) {
        if(root==null)
        	return 0;
        return findHelper(root)[0];
    }
	private int[] findHelper(TreeNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return new int[] {0,0};
		if(root.left==null&&root.right==null)
			return new int[] {0,root.val};
		int[] subLeft=findHelper(root.left);
		int[] subRight=findHelper(root.right);
		return new int[] {subLeft[0]+subRight[0]+Math.abs(subLeft[1]-subRight[1]),subLeft[1]+subRight[1]+root.val};
		
	}
	public int arrayPairSum(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int res = 0;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i % 2 == 0)
				res += nums[i];
		}
		return res;
	}

	public int subarraySum(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return 0;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum == k)
					count++;
			}
		}
		return count;
	}

	public int subarraySum1(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return 0;
		int count = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		for (int num : nums) {
			sum += num;
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			if (map.containsKey(sum)) {
				map.put(sum, map.get(sum) + 1);
			} else {
				map.put(sum, 1);
			}
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