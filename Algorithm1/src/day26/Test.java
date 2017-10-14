package day26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		int[] b= {3,2,3};
		a.majorityElement(b);
	}
    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> res=new ArrayList<Integer>();
        if(nums.length==0||nums==null)
        {
        	return res;
        }
        if(nums.length==1)
        {
        	res.add(nums[0]);
        	return res;
        }
        Arrays.sort(nums);
        int majority=nums.length/3;
        for(int i=0;i<nums.length-majority;i++)
        {
        	if(nums[i]==nums[i+majority]&&!res.contains(nums[i]))
        	{
        		res.add(nums[i]);
        		i=i+majority;
        	}
        }
        return res;
    }

}
class MyStack {
	Queue<Integer> in;
    /** Initialize your data structure here. */
    public MyStack() {
        in = new LinkedList<>();
        
    }

    /** Push element x onto stack. */
    public void push(int x) {
        in.add(x);
        for (int i = 0; i < in.size() - 1; i++){
            in.add(in.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return in.poll();
    }

    /** Get the top element. */
    public int top() {
        return in.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return in.isEmpty();
    }
}