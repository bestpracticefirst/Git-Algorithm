package dayEight;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathlist=new ArrayList<List<Integer>>();
        List<Integer> sumlist = new ArrayList<Integer>();
        pathSumHelper(root,sum,sumlist,pathlist);
        return pathlist;
    }
	public void pathSumHelper(TreeNode root, int sum, List <Integer> sumlist, List<List<Integer>> pathlist){
        if(root==null) 
            return;
        sumlist.add(root.val);
        sum = sum-root.val;
        if(root.left==null && root.right==null){
            if(sum==0){
                pathlist.add(new ArrayList<Integer>(sumlist));
            }
        }else{
            if(root.left!=null)
                pathSumHelper(root.left,sum,sumlist,pathlist);
            if(root.right!=null)
                pathSumHelper(root.right,sum,sumlist,pathlist);
        }
        sumlist.remove(sumlist.size()-1);
    }
    public boolean hasPathSum(TreeNode root, int sum) {
    	if(root==null)
    		return false;
    	if(root.val==sum&&root.left==null&&root.right==null)
    		return true;
    	sum=sum-root.val;
    	return hasPathSum(root.left, sum)||hasPathSum(root.right, sum);
    }
    public int minDepth(TreeNode root) {
    	if(root==null)
    		return 0;
    	if(root.left==null)
    		return minDepth(root.right)+1;
    	if(root.right==null)
    		return minDepth(root.left)+1;
    	return Math.min(minDepth(root.left), minDepth(root.right))+1;
    }
    public boolean isBalanced(TreeNode root) {
    	if(root==null)
    		return true;
    	if(Math.abs(deptHelper(root.left)-deptHelper(root.right))<2)
    		return isBalanced(root.left)&&isBalanced(root.right);
    	return false;
    }
    public int deptHelper(TreeNode root)
    {
    	if(root==null)
    		return 0;
    	return Math.max(deptHelper(root.left), deptHelper(root.right))+1;
    }
    

	static ListNode currentHead = null;   
    public TreeNode sortedListToBST(ListNode head) {  
        if(head==null) {  
            return null;  
        }  
        currentHead = head;  
        int len = 0;  
        while(head!=null) {  
            len++;  
            head = head.next;  
        }  
          
        return buildTree(0, len-1);  
    }
    TreeNode buildTree(int start, int end) {  
        if(start>end) {  
            return null;  
        }  
        int mid = start + (end - start)/2;  
        TreeNode left = buildTree(start, mid-1);  
        TreeNode root = new TreeNode(currentHead.val);  
        root.left = left;  
        currentHead = currentHead.next;  
        root.right = buildTree(mid + 1, end);  
        return root;  
    }  
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        DFSHelper(root, 0, ans);
        return ans;
	}
	public void DFSHelper(TreeNode root,int height ,List<List<Integer>> ans)
	{
		if(root==null)
			return;
		if(height==ans.size())
		{
			ArrayList item=new ArrayList<>();
			ans.add(0, item);
		}
		ans.get(ans.size()-height-1).add(root.val);
		DFSHelper(root.left, height+1, ans);
		DFSHelper(root.right, height+1, ans);
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
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}