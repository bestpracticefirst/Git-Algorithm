package day30;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		a.numSquares(13);
	}
    public int hIndex(int[] citations) {
    	
        int count=0;
        for(int i=citations.length-1;i>=0;i--)
        {
        	
        	if(count>=citations[i])
        		return count;
        	count++;
        }
        return count;
    }
    public int numSquares(int n) {
        int[] dp =new int[n];
        dp[0]=1;
        for(int i=1;i<n;i++)
        {
        	if((int)Math.sqrt(i+1)*(int)Math.sqrt(i+1)==i+1)
        	{
        		dp[i]=1;
        		continue;
        	}
        	int min=Integer.MAX_VALUE;
        	for(int j=1;j<Math.sqrt(i+1);j++)
        	{
        		min=Math.min(min, dp[i-j*j]+1);
        	}
        	dp[i]=min;
        }
        return dp[n-1];
    }
}
