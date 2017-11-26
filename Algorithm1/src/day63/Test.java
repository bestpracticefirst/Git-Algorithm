package day63;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest = new Test();
		int[][] a = { { 1, 0, 1, 1, 0, 0, 1, 0, 0, 1 }, { 0, 1, 1, 0, 1, 0, 1, 0, 1, 1 },
				{ 0, 0, 1, 0, 1, 0, 0, 1, 0, 0 }, { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1 }, { 0, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
				{ 0, 0, 1, 0, 1, 1, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 0, 1, 1 }, { 1, 0, 0, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 0, 1, 0 }, { 1, 1, 1, 1, 0, 1, 0, 0, 1, 1 } };
		aTest.updateMatrix(a);
	}
	int mod= 1000000007;
    public int checkRecord(int n) {
        if(n==0)
        	return 0;
        long[] PorL=new long[n+1];
        long[] P=new long[n+1];
        PorL[0]=P[0]=1;
        PorL[1]=2;
        P[1]=1;
        for(int i=2;i<=n;i++)
        {
        	P[i]=PorL[i-1];
        	PorL[i]=(P[i]+P[i-1]+P[i-2])%mod;
        }
        long res=PorL[n];
        for(int i=1;i<n;i++)
        {
        	long s=(PorL[i]*PorL[n-i-1])%mod;
        	res=(res+s)%mod;
        }
        return (int)res;
    }
    public boolean checkRecord(String s) {
    	if(s==null||s.length()<2)
    		return true;
    	int Acount=0;
    	for(int i=0;i<s.length();i++)
    	{
    		if(s.charAt(i)=='A')
    		{
    			Acount++;
    			if(Acount>1)
    				return false;
    		}
    		if(s.charAt(i)=='L'&&i>0&&i<s.length()-1)
    		{
    			if(s.charAt(i-1)=='L'&&s.charAt(i+1)=='L')
    				return false;
    		}
    	}
    	return true;
    }
    public int findCircleNum(int[][] M) {
        if(M==null||M.length==0||M[0].length==0)
        	return 0;
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<M.length;i++)
        	set.add(i);
        int count=0;
        int friend=-1;
        while(set.size()!=0)
        {
        	Queue<Integer> queue=new LinkedList<>();
        	for(int i=0;i<M.length;i++)
        	{
        		if(set.contains(i))
        		{
            		queue.add(i);
            		count++;
            		break;
        		}

        	}
        	while(queue.size()!=0)
        	{
        		friend=queue.poll();
        		for(int i=0;i<M[0].length;i++)
        		{
        			if(M[friend][i]==1&&set.contains(i))
        			{
        				queue.add(i);
        				set.remove(i);
        			}
        				
        		}
        	}
        }
        return count;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null)
        	return 0;
        return compute(root)[0]-1;
    }
    
	private int[] compute(TreeNode root) {
		// TODO Auto-generated method stub
		if(root==null)
			return new int[]{0,0};
        int[] left=compute(root.left);
        int[] right=compute(root.right);
        int max=Math.max(left[0], Math.max(right[0], left[1]+right[1]+1));	
        int lenMax=Math.max(left[1]+1, right[1]+1);
        return new int[] {max,lenMax};
	}
	public int[][] updateMatrix(int[][] matrix) {

		// two-pass dp
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return matrix;
		}

		int n = matrix.length;
		int m = matrix[0].length;

		int[][] dist = new int[n][m];

		// first pass
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 0) {
					continue;
				} else {
					dist[i][j] = Integer.MAX_VALUE - 1;
				}
				if (i > 0)
					dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
				if (j > 0)
					dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
			}
		}

		// second pass
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (matrix[i][j] == 0) {
					continue;
				}
				if (i < n - 1)
					dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
				if (j < m - 1)
					dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
			}
		}

		return dist;
	}

	public String reverseStr1(String s, int k) {
		if (s == null || s.length() == 0 || k < 2)
			return s;
		char[] arr = s.toCharArray();
		int len = s.length();
		int i = 0;
		while (i < len) {
			int j = Math.min(i + k - 1, len - 1);
			swap(arr, i, j);
			i += 2 * k;
		}
		return String.valueOf(arr);
	}

	private void swap(char[] arr, int i, int j) {
		// TODO Auto-generated method stub
		while (i < j) {
			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}

	public String reverseStr(String s, int k) {
		if (s == null || s.length() == 0 || k < 2)
			return s;
		int n = s.length() / k;
		String res = "";
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				String temp = "";
				for (int j = i * k; j < (i + 1) * k; j++) {
					temp = s.charAt(j) + temp;
				}
				res += temp;
			} else {
				for (int j = i * k; j < (i + 1) * k; j++) {
					res += s.charAt(j);
				}
			}
		}
		if (n % 2 == 1) {
			for (int j = n * k; j < s.length(); j++) {
				res += s.charAt(j);
			}
		} else {
			String temp = "";
			for (int j = n * k; j < s.length(); j++) {
				temp = s.charAt(j) + temp;
			}
			res += temp;
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
