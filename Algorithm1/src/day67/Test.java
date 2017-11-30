package day67;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null)
        	return t2;
        if(t2==null)
        	return t1;
        t1.val+=t2.val;
        t1.left=mergeTrees(t1.left, t2.left);
        t1.right=mergeTrees(t1.right, t2.right);
        return t1;
    }
    public int triangleNumber(int[] nums) {
    	if(nums==null||nums.length<3)
    		return 0;
        Arrays.sort(nums);
        int res=0;
        for(int i=0;i<nums.length;i++)
        {
        	for(int j=i+1;j<nums.length;j++)
        	{
        		for(int k=j+1;k<nums.length;k++)
        		{
        			if(nums[i]+nums[j]>nums[k])
        				res++;
        			else
        				break;
        		}
        	}
        }
        return res;
    }
	public String tree2str(TreeNode t) {
		if(t==null)
			return "";
		StringBuilder sb=new StringBuilder();
		preOrder(t,sb);
		return sb.substring(1,sb.length()-1);
	}

	private void preOrder(TreeNode t, StringBuilder sb) {
		// TODO Auto-generated method stub
		sb.append("("+t.val);
		if(t.left==null&&t.right!=null)
		{
			sb.append("()");
			preOrder(t.right, sb);

		}
		else if(t.left!=null&&t.right==null)
		{
			preOrder(t.left, sb);
		}
		else if(t.left!=null&&t.right!=null)
		{
			preOrder(t.left, sb);
			preOrder(t.right, sb);

		}
		sb.append(")");
	}

	public boolean canPlaceFlowers1(int[] flowerbed, int n) {
		if (n == 0)
			return true;

		// padding
		int[] flower = new int[flowerbed.length + 2];
		System.arraycopy(flowerbed, 0, flower, 1, flowerbed.length);

		int ptr = 1;
		while (ptr < flower.length - 1) {
			if (flower[ptr] == 0 && flower[ptr - 1] == 0 && flower[ptr + 1] == 0) {
				flower[ptr] = 1;
				ptr += 2;
				n--;
				if (n == 0)
					return true;
				continue;
			}
			ptr++;
		}
		return false;
	}

	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		if (n == 0)
			return true;
		if (flowerbed == null || flowerbed.length == 0)
			return false;
		for (int i = 0; i < flowerbed.length; i += 2) {
			if (flowerbed[i] == 0) {
				if (i == 0) {
					if (flowerbed.length == 1) {
						if (n == 1)
							return true;
						else
							return false;
					} else if (flowerbed[1] == 0) {
						flowerbed[i] = 1;
						n--;
						continue;
					}
				} else if (i == flowerbed.length - 1) {
					if (flowerbed[i - 1] == 0) {
						flowerbed[i] = 1;
						n--;
					}
				} else {
					if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
						flowerbed[i] = 1;
						n--;
					}
				}
			}
			if (n == 0)
				return true;
		}
		return false;
	}

	public String[] findRestaurant(String[] list1, String[] list2) {
		if (list1 == null || list2 == null || list1.length == 0 || list2.length == 0)
			return new String[0];
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < list1.length; i++) {
			map.put(list1[i], i);
		}
		Set<String> set = new HashSet<>();
		int cur = Integer.MAX_VALUE;
		for (int i = 0; i < list2.length; i++) {
			if (map.containsKey(list2[i])) {
				int sum = map.get(list2[i]) + i;
				if (sum < cur) {
					cur = sum;
					set.clear();
					set.add(list2[i]);
				} else if (sum == cur) {
					set.add(list2[i]);
				}
			}
		}
		String[] res = new String[set.size()];
		int count = 0;
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			res[count++] = it.next();
		}
		return res;
	}

	public int maxCount(int m, int n, int[][] ops) {
		int row = Integer.MAX_VALUE;
		int column = Integer.MAX_VALUE;
		for (int[] op : ops) {
			row = Math.min(op[0], row);
			column = Math.min(op[1], column);
		}
		return Math.min(m, row) * Math.min(n, column);

	}

	public int findPaths(int m, int n, int N, int i, int j) {
		if (N <= 0)
			return 0;
		int mod = 1000000007;
		int result = 0;
		int[][] dicts = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int[][] count = new int[m][n];
		count[i][j] = 1;
		for (int step = 0; step < N; step++) {
			int[][] temp = new int[m][m];
			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					for (int[] dic : dicts) {
						int nr = r + dic[0];
						int nc = c + dic[1];
						if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
							result = (result + count[r][c]) % mod;
						} else {
							temp[nr][nc] = (temp[nr][nc] + count[r][c]) % mod;
						}
					}
				}
			}
			count = temp;
		}
		return result;
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