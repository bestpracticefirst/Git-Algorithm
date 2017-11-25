package day62;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(1^1);
		Test aTest = new Test();
	}
    public int singleNonDuplicate(int[] nums) {
    	if(nums==null||nums.length==0)
    		return 0;
    	int res=0;
    	for(int num:nums)
    		res^=num;
    	return res;
    }
    public int findMinDifference(List<String> timePoints) {
        if(timePoints==null||timePoints.size()==0)
        	return 0;
    	int[] res=new int[timePoints.size()];
        int i=0;
        for(String timePoint:timePoints)
        {
        	res[i++]=timeToInt(timePoint);
        }
        Arrays.sort(res);
        int min=Integer.MAX_VALUE;
        for(i=0;i<res.length-1;i++)
        {
        	min=Math.min(min, res[i+1]-res[i]);
        }
        min=Math.min(min, 1440-(res[i]-res[0]));
        return min;
    }
	private int timeToInt(String timePoint) {
		// TODO Auto-generated method stub
		String[] time=timePoint.split(":");
		return Integer.valueOf(time[0])*60+Integer.valueOf(time[1]);
	}
	TreeNode pre;
    public TreeNode convertBST(TreeNode root) {
        if(root==null)
        	return root;
        convertBST(root.right);
        if(pre!=null)
        	root.val+=pre.val;
        pre=root;
        convertBST(root.left);
        return root;
    }
    public int findPairs(int[] nums, int k) {
    	if(nums==null|nums.length==0)
    		return 0;
        int count=0;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
        	if(i>0&&nums[i]==nums[i-1])
        		continue;
        	for(int j=i+1;j<nums.length;j++)
        	{
        		if(nums[j]-nums[i]==k)
        		{
        			count++;
        			break;
        		}
        		if(nums[j]-nums[i]>k)
        			break;
        	}
        }
        return count;
    }
	int minDiff = Integer.MAX_VALUE;
    TreeNode prev;
    public int getMinimumDifference(TreeNode root) {
    	inorder(root);
    	return minDiff;
    }
	private void inorder(TreeNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		inorder(root.left);
		if(prev!=null)
			minDiff=Math.min(minDiff, root.val-prev.val);
		prev=root;
		inorder(root.right);
	}
	public char[][] updateBoard(char[][] board, int[] click) {
		if (click == null || click.length < 2 || board == null || board.length == 0 || board[0].length == 0
				|| click[0] >= board.length || click[1] >= board[0].length)
			return board;
		int m = click[0];
		int n = click[1];
		int col = board.length;
		int row = board[0].length;
		if (board[m][n] == 'M') {
			board[m][n] = 'X';
			return board;
		}
		boolean[][] visited = new boolean[col][row];
		updateBoard(board, click, visited);
		return board;
	}

	public void updateBoard(char[][] board, int[] click, boolean[][] visited) {
		int m = click[0];
		int n = click[1];
		visited[m][n] = true;
		if (m == 0 && n == 6)
			System.out.println(1);
		if (board[m][n] == 'B') {
			visited[m][n] = true;
			return;
		}
		int count = countHelper(board, m, n, visited);
		if (count == 0)
			board[m][n] = 'B';
		else {
			board[m][n] = (char) ('0' + count);
			return;

		}
		if (m - 1 >= 0) {
			if (n - 1 >= 0 && visited[m - 1][n - 1] != true) {
				int[] nextClick = { m - 1, n - 1 };
				updateBoard(board, nextClick, visited);
			}
			if (visited[m - 1][n] != true) {
				int[] nextClick = { m - 1, n };
				updateBoard(board, nextClick, visited);
			}
			if (n + 1 < board[0].length && visited[m - 1][n + 1] != true) {
				int[] nextClick = { m - 1, n + 1 };
				updateBoard(board, nextClick, visited);
			}
		}
		if (n - 1 >= 0 && visited[m][n - 1] != true) {
			int[] nextClick = { m, n - 1 };
			updateBoard(board, nextClick, visited);
		}
		if (n + 1 < board[0].length && visited[m][n + 1] != true) {
			int[] nextClick = { m, n + 1 };
			updateBoard(board, nextClick, visited);
		}
		if (m + 1 < board.length) {
			if (n - 1 >= 0 && visited[m + 1][n - 1] != true) {
				int[] nextClick = { m + 1, n - 1 };
				updateBoard(board, nextClick, visited);
			}
			if (visited[m + 1][n] != true) {
				int[] nextClick = { m + 1, n };
				updateBoard(board, nextClick, visited);
			}
			if (n + 1 < board[0].length && visited[m + 1][n + 1] != true) {
				int[] nextClick = { m + 1, n + 1 };
				updateBoard(board, nextClick, visited);
			}
		}

	}

	private int countHelper(char[][] board, int m, int n, boolean[][] visited) {
		// TODO Auto-generated method stub
		int col = board.length;
		int row = board[0].length;
		int count = 0;
		if (m - 1 >= 0 && board[m - 1][n] == 'M') {
			count++;
			visited[m - 1][n] = true;
		}
		if (m - 1 >= 0 && n - 1 >= 0 && board[m - 1][n - 1] == 'M') {
			count++;
			visited[m - 1][n - 1] = true;
		}
		if (m - 1 >= 0 && n + 1 <= row - 1 && board[m - 1][n + 1] == 'M') {
			count++;
			visited[m - 1][n + 1] = true;
		}
		if (n - 1 >= 0 && board[m][n - 1] == 'M') {
			count++;
			visited[m][n - 1] = true;
		}
		if (n + 1 <= row - 1 && board[m][n + 1] == 'M') {
			count++;
			visited[m][n + 1] = true;
		}
		if (m + 1 <= col - 1 && board[m + 1][n] == 'M') {
			count++;
			visited[m + 1][n] = true;
		}
		if (m + 1 <= col - 1 && n - 1 >= 0 && board[m + 1][n - 1] == 'M') {
			count++;
			visited[m + 1][n - 1] = true;
		}
		if (m + 1 <= col - 1 && n + 1 <= row - 1 && board[m + 1][n + 1] == 'M') {
			count++;
			visited[m + 1][n + 1] = true;
		}
		return count;
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
class Codec {
    private Map<String,String> map= new HashMap<>(); 
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
    	if(map.containsKey(longUrl))
    		return map.get(longUrl);
    	String shortUrl="http://tinyurl.com/" + longUrl.hashCode();
        map.put(shortUrl,longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}