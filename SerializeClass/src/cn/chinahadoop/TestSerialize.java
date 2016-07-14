package cn.chinahadoop;

import java.io.IOException;

class Dog {
	int age = 1;
	private char nickChar = 'x';

	public char getChar() {
		return nickChar;
	}
}

public class TestSerialize {

	static class LittleDog extends Dog {

	}

	public static void main(String[] args)
			throws IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
		Dog dog = new Dog();
		Serializer.serialize(dog);
		Dog dog2 = (Dog) Serializer.deserialize();
		System.out.println("dog2 age is " + dog2.age);
		System.out.println("dog2 nickChar is " + dog2.getChar());
	}

}
