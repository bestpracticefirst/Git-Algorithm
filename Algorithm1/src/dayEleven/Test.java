package dayEleven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		List<String>b=new ArrayList<>();
		b.add("hot");
		b.add("dot");
		b.add("dog");
		b.add("lot");
		b.add("log");
		b.add("cog");
		a.ladderLength1("hit", "cog", b);
	}
	   public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
	        if (wordList == null || wordList.size() == 0) return 0;
	        Set<String> dic = new HashSet<>(wordList);
	        if (!dic.contains(endWord)) return 0;
	        if (beginWord.equals(endWord)) return 1;
	        Set<String> q1 = new HashSet<>(), q2 = new HashSet<>();
	        q1.add(beginWord);
	        dic.remove(beginWord);
	        q2.add(endWord);
	        dic.remove(endWord);
	        return twoEndBFS(q1, q2, dic, 2);
	    }

	    private int twoEndBFS(Set<String> q1, Set<String> q2, Set<String> dic, int len) {
	        if (q1.isEmpty() || q2.isEmpty()) return 0;
	        if (q1.size() > q2.size()) return twoEndBFS(q2, q1, dic, len);

	        Set<String> temp = new HashSet<>();
	        for (String word : q1) {
	            char[] chArr = word.toCharArray();
	            for (int i = 0; i < chArr.length; ++i) {
	                char c = chArr[i];
	                for (char newC = 'a'; newC <= 'z'; ++newC) {
	                    chArr[i] = newC;
	                    String next = new String(chArr);
	                    if (q2.contains(next)) return len;
	                    if (dic.contains(next)) {
	                        temp.add(next);
	                        dic.remove(next);
	                    }
	                }
	                chArr[i] = c;
	            }
	        }
	        return twoEndBFS(temp, q2, dic, len + 1);
	    }
	public int maxProfit1(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int len = prices.length;
		int maxProfit = 0;
		int min = prices[0];
		int arrayA[] = new int[len];

		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			arrayA[i] = Math.max(arrayA[i - 1], prices[i] - min);
		}

		int max = prices[len - 1];
		int arrayB[] = new int[len];
		for (int i = len - 2; i >= 0; i--) {
			max = Math.max(prices[i], max);
			arrayB[i] = Math.max(max - prices[i], arrayB[i + 1]);
		}

		for (int i = 0; i < len; i++) {
			maxProfit = Math.max(maxProfit, arrayA[i] + arrayB[i]);
		}

		return maxProfit;
	}

	public int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int buy2 = Integer.MIN_VALUE, buy1 = Integer.MIN_VALUE;
		int sell2 = 0, sell1 = 0;
		for (int p : prices) {
			sell2 = Math.max(sell2, buy2 + p);
			buy2 = Math.max(buy2, sell1 - p);
			sell1 = Math.max(sell1, buy1 + p);
			buy1 = Math.max(buy1, -p);
		}
		return sell2;
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		 if(beginWord==null || endWord==null || beginWord.length()==0 || endWord.length()==0 || beginWord.length()!=endWord.length())  
		        return 0; 
		        
		        LinkedList<String> wordQueue = new LinkedList<String>();
		        int level = 1; //the start string already count for 1
		        int curnum = 1;//the candidate num on current level
		        int nextnum = 0;//counter for next level
		        
		        wordQueue.add(beginWord);
		        
		        while(!wordQueue.isEmpty()){
		            String word = wordQueue.poll();
		            curnum--;
		            
		            for(int i = 0; i < word.length(); i++){
		                char[] wordunit = word.toCharArray();
		                for(char j = 'a'; j <= 'z'; j++){
		                    wordunit[i] = j;
		                    String temp = new String(wordunit);  
		                    

		                    if(wordList.contains(temp)){
			                    if(temp.equals(endWord))
			                        return level+1;
		                        wordQueue.add(temp);
		                        nextnum++;
		                        wordList.remove(temp);
		                    }
		                }
		            }
		            
		            if(curnum == 0){
		                curnum = nextnum;
		                nextnum = 0;
		                level++;
		            }
		        }
		        
		        return 0;
	}
}
