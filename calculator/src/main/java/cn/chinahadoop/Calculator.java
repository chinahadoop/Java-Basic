package cn.chinahadoop;

import java.util.regex.Pattern;

/**
 * Simple tool, calculator.
 *
 */
public class Calculator {
	static class Operator {
		String expression;
		enum OPERATE_TYPE {ADD, MINUS, MUL, DIV};
		OPERATE_TYPE operateType;
		double obj1, obj2;
		
		Operator(String expression) {
			this.expression = expression;
		}
		
		private void init() {
			int location;
			if (expression.contains("+")) {
				operateType = OPERATE_TYPE.ADD;
				location = expression.indexOf("+", 0);
			} else if (expression.contains("-")) {
				operateType = OPERATE_TYPE.MINUS;
				location = expression.indexOf("-", 0);
			} else if (expression.contains("*")) {
				operateType = OPERATE_TYPE.MUL;
				location = expression.indexOf("*", 0);
			} else {
				operateType = OPERATE_TYPE.DIV;
				location = expression.indexOf("/", 0);
			}
			String objString1 = expression.substring(0, location);
			String objString2 = expression.substring(location + 1, expression.length());
			obj1 = Double.valueOf(objString1);
			obj2 = Double.valueOf(objString2);
		}
		
		private double calculate() {
			double ret = 0;
			switch (operateType) {
			case ADD: 
				ret = obj1 + obj2;
				break;
			case MINUS: 
				ret = obj1 - obj2;
				break;
			case MUL:
				ret = obj1 * obj2;
				break;
			case DIV:
				ret = obj1 / obj2;
				break;
			}
			return ret;
		}
		
		public double operate() {
			init();
			double ret = calculate();
			return ret;
		}
	}
	
	private static boolean isInputValid(String input) {
		String regex = "^(\\(*\\d+(.\\d+)*\\)*(\\+|-|/|\\*))+\\d+(.\\d+)*\\)*$"; 
		return Pattern.matches(regex, input);
	}
	
	public static void main(String[] args) {
		boolean inputValid = false;
		if (args.length == 1) {
			if (isInputValid(args[0])) {
				inputValid = true;
			}
		}
		if (!inputValid) {
			System.err.println("Input parameter should be <number> +/-/*//<number2>");
			return;
		}
		Operator operator = new Operator(args[0]);
		System.out.println("Result: " + operator.operate());
	}
}
