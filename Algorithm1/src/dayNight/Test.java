package dayNight;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		a.numDistinct("a", "b");
	}
    public int numDistinct(String s, String t) {
    	int[][] dp= new int[s.length()+1][t.length()+1];
    	if(t==null||s==null||t.length()<s.length())
    		return 0;
    	dp[0][0]=1;
    	for(int i=0;i<s.length();i++)
    	{
    		dp[i][0]=1;
    	}
    	for(int i=1;i<=s.length();i++)
    	{
    		for(int j=1;j<=t.length();j++)
    		{
    			dp[i][j]=dp[i-1][j];
    			if(s.charAt(i-1)==s.charAt(j-1))
    				dp[i][j]+=dp[i-1][j-1];
    		}
    	}
    	return dp[s.length()][t.length()];
    }
	public void flatten(TreeNode root) {
		if(root==null)
			return;
		if(root.left!=null)flatten(root.left);
		if(root.right!=null)flatten(root.right);
		TreeNode temp=root.right;
		root.right=root.left;
		root.left=null;
		while(root.right!=null)
			root=root.right;
		root.right=temp;
	}
	

	public List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<Integer>();
		if (rowIndex < 0)
			return res;
		res.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			for (int j = res.size() - 2; j >= 0; j--) {
				res.set(j + 1, res.get(j + 1) + res.get(j));
			}
			res.add(1);
		}
		return res;
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for (int i = 1; i <= numRows; i++) {
			List<Integer> item = new ArrayList<Integer>();
			if (i == 1) {
				item.add(1);
				res.add(item);
				continue;
			}
			List<Integer> lastItem = res.get(i - 2);
			for (int j = 0; j < i; j++) {
				if (j == 0 || j == lastItem.size()) {
					item.add(1);
				} else {
					item.add(lastItem.get(j - 1) + lastItem.get(j));
				}
			}
			res.add(item);
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