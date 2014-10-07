package cn.chinahadoop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A dual sort example. Sort the odd in ascending order, sort the even in
 * descending order. Furthermore, put the odd numbers before the even numbers.
 */
public class DualSort {
	ArrayList<Integer> numberList = new ArrayList<Integer>();

	class DualComparator implements Comparator<Integer> {
		boolean isOdd(Integer num) {
			return (num % 2 == 0) ? false : true;
		}

		public int compare(Integer num1, Integer num2) {
			boolean isOdd1 = isOdd(num1);
			boolean isOdd2 = isOdd(num2);
			if (isOdd1 && !isOdd2) {
				return -1;
			} else if (!isOdd1 && isOdd2) {
				return 1;
			} else if (isOdd1) {
				return num1 - num2;
			} else {
				return num2 - num1;
			}
		}
	}

	private void operate() {
		DualComparator dualComparator = new DualComparator();
		Collections.sort(numberList,dualComparator);
	}
	
	private void output() {
		System.out.println("Result is:");
		for (Integer number : numberList) {
			System.out.print(number + " ");
		}
		System.out.println();
	}
	
	private void initalizeList(String[] inputs) {
		for (String inputValue : inputs) {
			numberList.add(Integer.valueOf(inputValue));
		}
	}

	public static void main(String[] args) {
		DualSort dualSort = new DualSort();
		dualSort.initalizeList(args);
		dualSort.operate();
		dualSort.output();
	}
}
