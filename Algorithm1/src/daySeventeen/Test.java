package daySeventeen;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point a=new Point(2,3);
		Point b=new Point(3,3);
		Point c=new Point(-5,3);
		Test d=new Test();
		int[] e= {1,2};
		d.findMin(e);
	}
	
    public int findMin(int[] nums) {
        int min = 0, max = nums.length - 1;
        while(min <= max){
            int mid = min + (max - min) / 2;
            if(mid == 0) return Math.min(nums[mid], nums[max]);
            if(mid == nums.length - 1) return Math.min(nums[mid], nums[min]);
            if(nums[mid + 1] >= nums[mid] && nums[mid - 1] >= nums[mid]){
                return nums[mid];
            } else if(nums[mid] > nums[max]){
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return nums[0];
    }
    public int maxProduct(int[] nums) {
    	if(nums.length==1)
    		return nums[0];
    	int minProduct=nums[0];
    	int maxProduct=nums[0];
    	int maxglobal=nums[0];
    	for(int i=1;i<nums.length;i++)
    	{
    		int maxCopy=maxProduct;
    		maxProduct=Math.max(Math.max(nums[i], nums[i]*maxProduct),nums[i]*minProduct);
    		minProduct=Math.min(Math.min(nums[i], nums[i]*maxCopy),nums[i]*minProduct);
    		maxglobal=Math.max(maxglobal, maxProduct);
    	}
    	return maxglobal;
    }
    public String reverseWords(String s) {
    	if(s.length()==0)
    		return "";
    	String[] a=s.split(" ");
    	StringBuilder res=new StringBuilder();
    	for(int i=a.length-1;i>=0;i--)
    	{
    		if(!a[i].equals(""))
    			res.append(a[i]).append(" ");
    	}
    	return res.toString().trim();
    }
	 public int maxPoints1(Point[] points) {
	    	
	        if (points.length < 3) return points.length;
	        
	        int max = 0;//用于返回的结果，即共线点的最大个数
	        Map<Double, Integer> map = new HashMap<Double, Integer>();//保存同一个斜率的点的个数
	        
	        for (int i = 0; i < points.length; i++) {//以每一个点为固定点
	        	map.clear();
	        	int duplicate = 1;//记录跟固定点重合的个数
	        	
	        	for(int j = 0 ; j < points.length; j++){//遍历其他点，求其与固定点之间的斜率
	        		if (i == j) continue;//如果是自己，跳过
	        		double slope = 0.0;//斜率
	        		
	        		if (points[i].x == points[j].x && points[i].y == points[j].y) {//如果跟固定点重合
	        			duplicate++;
	        			continue;
	        		} else if (points[i].x == points[j].x) {//如果跟固定点在同一条竖线上，斜率设为最大值
	        			slope = Integer.MAX_VALUE;
	        		} else {//计算该点与固定点的斜率
	        			slope = (double) (points[i].y - points[j].y) / (points[i].x - points[j].x);
	        		}
	        		map.put(slope, map.containsKey(slope) ? map.get(slope) + 1 : 1);
	        	}
	        	
	        	//更新最优解
	        	if (map.keySet().size() == 0) {//如果map为空
	        		max = duplicate > max ? duplicate : max;
	        	} else {
	        		for (double key : map.keySet()) {
	        			max = Math.max((duplicate + map.get(key)), max); 
	        		}
	        	}
	        }
	        return max;
	    }
	public int maxPoints(Point[] points) {
		if(points.length<3)
			return points.length;
		HashMap<Double, Integer> map=new HashMap<>();
		int max=0;
		for(int i=0;i<points.length&&max<(points.length-i);i++)
		{
			map.clear();
			int duplicate=1;
			for(int j=i+1;j<points.length&&max<(points.length-j);j++)
			{
				double slope=0.0;
				if(points[i].x==points[j].x&&points[i].y==points[j].y)
				{
					duplicate++;
					continue;
				}
				else if(points[i].x==points[j].x)
				{
					slope=Integer.MAX_VALUE;
				}
				else{
					slope=(double)(points[i].y - points[j].y) / (points[i].x - points[j].x);  
				}
				map.put(slope, map.containsKey(slope)?map.get(slope)+1:1);
			}
			if (map.keySet().size() == 0) {//如果map为空
        		max = duplicate > max ? duplicate : max;
        	} else {
        		for (double key : map.keySet()) {
        			max = Math.max((duplicate + map.get(key)), max); 
        		}
        	}
		}
		return max;
	}

	public ListNode sortList(ListNode head) {
		return mergeSort(head);
	}

	private ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode walker = head;
		ListNode runner = head;
		while (runner.next != null && runner.next.next != null) {
			walker = walker.next;
			runner = runner.next.next;
		}
		ListNode head2 = walker.next;
		walker.next = null;
		ListNode head1 = head;
		head1 = mergeSort(head1);
		head2 = mergeSort(head2);
		return merge(head1, head2);
	}

	private ListNode merge(ListNode head1, ListNode head2) {
		ListNode helper = new ListNode(0);
		helper.next = head1;
		ListNode pre = helper;
		while (head1 != null && head2 != null) {
			if (head1.val < head2.val) {
				head1 = head1.next;
			} else {
				ListNode next = head2.next;
				head2.next = pre.next;
				pre.next = head2;
				head2 = next;
			}
			pre = pre.next;
		}
		if (head2 != null) {
			pre.next = head2;
		}
		return helper.next;
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
	}
}

class Point {
	int x;
	int y;
	Point() {
		x = 0;
		y = 0;
	}
	Point(int a, int b) {
		x = a;
		y = b;
	}
}