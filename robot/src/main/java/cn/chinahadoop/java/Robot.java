package cn.chinahadoop.java;

import java.util.Scanner;

/**
 * Judy and Max.
 *
 */
public class Robot {

	static String[] questionList = { "Hi", "Ping", "Bye" };
	static String[] JudyAnswer = { "Hi honey", "Pong", "See you" };
	static String[] MaxAnswer = { "Hi what's up", "Ping", "8" };

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Please input the robot name!");
			return;
		}

		String[] answer = null;
		if (args[0].equalsIgnoreCase("Judy")) {
			answer = JudyAnswer;
		} else if (args[0].equalsIgnoreCase("Max")) {
			answer = MaxAnswer;
		}

		if (null == answer) {
			System.err.println("Can not find the robot!");
			return;
		}
		while (true) {
			Scanner input = new Scanner(System.in);
			String question = input.next();
			int matchIndex = -1;
			for (int i = 0; i < questionList.length; i++) {
				if (questionList[i].equalsIgnoreCase(question)) {
					matchIndex = i;
					break;
				}
			}
			if (matchIndex == -1) {
				System.out.println("No match answer!");
			} else {
				System.out.println(answer[matchIndex]);
			}

			if (question.equalsIgnoreCase("Bye")) {
				System.out.println("The robot exists!");
				input.close();
				break;
			}
		}
	}
}
