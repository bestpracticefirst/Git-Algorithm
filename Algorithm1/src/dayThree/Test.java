package dayThree;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] a = { 2, 0 };
//		int[] b = { 1 };
		ListNode a=new ListNode(1);
		Test c = new Test();
		c.partition(a, 2);
	}
    public ListNode partition(ListNode head, int x) {
        ListNode before=new ListNode(-1);
        ListNode after=new ListNode(-1);
        ListNode tempBefore=before,tempAfter=after;
        while(head!=null)
        {
        	if(head.val<x)
        	{
        		ListNode temp=head;
        		tempBefore.next=temp;
        		tempBefore=tempBefore.next;
        	}
        	else {
        		ListNode temp=head;
        		tempAfter.next=temp;
        		tempAfter=tempAfter.next;
        	}
        	head=head.next;
        }
        tempAfter.next=null;
        tempBefore.next=after.next;
        return before.next;
    }
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (n == 0)
			return;
		for (int i = m + n - 1; i >= 0; i--) {
			if (m != 0 && n != 0) {
				if (nums1[m - 1] > nums1[n - 1]) {
					nums1[i] = nums1[m - 1];
					m--;
				} else {
					nums1[i] = nums1[n - 1];
					n--;
				}
			} else {
				if (m == 0) {
					nums1[i] = nums1[n - 1];
					n--;
				} else {
					nums1[i] = nums1[m - 1];
					m--;
				}
			}
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
