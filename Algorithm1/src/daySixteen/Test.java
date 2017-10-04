package daySixteen;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode a=new ListNode(1);
		ListNode b=new ListNode(1);
		ListNode c=new ListNode(3);
		a.next=b;
		Test d=new Test();
		d.insertionSortList(a);
	}
    public ListNode insertionSortList(ListNode head) {
    	ListNode dummyHead=new ListNode(-1);
    	dummyHead.next=head;
        ListNode cur=head;;
        ListNode pre=dummyHead;
        if(head==null||head.next==null)
        	return cur;
        head=head.next;
        cur.next=null;
        while(head!=null)
        {
        	while(cur!=null&&head.val>cur.val)
        	{
        		cur=cur.next;
        		pre=pre.next;
        	}
        	pre.next=head;
        	ListNode temp=head.next;
        	head.next=cur;
        	head=temp;
        	pre=dummyHead;
        	cur=pre.next;
        }
        return dummyHead.next;
    }
	 public int candy(int[] ratings) {
	        int sum = 0;
	        int[] left2right = new int[ratings.length];
	        int[] right2left = new int[ratings.length];
	        Arrays.fill(left2right, 1);
	        Arrays.fill(right2left, 1);
	        for (int i = 1; i < ratings.length; i++) {
	            if (ratings[i] > ratings[i - 1]) {
	                left2right[i] = left2right[i - 1] + 1;
	            }
	        }
	        for (int i = ratings.length - 2; i >= 0; i--) {
	            if (ratings[i] > ratings[i + 1]) {
	                right2left[i] = right2left[i + 1] + 1;
	            }
	        }
	        for (int i = 0; i < ratings.length; i++) {
	            sum += Math.max(left2right[i], right2left[i]);
	        }
	        return sum;
	    }
    public void reorderList(ListNode head) {
    	if(head==null)
    		return;
    	ListNode temp=head;
    	int count=0;
    	while(temp!=null)
    	{
    		count++;
    		temp=temp.next;
    	}
    	if(count<=2)
    		return;
    	count=(count-1)/2;
    	temp=head;
    	while(count>0)
    	{
    		temp=temp.next;
    		count--;
    	}
    	ListNode temp2=temp.next;
    	temp.next=null;
    	ListNode dummyHead=new ListNode(0);
    	dummyHead.next=temp2;
    	ListNode node1=temp2;
    	ListNode node2=temp2.next;
    	while(node2!=null)
    	{
    		node1.next=node2.next;
    		node2.next=dummyHead.next;
    		dummyHead.next=node2;
    		node2=node1.next;
    	}
    	temp2=dummyHead.next;
    	temp=head;
    	while(temp!=null&&temp2!=null)
    	{
    		ListNode temp3=temp.next;
    		temp.next=temp2;
    		ListNode temp4=temp2.next;
    		temp2.next=temp3;
    		temp=temp3;
    		temp2=temp4;
    	}
    }
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}