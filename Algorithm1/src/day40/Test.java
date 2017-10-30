package day40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		System.out.println(aTest.isPerfectSquare(7));
	}
    public boolean isPerfectSquare(int num) {
    	if(num==1)
    		return true;
    	int left=2,right=num/left;
    	while(true)
    	{
    		if(left*left==num)
    			return true;
    		if(Math.abs(left-right)<2)
    			return false;
    		left=left+(right-left)/2;
    		right=num/left;
    	}
    }
    public int[] intersect(int[] nums1, int[] nums2) {
    	List<Integer> res= new ArrayList<>();
    	Arrays.sort(nums1);
    	Arrays.sort(nums2);
    	int num1=0,num2=0;
    	for(;num1<nums1.length&&num2<nums2.length;)
    	{
    		if(nums1[num1]==nums2[num2])
    		{
    			res.add(nums1[num1]);
    			num1++;
    			num2++;
    		}
    		else if(nums1[num1]>nums2[num2])
    		{
    			num2++;
    		}
    		else
    		{
    			num1++;
    		}
    	}
    	int[] result=new int[res.size()];
    	int index=0;
    	for(int temp:res)
    	{
    		result[index++]=temp;
    	}
    	return result;
    }
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1=new HashSet<>();
        Set<Integer> set2=new HashSet<>();
        for(int i=0;i<nums1.length;i++)
        {
        	set1.add(nums1[i]);
        }
        for(int i=0;i<nums2.length;i++)
        {
        	if(set1.contains(nums2[i]))
        		set2.add(nums2[i]);
        }
        int[] res=new int[set2.size()];
        int index=0;
        Iterator<Integer> it=set2.iterator();
        while(it.hasNext())
        {
        	res[index++]=it.next();
        }
        return res;
    }
}