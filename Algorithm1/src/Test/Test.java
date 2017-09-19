package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []a= {1,1,1,1,3,3};
		Test test=new Test();
		System.out.println(test.removeDuplicates(a));
	}
    public int removeDuplicates(int[] nums) {
    	if(nums==null||nums.length<3)
    		return nums.length;
        int length=0;
        int repeat=1;
        for(int i=1;i<nums.length;i++)
        {
        	if(nums[i]==nums[length]&&repeat<2)
        	{
        		nums[++length]=nums[i];
        		repeat++;
        	}
        	else if(nums[i]!=nums[length])
        	{
        		nums[++length]=nums[i];
        		repeat=1;
        	}

        }
    	return ++length;
    }

	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
			if (prefix.equals(""))
				return "";

		}
		return prefix;

	}

	public String longestCommonPrefix1(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";

		String prefix = strs[0];

		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
			if (prefix.equals(""))
				break;
		}

		return prefix;
	}

	public static int[] twoSum(int[] nums, int target) {
		// int i,j;
		// int n = nums.length;
		// int number=0;
		// for(i = 1;i < n; i++)
		// {
		// j = i;
		// target = nums[i];
		// while(j>0 && number<nums[j-1])
		// {
		// nums[j] = nums[j-1];
		// j--;
		// }
		// nums[j] = target;
		// }
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] == target - nums[i]) {
					return new int[] { i, j };
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.nextNode = new ListNode(sum % 10);
			curr = curr.nextNode;
			if (p != null)
				p = p.nextNode;
			if (q != null)
				q = q.nextNode;
		}
		if (carry > 0) {
			curr.nextNode = new ListNode(carry);
		}
		return dummyHead.nextNode;

	}

	private static ListNode buildListNode(int[] input) {
		ListNode first = null, last = null, newNode;
		int num;
		if (input.length > 0) {
			for (int i = 0; i < input.length; i++) {
				newNode = new ListNode(input[i]);
				newNode.nextNode = null;
				if (first == null) {
					first = newNode;
					last = newNode;
				} else {
					last.nextNode = newNode;
					last = newNode;
				}

			}
		}
		return first;
	}

	private static ListNode removeElements(ListNode head, int val) {
		if (head == null) {
			return null;
		}
		ListNode p = head, q = head.nextNode;
		while (q != null) {
			if (q.val == val) {
				p.nextNode = q.nextNode;
				q = q.nextNode;
			} else {
				p = p.nextNode;
				q = q.nextNode;
			}
		}
		if (head.val == val) {
			return head.nextNode;
		}
		return head;

	}

	public static int lengthOfLongestSubstring(String s) {
		// int length = 0;
		// char []charArray = s.toCharArray();
		// int currentLength = 0;
		// String currentSubString = "";
		// for(char a: charArray)
		// {
		// if(!currentSubString.contains(String.valueOf(a)))
		// {
		// currentSubString += String.valueOf(a);
		// }
		// else
		// {
		// currentLength = currentSubString.length();
		// if(length < currentLength)
		// {
		// length = currentLength;
		// }
		// currentSubString =
		// currentSubString.substring(currentSubString.lastIndexOf(String.valueOf(a))+1)+String.valueOf(a);
		// }
		// }
		// currentLength = currentSubString.length();
		// if(length < currentLength)
		// {
		// length = currentLength;
		// }
		// return length;
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int number1 = 0;
		int number2 = 0;
		int sortedNumber = 0;
		int[] sortedArray = new int[(nums1.length + nums2.length) / 2 + 1];
		while (true) {
			if (number1 + number2 == sortedArray.length) {
				if ((nums1.length + nums2.length) % 2 == 0) {
					return (double) (sortedArray[sortedArray.length - 1] + sortedArray[sortedArray.length - 2]) / 2;
				} else {
					return (double) (sortedArray[sortedArray.length - 1]);
				}
			} else {
				if (number1 < nums1.length) {
					if (number2 < nums2.length) {
						if (nums1[number1] < nums2[number2]) {
							sortedArray[sortedNumber] = nums1[number1];
							number1++;
							sortedNumber++;
						} else {
							sortedArray[sortedNumber] = nums2[number2];
							number2++;
							sortedNumber++;
						}
					} else {
						sortedArray[sortedNumber] = nums1[number1];
						number1++;
						sortedNumber++;
					}

				} else {
					sortedArray[sortedNumber] = nums2[number2];
					number2++;
					sortedNumber++;
				}
			}
		}

	}

	public static double findMedianSortedArraysResult(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		if (m > n) { // to ensure m<=n
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			if (i < iMax && B[j - 1] > A[i]) {
				iMin = iMin + 1; // i is too small
			} else if (i > iMin && A[i - 1] > B[j]) {
				iMax = iMax - 1; // i is too big
			} else { // i is perfect
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = B[j - 1];
				} else if (j == 0) {
					maxLeft = A[i - 1];
				} else {
					maxLeft = Math.max(A[i - 1], B[j - 1]);
				}
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}

				int minRight = 0;
				if (i == m) {
					minRight = B[j];
				} else if (j == n) {
					minRight = A[i];
				} else {
					minRight = Math.min(B[j], A[i]);
				}

				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}

	public static String longestPalindrome(String s) {
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private static int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}

	public static int expandedFromCenter(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}

	public static String findLongest(String s) {
		int start = 0;
		int end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandedFromCenter(s, i, i);
			int len2 = expandedFromCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	public static String findLongest1(String s) {
		String result = "";
		String s1 = new StringBuilder(s).reverse().toString();
		for (int i = 0; i < s.length(); i++) {
			int k = 1;
			for (int j = s1.length() - i - 1; j >= 0; j--) {
				String s2 = s.substring(i, i + k);
				String s3 = s1.substring(j, s1.length() - i);
				if (s2.equals(s3) && s.substring(i, i + k).length() > result.length()) {
					result = s.substring(i, i + k);
				}
				k++;
			}
		}
		return result;

	}

	public static int reverse(int x) {
		boolean flag = true;
		if (x == Integer.MIN_VALUE)
			return 0;
		if (x < 0) {
			flag = false;
			x = Math.abs(x);
		}
		String s = String.valueOf(x);
		s = new StringBuilder(s).reverse().toString();
		if (!flag) {
			s = "-" + s;
		}
		if (Double.valueOf(s) > 2147483647 || Double.valueOf(s) < -2147483648) {
			return 0;
		} else {
			return Integer.parseInt(s);
		}
	}

	public int reverse1(int x) {
		boolean negative = false;
		if (x == Integer.MIN_VALUE)
			return 0;
		// case overflow
		int temp = 0;
		if (x < 0) {
			temp = -x;
			negative = true;
		} else
			temp = x;
		long result = 0;
		while (temp > 0) {
			result = result * 10 + (temp % 10);
			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
				return 0;
			// in case overflow shall be another way
			temp = temp / 10;
		}
		// in what case is overflow?
		result = negative ? -result : result;
		return (int) result;

	}

	public int reverse2(int x) {
		long res = 0;
		while (x != 0) {
			int b = x % 10;
			x = x / 10;
			res = res * 10 + b;
			if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
				return 0;
			}
		}
		return (int) res;
	}

	public static String convert(String s, int nRows) {
		if (nRows == 1)
			return s;
		int len = s.length(), k = 0, interval = nRows * 2 - 2;
		String res = "";
		for (int j = 0; j < len; j += interval)// 处理第一行
			res += s.charAt(j);
		for (int i = 1; i < nRows - 1; i++)// 处理中间行
		{
			int inter = i * 2;
			for (int j = i; j < len; j += inter) {
				res += s.charAt(j);
				inter = interval - inter;
			}
		}
		for (int j = nRows - 1; j < len; j += interval)// 处理最后一行
			res += s.charAt(j);
		return res;
	}

	public static String convert1(String s, int numRows) {
		/**
		 * 0 12 1 1113 2 10 14 3 9 15 4 8 16 5 7 17 6 18
		 *
		 * 对于有n行的, 每一行 r , 两类: 类a:首项 r 公差 2n-2 类b:首项 2n - r - 2 公差 2n-2
		 */
		if (numRows == 1)
			return s;

		StringBuilder stringBuilder = new StringBuilder();
		int offset = 2 * numRows - 2;
		int length = s.length();
		for (int n = 0; n < numRows; n++) {
			for (int colPair = 0;; colPair++) {

				int indexEven = n + colPair * offset;
				if (indexEven >= length) {
					break;
				} else {
					stringBuilder.append(s.charAt(indexEven));
				}

				if (n != 0 && n != numRows - 1) {
					int indexOdd = offset - n + colPair * offset;
					if (indexOdd >= length) {
						break;
					} else {
						stringBuilder.append(s.charAt(indexOdd));
					}
				}

			}
		}

		return stringBuilder.toString();
	}

	public static int myAtoi(String str) {
		if (str == "")
			return 0;
		char[] Array = str.toCharArray();
		int i = 0;
		long res = 0;
		boolean flag = true;
		boolean begin = true;
		for (char a : Array) {
			if (a == '-' && i == 0) {
				flag = false;
				begin = false;
				i++;
				continue;
			}
			if (a == '+' && i == 0) {
				flag = true;
				begin = false;
				i++;
				continue;
			}
			if (begin && (a == ' ' || a == '0')) {
				continue;
			}
			if ((int) a >= 48 && (int) a <= 57) {
				begin = false;
				res = res * 10 + (int) a - 48;
				;
			} else {
				break;
			}
			if (flag == true) {
				if (res >= Integer.MAX_VALUE)
					return Integer.MAX_VALUE;
			} else {
				if (-res <= Integer.MIN_VALUE)
					return Integer.MIN_VALUE;
			}
		}
		if (flag == true) {
			return (int) res;
		} else {
			return (int) -res;
		}

	}

	public static int myAtoi1(String str) {
		boolean isNegative = false;
		int length = str.length();

		long res = 0;
		int i = 0;

		for (; i < length; i++) {
			if (str.charAt(i) != ' ') {
				break;
			}
		}

		if (i < length) {
			switch (str.charAt(i)) {
			case '-':
				isNegative = true;
			case '+':
				i++;
			}
		}

		loop: for (; i < length; i++) {
			int number = str.charAt(i) - 48;
			if (number >= 0 && number <= 9) {
				res = res * 10 + number;
				if (isNegative) {
					if (-res > Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					} else if (-res < Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					}
				} else {
					if (res > Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					} else if (res < Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					}
				}
			} else {
				break loop;
			}
		}

		if (isNegative) {
			res = -res;
		}

		return (int) res;
	}

	public static int romanToInt(String s) {
		String[][] Roman = { { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, { "M", "MM", "MMM" } };
		int[][] RomanNumber = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 10, 20, 30, 40, 50, 60, 70, 80, 90 },
				{ 100, 200, 300, 400, 500, 600, 700, 800, 900 }, { 1000, 2000, 3000 } };
		int res = 0;
		for (int i = 3; i >= 0; i--) {
			for (int j = Roman[i].length - 1; j >= 0; j--) {
				if (s.startsWith(Roman[i][j])) {
					res += RomanNumber[i][j];
					s = s.substring(Roman[i][j].length());
				}
			}

		}
		return res;

	}

	public static String intToRoman(int num) {
		String[][] Roman = { { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, { "", "M", "MM", "MMM" } };
		String s = "";
		int carry = 1000;
		for (int i = Roman.length - 1; i >= 0; i--) {
			s += Roman[i][num / carry];
			num = num % carry;
			carry = carry / 10;
		}
		return s;
	}

	public String intToRoman1(int num) {
		String M[] = { "", "M", "MM", "MMM" };
		String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	}

	public static boolean isValid(String s) {
		if (s == "")
			return true;
		char[] Array = s.toCharArray();
		char[] A = new char[Array.length];
		int i = 0;
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		ArrayList<Character> places = new ArrayList<Character>();
		for (char a : Array) {
			if (a == '(' || a == '{' || a == '[') {
				places.add(a);
			} else {
				if (places.isEmpty())
					return false;
				else if (map.get(places.get(places.size() - 1)) == a)
					places.remove(places.size() - 1);
				else {
					return false;
				}
			}
		}
		if (places.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public static List<List<Integer>> threeSum(int[] nums) {
		List res = new ArrayList<ArrayList<Integer>>();
		int n = nums.length;
		int target;

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1, k = nums.length - 1; j < k;) {
				if (nums[i] + nums[j] + nums[k] == 0) {
					List<Integer> a = new ArrayList<Integer>();
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

				} else if (nums[i] + nums[j] + nums[k] < 0) {
					j++;
				} else {
					k--;
				}

			}
		}
		return res;

	}
	public static int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int container = 0;
		int contain = 0;
		while (left < right) {
			if (height[left] <= height[right]) {
				contain = height[left] * (right - left);
				left++;
			} else {
				contain = height[right] * (right - left);
				right--;
			}
			if (contain > container)
				container = contain;

		}
		return container;

	}
	static List<List<Integer>> ret = new ArrayList<List<Integer>>();

	public static List<List<Integer>> threeSum1(int[] num) {
		if (num == null || num.length < 3)
			return ret;

		Arrays.sort(num);

		int len = num.length;
		for (int i = 0; i < len - 2; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			find(num, i + 1, len - 1, num[i]); // 寻找两个数与num[i]的和为0
		}

		return ret;
	}



	public static void find(int[] num, int begin, int end, int target) {
		int l = begin, r = end;
		while (l < r) {
			if (num[l] + num[r] + target == 0) {
				List<Integer> ans = new ArrayList<Integer>();
				ans.add(target);
				ans.add(num[l]);
				ans.add(num[r]);
				ret.add(ans); // 放入结果集中
				while (l < r && num[l] == num[l + 1])
					l++;
				while (l < r && num[r] == num[r - 1])
					r--;
				l++;
				r--;
			} else if (num[l] + num[r] + target < 0) {
				l++;
			} else {
				r--;
			}
		}
	}

	public int romanToInt1(String s) {
		int[] chs = new int[s.length()];
		int result = 0;

		for (int i = 0; i < chs.length; i++) {
			switch (s.charAt(i)) {
			case 'I':
				chs[i] = 1;
				break;
			case 'V':
				chs[i] = 5;
				break;
			case 'X':
				chs[i] = 10;
				break;
			case 'L':
				chs[i] = 50;
				break;
			case 'C':
				chs[i] = 100;
				break;
			case 'D':
				chs[i] = 500;
				break;
			case 'M':
				chs[i] = 1000;
				break;
			}
		}
		for (int i = 0; i < chs.length; i++) {
			if (i == chs.length - 1) {
				result += chs[i];
			} else if (chs[i] < chs[i + 1]) {
				result = result - chs[i];
			} else
				result += chs[i];

		}
		return result;
	}

	public boolean isNumber(String s) {
		boolean num = false, numAfterE = true, dot = false, exp = false, sign = false;
		int n = s.length();
		for (int i = 0; i < n; ++i) {
			if (s.charAt(i) == ' ') {
				if (i < n - 1 && s.charAt(i + 1) != ' ' && (num || dot || exp || sign))
					return false;
			} else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != ' ')
					return false;
				sign = true;
			} else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				num = true;
				numAfterE = true;
			} else if (s.charAt(i) == '.') {
				if (dot || exp)
					return false;
				dot = true;
			} else if (s.charAt(i) == 'e') {
				if (exp || !num)
					return false;
				exp = true;
				numAfterE = false;
			} else
				return false;
		}
		return num && numAfterE;

	}

	public boolean isNumber1(String s) {
		s = s.trim();
		boolean point = false, e = false, number = false, eNum = true;
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (cur >= '0' && cur <= '9') {
				number = true;
				eNum = true;
			} else if (cur == '.') {
				if (e || point)
					return false;
				point = true;
			} else if (cur == 'e') {
				if (e || !number)
					return false;
				e = true;
				eNum = false;
			} else if (cur == '-' || cur == '+') {
				if (i != 0 && s.charAt(i - 1) != 'e')
					return false;
			} else {
				return false;
			}
		}
		return number && eNum;
	}

}

class ListNode {
	int val;
	ListNode nextNode;

	ListNode(int val) {
		this.val = val;
		this.nextNode = null;
	}
}
