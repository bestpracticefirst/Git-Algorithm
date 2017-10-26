package day37;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		System.out.println(aTest.isPowerOfThree(45));
	}
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null)
        	return head;
        ListNode odd=head;
        ListNode ehead=head.next;
        ListNode temp=ehead;
        while(ehead!=null&&ehead.next!=null)
        {
        	odd.next=ehead.next;
        	odd=odd.next;
        	ehead.next=odd.next;
        	ehead=ehead.next;
        }
        odd.next=temp;
        return head;
        
    }
    public boolean isPowerOfThree(int n) {
    	if(n==1)
    		return true;
    	if(n%3!=0||n==0)
    		return false;
    	return isPowerOfThree(n/3);
    }
    public void wiggleSort(int[] nums) {

        int[] temp = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(temp);
        int large = temp.length / 2 + (temp.length % 2 == 0 ? -1 : 0);
        int small = temp.length - 1;
        for (int i = 0, j = 1; i < temp.length; i+=2, j+=2) {
            if(j < temp.length) nums[j] = temp[small--];
            nums[i] = temp[large--];
        }
    }
	public int coinChange(int[] coins, int amount) {
	    if(amount<1) return 0;
	    int[] dp = new int[amount+1];
	    int sum = 0;
	    
		while(++sum<=amount) {
			int min = -1;
	    	for(int coin : coins) {
	    		if(sum >= coin && dp[sum-coin]!=-1) {
	    			int temp = dp[sum-coin]+1;
	    			min = min<0 ? temp : (temp < min ? temp : min);
	    		}
	    	}
	    	dp[sum] = min;
		}
		return dp[amount];
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
