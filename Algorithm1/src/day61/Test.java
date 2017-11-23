package day61;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] b = { "ale", "apple", "monkey", "plea" };
		String aString = "abpcplea";
		Test aTest = new Test();
		System.out.println(aTest.countArrangement(4));
		;
	}
	private int count = 0;

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	private void helper(int[] nums, int start) {
		if (start == 0) {
			count++;
			return;
		}
		for (int i = start; i > 0; i--) {
			swap(nums, start, i);
			if (nums[start] % start == 0 || start % nums[start] == 0)
				helper(nums, start - 1);
			swap(nums, i, start);
		}
	}

	public int countArrangement(int N) {
		if (N == 0)
			return 0;
		int[] nums = new int[N + 1];
		for (int i = 0; i <= N; i++)
			nums[i] = i;
		helper(nums, N);
		return count;
	}

	public int findMaxLength(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0)
				nums[i] = -1;
		}
		int max = 0, sum = 0;
		map.put(0, -1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum)) {
				max = Math.max(max, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		return max;
	}

	public String findLongestWord(String s, List<String> d) {
		String temp = "";
		if (s == null || s.length() == 0 || d == null || d.size() == 0)
			return temp;
		for (String dic : d) {
			if (dic.length() < temp.length())
				continue;
			if (isDeleting(s, dic)) {
				if (dic.length() > temp.length())
					temp = dic;
				else if (dic.length() == temp.length()) {
					String[] str = { temp, dic };
					Arrays.sort(str);
					temp = str[0];
				}
			}
		}
		return temp;
	}

	private boolean isDeleting(String s, String dic) {
		// TODO Auto-generated method stub
		int i = 0, j = 0;
		for (; i < s.length() && j < dic.length(); i++) {
			if (s.charAt(i) == dic.charAt(j))
				j++;
		}
		if (j == dic.length())
			return true;
		else
			return false;
	}

}
