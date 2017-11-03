package day44;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test aTest=new Test();
		aTest.firstUniqChar("loveleetcode");
	}
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 97] == 1) {
                return i;
            }
        }
        return -1;
    }
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] count1 = new int[26];
		int[] count2 = new int[26];
		for(char a:ransomNote.toCharArray())
		{
			count1[a-'a']++;
		}
		for(char a:magazine.toCharArray())
		{
			count2[a-'a']++;
		}
		for(int i=0;i<26;i++)
		{
			if(count1[i]>count2[i])
				return false;
		}
		return true;
	}

}

class RandomizedSet {
	Set<Integer> set = new HashSet<>();

	/** Initialize your data structure here. */
	public RandomizedSet() {
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val) {
		boolean flag = set.contains(val);
		set.add(val);
		return !flag;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val) {
		boolean flag = set.contains(val);
		if (flag)
			set.remove(val);
		return flag;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		int size = set.size();
		Integer[] temp = new Integer[size];
		set.toArray(temp);
		Random random = new Random();
		int index = random.nextInt(size);
		return temp[index];
	}
}

class RandomizedCollection {
	HashMap<Integer, HashSet<Integer>> map;
	List<Integer> list;
	Random random;

	/** Initialize your data structure here. */
	public RandomizedCollection() {
		map = new HashMap<>();
		list = new ArrayList<>();
		random = new Random();
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val) {
		list.add(val);
		if (map.containsKey(val)) {
			map.get(val).add(list.size() - 1);
			return true;
		} else {
			map.put(val, new HashSet<Integer>());
			map.get(val).add(list.size() - 1);
			return false;
		}
	}

	/**
	 * Removes a value from the collection. Returns true if the collection contained
	 * the specified element.
	 */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		} else {
			int loc = map.get(val).iterator().next();
			map.get(val).remove(loc);
			if (loc < list.size() - 1) {
				int lastone = list.get(list.size() - 1);
				list.set(loc, lastone);
				map.get(lastone).remove(list.size() - 1);
				map.get(lastone).add(loc);
			}
			list.remove(list.size() - 1);
			if (map.get(val).isEmpty())
				map.remove(val);
			return true;
		}
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return list.get(random.nextInt(list.size()));
	}

}

class Solution {
	ListNode dummyhead;
	int count = 0;
	Random random;

	/**
	 * @param head
	 *            The linked list's head. Note that the head is guaranteed to be not
	 *            null, so it contains at least one node.
	 */
	public Solution(ListNode head) {
		random = new Random();
		dummyhead = head;
		while (head != null) {
			count++;
			head = head.next;
		}

	}

	/** Returns a random node's value. */
	public int getRandom() {
		int index = random.nextInt(count) + 1;
		ListNode temp = dummyhead;
		while (index > 1) {
			temp = temp.next;
			index--;
		}
		return temp.val;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
