package day48;

import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest = new Test();
		System.out.println(aTest.removeKdigits("1011", 2));
	}
    public int sumOfLeftLeaves(TreeNode root) {
    	if(root==null)
    		return 0;
    	if(root.left!=null&&root.left.left==null&&root.left.right==null)
    		return root.left.val+sumOfLeftLeaves(root.right);
    	return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
    }
	public String removeKdigits(String num, int k) {
		int remain = num.length() - k;
		char[] numArray = num.toCharArray(), res = new char[remain];
		int index = 0;
		for (int i = 0; i < numArray.length; i++) {
			while ((numArray.length - i > remain - index) && (index > 0 && numArray[i] < res[index - 1]))
				index--;
			if (index < remain)
				res[index++] = numArray[i];
		}
		index = -1;
		while (++index < remain) {
			if (res[index] != '0')
				break;
		}
		String s = new String(res).substring(index);

		return s.length() == 0 ? "0" : s;
	}

	public List<String> readBinaryWatch(int num) {
		List<String> res = new ArrayList<String>();
		int[] nums1 = new int[] { 8, 4, 2, 1 }, nums2 = new int[] { 32, 16, 8, 4, 2, 1 };

		for (int i = Math.max(0, num - 6); i <= Math.min(4, num); i++) {
			List<Integer> list1 = genDigit(nums1, i);
			List<Integer> list2 = genDigit(nums2, num - i);

			for (int num1 : list1) {
				if (num1 >= 12)
					continue;

				for (int num2 : list2) {
					if (num2 >= 60)
						continue;

					res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
				}
			}
		}

		return res;

	}

	private List<Integer> genDigit(int[] num, int count) {
		List<Integer> res = new ArrayList<Integer>();
		genDigitHelper(num, count, 0, 0, res);
		return res;
	}

	private void genDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
		if (count == 0) {
			res.add(sum);
			return;
		}

		for (int i = pos; i < nums.length; i++) {
			genDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
		}
	}

	public int findNthDigit(int n) {
		int len = 1;
		long count = 9;
		int start = 1;

		while (n > len * count) {
			n -= len * count;
			len += 1;
			count *= 10;
			start *= 10;
		}
		start += (n - 1) / len;
		n = n - 1;
		int mod = n % len;
		int ret = String.valueOf(start).charAt(mod) - '0';
		return ret;
	}

	Map<String, Map<String, Double>> map = new HashMap<>();

	public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < equations.length; i++) {
			set.add(equations[i][0]);
			set.add(equations[i][1]);
			Map<String, Double> m;
			if (map.containsKey(equations[i][0])) {
				m = map.get(equations[i][0]);
			} else {
				m = new HashMap<String, Double>();
			}
			m.put(equations[i][1], values[i]);
			map.put(equations[i][0], m);

			if (map.containsKey(equations[i][1])) {
				m = map.get(equations[i][1]);
			} else {
				m = new HashMap<String, Double>();
			}
			m.put(equations[i][0], 1.0 / values[i]);
			map.put(equations[i][1], m);
		}
		double result[] = new double[query.length];
		for (int i = 0; i < query.length; i++) {

			// 初始化visited标记
			Iterator<String> it = set.iterator();
			Map<String, Boolean> visited = new HashMap<String, Boolean>();
			while (it.hasNext()) {
				visited.put(it.next(), false);
			}

			if (query[i][0].equals(query[i][1]) && set.contains(query[i][0])) {
				result[i] = 1;
				continue;
			}
			// dfs
			double res = dfs(query[i][0], query[i][1], 1, visited);
			result[i] = res;
		}
		return result;
	}

	public double dfs(String s, String t, double res, Map<String, Boolean> visited) {
		if (map.containsKey(s) && !visited.get(s)) {
			visited.put(s, true);
			Map<String, Double> m = map.get(s);
			if (m.containsKey(t)) {
				return res * m.get(t);
			} else {
				Iterator<String> keys = m.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					double state = dfs(key, t, res * m.get(key), visited);
					if (state != -1.0) {
						return state;
					}
				}
			}
		} else {
			return -1.0;
		}
		return -1.0;
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