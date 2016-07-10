package cn.chinahadoop.java;

import java.util.Scanner;

/**
 * OO Judy and Max.
 *
 */
public class OORobot {

	static abstract class QARobot {
		protected String[] questionList = { "Hi", "Ping", "Bye" };
		protected String[] answer;
		protected String name;

		abstract public void initAnswer();

		QARobot(String name) {
			this.name = name;
			System.out.println("Hi! I am " + name + ". Please input your question:");
			initAnswer();
		}

		public String ask(String question) {
			int questionIndex = getQuestionIndex(question);
			if (questionIndex < 0) {
				return "No match answer!";
			} else {
				return answer[questionIndex];
			}
		}

		protected int getQuestionIndex(String question) {
			int matchIndex = -1;
			for (int i = 0; i < questionList.length; i++) {
				if (questionList[i].equalsIgnoreCase(question)) {
					matchIndex = i;
					break;
				}
			}
			return matchIndex;
		}
	}

	static class Judy extends QARobot {
		String[] myAnswer = { "Hi honey", "Pong", "See you" };

		public Judy() {
			super("Judy");
		}

		@Override
		public void initAnswer() {
			answer = myAnswer;
		}
	}

	static class Max extends QARobot {
		public Max() {
			super("Max");
		}

		@Override
		public void initAnswer() {
			answer = new String[3];
			answer[0] = "Hi what's up";
			answer[1] = "Ping";
			answer[2] = "8";
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Please input the robot name!");
			System.exit(1);
		}

		QARobot robot = null;
		if (args[0].equalsIgnoreCase("Judy")) {
			robot = new Judy();
		} else {
			robot = new Max();
		}

		while (true) {
			Scanner input = new Scanner(System.in);
			String question = input.next();
			System.out.println(robot.ask(question));

			if (question.equalsIgnoreCase("Bye")) {
				System.out.println("The robot exists!");
				input.close();
				System.exit(0);
				;
			}
		}
	}
}
