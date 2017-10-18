package day29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		String[] s= {"practice", "makes", "perfect", "coding", "makes"};
		a.shortestDistance(s, "makes", "makes");
	}
	public List<List<String>> groupStrings(String[] strings) {
        HashMap<List<Integer>, List<String>> map = new HashMap<List<Integer>, List<String>>();  
        for ( int i = 0; i < strings.length; i ++ ){  
            List<Integer> pos = new ArrayList<Integer>();  
            for ( int j = 0; j < strings[i].length(); j ++ ){  
                int po = strings[i].charAt(j) - strings[i].charAt(0);  
                pos.add ( po > 0 ? po : po + 26 );  
            }  
            if ( map.containsKey ( pos ) )  
                map.get ( pos ).add ( strings[i] );  
            else{  
                List<String> strs = new ArrayList<String>();  
                strs.add ( strings[ i ] );  
                map.put( pos, strs );  
            }  
        }  
        for ( List<String> list : map.values() )  
            Collections.sort ( list );  
        List<List<String>> res = new ArrayList<List<String>>();  
        res.addAll ( map.values() );  
        return res; 
			
	}
	public List<String> findStrobogrammatic(int n) {
		return helper(n,n);
	}
	public List<String> helper(int m,int n)
	{
		if(m==0)
			return new ArrayList<>();
		if(m==1)
			return new ArrayList<>(Arrays.asList("0","1","8"));
		List<String> list = helper(m-2, n);
		List<String> result=new ArrayList<>();
		for(String s:list)
		{
			if(n!=m)
				result.add("0"+s+"0");
			result.add("1"+s+"1");
			result.add("6"+s+"9");
			result.add("9"+s+"6");
			result.add("8"+s+"8");
		}
		return result;
	}
    public boolean isStrobogrammatic(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1); map.put(6, 9); map.put(8, 8); map.put(9, 6); map.put(0, 0);
        
        int len = num.length();
        for (int i = 0; i < len; i++) {
            int c1 = num.charAt(i) - '0', c2 = num.charAt(len - 1 - i) - '0';
            if (!map.containsKey(c1) || !map.containsKey(c2) || c1 != map.get(c2)) {
                return false;
            }
        }
        return true;
    }
	public int shortestDistance(String[] words, String word1, String word2) {
		int index1 = -1, index2 = -1, min = words.length;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				if(word1.equals(word2))
				{
					index2=index1;
				}
				index1 = i;
				if(index1!=-1&&index2!=-1)
				{
				    min = Math.min(min, index2 - index1);
				}
			}
			else if (words[i].equals(word2)) {
				index2 = i;
				if(index1!=-1&&index2!=-1)
				{
				    min = Math.min(min, index2 - index1);
				}
			}
		}
		if (index1 == -1 || index2 == -1)
			return words.length;
		return min;
	}
    public boolean isUgly(int num) {
    	if(num==0)
    		return false;
    	while(num!=1)
    	{
    		if(num%2!=0&&num%3!=0&&num%5!=0)
    		{
    			return false;
    		}
    		if(num%2==0)
    			num=num/2;
    		if(num%3==0)
    			num=num/3;
    		if(num%5==0)
    			num=num/5;
    	}
        return true;
    }
    public int missingNumber(int[] nums) {
        int xor = 0, i = 0;
    	for (i = 0; i < nums.length; i++) {
    		xor = xor ^ i ^ nums[i];
    	}
    	return xor ^ i;
    }
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int count=0;
        for(int i=citations.length-1;i>=0;i--)
        {
        	
        	if(count>=citations[i])
        		return count;
        	count++;
        }
        return count;
    }

}
