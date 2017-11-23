package day60;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Constructor;
import java.lang.Class;   
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub'
 
        Elvis e2 = Elvis.getInstance(); 
        e2.setName("张浩文");
        System.out.println(e2.getName());

        Class<?> classType = Elvis.class;  
        
        Constructor<?> c = classType.getDeclaredConstructor(null);  
        c.setAccessible(true);  
        Elvis e1 = (Elvis)c.newInstance(); 
        System.out.println(e1.getName());
        System.out.println(e1==e2);  
	}
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k; 
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }
	public int longestPalindromeSubseq(String s) {
		int[][] dp = new int[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			dp[i][i] = 1;
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][s.length() - 1];
	}
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        
        Set<String> duplicates = getDuplicates(strs);
        for(int i = 0; i < strs.length; i++) {
            if(!duplicates.contains(strs[i])) {
                if(i == 0) return strs[0].length();
                for(int j = 0; j < i; j++) {
                    if(isSubsequence(strs[j], strs[i])) break;
                    if(j == i-1) return strs[i].length();
                }
            }
        }
        return -1;
    }
    
    public boolean isSubsequence(String a, String b) {
        int i = 0, j = 0;
        while(i < a.length() && j < b.length()) {
            if(a.charAt(i) == b.charAt(j)) j++;
            i++;
        }
        return j == b.length();
    }
    
    private Set<String> getDuplicates(String[] strs) {
        Set<String> set = new HashSet<String>();
        Set<String> duplicates = new HashSet<String>();
        for(String s : strs) {
            if(set.contains(s)) duplicates.add(s);
            set.add(s);
        }
        return duplicates;
    }

    public int findLUSlength(String a, String b) {
        int a_length = a.length();
        int b_length = b.length();
        if(a.equals(b))
            return -1;
        else
            return a_length>b_length ? a_length : b_length;
    }
	public boolean detectCapitalUse(String word) {
		if (word == null || word.length() == 0)
			return true;
		boolean isCaptital = true;
		for (int i = 0; i < word.length(); i++) {
			if (isCaptital) {
				if (word.charAt(i) - 'A' > 25) {
					if (i > 1)
						return false;
					else
					    isCaptital = false;
				}
			} else {
				if (word.charAt(i) - 'A' < 26)
					return false;
			}
		}
		return true;
	}
}
