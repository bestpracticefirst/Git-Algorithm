package day39;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		System.out.println(~4);
		System.out.println(((int)Math.abs(~4)));
		System.out.println(aTest.integerBreak(10));
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