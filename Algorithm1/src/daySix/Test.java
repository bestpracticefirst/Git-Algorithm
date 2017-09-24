package daySix;

import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test e = new Test();
		ListNode a=new ListNode(1);
		ListNode b=new ListNode(2);
		ListNode c=new ListNode(3);
		ListNode d=new ListNode(4);
		a.next=b;
		b.next=c;
		c.next=d;
		e.lengthOfLongestSubstring("abacdefg");

	}
	   public List<List<Integer>> levelOrder(TreeNode root) {
	        List<List<Integer>> ans = new ArrayList<>();
	        DFSHelper(root, 0, ans);
	        return ans;
	    }
	    
	    public void DFSHelper(TreeNode node, int height, List<List<Integer>> ans) {
	        if (node == null) return;
	        if (ans.size() == height) 
	        {
	        	List<Integer> item=new ArrayList<>();
	        	ans.add(item);
	        }
	        ans.get(height).add(node.val);
	        DFSHelper(node.left, height + 1, ans);
	        DFSHelper(node.right, height + 1, ans);
	    }

    
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
        	return true;
        return symmetricHelper(root.left, root.right);
        
    }
    public boolean symmetricHelper(TreeNode left, TreeNode right)
    {
    	if(left!=null&&right!=null)
    	{
    		if(left.val==right.val)
    			return symmetricHelper(left.left, right.right)&&symmetricHelper(left.right, right.left);
    		return false;
    	}
    	if(left==null&&right==null)
    		return true;
    	return false;
    }
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        // for ASCII char sequence, use this as a hashmap
        int[] idx = new int[129];
        //initaize arr to -1 inorder to correct calculate interval
        for(int i = 32 ; i < idx.length; i++) {
            idx[i] = -1;
        }
        int max = 0, m = 0;
        for (int i = 0; i < s.length(); i++){
            int ascii = (int) (s.charAt(i));
            m = Math.max(idx[ascii] + 1, m);
            idx[ascii] = i;
            max = Math.max(max, i - m + 1);
        }
        return max;
    }
	public List<Integer> findSubstring(String S, String[] L) {  
	    // Note: The Solution object is instantiated only once and is reused by each test case.  
	    List<Integer> res = new ArrayList<Integer>();  
	    if(S==null || S.length()==0 || L==null || L.length==0)  
	        return res;  
	    HashMap<String,Integer> map = new HashMap<String,Integer>();  
	    for(int i=0;i<L.length;i++)  
	    {  
	        if(map.containsKey(L[i]))  
	        {  
	            map.put(L[i],map.get(L[i])+1);  
	        }  
	        else  
	        {  
	            map.put(L[i],1);  
	        }  
	    } 
	    //首先将单词转化成一个词典
	    for(int i=0;i<L[0].length();i++)  
	    {  
	        HashMap<String,Integer> curMap = new HashMap<String,Integer>();  
	        int count = 0;  
	        int left = i;  
	        for(int j=i;j<=S.length()-L[0].length();j+=L[0].length())  
	        {  
	            String str = S.substring(j,j+L[0].length());  
	              
	            if(map.containsKey(str))  
	            {  
	                if(curMap.containsKey(str))  
	                    curMap.put(str,curMap.get(str)+1);  
	                else  
	                    curMap.put(str,1);  
	                if(curMap.get(str)<=map.get(str))  
	                    count++;  
	                else  
	                {  
	                    while(curMap.get(str)>map.get(str))  
	                    {  
	                        String temp = S.substring(left,left+L[0].length());  
	                        if(curMap.containsKey(temp))  
	                        {  
	                            curMap.put(temp,curMap.get(temp)-1);  
	                            if(curMap.get(temp)<map.get(temp))  
	                                count--;  
	                        }  
	                        left += L[0].length();  
	                    }  
	                }  
	                if(count == L.length)  
	                {  
	                    res.add(left);  
	                    //if(left<)  
	                    String temp = S.substring(left,left+L[0].length());  
	                    if(curMap.containsKey(temp))  
	                        curMap.put(temp,curMap.get(temp)-1);  
	                    count--;  
	                    left += L[0].length();  
	                }  
	            }  
	            else  
	            {  
	                curMap.clear();  
	                count = 0;  
	                left = j+L[0].length();  
	            }  
	        }  
	    }  
	    return res;  
	}  
    public ListNode reverseKGroup(ListNode head, int k) {
    	if(k<=1)
    		return head;
        ListNode newHead= new ListNode(-1);
        newHead.next=head;
        int size=0;
        while(head!=null)
        {
        	size++;
        	head=head.next;
        }
        head=newHead.next;
        ListNode startPoint=newHead;
        ListNode node1=null; //需要放到后边的node
        ListNode node2=null; //需要放到前边的node
        int times=0;
        for(int i=0;i<size&&times*k<=size;i++)
        {
        	if(i%k==0)
        	{
        		times++;
        		if(i!=0)
        		{
        			startPoint=node1;
        		}
        		node1=startPoint.next;
        		node2=node1.next;
        	}else
        	{
                node1.next = node2.next;//node1交换到node2的后面
                node2.next = startPoint.next;//node2交换到最开始
                startPoint.next = node2;//node2作为新的点
                node2 = node1.next;//node2回归到node1的下一个，继续遍历
        	}
        }
        return newHead.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res=new ListNode(0);
        if(lists.length==0)
        	return res.next;
        if(lists.length==1)
        	return lists[0];
        return mergeHelper(lists, 0, lists.length-1);
        
    }
    public ListNode mergeHelper(ListNode []lists,int l,int r)
    {
    	if(l<r)
    	{
    		int m=(l+r)/2;
    		return mergeTwoLists(mergeHelper(lists, l, m),mergeHelper(lists, m+1, r)) ;
    	}
    	return lists[l];
    		
    }
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(0);
		ListNode resTemp = res;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				resTemp.next = l1;
				l1 = l1.next;
			} else {
				resTemp.next = l2;
				l2 = l2.next;
			}
			resTemp = resTemp.next;
		}
		if (l1 == null)
			resTemp.next = l2;
		else
			resTemp.next = l1;
		return res.next;
	}

	public int numTrees(int n) {
		if (n <= 0)
			return 0;
		int[] res = new int[n + 1];
		res[0] = 1;
		res[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				res[i] += res[j] * res[i - j - 1];
			}
		}
		return res[n];
	}

	public boolean isValidBST(TreeNode root) {
		return validHelper(root, ((long) Integer.MAX_VALUE) + 1, ((long) Integer.MIN_VALUE) - 1);
	}

	public boolean validHelper(TreeNode root, long max, long min) {
		if (root == null || (root.left == null && root.right == null))
			return true;
		if ((root.left != null && (root.left.val >= root.val || root.left.val <= min))
				|| (root.right != null && (root.right.val <= root.val || root.right.val >= max)))
			return false;
		return validHelper(root.left, root.val, min) && validHelper(root.right, max, root.val);
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		return isSameHelper(p, q);
	}

	public boolean isSameHelper(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p != null && q != null) {
			if (p.val == q.val)
				return isSameHelper(p.left, q.left) && isSameHelper(p.right, q.right);
			return false;
		}
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

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}