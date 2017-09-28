package dayTen;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Test a = new Test();
//		TreeLinkNode b=new TreeLinkNode(1);
//		TreeLinkNode b1=new TreeLinkNode(2);
//		TreeLinkNode b2=new TreeLinkNode(3);
//		TreeLinkNode b3=new TreeLinkNode(4);
//		TreeLinkNode b4=new TreeLinkNode(5);
//		TreeLinkNode b5=new TreeLinkNode(6);
//		TreeLinkNode b6=new TreeLinkNode(7);
//		TreeLinkNode b7=new TreeLinkNode(8);
//		b.left=b1;
//		b.right=b2;
//		b1.left=b3;
//		b1.right=b4;
//		b2.right=b5;
//		b3.left=b6;
//		b5.right=b7;
//		a.connect1(b);
		System.out.println((5^3));
	}
	public int singleNumber3(int[] A) {  
        int [] count = new int[32];
        int result = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                if (((A[j] >> i) & 1)==1) {
                    count[i]++;
                }
            }
            result |= ((count[i] % 3) << i);
        }
        return result;
    }
	 public int singleNumber2(int[] nums) {
	        int a = 0, b = 0;
	        for (int num : nums) {
	            b = b ^ num & (~a);
	            a = a ^ num & (~b);
	        }
	        return b;
	    }
    public int singleNumber(int[] nums) {
        int res=0;
        for(int i=0;i<nums.length;i++)
        {
            res=res^nums[i];
        }
        return res;
    }
    public int minimumTotal(List<List<Integer>> triangle) {
    	if(triangle==null||triangle.size()==0)
    		return 0;
    	int row=triangle.size();
    	int[] res=new int [row+1];
    	for(int i=triangle.size()-1;i>=0;i--)
    	{
    		List<Integer> item=triangle.get(i);
    		for(int j=0;j<item.size();j++)
    		{
    			res[j]=item.get(j)+Math.min(res[j], res[j+1]);
    		}
    			
    	}
    	return res[0];
    }
    public void connect1(TreeLinkNode root) {
        if (root == null) return;
        while (root != null) {
            TreeLinkNode head = new TreeLinkNode(0);
            TreeLinkNode pt = head;
            while (root != null) {
                if (root.left != null) {
                    pt.next = root.left;
                    pt = pt.next;
                }
                if (root.right != null) {
                    pt.next = root.right;
                    pt = pt.next;
                }
                root = root.next;
            }
            root = head.next;
        }
    }
	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}
		TreeLinkNode head = root;
		TreeLinkNode curt = null;
		while(head!=null)
		{
			curt=head;
			TreeLinkNode pre=null;
			while(curt!=null)
			{
				if(pre==null&&curt.left==null&&curt.right==null)
				{
					curt=curt.next;
					continue;
				}
				else if(pre==null)
				{
					if(curt.left!=null)
					{
						pre=curt.left;
						if(curt.right!=null)
						{
							pre.next=curt.right;
							pre=pre.next;
						}
					}else
					{
						pre=curt.right;
					}
					
				}
				else
				{
					if(curt.left!=null)
					{
						pre.next=curt.left;
						pre=pre.next;
						if(curt.right!=null)
						{
							pre.next=curt.right;
							pre=pre.next;
						}
					}else if(curt.right!=null)
					{
						pre.next=curt.right;
						pre=pre.next;
					}
				}
				curt=curt.next;
			}
			if(head.left!=null)
				head=head.left;
			else if(head.right!=null)
			{
				head=head.right;
			}
			else
			{
				head=head.next;
			}
				
		}

	}

	// 其实只要后边的数比前边数大，就可以在结果上加上他们的差值
	public int maxProfit1(int[] prices) {
		if (prices.length <= 1)
			return 0;
		// int min = prices[0];
		int res = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1])
				res += prices[i] - prices[i - 1];
		}
		return res;
	}

	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;
		int currentProfit = 0;
		int sellValue = prices[0];
		int buyValue = prices[0];
		int tempProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (sellValue > prices[i]) {
				currentProfit += tempProfit;
				buyValue = prices[i];
				sellValue = prices[i];
				tempProfit = 0;
			} else {
				sellValue = prices[i];
				tempProfit = sellValue - buyValue;
			}
		}
		if (tempProfit > 0)
			currentProfit += tempProfit;
		return currentProfit;
	}
}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}
