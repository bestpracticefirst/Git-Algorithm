package dayTwentytwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		int[] b = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(a.isIsomorphic("abc", "bcd"));
	}
    public ListNode reverseList(ListNode head) {
    	ListNode dummyHead=new ListNode(0);
    	dummyHead.next=head;
    	if(head==null||head.next==null)
    		return dummyHead.next;
    	ListNode temp=head.next;
    	head.next=null;
    	while(temp!=null)
    	{
    		ListNode node=temp.next;
    		temp.next=dummyHead.next;
    		dummyHead.next=temp;
    		temp=node;
    	}
    	return dummyHead.next;
    }
    public boolean isIsomorphic1(String s, String t) {
        
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        }
        int[] arrS = new int[256], arrT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (arrS[s.charAt(i)] == arrT[t.charAt(i)]) {
                arrS[s.charAt(i)] = i + 1;
                arrT[t.charAt(i)] = i + 1;
            } else {
                return false;
            }
        }
        return true;
    }
    public boolean isIsomorphic(String s, String t) {
    	if(s.length()!=t.length())
    		return false;
    	
    	Map<Character, Character> map1=new HashMap<Character,Character>();
    	Map<Character, Character> map2=new HashMap<Character,Character>();
    	for(int i=0;i<s.length();i++)
    	{
    		if(map1.containsKey(s.charAt(i)))
    		{
    			if(map1.get(s.charAt(i))!=t.charAt(i))
    				return false;
    		} if(map2.containsKey(t.charAt(i)))
    		{
    			if(map2.get(t.charAt(i))!=s.charAt(i))
    				return false;
    		}
    		else
    		{
    			map1.put(s.charAt(i), t.charAt(i));
    			map2.put(t.charAt(i), s.charAt(i));
    		}
    	}
    	return true;
    }
    public int countPrimes(int n) {
    	int num=0;
    	if(n<3)
    		return 0;
    	boolean[] bool=new boolean[n-1];
    	Arrays.fill(bool, true);
    	bool[0]=false;
    	for(int i=2;i<=Math.sqrt(n);i++)
    	{
    		if(bool[i-1])
    		{
    		for(int j=i+i;j<n;j+=i)
    			bool[j-1]=false;
    		}
    	}
    	for(int i=0;i<n-1;i++)
    		if(bool[i])
    			num++;
    	return num;
    }
	public ListNode removeElements(ListNode head, int val) {
		ListNode dummyHead=new ListNode(0);
		dummyHead.next=head;
		ListNode temp=dummyHead;
		while(temp.next!=null)
		{
			if(temp.next.val==val)
			{
				temp.next=temp.next.next;
				if(temp.next==null)
					break;
				continue;
			}
			temp=temp.next;
				
		}
		return dummyHead.next;
	}

	public int rangeBitwiseAnd(int m, int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			if ((n & 1) == 1 && (m & 1) == 1) {
				res += Math.pow(2, i);
				n >>= 1;
				m >>= 1;
				continue;
			}
			if ((n & 1) == 1 || (m & 1) == 1) {
				res = 0;
			}
			n >>= 1;
			m >>= 1;
		}
		return res;
	}

	public int rangeBitwiseAnd1(int m, int n) {
		int p = 0;
		while (m != n) {
			m >>>= 1;
			n >>>= 1;
			p++;
		}
		return m << p;
	}

	public int reverseBits(int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res += n & 1;
			n = n >> 1;
			if (i < 31) {
				res <<= 1;
			}
		}
		return res;
	}

	public void rotate(int[] nums, int k) {
		k = (nums.length + (k % nums.length)) % nums.length;
		int tmp;
		for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
			tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
		}

		for (int i = 0, j = k - 1; i < j; i++, j--) {
			tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
		}

		for (int i = k, j = nums.length - 1; i < j; i++, j--) {
			tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
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