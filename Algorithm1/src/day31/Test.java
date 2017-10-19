package day31;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest= new Test();
		TreeNode a=new TreeNode(5);
		TreeNode b=new TreeNode(1);
		TreeNode c=new TreeNode(5);
		TreeNode d=new TreeNode(5);
		TreeNode e=new TreeNode(5);
		TreeNode f=new TreeNode(5);
		a.left=b;
		a.right=c;
		b.left=d;
		b.right=e;
		c.right=f;
		aTest.countUnivalSubtrees(a);
	}

	int count = 0;  
    public int countUnivalSubtrees(TreeNode root) {  
        helper ( root );  
        return count;  
    }  
    public boolean helper ( TreeNode root ){  
        if ( root == null )  
            return true;  
        boolean left = helper ( root.left );  
        boolean right = helper ( root.right );  
        if ( left && right ){  
            if ( root.left != null && root.val != root.left.val )  
                return false;  
            if ( root.right != null && root.val != root.right.val )  
                return false;  
            count ++;  
            return true;  
        }  
        return false;  
    } 

	public int nthUglyNumber(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		int index1 = 0, index2 = 0, index3 = 0;
		int factor1 = 2, factor2 = 3, factor3 = 5;
		for (int i = 1; i < n; i++) {
			int min = Math.min(factor1, Math.min(factor2, factor3));
			ugly[i] = min;
			if (factor1 == min)
				factor1 = 2 * ugly[++index1];
			if (factor2 == min)
				factor2 = 3 * ugly[++index2];
			if (factor3 == min)
				factor3 = 5 * ugly[++index3];
		}
		return ugly[n - 1];
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