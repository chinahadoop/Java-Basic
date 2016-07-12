package cn.chinahadoop.java;

public class TopFilter {

	public static void main(String[] args) {
		int[] intArray = {-1, 0, -10, 30, 22, -100};
		Top<Integer> intTop =  new Top<Integer>();
		for (int element : intArray) {
			intTop.add(element);
		}
		System.out.println("The top element of int array is " + intTop.getTop());
		
		String[] stringArray = {"I", "am", "happy", "today", "!"};
		Top<String> stringTop =  new Top<String>();
		for (String element : stringArray) {
			stringTop.add(element);
		}
		System.out.println("The top element of string array is " + stringTop.getTop());
	}

}
