package day32;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a= new Test();
		int[] b= {1,3,4,2,2};
		a.findDuplicate(b);
	}
    public int findDuplicate(int[] nums) {
    	int left=0;
    	int right=nums.length-1;
    	while(left<right)
    	{
        	if(nums[left]==nums[right])
        		return nums[left];
        	for(int i=left+1;i<right;i++)
        	{
        		if(nums[i]==nums[left]||nums[i]==nums[right])
        			return nums[i];
        	}
        	left++;
        	right--;
        	if(nums[left]==nums[right])
        		return nums[left];
    	}
    	return nums[left];
    }
    public boolean wordPattern(String pattern, String str) {
    	String[] s=new String[26];
    	String[] strs=str.split(" ");
    	if(pattern.length()!=strs.length)
    		return false;
    	HashMap<String, Character> map=new HashMap<>();
    	for(int i=0;i<pattern.length();i++)
    	{
    		char ch=pattern.charAt(i);
    		if(s[ch-'a']==null)
    			s[ch-'a']=strs[i];
    		else if(!s[ch-'a'].equals(strs[i]))
    			return false;
    		if(map.containsKey(strs[i]))
    		{
    			if(!map.get(strs[i]).equals(ch))
    				return false;
    		}
    		map.put(strs[i], ch);
    	}
    	return true;
    }
	//�ĸ�״̬
	//0��ʾ��ϸ�������ϸ��
	//1��ʾ��ϸ����ɻ�ϸ��
	//2��ʾ��ϸ�������ϸ��
	//3��ʾ��ϸ����ɻ�ϸ��
    public void gameOfLife(int[][] board) {
    	if(board==null||board[0].length==0)
    		return;
        int m=board.length;
        int n=board[0].length;
        for(int i=0;i<m;i++)
        {
        	for(int j=0;j<n;j++)
        	{
        		int lives=0;
        		if(i>0)
        			lives+=board[i-1][j]==1||board[i-1][j]==2?1:0;
        		if(i<m-1)
        			lives+=board[i+1][j]==1||board[i+1][j]==2?1:0;
        		if(j>0)
        			lives+=board[i][j-1]==1||board[i][j-1]==2?1:0;
        		if(j<n-1)
        			lives+=board[i][j+1]==1||board[i][j+1]==2?1:0;
        		if(i>0&&j>0)
        			lives+=board[i-1][j-1]==1||board[i-1][j-1]==2?1:0;
        		if(i>0&&j<n-1)
        			lives+=board[i-1][j+1]==1||board[i-1][j+1]==2?1:0;
        		if(i<m-1&&j>0)
        			lives+=board[i+1][j-1]==1||board[i+1][j-1]==2?1:0;
        		if(i<m-1&&j<n-1)
        			lives+=board[i+1][j+1]==1||board[i+1][j+1]==2?1:0;
        		if(board[i][j]==0&&lives==3)
        			board[i][j]=3;
        		else if(board[i][j]==2&&(lives<2||lives>3))
        		{
        			board[i][j]=2;
        		}
        	}

        }
    	for(int i=0;i<m;i++)
    	{
    		for(int j=0;j<n;j++)
    		{
    			board[i][j]%=2;
    		}
    	}
    }
    public void moveZeroes(int[] nums) {
    	int count=0;
    	for(int i=0;i<nums.length;i++)
    	{
    		if(nums[i]!=0)
    		{
    			nums[count]=nums[i];
    			if(count!=i)
    				nums[i]=0;
    			count++;
    		}
    	}
    	System.out.println();
    }
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        // �ö���������Ľ���ʱ��,����С��ʱ�䴦�ڶ��е����ȳ�ȥ��λ��
        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();
        endTimes.offer(intervals[0].end);
        for(int i = 1; i < intervals.length; i++){
            // �����ǰʱ��εĿ�ʼʱ��������������ʱ�䣬����Ը����������Ľ���ʱ��Ϊ��ǰʱ��εĽ���ʱ�䣬���С�ڵĻ����ͼ���һ���µĽ���ʱ�䣬��ʾ�µķ���
            if(intervals[i].start >= endTimes.peek()){
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }
        // �ж��ٽ���ʱ����ж��ٷ���
        return endTimes.size();
    }
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval arg0, Interval arg1) {
				// TODO Auto-generated method stub
				return arg0.start-arg1.start;
			}
		});
		for(int i=1;i<intervals.length;i++)
		{
			if(intervals[i].start<intervals[i-1].end)
				return false;
		}
		return true;
	}

}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
class PeekingIterator implements Iterator<Integer> {
	Iterator<Integer> iterator1=null;
	boolean peeked=false;
	int peekValue;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    iterator1=iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(peeked)
        	return peekValue;
        else
        {
        	peekValue=iterator1.next();
        	peeked=true;
        	return peekValue;
        }
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(peeked)
	    {
	    	peeked=false;
	    	return peekValue;
	    	
	    }
	    else
	    {
	    	return iterator1.next();
	    }
	}

	@Override
	public boolean hasNext() {
		return peeked||iterator1.hasNext();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}