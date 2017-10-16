package day28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		System.out.println(a.addDigits(10000));
	}
    public int addDigits(int num) {
        return num==0 ? 0:(num%9==0 ? 9: num%9);
    }
    public int addDigits1(int num) {
    	 while (num / 10 > 0) {
             int sum = 0;
             while (num > 0) {
                 sum += num % 10;
                 num /= 10;
             }
             num = sum;
         }
         return num;
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=new ArrayList<>();
        if(root==null)
        	return res;
        dfsHelper(root, new StringBuilder(), res);
        return res;
    }
    public void dfsHelper(TreeNode root,StringBuilder s,List<String> res)
    {
    	if(root.left==null&&root.right==null)
    	{
    		StringBuilder temp=new StringBuilder(s);
    		temp.append(root.val);
    		res.add(temp.toString());
    		return;
    	}
    	s.append(root.val).append("->");
    	if(root.left!=null)
    	{
    		dfsHelper(root.left, new StringBuilder(s), res);
    	}
    	if(root.right!=null)
    	{
    		dfsHelper(root.right, new StringBuilder(s), res);
    	}
    }
	public int shortestDistance(String[] words, String word1, String word2) {
		int index1 = -1, index2 = -1, min = words.length;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				index1 = i;
				min = Math.min(min, index1 - index2);
			}
			if (words[i].equals(word2)) {
				index2 = i;
				min = Math.min(min, index2 - index1);
			}
		}
		if (index1 == -1 || index2 == -1)
			return words.length;
		return min;
	}

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length())
			return false;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
		}
		for (int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			if (map.containsKey(ch)) {
				if (map.get(ch) == 1) {
					map.remove(ch);
				} else {
					map.put(ch, map.get(ch) - 1);
				}
			} else {
				return false;
			}
		}
		if (map.isEmpty())
			return true;
		return false;
	}

	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));
				for (int m : left) {
					for (int n : right) {
						switch (ch) {
						case '+':
							res.add(m + n);
							break;
						case '-':
							res.add(m - n);
							break;
						case '*':
							res.add(m * n);
							break;
						default:
							break;
						}
					}
				}
			}
		}
		if (res.size() == 0) {
			res.add(Integer.parseInt(input));
		}
		return res;
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

class WordDistance {
	private HashMap<String, List<Integer>> indexer = new HashMap<String, List<Integer>>();

	public WordDistance(String[] words) {
		if (words == null)
			return;
		for (int i = 0; i < words.length; i++) {
			if (indexer.containsKey(words[i])) {
				indexer.get(words[i]).add(i);
			} else {
				List<Integer> positions = new ArrayList<Integer>();
				positions.add(i);
				indexer.put(words[i], positions);
			}
		}
	}

	public int shortest(String word1, String word2) {
		List<Integer> posList1 = indexer.get(word1);
		List<Integer> posList2 = indexer.get(word2);
		int i = 0, j = 0;
		int diff = Integer.MAX_VALUE;
		while (i < posList1.size() && j < posList2.size()) {
			int pos1 = posList1.get(i), pos2 = posList2.get(j);
			if (pos1 < pos2) {
				diff = Math.min(diff, pos2 - pos1);
				i++;
			} else {
				diff = Math.min(diff, pos1 - pos2);
				j++;
			}
		}
		return diff;
	}
}