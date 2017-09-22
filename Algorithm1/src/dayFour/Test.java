package dayFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode a=new ListNode(1);
		ListNode b=new ListNode(2);
		ListNode c=new ListNode(3);
		a.next=b;
		b.next=c;
		Test d = new Test();
		d.reverseBetween(a, 2, 3);
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null||m==n)
			return head;
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		ListNode tempDummy = dummyHead;
		ListNode mNode = null, nNode = null, bmNode = null, bnNode = null;
		int i=1;
		while (tempDummy.next != null) {
			if (i == m) {
				bmNode = tempDummy;
				mNode = tempDummy.next;
				tempDummy = tempDummy.next;
				i++;
				while (tempDummy.next != null) {
					if (i == n) {
						if (n-1!= m) {
							bnNode = tempDummy;
							nNode = tempDummy.next;
							ListNode temp = nNode.next;
							nNode.next = mNode.next;
							bmNode.next = nNode;
							mNode.next = temp;
							bnNode.next = mNode;
							return dummyHead.next;
						} else {
							nNode = tempDummy.next;
							mNode.next=nNode.next;
							nNode.next=mNode;
							bmNode.next=nNode;
							return dummyHead.next;
						}
					}
					tempDummy=tempDummy.next;
					i++;
					
				}
			}
			tempDummy=tempDummy.next;
			i++;
		}
		return dummyHead.next;
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		res.add(item);
		if (nums == null || nums.length == 0)
			return res;
		Arrays.sort(nums);
		subsetsHelper(nums, new ArrayList<>(item), res, 0);
		return res;

	}

	public int numDecodings1(String s) {
		if (s == null || s.length() == 0 || s.charAt(0) == '0') {
			return 0;
		}
		int[] dp = new int[s.length() + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= s.length(); ++i) {
			if (s.charAt(i - 1) != '0') {
				dp[i] = dp[i - 1];
			}
			int twoDigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
			if (twoDigits >= 10 && twoDigits <= 26) {
				dp[i] += dp[i - 2];
			}
		}
		return dp[s.length()];
	}

	public int numDecodings(String s) {
		int[] dynamic = new int[s.length() + 1];
		if (s.length() == 0 || s.charAt(0) == '0')
			return 0;
		if (s.length() < 2)
			return 1;
		dynamic[0] = 1;
		dynamic[1] = 1;
		for (int i = 2; i <= s.length(); i++) {
			String temp = s.substring(i - 2, i);
			if (Integer.valueOf(temp) == 0) {
				return 0;
			}
			if (s.charAt(i - 1) == '0' && s.charAt(i - 2) < '3') {
				dynamic[i] = dynamic[i - 2];
				continue;
			} else if (s.charAt(i - 1) == '0' && s.charAt(i - 2) > '2') {
				return 0;
			}
			if (s.charAt(i - 2) == '0') {
				dynamic[i] = dynamic[i - 1];
				continue;
			}
			if (Integer.valueOf(temp) <= 26 && s.charAt(i - 1) != '0' && s.charAt(i - 2) != '0') {
				dynamic[i] = dynamic[i - 1] + dynamic[i - 2];
			} else {
				dynamic[i] = dynamic[i - 1];
			}
		}
		return dynamic[s.length()];
	}

	public void subsetsHelper(int[] nums, ArrayList<Integer> item, List res, int begin) {
		if (begin == nums.length)
			return;
		for (int i = begin; i < nums.length; i++) {
			if (i > begin && nums[i] == nums[i - 1]) {
				continue;
			}
			item.add(nums[i]);
			res.add(new ArrayList<>(item));
			subsetsHelper(nums, item, res, i + 1);
			item.remove(item.size() - 1);
		}

	}

	public List<Integer> grayCode(int n) {
		if (n == 0) {
			List<Integer> result = new ArrayList<Integer>();
			result.add(0);
			return result;
		}

		List<Integer> tmp = grayCode(n - 1);
		int addNumber = 1 << (n - 1);
		ArrayList<Integer> result = new ArrayList<Integer>(tmp);
		for (int i = tmp.size() - 1; i >= 0; i--) {
			result.add(addNumber + tmp.get(i));
		}
		return result;
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
