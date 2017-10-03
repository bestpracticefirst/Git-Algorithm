package dayFifteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a = new Test();
		String b = "leetcode";
		String c = "leet";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leetcode");
		wordDict.add("leet");
		wordDict.add("code");
		a.wordBreak2("leetcode", wordDict);

	}
	public List<String> wordBreak2(String s, List<String> wordDict) {
	    return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
	  }

	  List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {

	    if(map.containsKey(s))
	      return map.get(s);

	    LinkedList<String> res = new LinkedList<>();
	    if(s.length() == 0) {
	      res.add("");
	      return res;
	    }

	    for(String word : wordDict) {
	      if(s.startsWith(word)) {
	        List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
	        for(String each : sublist) {
	          res.add(word+(each.isEmpty() ? "":" ")+each);
	        }
	      }
	    }
//	    map.put(s,res);
	    return res;
	  }
	public List<String> wordBreak1(String s, List<String> wordDict) {

		List<String> res = new ArrayList<String>();
		if(wordDict==null||wordDict.size()==0)
			return res;
		HashMap<String, List<String>> map = new HashMap<>();
		boolean[] seperated = new boolean[s.length() + 1];
		seperated[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (wordDict.contains(s.substring(j, i)) && seperated[j]) {
					seperated[i] = true;
					if (!map.containsKey(s.substring(0, i))) {
						ArrayList<String> item = new ArrayList<>();
						item.add(s.substring(j, i));
						map.put(s.substring(0, i), item);
					} else {
						String c=s.substring(j,i);
						map.get(s.substring(0, i)).add(c);
					}

				}
			}
		}
		if (seperated[s.length()] == true) {
			buildSolution(res, map, s, "");
		}
		return res;
	}

	public void buildSolution(List<String> res, HashMap<String, List<String>> map, String s, String temp) {
		for (String a : map.get(s)) {
			if (a.equals(s)) {
				temp = a + " " + temp;
				temp = temp.trim();
				res.add(temp);
			} else {
				String temp2 = temp;
				temp = a + " " + temp;
				buildSolution(res, map, s.substring(0,s.lastIndexOf(a)), temp);
				temp = temp2;
			}
		}
	}

	public boolean wordBreak(String s, List<String> wordDict) {
		boolean[] seperated = new boolean[s.length() + 1];
		seperated[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = i; j >= 0; j--) {
				if (wordDict.contains(s.substring(j, i)) && seperated[j]) {
					seperated[i] = true;
					break;
				}
			}
		}
		return seperated[s.length()];
	}

	public RandomListNode copyRandomList1(RandomListNode head) {
		if (head == null)
			return head;
		RandomListNode node = head;
		while (node != null) {
			RandomListNode newNode = new RandomListNode(node.label);
			newNode.next = node.next;
			node.next = newNode;
			node = newNode.next;
		}
		node = head;
		while (node != null) {
			if (node.random != null)
				node.next.random = node.random.next;
			node = node.next.next;
		}
		RandomListNode newHead = head.next;
		node = head;
		while (node != null) {
			RandomListNode newNode = node.next;
			node.next = newNode.next;
			if (newNode.next != null)
				newNode.next = newNode.next.next;
			node = node.next;
		}
		return newHead;
	}

	public RandomListNode copyRandomList(RandomListNode head) {
		HashMap<RandomListNode, RandomListNode> list = new HashMap<>();
		if (head == null)
			return null;
		RandomListNode newHead = new RandomListNode(head.label);
		list.put(head, newHead);
		RandomListNode pre = newHead;
		RandomListNode node = head.next;
		while (node != null) {
			RandomListNode newNode = new RandomListNode(node.label);
			list.put(node, newNode);
			pre.next = newNode;
			pre = newNode;
			node = node.next;
		}
		node = head;
		RandomListNode pre1 = newHead;
		while (node != null) {
			pre1.random = list.get(node.random);
			pre1 = pre1.next;
			node = node.next;
		}
		return newHead;
	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0)
			return paths;
		Set<String> dic = new HashSet<>(wordList);
		if (!dic.contains(endWord))
			return paths;
		if (beginWord.equals(endWord)) {
			List<String> path = new ArrayList<String>();
			path.add(endWord);
			paths.add(path);
			return paths;
		}
		Set<String> q1 = new HashSet<>(), q2 = new HashSet<>();
		Map<String, List<String>> prev = new HashMap<>();
		q1.add(beginWord);
		dic.remove(beginWord);
		q2.add(endWord);
		dic.remove(endWord);
		boolean isL2R = true, found = false;
		while (!q1.isEmpty() && !q2.isEmpty()) {
			if (q1.size() > q2.size()) {
				Set<String> temp = q1;
				q1 = q2;
				q2 = temp;
				isL2R = !isL2R;
			}
			Set<String> next = new HashSet<>();
			for (String word : q1) {
				char[] chArr = word.toCharArray();
				for (int i = 0; i < chArr.length; ++i) {
					char c = chArr[i];
					for (char ch = 'a'; ch <= 'z'; ++ch) {
						if (ch == c)
							continue;
						chArr[i] = ch;
						String newWord = new String(chArr);
						if (q2.contains(newWord)) {
							found = true;
							if (isL2R) {
								record(word, newWord, prev);
							} else {
								record(newWord, word, prev);
							}
						} else if (dic.contains(newWord)) {
							next.add(newWord);
							if (isL2R) {
								record(word, newWord, prev);
							} else {
								record(newWord, word, prev);
							}
						}
					}
					chArr[i] = c;
				}
			}
			if (found)
				break;
			q1 = next;
			dic.removeAll(next);
		}

		List<String> path = new LinkedList<String>();
		path.add(endWord);
		buildPaths(prev, path, endWord, beginWord, paths);
		return paths;
	}

	private void buildPaths(Map<String, List<String>> prev, List<String> path, String word, String target,
			List<List<String>> paths) {
		if (word.equals(target)) {
			paths.add(new ArrayList<String>(path));
			return;
		}
		if (!prev.containsKey(word))
			return;
		for (String preWord : prev.get(word)) {
			path.add(0, preWord);
			buildPaths(prev, path, preWord, target, paths);
			path.remove(0);
		}
	}

	private void record(String a, String b, Map<String, List<String>> prev) {
		if (!prev.containsKey(b)) {
			prev.put(b, new ArrayList<String>());
		}
		prev.get(b).add(a);
	}
}

class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
}