package Test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashMap;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Interval a = new Interval(1, 3);
//		Interval a1 = new Interval(2, 6);
//		Interval a2 = new Interval(8, 10);
//		Interval a3 = new Interval(15, 18);
//		List<Interval> intervals = new ArrayList<Interval>();
//		intervals.add(a1);
//		intervals.add(a);
//		intervals.add(a2);
//		intervals.add(a3);
//		ListNode a1=new ListNode(1);
//		ListNode a2=new ListNode(2);
//		a1.next=a2;;
		char[][] a={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		Test2 test = new Test2();
		test.exist(a, "ABCCED");
		
	}
	  
    
	public List<String> findWords(char[][] board, String[] words) { 
		List<String> res=new ArrayList<String>();
        Trie trie = new Trie();  
        for (String word : words) {  
            trie.insert(word);  
        }  
          
        int m = board.length;  
        int n = board[0].length;  
        boolean[][] visited = new boolean[m][n];  
        for (int i = 0; i < m; i++) {  
            for (int j = 0; j < n; j++) {  
                dfs(board, visited, "", i, j, trie,res);  
            }  
        }  
          
        return res;  
    }  
      
    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie,List res) {  
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;  
        if (visited[x][y]) return;  
          
        str += board[x][y];  
        if (!trie.startsWith(str)) return;  
          
        if (trie.search(str)) {  
            if(!res.contains(str))
            {
            	res.add(str);
            }
        }  
          
        visited[x][y] = true;  
        dfs(board, visited, str, x - 1, y, trie,res);  
        dfs(board, visited, str, x + 1, y, trie,res);  
        dfs(board, visited, str, x, y - 1, trie,res);  
        dfs(board, visited, str, x, y + 1, trie,res);  
        visited[x][y] = false;  
    }  
    public boolean exist(char[][] board, String word) {
        if(board==null||board.length==0||board[0].length==0)
        	return false;
        if(word==null||word=="")
        	return true;
        boolean [][]visited =new boolean[board.length][board[0].length];
        for(int row=0;row<board.length;row++)
        {
        	for(int column=0;column<board[0].length;column++)
        	{
        		if(board[row][column]==word.charAt(0))
        		{
        			if(word.length()==1)
        				return true;
        			visited[row][column]=true;
        			if(wordExist(board, word.substring(1,word.length()), visited, row, column))
        				return true;
        			visited[row][column]=false;
        		}
        	}
        }
        return false;
    }
    public boolean wordExist(char[][] board,String word, boolean [][]visited, int row, int column)
    {
    	if(word.length()==0)
    		return true;
    	if(row-1>=0&&(!visited[row-1][column])&&word.charAt(0)==board[row-1][column])//上边一行
    	{
    		visited[row-1][column]=true;
			if(wordExist(board, word.substring(1,word.length()), visited, row-1, column))
				return true;
			visited[row-1][column]=false;
    	}
    	if(row+1<board.length&&(!visited[row+1][column])&&word.charAt(0)==board[row+1][column])//下边一行
    	{
    		visited[row+1][column]=true;
			if(wordExist(board, word.substring(1,word.length()), visited, row+1, column))
				return true;
			visited[row+1][column]=false;
    	}
    	if(column-1>=0&&(!visited[row][column-1])&&word.charAt(0)==board[row][column-1])
    	{
    		visited[row][column-1]=true;
			if(wordExist(board, word.substring(1,word.length()), visited, row, column-1))
				return true;
			visited[row][column-1]=false;
    	}
    	if(column+1<board[0].length&&(!visited[row][column+1])&&word.charAt(0)==board[row][column+1])
    	{
    		visited[row][column+1]=true;
			if(wordExist(board,word.substring(1,word.length()), visited, row, column+1))
				return true;
			visited[row][column+1]=false;
    	}
    	return false;
    }
    public void sortColors(int[] nums) {
    	if(nums==null||nums.length<2)
    		return;
    	int head=0,end=nums.length-1;
    	for(int i=0;i<=end;i++)
    	{
    		if(nums[i]==0)
    		{
    			int temp=nums[i];
    			nums[i]=nums[head];
    			nums[head]=temp;
    			head++;
    		}
    		if(nums[i]==2)
    		{
    			int temp=nums[end];
    			nums[end]=nums[i];
    			nums[i]=temp;
    			end--;
    			i--;
    		}
    	}
    	System.out.println(nums.length);
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length==0 || matrix[0].length==0)  
            return false;  
        int l = 0;  
        int r = matrix.length-1;  
        while(l<=r)  
        {  
            int mid = (l+r)/2;  
            if(matrix[mid][0] == target) return true;  
            if(matrix[mid][0] > target)  
            {  
                r = mid-1;  
            }  
            else  
            {  
                l = mid+1;  
            }  
        }  
        int row = r;  
        if(row<0)  
            return false;  
        l = 0;  
        r = matrix[0].length-1;  
        while(l<=r)  
        {  
            int mid = (l+r)/2;  
            if(matrix[row][mid] == target) return true;  
            if(matrix[row][mid] > target)  
            {  
                r = mid-1;  
            }  
            else  
            {  
                l = mid+1;  
            }  
        }     
        return false;  
        
    }
    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean dr[] = new boolean[m];
        boolean dc[] = new boolean[n]; 
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(matrix[i][j] == 0){
                    dr[i] = true;
                    dc[j] = true;
                }
        
        for(int i=0;i<m;i++)
            if(dr[i] == true){
                for(int j=0;j<n;j++) 
                    matrix[i][j] = 0;
            }
        
        for(int i=0;i<n;i++)
            if(dc[i] == true){
                for(int j=0;j<m;j++) 
                    matrix[j][i] = 0;
            }
    }
    public void setZeroes(int[][] matrix) {
    	int len1=matrix.length;
    	if(len1==0)
    		return;
    	int len2=matrix[0].length;
    	if(len2==0)
    		return;
    	int [][]newMatrix=new int[len1+1][len2+1];
    	for(int i=0;i<len1;i++)
    	{
    		for(int j=0;j<len2;j++)
    		{
    			if(matrix[i][j]==0)
    			{
    				if(newMatrix[i+1][0]!=1)
    				{
    					newMatrix[i+1][0]=1;
    				}
    				if(newMatrix[0][j+1]!=1)
    				{
    					newMatrix[0][j+1]=1;
    				}
    			}
    		}
    	}
    	for(int i=0;i<len1;i++)
    	{
    		if(newMatrix[i+1][0]==1)
    		{
    			for(int j=0;j<len2;j++)
    			{
    				matrix[i][j]=0;
    			}
    		}
    	}
    	for(int j=0;j<len2;j++)
    	{
    		if(newMatrix[0][j+1]==1)
    		{
    			for(int i=0;i<len1;i++)
    			{
    				matrix[i][j]=0;
    			}
    		}
    	}
    }
    public int minDistance(String word1, String word2) {
    	int len1=word1.length(), len2=word2.length();
        int [][]dp=new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++)
        {
        	dp[i][0]=i;
        }
        for(int i=0;i<=len2;i++)
        {
        	dp[0][i]=i;
        }
        for(int i=1;i<=len1;i++)
        {
        	for(int j=1;j<=len2;j++)
        	{
        		if(word1.charAt(i-1)==word2.charAt(j-1))
        		{
        			dp[i][j]=dp[i-1][j-1];
        		}
        		else
        		{
        			dp[i][j]=1+Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
        		}
        	}
        }
        return dp[len1][len2];
    }
    public String simplifyPath(String path) {
    	String []paths=path.split("/");
    	if(paths.length==0&&!path.contains("/"))
    		return "";
    	else if(paths.length==0&&path.contains("/"))
    		return "/";
    	StringBuilder currentPath=new StringBuilder();
    	currentPath.append("/");
    	for(String temp:paths)
    	{
    		if(!temp.equals(".")&&!temp.equals(""))
    		{
    			if(temp.equals(".."))
    			{
    				if(currentPath.lastIndexOf("/")!=0){
    				    currentPath.delete(currentPath.lastIndexOf("/"), currentPath.length());
    				}
    			}
    			else
    			{
    				currentPath.append(temp+"/");
    			}
    		}
    	}
    	String s=currentPath.toString();
    	if(s.length()==1)
    		return s;
    	return s.substring(0,s.length()-1);
    }
    public int climbStairs(int n) {
    	if(n==1)
    		return 1;
    	int oneStep=1,twoStep=1,res=0;
    	for(int i=2;i<=n;i++)
    	{
    		res=oneStep+twoStep;
    		twoStep=oneStep;
    		oneStep=res;
    	}
    	return res;
    }
    public int mySqrt(int x) {
    	if(x<0) return -1;  
        if(x==0) return 0;  
        int l=1;  
        int r=x/2+1;  
        while(l<=r)  
        {  
            int m = (l+r)/2;  
            if(m<=x/m && x/(m+1)<m+1)  
                return m;  
            if(x/m<m)  
            {  
                r = m-1;  
            }  
            else  
            {  
                l = m+1;  
            }  
        }  
        return 0;  
    }
    public String addBinary(String a, String b) {
    	if(a.length()>b.length())
    	{
    		String tempS=a;
    		a=b;
    		b=tempS;
    	}
    	int length1=a.length()-1;
    	int length2=b.length()-1;
    	int carry=0;
    	StringBuilder res=new StringBuilder();
    	for(int i=0;i<=length1;i++)
    	{
    		int temp=(a.charAt(length1-i)-'0')+(b.charAt(length2-i)-'0')+carry;
    		carry=temp/2;
    		res.append(temp%2);
    	}
    	while(length1<length2)
    	{
    		int temp=(b.charAt(length2-length1-1)-'0')+carry;
    		carry=temp/2;
    		res.append(temp%2);
    		length1++;
    	}
    	if(carry==1)
    	{
    		res.append(carry);
    	}
    	return res.reverse().toString();
    }
    public int[] plusOne(int[] digits) {
        int carry=0;
        if(digits==null||digits.length==0)
        	return digits;
        digits[digits.length-1]=digits[digits.length-1]+1;
        for(int i=digits.length-1;i>=0;i--)
        {
        	int temp=digits[i]+carry;
        	digits[i]=temp%10;
        	carry=temp/10;
        	if(carry==0)
        		break;
        }
        if(carry==1)
        {
        	int []newdigits=new int[digits.length+1];
        	newdigits[0]=1;
        	return newdigits;
        }
        return digits;
    }
    public int uniquePaths(int m, int n) {
    	long M=1;
    	long N=1;
    	if(m<n)
    	{
    		int temp=m;
    		m=n;
    		n=temp;
    	}
    	for(int i=m+n-2;i>m-1;i--)
    	{
    		M*=i;
    	}
    	for(int i=n-1;i>0;i--)
    	{
    		N*=i;
    	}
    	return (int) (M/N);
    }
    public ListNode rotateRight(ListNode head, int k) {
    	ListNode dummyhead = new ListNode(0);
    	if(head==null||head.next==null)
    	{
    		return head;
    	}
    	dummyhead.next=head;
    	ListNode temp=head;
    	ListNode temp2=head;
    	int totalSize=1;
    	while(temp.next!=null)
    	{
    		totalSize++;
    		temp=temp.next;
    	}
    	k=k%totalSize;
    	if(k==0)
    		return head;
    	int i=1;
    	while(i<totalSize-k)
    	{
    		temp2=temp2.next;
    		i++;
    	}
    	temp.next=dummyhead.next;
    	dummyhead.next=temp2.next;
    	temp2.next=null;
    	return dummyhead.next;
    }
    public int[][] generateMatrix(int n) {
        int[][] matrix=new int [n][n];
        generate(matrix,0,0,0);
        return matrix;
    }
    public void generate(int[][] matrix, int row, int column,int n) {

		int startRow = row;
		int endRow = matrix.length - 1 - row;
		int startColumn = column;
		int endColumn = matrix[0].length - 1 - column;
		// 首行
		if (startColumn <= endColumn && startRow <= endRow) {
			for (int i = startColumn; i <= endColumn; i++)
			{
				n++;
				matrix[row][i]=n;
			}
			// 末列
			if (startRow < endRow) {
				for (int i = startRow + 1; i <= endRow; i++) {
					n++;
					matrix[i][endColumn]=n;
				}
				if (startColumn < endColumn) {
					for (int i = endColumn - 1; i >= startColumn; i--) {
						n++;
						matrix[endRow][i]=n;
					}
					if (startRow < endRow - 1) {
						for (int i = endRow - 1; i > startRow; i--) {
							n++;
							matrix[i][startRow]=n;
						}
					}
				}
			}
		} else {
			return;
		}
		generate(matrix, row + 1, column + 1, n);
	}
    public String getPermutation(int n, int k) {
    	k--;
    	int []index=new int[n];
    	for(int i=1;i<n+1;i++)
    	{
    		index[i-1]=i;
    	}
    	int frac=1;
    	for(int i=2;i<n;i++)
    	{
    		frac*=i;
    	}
    	int times=n-1;
    	String s="";
    	while(times>0)
    	{
    		int lastIndex=k/frac;
    		s+=index[lastIndex];
            for (int j = lastIndex; j < times; j++) {
                index[j] = index[j+1];   
            }
    		k=k%frac;
    		frac=frac/times;
    		times--;
    	}
    	s+= index[0];
    	return s;
    	
    }

	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() < 2)
			return intervals;
		Collections.sort(intervals, new IntervalComparator());
        if (intervals == null || intervals.size() <= 1)  
            return intervals;  
  
        // sort intervals by using self-defined Comparator  
        Collections.sort(intervals, new IntervalComparator());  
  
        List<Interval> result = new ArrayList<Interval>();  
  
        Interval prev = intervals.get(0);  
        for (int i = 1; i < intervals.size(); i++) {  
            Interval curr = intervals.get(i);  
  
            if (prev.end >= curr.start) {  
                // merged case  
                Interval merged = new Interval(prev.start, Math.max(prev.end,  
                        curr.end));  
                prev = merged;  
            } else {  
                result.add(prev);  
                prev = curr;  
            }  
        }  
  
        result.add(prev);  
        return result;  

	}

	public boolean canJump(int[] nums) {
		if (nums == null || nums.length < 2)
			return true;
		int temp = nums[0];
		for (int i = 1; i < nums.length; i++) {
			temp--;
			if (temp < 0)
				return false;
			if (temp < nums[i])
				temp = nums[i];
		}
		return true;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if (matrix.length == 0)
			return res;
		Spiral(matrix, 0, 0, res);
		return res;
	}

	public void Spiral(int[][] matrix, int row, int column, List<Integer> res) {

		int startRow = row;
		int endRow = matrix.length - 1 - row;
		int startColumn = column;
		int endColumn = matrix[0].length - 1 - column;
		// 首行
		if (startColumn <= endColumn && startRow <= endRow) {
			for (int i = startColumn; i <= endColumn; i++)
				res.add(matrix[row][i]);
			// 末列
			if (startRow < endRow) {
				for (int i = startRow + 1; i <= endRow; i++) {
					res.add(matrix[i][endColumn]);
				}
				if (startColumn < endColumn) {
					for (int i = endColumn - 1; i >= startColumn; i--) {
						res.add(matrix[endRow][i]);
					}
					if (startRow < endRow - 1) {
						for (int i = endRow - 1; i > startRow; i--) {
							res.add(matrix[i][startRow]);
						}
					}
				}
			}
		} else {
			return;
		}
		Spiral(matrix, row + 1, column + 1, res);
	}

	public int lengthOfLastWord(String s) {
		String[] words = s.split(" ");
		if (words.length == 0)
			return 0;
		else
			return words[words.length - 1].length();
	}

	public double myPow(double x, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		if (n == -1)
			return 1 / x;
		return myPow(x * x, n / 2) * myPow(x, n % 2);

	}

	public int maxSubArray(int[] nums) {
		int max = nums[0];
		int curr = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			int temp = curr + nums[i];
			if (temp > nums[i]) {
				curr = temp;
			} else {
				curr = nums[i];
			}
			if (max < curr) {
				max = curr;
			}
		}
		return max;
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List res = new ArrayList<ArrayList<String>>();
		boolean[] visited = new boolean[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (visited[i])
				continue;
			ArrayList<String> item = new ArrayList<String>();
			item.add(strs[i]);
			visited[i] = true;
			HashMap<Character, Integer> map = new HashMap<Character, Integer>();
			for (char c : strs[i].toCharArray()) {
				if (map.containsKey(c)) {
					map.put(c, map.get(c) + 1);
				} else {
					map.put(c, 1);
				}
			}
			for (int j = i + 1; j < strs.length; j++) {
				HashMap<Character, Integer> tempmap = new HashMap<Character, Integer>(
						map);
				if (visited[j])
					continue;
				if (strs[j].length() != strs[i].length()) {
					continue;
				}
				for (char c : strs[j].toCharArray()) {
					if (tempmap.containsKey(c)) {
						if (tempmap.get(c) > 1) {
							tempmap.put(c, tempmap.get(c) - 1);
						} else {
							tempmap.remove(c);
						}
					} else {
						break;
					}
				}
				if (tempmap.size() != 0)
					continue;
				else {
					item.add(strs[j]);
					visited[j] = true;
				}
			}
			res.add(item);
		}
		return res;

	}

	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		int[][] temp = new int[matrix.length][matrix.length];
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j >= 0; j--) {
				temp[i][n - 1 - j] = matrix[j][i];

			}
		}
		System.arraycopy(temp, 0, matrix, 0, matrix.length);
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		boolean[] visited = new boolean[nums.length];
		Arrays.sort(nums);
		permutation_helper2(nums, res, item, visited);
		return res;
	}

	public void permutation_helper2(int[] num, List<ArrayList<Integer>> res,
			ArrayList<Integer> item, boolean[] visited) {
		if (num.length == item.size()) {
			res.add(new ArrayList<Integer>(item));
			return;
		}
		for (int i = 0; i < num.length; i++) {
			if (i > 0 && num[i] == num[i - 1] && !visited[i - 1])
				continue;
			if (!visited[i]) {
				item.add(num[i]);
				visited[i] = true;
				permutation_helper2(num, res, item, visited);
				item.remove(item.size() - 1);
				visited[i] = false;
			}
		}
	}

	public List<List<Integer>> permute(int[] nums) {
		List res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		boolean[] visited = new boolean[nums.length];
		permutation_helper(nums, res, item, visited);
		return res;
	}

	public void permutation_helper(int[] num, List<ArrayList<Integer>> res,
			ArrayList<Integer> item, boolean[] visited) {
		if (num.length == item.size()) {
			res.add(new ArrayList<Integer>(item));
			return;
		}
		for (int i = 0; i < num.length; i++) {
			if (!visited[i]) {
				item.add(num[i]);
				visited[i] = true;
				permutation_helper(num, res, item, visited);
				item.remove(item.size() - 1);
				visited[i] = false;
			}
		}
	}

	public String multiply(String num1, String num2) {
		num1 = new StringBuilder(num1).reverse().toString();
		num2 = new StringBuilder(num2).reverse().toString();
		int[] d = new int[num1.length() + num2.length()];
		char[] nums1 = num1.toCharArray();
		char[] nums2 = num2.toCharArray();
		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				d[i + j] += (nums1[i] - '0') * (nums2[j] - '0');
			}
		}
		for (int i = 0, c = 0; i < d.length; i++) {
			int temp = d[i] + c;
			d[i] = temp % 10;
			c = temp / 10;
		}
		String s = "";
		for (int i = 0; i < d.length; i++) {
			s = d[i] + s;
		}
		while (s.length() > 0 && s.charAt(0) == '0')
			s = s.substring(1);
		return s;

	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		getResult2(list, new ArrayList<Integer>(), candidates, target, 0);
		return list;
	}

	private void getResult2(List<List<Integer>> list, List<Integer> curList,
			int[] candidates, int target, int start) {
		if (target > 0) {
			for (int i = start; i < candidates.length; i++) {
				if (i > start && candidates[i] == candidates[i - 1])
					continue;
				if (target - candidates[i] >= 0) {
					curList.add(candidates[i]);
					getResult2(list, curList, candidates, target
							- candidates[i], i + 1);
					curList.remove(curList.size() - 1);
				} else {
					break;
				}
			}
		} else
			list.add(new ArrayList<Integer>(curList));
	}

	public List<List<Integer>> combinationSum1(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		getResult(list, new ArrayList<Integer>(), candidates, target, 0);
		return list;
	}

	private void getResult(List<List<Integer>> list, List<Integer> curList,
			int[] candidates, int target, int start) {
		if (target > 0) {
			for (int i = start; i < candidates.length; i++) {
				if (target - candidates[i] >= 0) {
					curList.add(candidates[i]);
					getResult(list, curList, candidates,
							target - candidates[i], i);
					curList.remove(curList.size() - 1);
				} else {
					break;
				}
			}
		} else
			list.add(new ArrayList<Integer>(curList));
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List res = new ArrayList<ArrayList<Integer>>();
		List<Integer> result = new ArrayList<Integer>();
		Arrays.sort(candidates);
		if (candidates == null || candidates.length == 0)
			return res;
		for (int i = 0; i < candidates.length; i++) {
			if (candidates[i] == target) {
				result.add(candidates[i]);
				res.add(result);
				break;
			} else if (candidates[i] > target)
				break;
			result.add(candidates[i]);
			findNumberToSum(candidates, target - candidates[i], res, result, i);
			result.clear();

		}
		return res;
	}

	public void findNumberToSum(int[] candidates, int target,
			List<List<Integer>> res, List<Integer> result, int i) {

		if (candidates[i] > target) {
			result.add(0);
			return;
		}
		for (int j = i; j < candidates.length; j++) {
			if (candidates[j] == target) {
				result.add(candidates[j]);
				List<Integer> temp = new ArrayList<Integer>();
				Iterator it = result.iterator();
				while (it.hasNext()) {
					temp.add((Integer) it.next());
				}
				res.add(temp);
				break;
			}
			result.add(candidates[j]);
			findNumberToSum(candidates, target - candidates[j], res, result, j);
			if (result.size() == 1) {
				result.remove(result.size() - 1);
			} else {
				result.remove(result.size() - 1);
				result.remove(result.size() - 1);
			}
		}
	}

	public List<List<Integer>> threeSum1(int[] nums, int num, int target,
			List<List<Integer>> res, int index) {
		for (int i = index + 1; i < nums.length - 2; i++) {
			if (i > index + 1 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1, k = nums.length - 1; j < k;) {
				if (nums[i] + nums[j] + nums[k] == target) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(num);
					a.add(nums[i]);
					a.add(nums[j]);
					a.add(nums[k]);
					res.add(a);
					while (j < k && nums[j] == nums[j + 1])
						j++;
					while (j < k && nums[k] == nums[k - 1])
						k--;
					j++;
					k--;

				} else if (nums[i] + nums[j] + nums[k] < target) {
					j++;
				} else {
					k--;
				}

			}
		}
		return res;
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List res = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			threeSum(nums, nums[i], target - nums[i], res, i);
		}
		return res;
	}

	public static List<List<Integer>> threeSum(int[] nums, int num, int target,
			List<List<Integer>> res, int index) {
		for (int i = index + 1; i < nums.length - 2; i++) {
			for (int j = i + 1, k = nums.length - 1; j < k;) {
				if (nums[i] + nums[j] + nums[k] == target) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(num);
					a.add(nums[i]);
					a.add(nums[j]);
					a.add(nums[k]);
					while (j < k && nums[j] == nums[j + 1])
						j++;
					while (j < k && nums[k] == nums[k - 1])
						k--;
					j++;
					k--;

				} else if (nums[i] + nums[j] + nums[k] < target) {
					j++;
				} else {
					k--;
				}

			}
		}
		return res;
	}

	public String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		return say(countAndSay(n - 1));
	}

	private String say(String pre) {
		StringBuilder sb = new StringBuilder();
		char[] arr = pre.toCharArray();
		int count = 0;
		char p = ' ';
		for (char c : arr) {
			if (c == p) {
				count++;
			} else {
				if (count != 0)
					sb.append(count).append(p);
				p = c;
				count = 1;
			}
		}
		sb.append(count).append(p);
		return sb.toString();
	}

	public boolean isValidSudoku1(char[][] board) {
		int[] checkr = new int[10];
		int[] checkc = new int[10];
		for (int i = 0; i < board.length; i++) {
			checkr = new int[10];
			checkc = new int[10];
			for (int j = 0; j < board[0].length; j++) {
				char r = board[i][j];
				char c = board[j][i];
				if (r != '.') {
					if (checkr[r - '0'] != 0)
						return false;
					else
						checkr[r - '0'] = r;
				}
				if (c != '.') {
					if (checkc[c - '0'] != 0)
						return false;
					else
						checkc[c - '0'] = c;
				}
			}
		}
		// System.out.println("second");
		int[] checkb = new int[10];
		for (int k = 0; k < 9; k++) {
			checkb = new int[10];
			for (int i = k / 3 * 3; i < k / 3 * 3 + 3; i++) {
				for (int j = (k % 3) * 3; j < (k % 3) * 3 + 3; j++) {
					char c = board[i][j];
					if (c != '.') {
						if (checkb[c - '0'] != 0)
							return false;
						else
							checkb[c - '0'] = c;
					}
				}
			}
		}
		return true;

	}

	public static boolean isValidSudoku(char[][] board) {
		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.')
					continue;
				if (list.contains(board[i][j]))
					return false;
				list.add(board[i][j]);
			}
			list.clear();
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[j][i] == '.')
					continue;
				if (list.contains(board[j][i]))
					return false;
				list.add(board[j][i]);
			}
			list.clear();
		}
		for (int k = 0; k < 9; k++) {
			for (int i = k / 3 * 3; i < k / 3 * 3 + 3; i++) {
				for (int j = (k % 3) * 3; j < (k % 3) * 3 + 3; j++) {
					if (board[j][i] == '.')
						continue;
					if (list.contains(board[j][i]))
						return false;
					list.add(board[j][i]);
				}

			}
			list.clear();
		}
		return true;
	}

	public static int searchInsert(int[] nums, int target) {
		if (nums == null || nums.length == 0 || target <= nums[0])
			return 0;
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) >> 1;
			if (nums[mid] < target)
				left++;
			else
				right = mid;
		}
		return nums[left] >= target ? left : left + 1;
	}

	public int[] searchRange1(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return new int[] { -1, -1 };
		int left = 0, right = nums.length - 1;

		if (target < nums[left] || target > nums[right])
			return new int[] { -1, -1 };

		int[] res = new int[2];
		while (left < right) {
			int mid = (left + right) >> 1;
			if (nums[mid] < target)
				left++;
			else
				right = mid;
		}
		if (nums[left] != target)
			return new int[] { -1, -1 };
		res[0] = left;
		right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) >> 1;
			if (nums[mid] <= target)
				left++;
			else
				right = mid;
		}
		res[1] = nums[left] == target ? left : left - 1;
		return res;

	}

	public static int[] searchRange(int[] nums, int target) {
		int begin = 0;
		int end = 0;
		boolean beginflag = true;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target && beginflag) {
				begin = i;
				end = i;
				beginflag = false;
			} else if (nums[i] == target) {
				end = i;
			}
		}
		if (beginflag) {
			return new int[] { -1, -1 };
		} else {
			return new int[] { begin, end };
		}

	}

	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;
		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (target == nums[m])
				return m;
			if (nums[m] < nums[r]) {
				if (target > nums[m] && target <= nums[r])
					l = m + 1;
				else
					r = m - 1;
			} else {
				if (target >= nums[l] && target < nums[m])
					r = m - 1;
				else
					l = m + 1;
			}
		}
		return -1;
	}

	public static int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		// for ASCII char sequence, use this as a hashmap
		int[] idx = new int[129];
		// initaize arr to -1 inorder to correct calculate interval
		for (int i = 32; i < idx.length; i++) {
			idx[i] = -1;
		}
		int max = 0, m = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = (int) (s.charAt(i));
			m = Math.max(idx[ascii] + 1, m);
			idx[ascii] = i;
			max = Math.max(max, i - m + 1);
		}
		return max;
	}

	public static int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3)
			return 0;
		Arrays.sort(nums);
		int closest = nums[0] + nums[1] + nums[nums.length - 1];
		int distance = Math.abs(closest - target);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int temp = find(nums, i + 1, nums.length - 1, nums[i], target);
			System.out.println(temp);
			if (Math.abs(temp - target) < distance) {
				closest = temp;
				distance = Math.abs(temp - target);
			}
		}

		return closest;

	}

	public static int find(int[] nums, int begin, int end, int num, int target) {
		int l = begin, r = end;
		int closest = nums[l] + nums[r] + num;
		int result = nums[l] + nums[r] + num;
		int distance = Math.abs(nums[l] + nums[r] + num - target);
		while (l < r) {
			if (r == 6)
				System.out.println(r);
			result = nums[l] + nums[r] + num;
			if (Math.abs(result - target) < distance) {
				closest = result;
				distance = Math.abs(result - target);
			}
			if (result > target) {
				r--;
			} else if (result < target) {
				l++;
			} else {
				return target;
			}
		}
		return closest;
	}

	public static List<String> letterCombinations(String digits) {
		String[] digtochar = { "abc", "def", "ghi", "jkl", "mno", "pqrs",
				"tuv", "wxyz" };
		List<String> result = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		for (char a : digits.toCharArray()) {
			if (a == '1')
				continue;
			if (result.isEmpty()) {
				for (char b : digtochar[(int) a - 50].toCharArray()) {
					result.add(String.valueOf(b));
				}
			} else {
				for (char b : digtochar[(int) a - 50].toCharArray()) {
					Iterator<String> re = result.iterator();
					while (re.hasNext()) {
						temp.add(re.next() + String.valueOf(b));
					}
				}
				result = temp;
				temp = new ArrayList<String>();
			}
		}
		return result;

	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		List<ListNode> listnode = new ArrayList<ListNode>();
		while (head.next != null) {
			listnode.add(head);
			head = head.next;
		}
		listnode.add(head);
		ListNode res = null;
		for (int i = listnode.size() - 1, j = 1; i >= 0; i--, j++) {
			if (j == n)
				continue;
			listnode.get(i).next = res;
			res = listnode.get(i);

		}
		return res;

	}

	public ListNode removeNthFromEnd1(ListNode head, int n) {
		if (head == null || head.next == null)
			return null;
		ListNode fakeHead = new ListNode(0);// 防止n和head的长度相等时，出现空指针
		fakeHead.next = head;
		ListNode fNode = fakeHead, sNode = fakeHead;
		for (int i = 0; i <= n; i++)
			fNode = fNode.next;
		while (fNode != null) {
			fNode = fNode.next;
			sNode = sNode.next;
		}
		sNode.next = sNode.next.next;
		return fakeHead.next;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(0);
		ListNode resTemp = res;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				resTemp.next = l1;
				l1 = l1.next;
			} else {
				resTemp.next = l2;
				l2 = l2.next;
			}
			resTemp = resTemp.next;
		}
		if (l1 == null)
			resTemp.next = l2;
		else
			resTemp.next = l1;
		return res.next;

	}

	public static List<String> generateParenthesis(int n) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		List<String> finalresult = new ArrayList<String>();
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		for (int i = 0; i < (2 * n); i++) {
			if (result.isEmpty()) {
				result.put("(", 1);
			} else {
				for (Map.Entry<String, Integer> entry : result.entrySet()) {
					if (entry.getValue() == (2 * n - entry.getKey().length())) {
						String s = "";
						for (int j = 0; j < entry.getValue(); j++)
							s += ")";
						finalresult.add(entry.getKey() + s);
					} else {
						if (entry.getValue() == 0)
							temp.put(entry.getKey() + "(", 1);
						else {
							temp.put(entry.getKey() + "(", entry.getValue() + 1);
							temp.put(entry.getKey() + ")", entry.getValue() - 1);

						}

					}

				}
				result = temp;
				temp = new HashMap<String, Integer>();
			}
		}
		return finalresult;
	}

	public static List<String> generateParenthesis1(int n) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		dfs(n, n, sb, res);
		return res;
	}

	private static void dfs(int left, int right, StringBuilder sb,
			List<String> list) {
		// base case
		if (left == 0 && right == 0) {
			list.add(String.valueOf(sb));
			return;
		}

		if (left > 0) {
			sb.append("(");
			dfs(left - 1, right, sb, list);
			sb.deleteCharAt(sb.length() - 1);
		}
		if (left < right) {
			sb.append(")");
			dfs(left, right - 1, sb, list);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public ListNode swapPairs(ListNode head) {
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		swap(dummyHead);
		return dummyHead.next;
	}

	public void swap(ListNode head) {
		while (head.next != null && head.next.next != null) {
			ListNode temp = head.next;
			head.next = head.next.next;
			temp.next = head.next.next;
			head.next.next = temp;
			head = head.next.next;
		}
	}

	public static int removeDuplicates(int[] nums) {
		if (nums == null)
			return 0;
		int length = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[length])
				nums[++length] = nums[i];
		}
		return ++length;
	}

	public int removeElement(int[] nums, int val) {
		// if(nums==null)
		// return 0;
		int length = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == val)
				continue;
			nums[length++] = nums[i];
		}
		return length;
	}

	public int strStr(String haystack, String needle) {
		return haystack.indexOf(needle);
	}

	public static void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 1)
			return;
		int max = Integer.MIN_VALUE;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] >= nums[i + 1] && nums[i] >= max) {
				max = nums[i];
				int temp = nums[i];
				nums[i] = nums[i + 1];
				nums[i + 1] = temp;
			} else {
				int target;
				for (int j = i + 1; j < nums.length; j++) {
					for (int k = i + 1; k < (nums.length + i - j); k++) {
						if (nums[k] >= nums[k + 1]) {
							int temp = nums[k];
							nums[k] = nums[k + 1];
							nums[k + 1] = temp;
						}
					}
				}
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[i] < nums[j]) {
						int temp = nums[i];
						nums[i] = nums[j];
						nums[j] = temp;
						return;
					}
				}
			}

		}
		Arrays.sort(nums);
	}

	public void nextPermutation1(int[] nums) {
		int k = -1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				k = i;
				break;
			}
		}

		if (k == -1) {
			reverse(nums, 0, nums.length - 1);
			return;
		}

		int l = k + 1;
		for (int i = nums.length - 1; i > k; i--) {
			if (nums[i] > nums[k]) {
				l = i;
				break;
			}
		}

		int temp = nums[l];
		nums[l] = nums[k];
		nums[k] = temp;

		reverse(nums, k + 1, nums.length - 1);
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

}
class IntervalComparator implements Comparator<Interval> {  
    public int compare(Interval i1, Interval i2) {  
        return i1.start - i2.start;  
    }  
}
class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

