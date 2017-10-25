package day36;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		String[] words= {"d","e"};
		aTest.bulbSwitch(3);
	}
    public int bulbSwitch(int n) {
    	return (int) Math.sqrt(n);
        	
        
    }
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] M=new int[n];
		M[0]=1;
		int[] indices=new int[primes.length];
		for(int i=1;i<n;i++)
		{
			M[i]=Integer.MAX_VALUE;
			for(int j=0;j<indices.length;j++)
			{
				M[i]=Math.min(M[i], M[indices[j]]*primes[j]);
			}
			for(int j=0;j<indices.length;j++)
			{
				if(M[i]==M[indices[j]]*primes[j])
					indices[j]++;
			}
		}
		return M[n-1];
	}
    public int maxProduct(String[] words) {
    	if(words==null||words.length==0)
    		return 0;
    	int length= words.length;
    	int[] value=new int[length];
    	for(int i=0;i<length;i++)
    	{
    		value[i]=0;
    		for(int j=0;j<words[i].length();j++)
    		{
    			value[i] |=1<<(words[i].charAt(j)-'a');
    		}
    	}
    	int maxProduct=0;
    	for(int i=0;i<length;i++)
    	{
    		for(int j=i+1;j<length;j++)
    		{
    			if(((value[i]&value[j])==0)&&maxProduct<words[i].length()*words[j].length())
    			{
    				maxProduct=words[i].length()*words[j].length();
    			}
    		}
    	}
    	return maxProduct;
    }

}
