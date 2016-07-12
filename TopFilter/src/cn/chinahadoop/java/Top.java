package cn.chinahadoop.java;

public class Top<K extends Comparable<K>> {
	K topElement;

	public void add(K newElement) {
		if (topElement == null) {
			topElement = newElement;
			return;
		}
		if (newElement.compareTo(this.topElement) > 0) {
			topElement = newElement;
		}
	}

	public K getTop() {
		return topElement;
	}
}
