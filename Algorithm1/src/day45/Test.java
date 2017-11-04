package day45;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		System.out.println(aTest.lastRemaining(10));
	}
    public int lastRemaining(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        
        while(remaining > 1){
            if(left || remaining % 2 == 1){
                head += step;
            }
            step = step * 2; // 1,3,5,7,... then 8,4,
            left = !left;
            remaining = remaining/2; // After each iteration we are left with half the elements
        }
        
        return head;
    }
    public char findTheDifference(String s, String t) {
       int[] count=new int[26];
       for(int i=0;i<t.length();i++)
       {
    	   count[t.charAt(i)-'a']++;
       }
       for(int i=0;i<s.length();i++)
       {
    	   count[s.charAt(i)-'a']--;
       }
       for(int i=0;i<26;i++)
       {
    	   if(count[i]==1)
    		   return (char) (i+'a');
       }
       return 'a';
    }
	public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        int cur=1;
        for(int i=1;i<=n;i++)
        {
        	list.add(cur);
        	if(cur*10<=n)
        	{
        		cur*=10;
        	}
        	else if(cur%10!=9&&cur+1<=n)
        	{
        		cur++;
        	}
        	else
        	{
        		while((cur/10)%10==9)
        			cur=cur/10;
        		cur=cur/10+1;
        	}
        }
        return list;
    }

}
