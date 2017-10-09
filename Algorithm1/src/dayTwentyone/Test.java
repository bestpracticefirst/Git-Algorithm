package dayTwentyone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		String b="AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		a.findRepeatedDnaSequences(b);
	}
    public List<String> findRepeatedDnaSequences(String s) {
    	List<String> res= new ArrayList<>();
    	if(s==null||s.length()<11)
    		return res;
    	Set<String> set=new HashSet<>();
    	for(int i=10;i<=s.length();i++)
    	{
    		String temp=s.substring(i-10, i);
    		if(set.contains(temp))
    			res.add(temp);
    		else
    		{
    			set.add(temp);
    		}
    	}
    	return res;
    }
    public String largestNumber(int[] nums) {
        int n = nums.length;  
        if (n < 1) return "";  
          
        //把数组转化为字符串数组  
        String[] strs = new String[n];  
        for (int i = 0; i < n; i++) {  
            strs[i] = String.valueOf(nums[i]);  
        } 
		Arrays.sort(strs, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				StringBuilder s1=new StringBuilder();
				StringBuilder s2=new StringBuilder();
				s1.append(o1).append(o2);
				s2.append(o2).append(o1);
				if(Double.valueOf(s1.toString())-Double.valueOf(s2.toString())>0)
				{
					return -1;
				}
				return 1;

			}
		});
		StringBuilder s=new StringBuilder();
		if(strs[0].startsWith("0"))
			return "0";
		for(int i=0;i<n;i++)
			s.append(strs[i]);
		return s.toString();
    }
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[][] ans = new int[m][n];
        
        //初始化最后一行和最后一列
        ans[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0 ? 0 : -dungeon[m - 1][n - 1];
        for (int i = m - 2; i >= 0; i--) {
            ans[i][n - 1] = dungeon[i][n - 1] >= ans[i + 1][n - 1] ? 0 : ans[i + 1][n - 1] - dungeon[i][n - 1];
        }
        for (int j = n - 2; j >= 0; j--) {
            ans[m - 1][j] = dungeon[m - 1][j] >= ans[m - 1][j + 1] ? 0 : ans[m - 1][j + 1] - dungeon[m - 1][j];
        }
        
        //从右下角往左上角遍历
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = dungeon[i][j] >= ans[i + 1][j] ? 0 : ans[i + 1][j] - dungeon[i][j];
                int right = dungeon[i][j] >= ans[i][j + 1] ? 0 : ans[i][j + 1] - dungeon[i][j];
                ans[i][j] = Math.min(down, right);
            }
        }
        
        //要保证勇士活着，至少需要1魔力
        return ans[0][0] + 1;
    }
}
