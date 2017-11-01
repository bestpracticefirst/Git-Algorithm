package day42;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		int[] ab= {1,1,7,4,9,2,5};
		System.out.println(a.wiggleMaxLength(ab));
	}
    public int wiggleMaxLength(int[] nums) {
       if(nums==null)
    	   return 0;
       if(nums.length<2)
    	   return nums.length;
       int[] dif=new int[nums.length-1];
       for(int i=0;i<nums.length-1;i++)
       {
    	   dif[i]=nums[i+1]-nums[i];
       }
       int i=0;
       while(i<dif.length&&dif[i]==0)
    	   i++;
       if(i==dif.length)
    	   return 1;
       int count=1;
       boolean flag= dif[i]>0?true:false;
       for(i=i+1;i<dif.length;i++)
       {
    	   if(flag&&dif[i]<0)
    	   {
    		   flag=false;
    		   count++;
    	   }
    	   else if(!flag&&dif[i]>0)
    	   {
    		   flag=true;
    		   count++;
    	   }
       }
       return count+1;
       
    }
    public int getMoneyAmount(int n) {
    	int left=1, right=n;
    	int money=0;
    	while(left<right)
    	{
    		int mid=left+(right-left)/2;
    		money+=mid;
    		left=mid+1;
    	}
    	return money;
    }
    private class pairComp implements Comparator<int[]>{
        public int compare(int[] p1, int[] p2){
            return p1[0]+p1[1]-p2[0]-p2[1];
        }
    }
    public List<int[]> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
    	List<int []> res=new ArrayList<>();
    	if(nums1==null||nums2==null||nums1.length==0||nums2.length==0)
    		return res;
    	PriorityQueue<int []> pQueue=new PriorityQueue<>(Math.min(k, nums1.length*nums2.length), new pairComp());
    	for(int i=0;i<(Math.min(k, nums1.length));i++)
    	{
    		pQueue.add(new int[]{nums1[i],nums2[0],0 });
    	}
    	while(pQueue.size()>0&&k>0)
    	{
    		int[] cur=pQueue.poll();
    		if(cur[2]<nums2.length-1)
    		{
    			 pQueue.add(new int[]{cur[0],nums2[cur[2]+1],cur[2]+1});
    		}
    		res.add(new int[] {cur[0],cur[1]});
            k--;
    	}
    	return res;
    }
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1.length==0||nums2.length==0) return res;
        PriorityQueue<int[]> pQueue = new PriorityQueue(Math.min(k,nums1.length*nums2.length),new pairComp());
        for (int i=0;i<Math.min(k,nums1.length);i++){
            pQueue.add(new int[]{nums1[i],nums2[0],0}); // last entry is the index of "j" 
        }
        
        while (pQueue.size()>0&&k>0){
            int[] cur = pQueue.poll();
            if (cur[2]<nums2.length-1){
                pQueue.add(new int[]{cur[0],nums2[cur[2]+1],cur[2]+1});
            }
            res.add(new int[]{cur[0],cur[1]});
            k--;
        }
        return res;
    }

	int DIV = 1337;

	public int superPow(int a, int[] b) {
		if (a == 0 || a == DIV || b == null || b.length == 0)
			return 0;
		if (a == 1)
			return 1;
		if (a > DIV)
			return superPow(a % DIV, b);
		List<Integer> res = findloop(a);
		int loopsize = res.size();
		int rem = myMod(b, loopsize);
		if (rem == 0)
			rem = loopsize;
		return res.get(rem - 1);
	}

	public int myMod(int[] b, int m) {
		int rem = 0;
		for (int i = 0; i < b.length; i++) {
			rem = (rem * 10 + b[i]) % m;
		}
		return rem;
	}

	public List<Integer> findloop(int a) {
		List<Integer> res = new ArrayList<>();
		boolean[] set = new boolean[DIV];
		int rem = a % DIV;
		while (!set[rem]) {
			set[rem] = true;
			res.add(rem);
			rem = (rem * a) % DIV;
		}
		return res;
	}

}
