package day46;

import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		System.out.println(aTest.decodeString("3[a]2[bc]"));
	}
    public boolean isSubsequence(String s, String t) {
    	int lastIndex =0;
    	int currentIndex=0;
    	for(int i=0;i<s.length();i++)
    	{
    		char c= s.charAt(i);
    		currentIndex=t.indexOf(c, lastIndex);
    		if(currentIndex==-1)
    			return false;
    		lastIndex=currentIndex+1;
    	}
    	return true;
    }
    public String decodeString(String s) {
    	String res="";
    	int index=0;
    	Stack<Integer> countStack=new Stack<>();
    	Stack<String> resStack=new Stack<>();
    	while(index<s.length())
    	{
    		if(Character.isDigit(s.charAt(index)))
    		{
    			int count=0;
    			while(Character.isDigit(s.charAt(index)))
    			{
    				count=count*10+s.charAt(index)-'0';
    				index++;
    			}
    			countStack.push(count);
    		}
    		else if(s.charAt(index)=='[')
    		{
    			resStack.push(res);
    			res="";
    			index++;
    		}
    		else if(s.charAt(index)==']')
    		{
    			StringBuilder temp=new StringBuilder(resStack.pop());
    			int repeat=countStack.pop();
    			for(int i=0;i<repeat;i++)
    			{
    				temp.append(res);
    			}
    			res=temp.toString();
    			index++;
    		}else
    		{
    			res+=s.charAt(index);
    			index++;
    		}
    		
    	}
    	return res;
    }
}
