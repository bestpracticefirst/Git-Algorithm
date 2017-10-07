package dayNighteen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		a.titleToNumber("AA");

	}

	public int trailingZeroes(int n) {
		if (n < 1)
			return 0;
		int c = 0;
		while (n / 5 != 0) {
			n /= 5;
			c += n;
		}
		return c;

	}

	public int titleToNumber(String s) {
		if (s.length() < 1)
			return 0;
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			res *= 26;
			res += s.charAt(i) - 'A' + 1;
		}
		return res;
	}

	public String convertToTitle(int n) {
		if (n < 1)
			return "";

		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			int mod = (n - 1) % 26;
			sb.append((char) (mod + 'A'));
			n = (n - 1) / 26;
		}
		return sb.reverse().toString();
	}

	public int maximumGap(int[] nums) {
		if (nums.length < 2)
			return 0;
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > max)
				max = nums[i];
			if (nums[i] < min)
				min = nums[i];
		}
		int len = (int) Math.ceil((double) (max - min) / (nums.length - 1)); // 桶内数的最大差 向上取整 注意里面一定要先换算成double
																				// 要不就先向下取整了（int型除法）
		if (nums.length == 2)
			return max - min;

		int n = (max - min) / len;
		int[] bMin = new int[nums.length]; // n + 1 个 *** 其实应该可以用nums.length + 1
		int[] bMax = new int[nums.length];

		Arrays.fill(bMin, Integer.MAX_VALUE); // Arrays.fill相当于memset
		Arrays.fill(bMax, Integer.MIN_VALUE);

		for (int i = 0; i < nums.length; i++) {
			int temp = (nums[i] - min) / len; // 放入几号桶
			bMin[temp] = Math.min(nums[i], bMin[temp]); // bMin记录每个桶的最小值
			bMax[temp] = Math.max(nums[i], bMax[temp]); // bMax记录每个桶的最大值

		}

		int res = Integer.MIN_VALUE; // 最后要求的最大距离
		int pre = bMax[0];
		for (int i = 1; i < n; i++) {
			if (bMin[i] == Integer.MAX_VALUE && bMax[i] == Integer.MIN_VALUE)
				continue; // 空桶不管它
			res = Math.max(res, bMin[i] - pre);
			pre = bMax[i];
		}
		res = Math.max(res, bMin[n] - pre);
		return res;
	}

	public int findPeakElement(int[] nums) {
		if (nums.length == 1 || nums[0] > nums[1])
			return 0;
		if (nums[nums.length - 1] > nums[nums.length - 2])
			return nums.length - 1;
		for (int i = 1; i < nums.length - 1; i++) {
			if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
				return i;
		}
		return 0;

	}

	public boolean isOneEditDistance(String s, String t) {
		int diff = Math.abs(s.length() - t.length());
		if (diff > 1)
			return false;
		if (s.length() > t.length()) {
			String temp = t;
			t = s;
			s = temp;
		}

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (diff == 1) {
					return s.substring(i).equals(t.substring(i + 1));
				} else {
					return s.substring(i).equals(t.substring(i));
				}
			}
		}
		return true;
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