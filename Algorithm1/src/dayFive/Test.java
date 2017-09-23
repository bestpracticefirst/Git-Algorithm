package dayFive;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		a.right = b;
		b.left = c;
		Test d = new Test();
		d.generateTrees(3);

	}

	public List<TreeNode> generateTrees(int n) {
		if (n <= 0)
			return new ArrayList<TreeNode>();
		return generateSubTree(1, n);
	}

	public ArrayList<TreeNode> generateSubTree(int start, int end) {
		ArrayList<TreeNode> result = new ArrayList<TreeNode>();
		if (start > end) {
			result.add(null);
			return result;
		} else if (start == end) {
			result.add(new TreeNode(start));
			return result;
		}
		for (int rootVal = start; rootVal <= end; rootVal++)
			for (TreeNode leftSubTreeRoot : generateSubTree(start, rootVal - 1))
				for (TreeNode rightSubTreeRoot : generateSubTree(rootVal + 1, end)) {
					TreeNode root = new TreeNode(rootVal);
					root.left = leftSubTreeRoot;
					root.right = rightSubTreeRoot;
					result.add(root);
				}
		return result;
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		inorderHelper(res, root);
		return res;
	}

	public void inorderHelper(List<Integer> res, TreeNode root) {
		if (root.right == null && root.left == null) {
			res.add(root.val);
			return;
		}
		if (root.left != null)
			inorderHelper(res, root.left);
		res.add(root.val);
		if (root.right != null)
			inorderHelper(res, root.right);
	}

	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<String>();
		String item = "";
		if (s.length() < 4 || s.length() > 12)
			return res;
		ipHelper(s, 0, item, res);
		return res;
	}

	public void ipHelper(String s, int start, String item, List<String> res) {
		if (start == 3 && isValid(s)) {
			res.add(item + s);
		}
		for (int i = 0; i < 3 && i < s.length() - 1; i++) {
			String subs = s.substring(0, i + 1);
			if (isValid(subs)) {
				ipHelper(s.substring(i + 1, s.length()), start + 1, item + subs + ".", res);
			}
		}
	}

	public boolean isValid(String s) {
		if (s.charAt(0) == '0')
			return s.equals("0");
		int num = Integer.parseInt(s);

		if (num <= 255 && num > 0)
			return true;
		else
			return false;
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
