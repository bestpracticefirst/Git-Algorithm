package day23;
import java.util.*;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		int[] b= {1,2,3,5};
		a.rob(b);
	}
    public int rob(int[] nums) {
        if(nums.length==0||nums==null)
        	return 0;
        if(nums.length==1)
        	return nums[0];
        int[] dp1=new int[nums.length-1];
        int[] dp2=new int[nums.length-1];
        dp1[0]=nums[0];
        dp2[0]=nums[1];
        for(int i=1;i<nums.length-1;i++)
        {
        	if(i==1)
        	{
        		dp1[i]=Math.max(nums[0], nums[1]);
        		dp2[i]=Math.max(nums[1], nums[2]);
        		continue;
        	}
        	dp1[i]=Math.max(dp1[i-1], nums[i]+dp1[i-2]);
        	dp2[i]=Math.max(dp2[i-1], nums[i+1]+dp2[i-2]);
        }
        return Math.max(dp1[nums.length-2], dp2[nums.length-2]);
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Set> posts=new ArrayList<>();
        int[] order=new int[numCourses];
        for(int i=0;i<numCourses;i++)
        {
        	posts.add(new HashSet<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++)
        {
        	posts.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        int[] preNums=new int[numCourses];
        for(int i=0;i<numCourses;i++)
        {
        	Iterator<Integer>it=posts.get(i).iterator();
        	while(it.hasNext())
        	{
        		preNums[it.next()]++;
        	}
        }
        for(int i=0;i<numCourses;i++)
        {
        	int j=0;
        	for(;j<numCourses;j++)
        	{
        		if(preNums[j]==0)
        			break;
        	}
        	if(j==numCourses)
        		return new int[0];
        	preNums[j]=-1;
        	order[i]=j;
        	Iterator<Integer>it=posts.get(j).iterator();
        	while(it.hasNext())
        	{
        		preNums[it.next()]--;
        	}
        }
        return order;
    }
    public int minSubArrayLen1(int s, int[] nums) {
        if(nums.length==0) return 0;
        int left=0;
        int right=0;
        int sum=0;
        int minlen=Integer.MAX_VALUE;
        for(left=0;left<nums.length;left++){
            while(right<nums.length&&sum<s){
                sum+=nums[right];
                right++;
            }
            if(sum>=s) minlen=Math.min(minlen,right-left);
            sum-=nums[left];
        }
        return minlen!=Integer.MAX_VALUE?minlen:0;
    }
    public int minSubArrayLen(int s, int[] nums) {
        if(nums==null&&nums.length==0)
        	return 0;
    	int count=0;
        int end=-1;
        int start=0;
        int sum=0;
        int minLength=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++)
        {
        	sum+=nums[i];
        	if(sum>=s)
        	{
        		end=i;
        		int tempSum=sum;
        		for(int j=start;j<=end;j++)
        		{
        			tempSum-=nums[j];
        			if(tempSum>=s)
        			{
        				sum=tempSum;
        				start=j+1;
        			}
        		}
        		if(minLength==(end-start+1))
        			count++;
        		if(minLength>(end-start+1))
        		{
        			count=1;
        			minLength=end-start+1;
        		}
        	}			
        }
        if(count>0)
        	return minLength;
        return 0;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Set> posts= new ArrayList<>();
        for(int i=0;i<numCourses;i++)
        {
        	posts.add(new HashSet<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++)
        {
        	posts.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        int []preNums=new int[numCourses];
        for(int i=0;i<numCourses;i++)
        {
        	Set set=posts.get(i);
        	Iterator<Integer>it =set.iterator();
        	while(it.hasNext())
        		preNums[it.next()]++;
        }
        for(int i=0;i<numCourses;i++)
        {
        	int j=0;
        	for(;j<numCourses;j++)
        	{
        		if(preNums[j]==0)
        			break;
        	}
        	if(j==numCourses)
        		return false;
        	preNums[j]=1;
        	Set set=posts.get(j);
        	Iterator<Integer> it=set.iterator();
        	while(it.hasNext())
        	{
        		preNums[it.next()]--;
        	}
        }
        return true;
    }
}
class TrieNode {  
    // Initialize your data structure here.  
    char c;  
    boolean leaf;  
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();  
      
    public TrieNode(char c) {  
        this.c = c;  
    }  
          
    public TrieNode(){};  
} 
class Trie {
	private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
    	root=new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
    	HashMap<Character, TrieNode> children=root.children;
    	for(int i=0;i<word.length();i++)
    	{
    		char c=word.charAt(i);
    		TrieNode t;
    		if(children.get(c)!=null)
    		{
    			t=children.get(c);
    		}
    		else
    		{
    			t=new TrieNode(c);
    			children.put(c, t);
    		}
    		children=t.children;
    		if(i==word.length()-1)
    			t.leaf=true;
    	}
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	TrieNode t=searchTrie(word);
        return t!=null&&t.leaf;
    }
    public TrieNode searchTrie(String word) {
    	Map<Character, TrieNode> children = root.children;  
        TrieNode t = null;  
        for(int i=0;i<word.length();i++)
        {
        	char c=word.charAt(i);
    		if(!children.containsKey(c))
    		{
    			return null;
    		}
    		t=children.get(c);
    		children=t.children;
        }
        return t;
    }
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchTrie(prefix)!=null;
    }
}
