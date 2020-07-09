package proz.docker.service;

public class ExpressionService {

	public static double calculate(String num1, String op, String num2){
		double num1d = Double.parseDouble(num1);
		double num2d = Double.parseDouble(num2);
		switch(op) {
		case "/":
			return num1d / num2d;
		case "*":
			return num1d * num2d;
		case "+":
			return num1d + num2d;
		case "-":
			return num1d - num2d;
		case "^x":
			return Math.pow(num1d, num2d);
		default:
			throw new IllegalArgumentException("Illegal operation!");
		}
	}
	
	public static double calculate(String num1, String op){
		double num1d = Double.parseDouble(num1);
		switch(op) {
		case "fact":
			return factorial((int)num1d);
		case "sqrt":
			return Math.sqrt(num1d);
		default:
			throw new IllegalArgumentException("Illegal operation!");
		}
	}
	
	private static int factorial(int num) {
		if (num == 0 || num == 1)
			return 1; 
		else
			return num * factorial(num - 1);
	}
}
