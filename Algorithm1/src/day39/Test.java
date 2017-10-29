package day39;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		System.out.println(aTest.reverseVowels("leetcode"));
	}
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		if(nums.length<1)
			return new ArrayList<>();
		List<Integer>[] bucket=new List[nums.length+1];
		Map<Integer, Integer> map=new HashMap<>();
		for(int num:nums)
		{
			if(map.containsKey(num))
			{
				map.put(num, map.get(num)+1);
			}
			else
			{
				map.put(num, 1);
			}
		}
		for (int key : map.keySet()) {
			int frequency = map.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}

		List<Integer> res = new ArrayList<>();

		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
		
	}
    public String reverseVowels(String s) {
    	if(s.length()<2)
    		return s;
    	char[] res=s.toCharArray();
    	int left=0, right=s.length()-1;
    	Set<Character> set=new HashSet<>();
    	set.add('a');
    	set.add('e');
    	set.add('i');
    	set.add('o');
    	set.add('u');
    	set.add('A');
    	set.add('E');
    	set.add('I');
    	set.add('O');
    	set.add('U');
    	while(left<right)
    	{
    		while(left<right&&!set.contains(res[left]))
    			left++;
    		while(right>left&&!set.contains(res[right]))
    			right--;
    		if(left>=right)
    			return String.valueOf(res);
    		else
    		{
    			char temp=res[left];
    			res[left]=res[right];
    			res[right]=temp;
    			left++;
    			right--;
    		}
    	}
    	return String.valueOf(res);
    }
    public String reverseString(String s) {
    	StringBuilder sb=new StringBuilder(s);
    	return sb.reverse().toString();
    }
    public int integerBreak(int n) {
    	int[] dp= new int[n+1];
    	dp[1]=1;
    	dp[2]=1;
    	for(int i=3;i<=n;i++)
    	{
    		int max=1;
    		for(int j=1;j<i;j++)
    		{
    			max=Math.max(max,Math.max(j*(i-j), dp[j]*(i-j)));
    		}
    		dp[i]=max;
    	}
    	return dp[n];
    }
    public boolean isPowerOfFour(int num) {
    	return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
    }
}
class NestedIterator implements Iterator<Integer> {
	List<NestedInteger> nestedList;
	List<Integer> list;
	int index=0;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList=nestedList;
        list=convert(nestedList);
    }

    public List<Integer> convert(List<NestedInteger> nestedList)
    {
    	List<Integer> res=new ArrayList<>();
    	for(NestedInteger nested:nestedList)
    	{
    		if(nested.isInteger())
    		{
    			res.add(nested.getInteger());
    		}
    		else
    		{
    			res.addAll(convert(nested.getList()));
    		}
    	}
    	return res;
    }
    @Override
    public Integer next() {
        if(hasNext())
        	return list.get(index++);
        else
        	return null;
    }

    @Override
    public boolean hasNext() {
        return index<list.size();
    }

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
interface NestedInteger {

     // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
     public List<NestedInteger> getList();
 }