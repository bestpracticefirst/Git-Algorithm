package day43;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		int[] nums= {1,2,3};
		aTest.combinationSum4(nums, 3);
	}
    public int kthSmallest(int[][] matrix, int k) {
        int n=matrix.length;
        int lo=matrix[0][0],hi=matrix[n-1][n-1];
        while(lo<=hi)
        {
            int mid = lo + (hi - lo) / 2;
            int count = getLessEqual(matrix, mid);
            if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
    public int getLessEqual(int[][] matrix, int val)
    {
    	int res=0;
    	int n=matrix.length;
    	int i=n-1;
    	int j=0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) i--;
            else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }
    public int combinationSum4(int[] nums, int target) {
    	if(nums==null||nums.length==0)
    		return 0;
    	Arrays.sort(nums);
        int[] dp = new int[target+1];
        dp[0]=1;
        for(int i=1;i<=target;i++)
        {
        	for(int a:nums)
        	{
        		if(i-a>=0)
        			dp[i]+=dp[i-a];
        		else
        			break;
        	}
        }
        return dp[target];
    }
}
