package dayThirteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		a.partition("aab");
	}

	 public List<List<String>> partition(String s) {
	        int[][] dp=new int[s.length()][s.length()];
	        
	        List<List<String>> result=new ArrayList<List<String>>();
	        List<String> r=new ArrayList<String>();
	        
	        for(int i=0;i<s.length();i++)
	        {
	            for(int j=i;j<s.length();j++)
	            {
	                int k=0;
	                for(;k<(j-i+1)/2;k++)
	                {
	                    if(s.charAt(i+k)!=s.charAt(j-k)) break;
	                }
	                
	                if(k==(j-i+1)/2)
	                {
	                	dp[i][j]=1;
	                }
	            }
	        }
	        
	        dfs(0,s,dp,r,result);
	        
	        return result;
	    }
	    
	void dfs(int i,String s,int[][] dp,List<String> r,List<List<String>> result)
	    {
	        if(i==s.length())
	        {
	        	ArrayList<String> t=new ArrayList<String>(r);
	        	Collections.reverse(t);
	            result.add(t);
	            return;
	        }
	        
	        for(int j=i;j<s.length();j++)
	        {
	            if(dp[i][j]==1)
	            {
	                r.add(0,s.substring(i,j+1));
	                dfs(j+1,s,dp,r,result);
	                r.remove(0);
	            }
	        }
	    }

	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		int length = (int) (nums.length * 0.5);
		for (int i = 0; i <= length; i++) {
			if (nums[i] == nums[i + length])
				return nums[i];
		}
		return 0;

	}

	public void solve(char[][] board) {
		if (board.length <= 2 || board[0].length <= 2) {
			return;
		}
		int row = board.length;
		int col = board[0].length;
		for (int i = 0; i < row; i++) {
			if (board[i][0] == 'O') {
				dfs(board, i, 0);
			}
			if (board[i][col - 1] == 'O') {
				dfs(board, i, col - 1);
			}
		}
		for (int j = 0; j < col; j++) {
			if (board[0][j] == 'O') {
				dfs(board, 0, j);
			}
			if (board[row - 1][j] == 'O') {
				dfs(board, row - 1, j);
			}
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
	}

	public void dfs(char[][] board, int i, int j) {

		if (board[i][j] == 'O') {
			board[i][j] = '#';
			if (i > 1 && board[i - 1][j] == 'O') {
				dfs(board, i - 1, j);
			}
			if (i + 1 < board.length && board[i + 1][j] == 'O') {
				dfs(board, i + 1, j);
			}
			if (j > 1 && board[i][j - 1] == 'O') {
				dfs(board, i, j - 1);
			}
			if (j + 1 < board[0].length && board[i][j + 1] == 'O') {
				dfs(board, i, j + 1);
			}
		}
	}

	public boolean isPalindrome(String s) {
		if (s == null || s.length() == 0 || s.length() == 1)
			return true;
		int l = 0;
		int r = s.length() - 1;
		while (l < r) {
			char lc = s.charAt(l);
			while ((lc < '0' || lc > '9') && (lc < 'A' || lc > 'Z') && (lc < 'a' || lc > 'z')) {
				if (l >= r)
					break;
				l++;
				lc = s.charAt(l);
			}
			char rc = s.charAt(r);
			while ((rc < '0' || rc > '9') && (rc < 'A' || rc > 'Z') && (rc < 'a' || rc > 'z')) {
				if (l >= r)
					break;
				r--;
				rc = s.charAt(r);
			}
			if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
				return false;
			l++;
			r--;
		}
		return true;
	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0)
			return paths;
		Set<String> dic = new HashSet<>(wordList);
		if (!dic.contains(endWord))
			return paths;
		if (beginWord.equals(endWord)) {
			List<String> path = new ArrayList<String>();
			path.add(endWord);
			paths.add(path);
			return paths;
		}
		Set<String> q1 = new HashSet<>(), q2 = new HashSet<>();
		Map<String, List<String>> prev = new HashMap<>();
		q1.add(beginWord);
		dic.remove(beginWord);
		q2.add(endWord);
		dic.remove(endWord);
		boolean isL2R = true, found = false;
		while (!q1.isEmpty() && !q2.isEmpty()) {
			if (q1.size() > q2.size()) {
				Set<String> temp = q1;
				q1 = q2;
				q2 = temp;
				isL2R = !isL2R;
			}
			Set<String> next = new HashSet<>();
			for (String word : q1) {
				char[] chArr = word.toCharArray();
				for (int i = 0; i < chArr.length; ++i) {
					char c = chArr[i];
					for (char ch = 'a'; ch <= 'z'; ++ch) {
						if (ch == c)
							continue;
						chArr[i] = ch;
						String newWord = new String(chArr);
						if (q2.contains(newWord)) {
							found = true;
							if (isL2R) {
								record(word, newWord, prev);
							} else {
								record(newWord, word, prev);
							}
						} else if (dic.contains(newWord)) {
							next.add(newWord);
							if (isL2R) {
								record(word, newWord, prev);
							} else {
								record(newWord, word, prev);
							}
						}
					}
					chArr[i] = c;
				}
			}
			if (found)
				break;
			q1 = next;
			dic.removeAll(next);
		}

		List<String> path = new LinkedList<String>();
		path.add(endWord);
		buildPaths(prev, path, endWord, beginWord, paths);
		return paths;
	}

	private void buildPaths(Map<String, List<String>> prev, List<String> path, String word, String target,
			List<List<String>> paths) {
		if (word.equals(target)) {
			paths.add(new ArrayList<String>(path));
			return;
		}
		if (!prev.containsKey(word))
			return;
		for (String preWord : prev.get(word)) {
			path.add(0, preWord);
			buildPaths(prev, path, preWord, target, paths);
			path.remove(0);
		}
	}

	private void record(String a, String b, Map<String, List<String>> prev) {
		if (!prev.containsKey(b)) {
			prev.put(b, new ArrayList<String>());
		}
		prev.get(b).add(a);
	}
}
