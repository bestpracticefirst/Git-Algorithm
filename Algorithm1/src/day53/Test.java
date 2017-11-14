package day53;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a= {{0,0},{1,0},{2,0}};
		Test aTest=new Test();
		aTest.numberOfBoomerangs(a);

	}
    public int numberOfBoomerangs(int[][] points) {

    	if(points==null||points.length<3)
    		return 0;
    	int count=0;
    	HashMap<Integer, Integer> map=new HashMap<>();
    	for(int i=0;i<points.length;i++)
    	{

    		for(int j=0;j<points.length;j++)
    		{
    			if(i==j)
    				continue;
    			int distance=(int) (Math.pow(points[i][0]-points[j][0], 2)+Math.pow(points[i][1]-points[j][1], 2));
    			if(map.containsKey(distance))
    			{
    				map.put(distance,map.get(distance)+1);
    			}
    			else
    			{
    				map.put(distance, 1);
    			}
    		}
    		for (int k: map.values()) {  
    			  
    		    count+=k*(k-1);  
    		  
    		} 
    		map.clear();
    	}
    	return count;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		l1=reverseList(l1);
		l2=reverseList(l2);
		int cur=0;
		ListNode dummyHead=new ListNode(0);
		ListNode temp=dummyHead;
		while(l1!=null&&l2!=null)
		{
			int sum=cur+l1.val+l2.val;
			l1=l1.next;
			l2=l2.next;
			cur=sum/10;
			sum=sum%10;
			ListNode curNode=new ListNode(sum);
			temp.next=curNode;
			temp=temp.next;
			
		}
		while(l1!=null)
		{
			int sum=cur+l1.val;
			l1=l1.next;
			cur=sum/10;
			sum=sum%10;
			ListNode curNode=new ListNode(sum);
			temp.next=curNode;
			temp=temp.next;
		}
		while(l2!=null)
		{
			int sum=cur+l2.val;
			l2=l2.next;
			cur=sum/10;
			sum=sum%10;
			ListNode curNode=new ListNode(sum);
			temp.next=curNode;
			temp=temp.next;
		}
		if(cur==1)
		{
			ListNode curNode=new ListNode(cur);
			temp.next=curNode;
		}
		return reverseList(dummyHead.next);
		
	}
	public ListNode reverseList(ListNode l)
	{
		ListNode head=null;
		while(l!=null)
		{
			ListNode temp=l;
			l=l.next;
			temp.next=head;
			head=temp;
		}
		return head;
	}
	public int compress(char[] chars) {
		int start = 0;
		for (int end = 0, count = 0; end < chars.length; end++) {
			count++;
			if (end == chars.length - 1 || chars[end] != chars[end + 1]) {
				chars[start] = chars[end];
				start++;
				if (count != 1) {
					char[] arr = String.valueOf(count).toCharArray();
					for (int i = 0; i < arr.length; i++, start++) {
						chars[start] = arr[i];
					}
				}
				count = 0;
			}
		}
		return start;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}