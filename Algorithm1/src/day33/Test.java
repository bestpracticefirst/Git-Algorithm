package day33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		a.getFactors(8);
	}
    public boolean verifyPreorder1(int[] preorder) {
        // 用i标记栈顶
        int i = -1, min = Integer.MIN_VALUE;
        for(int num : preorder){
            if(num < min) return false;
            // 同样的解法，但是复用了数组作为栈，每pop一次相当于i--
            while(i >= 0 && num > preorder[i]){
                min = preorder[i--];
            }
            // push相当于i++
            preorder[++i] = num;
        }
        return true;
    }
	public boolean verifyPreorder(int[] preorder) {
		if(preorder.length<2)
			return true;
		Stack<Integer> res=new Stack<Integer>();
		res.add(preorder[0]);
		int min=Integer.MIN_VALUE;
		for(int i=1;i<preorder.length;i++)
		{
			if(preorder[i]<min)
				return false;
			if(!res.isEmpty()&&res.peek()<preorder[i])
			{
				min=res.pop();
			}	
			else
			{
				res.push(preorder[i]);
			}
		}
		return true;
	}
	private void find(int from, int n, int[] factors, int len, List<List<Integer>> results) {
		for (int i = from; i * i <= n; i++) {
			if (n % i == 0) {
				factors[len] = i;
				find(i, n / i, factors, len + 1, results);
			}
		}
		if (len > 0) {
			factors[len] = n;
			Integer[] f = new Integer[len + 1];
			for (int i = 0; i <= len; i++)
				f[i] = factors[i];
			results.add(Arrays.asList(f));
		}
	}

	public List<List<Integer>> getFactors(int n) {
		int p = 0;
		for (int i = 1; i <= n; p++, i *= 2)
			;
		List<List<Integer>> results = new ArrayList<>();
		find(2, n, new int[p], 0, results);
		return results;
	}

	public boolean canWinNim(int n) {
		if (n % 4 == 0)
			return false;
		return true;
	}

	public List<String> getFlipResult(String s1) {
		List<String> res = new ArrayList<>();
		char[] chs = s1.toCharArray();
		for (int i = 1; i < chs.length; i++) {
			if (chs[i] == '+') {
				if (chs[i - 1] == '+') {
					chs[i - 1] = '-';
					chs[i] = '-';
					res.add(chs.toString());
					chs[i - 1] = '+';
					chs[i] = '+';
				}
			}
		}
		return res;
	}
}
