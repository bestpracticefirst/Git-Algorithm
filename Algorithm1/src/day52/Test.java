package day52;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		int[] a= {4,3,2,7,8,2,3,1};
		System.out.println(aTest.findDuplicates(a));
	}
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> res=new ArrayList<>();
        if(nums==null||nums.length==0)
        	return res;
        for(int i=0;i<nums.length;i++)
        {
        	if(nums[Math.abs(nums[i])-1]<0)
        		res.add(Math.abs(nums[i]));
        	else
        	{
        		nums[Math.abs(nums[i])-1]=-nums[Math.abs(nums[i])-1];
        	}
        }
        return res;
    }
    public int arrangeCoins(int n) {
        double c=((long)n)*8;
        double d=Math.sqrt(1+c);
        return (int)(d-1)/2;
    }
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if(s == null || p == null || s.length() < p.length())
            return ans;
        
        int[] hash = new int[256];
        for(int i = 0; i < p.length(); ++i) 
            ++hash[p.charAt(i)];
        
        int left = 0, right = 0, cnt = p.length();
        while(right < s.length()) {
            if(hash[s.charAt(right)] > 0) 
            	--cnt;
            --hash[s.charAt(right)];
            ++right;
            
            if(cnt == 0)
            	ans.add(left);
            if(right - left == p.length()) {
                if(hash[s.charAt(left)] >= 0)
                	++cnt;
                ++hash[s.charAt(left)];
                ++left;
            }
        }
        return ans;
    }
    public List<Integer> findAnagrams(String s, String p) {
    	List<Integer> res=new ArrayList<>();
    	if(s==null||s.length()==0||p==null||p.length()==0||s.length()<p.length())
    		return res;
        int[] count=new int[26];
        for(int i=0;i<p.length();i++)
        	count[p.charAt(i)-'a']++;
        for(int i=0;i<=s.length()-p.length();i++)
        {
        	int[] temp=count.clone();
        	int j=i;
        	for(;j<i+p.length();j++)
        	{
        		temp[s.charAt(j)-'a']--;
        		if(temp[s.charAt(j)-'a']==-1)
        			break;
        	}
        	if(j==i+p.length())
        		res.add(i);
        }
        return res;
    }
	int count=0;
	public int pathSum(TreeNode root, int sum) {
		if(root==null)
			return 0;
		pathHelper(root, sum);
		pathSum(root.left, sum);
		pathSum(root.right, sum);
		return count;
	}
	public void pathHelper(TreeNode root,int sum)
	{
		if(root==null)
			return;
		if(root.val==sum)
			count++;
		pathHelper(root.left, sum-root.val);
		pathHelper(root.right, sum-root.val);
			
	}

	public int[] findRightInterval(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return new int[0];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < intervals.length; i++) {
			min = Math.min(min, intervals[i].start);
			max = Math.max(max, intervals[i].end);
		}
		int[] start = new int[max - min + 1];
		Arrays.fill(start, -1);
		for (int i = 0; i < intervals.length; i++) {
			start[intervals[i].start - min] = i;
		}
		for (int i = start.length - 2; i >= 0; i--) {
			if (start[i] == -1)
				start[i] = start[i + 1];
		}
		int[] ans = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			ans[i] = start[intervals[i].end - min];
		}
		return ans;
	}

}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
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