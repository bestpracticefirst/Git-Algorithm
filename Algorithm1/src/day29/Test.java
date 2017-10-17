package day29;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		String[] s= {"practice", "makes", "perfect", "coding", "makes"};
		a.shortestDistance(s, "makes", "makes");
	}
	public int shortestDistance(String[] words, String word1, String word2) {
		int index1 = -1, index2 = -1, min = words.length;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				if(word1.equals(word2))
				{
					index2=index1;
				}
				index1 = i;
				if(index1!=-1&&index2!=-1)
				{
				    min = Math.min(min, index2 - index1);
				}
			}
			else if (words[i].equals(word2)) {
				index2 = i;
				if(index1!=-1&&index2!=-1)
				{
				    min = Math.min(min, index2 - index1);
				}
			}
		}
		if (index1 == -1 || index2 == -1)
			return words.length;
		return min;
	}
    public boolean isUgly(int num) {
    	if(num==0)
    		return false;
    	while(num!=1)
    	{
    		if(num%2!=0&&num%3!=0&&num%5!=0)
    		{
    			return false;
    		}
    		if(num%2==0)
    			num=num/2;
    		if(num%3==0)
    			num=num/3;
    		if(num%5==0)
    			num=num/5;
    	}
        return true;
    }
    public int missingNumber(int[] nums) {
        int xor = 0, i = 0;
    	for (i = 0; i < nums.length; i++) {
    		xor = xor ^ i ^ nums[i];
    	}
    	return xor ^ i;
    }
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int count=0;
        for(int i=citations.length-1;i>=0;i--)
        {
        	
        	if(count>=citations[i])
        		return count;
        	count++;
        }
        return count;
    }

}
