package dayEleven;

import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		int[] b = { 2, 4, 3, 7 };
		a.maxProfit(b);
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
		if (wordList.size() == 0)
			return 0;

		LinkedList<String> wordQueue = new LinkedList<String>();
		LinkedList<Integer> distanceQueue = new LinkedList<Integer>();

		wordQueue.add(beginWord);
		distanceQueue.add(1);

		while (!wordQueue.isEmpty()) {
			String currWord = wordQueue.pop();
			Integer currDistance = distanceQueue.pop();
			if (currWord.equals(endWord)) {
				return currDistance;
			}
			for (int i = 0; i < currWord.length(); i++) {
				char[] currCharArr = currWord.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					currCharArr[i] = c;

					String newWord = new String(currCharArr);
					if (wordList.contains(newWord)) {
						wordQueue.add(newWord);
						distanceQueue.add(currDistance + 1);
						wordList.remove(newWord);
					}
				}
			}
		}
		return 0;
	}
}
