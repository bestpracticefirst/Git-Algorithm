package dayTwelve;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		
	}
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length==0||cost.length==0)
        	return -1;
        int sum=0;
        int total=0;
        int start=0;
        for(int i=0;i<gas.length;i++)
        {
        	total+=gas[i]-cost[i];
        	if(sum<0)
        	{
        		sum=gas[i]-cost[i];
        		start=i;
        	}
        	else
        	{
        		sum+=gas[i]-cost[i];
        	}
        }
        return total>=0?start:-1;
    }
	private int max = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		helper(root);
		return max;
	}

	public int helper(TreeNode root) {
		if (root == null)
			return 0;
		int left = helper(root.left);
		int right = helper(root.right);

		left = left < 0 ? 0 : left;
		right = right < 0 ? 0 : right;
		max = Math.max(left + right + root.val, max);

		return root.val + Math.max(left, right);
	}
	public int sumNumbers(TreeNode root) {
		if(root==null)
			return 0;
		return sumHelper(root,0);
	}
	public int sumHelper(TreeNode root, int sum)
	{
		if(root.left==null&&root.right==null)
			return sum*10+root.val;
		int left=0, right=0;
		sum=sum*10+root.val;
		if(root.left!=null)
			left=sumHelper(root.left, sum);
		if(root.right!=null)
			right=sumHelper(root.right, sum);
		return left+right;
	}

	public boolean hasCycle(ListNode head) {
		// extra space: hashset.
		// no extra space: fast pointer and slow pointer
		if (head == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public int longestConsecutive(int[] nums) {
		int res = 0;
		Set<Integer> s = new HashSet<Integer>();
		for (int num : nums)
			s.add(num);
		for (int num : nums) {
			if (s.remove(num)) {
				int pre = num - 1, next = num + 1;
				while (s.remove(pre))
					--pre;
				while (s.remove(next))
					++next;
				res = Math.max(res, next - pre - 1);
			}
		}
		return res;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
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