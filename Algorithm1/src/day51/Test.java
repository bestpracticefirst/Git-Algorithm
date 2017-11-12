package day51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest = new Test();
		Integer[] a= {1,2,3,4};
		Arrays.sort(a,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});
		
		char[][] board = { { 'X', '.', '.', 'X' }, { '.', '.', '.', 'X' }, { '.', '.', '.', 'X' } };
		System.out.println(aTest.countSegments(", , , ,        a, eaefa"));
	}
    public int eraseOverlapIntervals(Interval[] intervals) {
    	if(intervals==null||intervals.length<2)
    		return 0;
        Arrays.sort(intervals,new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				    return o1.end-o2.end;
			}
		});
        Interval interval=intervals[0];
        int count=0;
        for(int i=1;i<intervals.length;i++)
        {
        	if(intervals[i].start<interval.end)
        		count++;
        	else
        	{
        		interval=intervals[i];
        	}
        }
        return count;
    }
	public int countSegments(String s) {
		String[] res = s.split(" ");
		int count = 0;
		for (String temp : res) {
			if (!temp.equals(""))
				count++;
		}
		return count;
	}

	public int characterReplacement(String s, int k) {
		if (s == null)
			return 0;
		if (s.length() < k + 1)
			return s.length();
		int len = 0;
		int[] replaceCounts = new int[26];
		HashMap<Character, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				List<Integer> item = new ArrayList<>();
				item.add(i);
				map.put(s.charAt(i), item);
				len = Math.max(len, 1 + k);
			} else {
				List<Integer> item = map.get(s.charAt(i));
				int lastIndex = item.get(item.size() - 1);
				item.add(i);
				replaceCounts[s.charAt(i) - 'A'] += i - lastIndex - 1;
				while (replaceCounts[s.charAt(i) - 'A'] > k) {
					int temp = item.get(0);
					item.remove(0);
					lastIndex = item.get(0);
					replaceCounts[s.charAt(i) - 'A'] -= lastIndex - temp - 1;
				}
				lastIndex = item.get(0);
				len = Math.max(len, i - lastIndex + 1 + k - replaceCounts[s.charAt(i) - 'A']);
			}
		}
		return Math.min(s.length(), len);
	}

	public String originalDigits(String s) {
		if (s == null || s.length() == 0)
			return s;
		int[] counts = new int[26];
		String[] temp = { "zero", "two", "four", "six", "eight", "five", "seven", "three", "nine", "one" };
		int[] nums = { 0, 2, 4, 6, 8, 5, 7, 3, 9, 1 };
		int[] chars = { 'z', 'w', 'u', 'x', 'g', 'f', 's', 't', 'i', 'o' };
		for (int i = 0; i < s.length(); i++) {
			counts[s.charAt(i) - 'a']++;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			int count = counts[chars[i] - 'a'];
			for (int j = 0; j < temp[i].length(); j++) {
				counts[temp[i].charAt(j) - 'a'] -= count;
			}
			map.put(nums[i], count);
		}
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int count = map.get(i);
			while (count > 0) {
				res.append(i);
				count--;
			}
		}
		return res.toString();

	}

	public int findMaximumXOR(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		Trie root = new Trie();
		for (int num : nums) {
			Trie curNode = root;
			for (int i = 31; i >= 0; i--) {
				int curBit = (num >> i) & 1;
				if (curNode.children[curBit] == null)
					curNode.children[curBit] = new Trie();
				curNode = curNode.children[curBit];
			}
		}
		int max = Integer.MIN_VALUE;
		for (int num : nums) {
			int curSum = 0;
			Trie curNode = root;
			for (int i = 31; i >= 0; i--) {
				int curBit = (num >> i) & 1;
				if (curNode.children[curBit ^ 1] != null) {
					curSum += (1 << i);
					curNode = curNode.children[curBit ^ 1];
				} else
					curNode = curNode.children[curBit];
			}
			max = Math.max(max, curSum);
		}
		return max;
	}

	public int countBattleships(char[][] board) {
		int count = 0;
		if (board == null || board.length == 0 || board[0].length == 0)
			return count;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'X') {
					if (i > 0 && board[i - 1][j] == 'X')
						continue;
					if (j > 0 && board[i][j - 1] == 'X')
						continue;
					count++;
				}
			}
		}
		return count;
	}

}

class Trie {
	Trie[] children;

	public Trie() {
		children = new Trie[2];
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