package daySeven;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 a=new Test1();
		int[] b= {2,1};
		int[] c= {2,1};
		a.buildTree2(b, c);
	}
	public TreeNode buildTree2(int[] inorder, int[] postorder)
	{
		 return helper2(postorder, postorder.length-1, inorder, 0,inorder.length-1);
	}
	private TreeNode helper2(int[] postorder, int idx, int[] inorder, int start, int end) {
        if(idx <0 || start > end)
            return null;
        TreeNode root=new TreeNode(postorder[idx]);
        int j;
        for(j=start;j<=end;j++)
        {
        	if(inorder[j]==postorder[idx])
        		break;
        }
        root.right=helper2(postorder, idx-1, inorder, j+1, end);
        root.left=helper2(postorder, idx-end+j-1, inorder, start,j-1);
        return root;
        
	}
	public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return helper(preorder, 0, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode helper(int[] preorder, int idx, int[] inorder, int start, int end) {
        if(idx >= preorder.length || start > end)
            return null;
        
        TreeNode root = new TreeNode(preorder[idx]);
        int j;
        for(j = end; j >= start; j--) {
            if(preorder[idx] == inorder[j])
            {
                root.left = helper(preorder, idx + 1, inorder, start, j - 1);
                root.right = helper(preorder, idx + j - start + 1, inorder, j + 1, end);
                return root;
            }
        }
        return root;
    }
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0)
			return null;
		return buildHelper(preorder, inorder, 0, 0,preorder.length);
	}

	public TreeNode buildHelper(int[] preorder, int[] inorder, int left, int start,int end) {
		TreeNode root = null;
		for (; left < preorder.length; left++) {
			for (int i = start; i <end; i++) {
				if (preorder[left] == inorder[i]) {
					root = new TreeNode(preorder[left]);
					root.left = buildHelper(preorder, inorder, left + 1,0, i);
					root.right= buildHelper(preorder, inorder, left + 1, i+1,end);
					return root;
				}

			}
		}
		return root;

	}

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right) + 1;
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		DFSHelper(root, 0, ans);
		return ans;
	}

	public void DFSHelper(TreeNode node, int height, List<List<Integer>> ans) {
		if (node == null)
			return;
		if (ans.size() == height) {
			List<Integer> item = new ArrayList<>();
			ans.add(item);
		}
		if (height % 2 == 0) {
			ans.get(height).add(node.val);
		} else {
			ans.get(height).add(0, node.val);
		}
		DFSHelper(node.left, height + 1, ans);
		DFSHelper(node.right, height + 1, ans);
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