class ListNode {
	int val;
	public ListNode next;

	ListNode(int val) {
		this.val = val;
	}
}
class TrieNode {  
    public TrieNode[] children = new TrieNode[26];  
    public String item = "";  
      
    // Initialize your data structure here.  
    public TrieNode() {  
    }  
}  
  
class Trie {  
    private TrieNode root;  
  
    public Trie() {  
        root = new TrieNode();  
    }  
  
    // Inserts a word into the trie.  
    public void insert(String word) {  
        TrieNode node = root;  
        for (char c : word.toCharArray()) {  
            if (node.children[c - 'a'] == null) {  
                node.children[c - 'a'] = new TrieNode();  
            }  
            node = node.children[c - 'a'];  
        }  
        node.item = word;  
    }  
  
    // Returns if the word is in the trie.  
    public boolean search(String word) {  
        TrieNode node = root;  
        for (char c : word.toCharArray()) {  
            if (node.children[c - 'a'] == null) return false;  
            node = node.children[c - 'a'];  
        }  
        return node.item.equals(word);  
    }  
  
    // Returns if there is any word in the trie  
    // that starts with the given prefix.  
    public boolean startsWith(String prefix) {  
        TrieNode node = root;  
        for (char c : prefix.toCharArray()) {  
            if (node.children[c - 'a'] == null) return false;  
            node = node.children[c - 'a'];  
        }  
        return true;  
    }  
}  