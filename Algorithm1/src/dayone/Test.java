package dayone;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 1, 3, 1 };
		Test aa = new Test();
		System.out.println(aa.isMatch1("aaaa", "aaaac*"));
	}

	public boolean isMatch1(String s, String p) {
		boolean[] match = new boolean[s.length() + 1];
		match[s.length()] = true;
		for (int i = p.length() - 1; i >= 0; i--) {
			if (p.charAt(i) == '*') {
				for (int j = s.length() - 1; j >= 0; j--)
					match[j] = match[j + 1] && (p.charAt(i - 1) == '.' || s.charAt(j) == p.charAt(i - 1));
				i--;
			} else {
				for (int j = 0; j < s.length(); j++)
					match[j] = match[j + 1] && (p.charAt(i) == '.' || p.charAt(i) == s.charAt(j));
				match[s.length()] = false;
			}
		}
		return match[0];
	}

	public boolean isMatch(String s, String p) {
		return helper(s, p, 0, 0);
	}

	private boolean helper(String s, String p, int i, int j) {
		if (j == p.length())
			return i == s.length();

		if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
			if (i == s.length() || s.charAt(i) != p.charAt(j) && p.charAt(j) != '.')
				return false;
			else
				return helper(s, p, i + 1, j + 1);
		}
		// p.charAt(j+1)=='*'
		while (i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
			if (helper(s, p, i, j + 2))
				return true;
			i++;
		}
		return helper(s, p, i, j + 2);
	}

	public ListNode deleteDuplicates1(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode dummyHead = new ListNode(-1);
		ListNode newDummy = dummyHead;
		ListNode current = head;
		boolean repeated = false;
		while (current.next != null) {
			if (current.val == current.next.val) {
				current = current.next;
				repeated = true;
			} else {
				if (!repeated) {
					newDummy.next = current;
					current = current.next;
					newDummy = newDummy.next;
					newDummy.next = null;
					repeated = false;
				} else {
					current = current.next;
					repeated = false;
				}
			}
		}
		if (!repeated)
			newDummy.next = current;
		return dummyHead.next;
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		ListNode length = head;
		ListNode current = head.next;
		length.next = null;
		while (current != null) {
			if (length.val == current.val) {
				current = current.next;
			} else {
				length.next = current;
				length = length.next;
				current = current.next;
				length.next = null;
			}
		}
		return dummyHead.next;
	}

	public boolean search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return false;
		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (target == nums[m])
				return true;
			if (nums[m] < nums[r]) // 如果后半部分是有序的
			{
				if (target > nums[m] && target <= nums[r]) // 如果目标在后半部分
					l = m + 1;
				else
					r = m - 1; // 目标不在后半部分，所以右端应该为m-1，再进行折半查找
			} else if (nums[m] > nums[l]) {
				if (target >= nums[l] && target < nums[m])
					r = m - 1;
				else
					l = m + 1;
			} else if (nums[m] == nums[r] && nums[m] == nums[l]) {
				if (r == 0 || l == nums.length - 1)
					return false;
				l++;
				r--;
			} else if (nums[m] == nums[l]) {
				if (m == nums.length - 1)
					return false;
				l = m + 1;
			} else if (nums[m] == nums[r]) {
				if (m == 0)
					return false;
				r = m - 1;
			}

		}
		return false;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
