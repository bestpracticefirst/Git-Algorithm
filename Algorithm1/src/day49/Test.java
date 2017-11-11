package day49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		System.out.println(aTest.longestPalindrome("abccccdd"));
	}
    public List<String> fizzBuzz(int n) {
    	List<String> res=new ArrayList<>();
    	for(int i=1;i<=n;i++)
    	{
    		if(i%3==0&&i%5==0)
    		{
    			res.add("FizzBuzz");
    		}
    		else if(i%3==0)
    		{
    			res.add("Fizz");
    		}
    		else if(i%5==0)
    		{
    			res.add("Buzz");
    		}
    		else
    		{
    			res.add(String.valueOf(i));
    		}
    			
    	}
    	return res;
    }
    public int longestPalindrome(String s) {
    	if(s==null||s.length()==0)
    		return 0;
    	char[] temp=s.toCharArray();
    	int[] count=new int[52];
    	boolean flag=true;
    	for(int i=0;i<temp.length;i++)
    	{
    		if('A'<=temp[i]&&temp[i]<='Z')
    			count[temp[i]-'A']++;
    		else
    		{
    			count[26+temp[i]-'a']++;
    		}
    	}
    	int result=0;
    	for(int i=0;i<52;i++)
    	{
    		if(count[i]%2==1)
    		{
    			flag=false;
    			result+=count[i]-1;
    		}
    		else
    		{
    			result+=count[i];
    		}
    		
    	}
    	if(!flag)
    		return result+1;
    	else
    		return result;
    }
    public String toHex(int num) {
    	char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result; 
            num = (num >>> 4);
        }
        return result;
    }
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0)
            return new int[0][0];
            
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (b[0] == a[0]) return a[1] - b[1];
                return b[0] - a[0];
            }
        });
        int n= people.length;
        List<int[]> temp=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
        	temp.add(people[i][1],new int[] {people[i][0],people[i][1]});
        }
        int[][] res=new int[people.length][2];
        for(int i=0;i<n;i++)
        {
        	res[i][0]=temp.get(i)[0];
        	res[i][1]=temp.get(i)[1];
        }
        return res;
    }
}
