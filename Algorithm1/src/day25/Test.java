package day25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test h= new Test();
		h.computeArea(-3, 0, 3, 4, 0, -1, 9, 2);
	}
    public List<String> summaryRanges(int[] nums) {
    	List<String> res =new ArrayList<String>();
    	if(nums==null||nums.length==0)
    		return res;
    	Arrays.sort(nums);
    	int pre=nums[0];
    	StringBuilder s=new StringBuilder();
    	s.append(pre).append("->");
    	for(int i=1;i<nums.length;i++)
    	{
    		if(nums[i]-pre==1)
    		{
    			s.append(nums[i]).append("->");
    		}
    		else
    		{
    			s.substring(0, s.length()-2);
    			res.add(s.toString());
    			s=new StringBuilder();
    			s.append(nums[i]).append("->");
    		}
    		pre=nums[i];	
    	}
    	s.substring(0, s.length()-2);
    	res.add(s.toString());
    	return res;
    }
    public TreeNode invertTree(TreeNode root) {
        if(root==null)
        	return root;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
        
    }
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int[] heights= {B,D,F,H};
        Arrays.sort(heights);
        int[] lengths= {A,C,E,G};
        Arrays.sort(lengths);
        return (lengths[2]-lengths[1])*(heights[2]-heights[1]);
    }
    public int countNodes(TreeNode root) {  
        if(root==null) return 0;  
          
        int l = getLeft(root) + 1;  
        int r = getRight(root) + 1;  
          
        if(l==r) {  
            return (2<<(l-1)) - 1;  
        } else {  
            return countNodes(root.left) + countNodes(root.right) + 1;  
        }  
    }  
      
    private int getLeft(TreeNode root) {  
        int count = 0;  
        while(root.left!=null) {  
            root = root.left;  
            ++count;  
        }  
        return count;  
    }  
      
    private int getRight(TreeNode root) {  
        int count = 0;  
        while(root.right!=null) {  
            root = root.right;  
            ++count;  
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