package model;

import expression.Expression;

//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//import jdk.jshell.JShell;
//import jdk.jshell.SnippetEvent;

/**
 * Klasa zajmuj�ca si� obliczeniami. Brak konstruktora, poniewa� obiekt klasy
 * nie jest tworzony w �adnym miejscu programu.
 * 
 * @author Daniel_Chmielewiec
 *
 */
public class Model {

	private static final int MAX_LENGTH = 11;
	//private static Function<String, Double> toDouble = Double::parseDouble;

	/**
	 * Metoda odpowiednio formatuj�ca wy�wietlany wynik na wy�wietlaczu kalkulatora
	 * 
	 * @param num Wynik dzia�ania jako typ "String"
	 * @return Sformatowany i gotowy do wy�wietlenia wynik jako typ "String"
	 */
	public static String makeItSuitable(String num) {
		if (num.length() <= MAX_LENGTH) {
			if (num.charAt(num.length() - 1) == '0')
				num = num.substring(0, num.length() - 2);
			return num;
		} else {
			for (int i = num.length() - MAX_LENGTH; i > 0; i--)
				num = num.substring(0, num.length() - 1);
			return num;
		}
	}
	
	public static void checkIfLegal(Expression expression) {
		if(expression.getOp().equals("/") && expression.getSecondNum().equals("0"))
			throw new IllegalArgumentException("Dividing by 0!");
		if(expression.getOp().equals("sqrt") && Double.parseDouble(expression.getFirstNum()) < 0)
			throw new IllegalArgumentException("Square root of negative number!");
		if(expression.getOp().equals("fact") && (int) Double.parseDouble(expression.getFirstNum()) != Double.parseDouble(expression.getFirstNum()))
			throw new IllegalArgumentException("Factorial of not integer number!");
		if(expression.getOp().equals("fact") && Double.parseDouble(expression.getFirstNum()) > 10)
			throw new IllegalArgumentException("Too big number!");
	}


	/**
	 * Metoda odpowiedzialna za wykonywanie operacji matematycznych
	 * 
	 * @param toJShell Instrukcja do wykonania w JShell-u
	 * @param jshell   Obiekt klasy JShell
	 * @return Wynik wykonania instrukcji
	 * @exception IllegalArgumentException Zg�aszany w przypadku pr�by wykonania
	 *                                     nielegalnego dzia�ania np dzielenia przez
	 *                                     0
	 */
	/*public static double jshellFun(String toJShell, @SuppressWarnings("exports") JShell jshell) {
		try (jshell) {
			List<SnippetEvent> events = jshell.eval(toJShell);
			for (SnippetEvent e : events) {
				if (e.causeSnippet() == null) {
					switch (e.status()) {
					case VALID:
						if (e.value() != null && Double.isFinite(toDouble.apply(e.value()))) {
							System.out.printf("%s = %s\n", toJShell, e.value());
							return toDouble.apply(e.value());
						} else
							throw new IllegalArgumentException("Illegal operation!");
					default:
						System.out.printf("Error\n");
						throw new IllegalArgumentException("Illegal operation!");
					}
				}
			}
		}
		return 0;
	}*/

	/**
	 * Metoda tworzy instrukcj� dla dzia�a� dwuargumentowych do wykonania w konsoli
	 * JShell
	 * 
	 * @param num1S Pierwszy argument dzia�ania jako "String"
	 * @param num2S Drugi argument dzia�ania jako "String"
	 * @param op    Operator jako "String"
	 * @return Wynik wykonania operacji
	 */
	/*public static double calculate(String num1S, String num2S, String op) {
		var jshell = JShell.create();
		String toJShell;
		var num1D = toDouble.apply(num1S);
		var num2D = toDouble.apply(num2S);

		if (op.equals("^x")) {
			toJShell = "Math.pow(" + num1D + ", " + num2D + ")";
		} else {
			toJShell = Stream.of(toDouble.apply(num1S).toString(), op, toDouble.apply(num2S).toString())
					.collect(Collectors.joining());
		}
		return jshellFun(toJShell, jshell);
	}*/

	/**
	 * Metoda tworzy instrukcj� dla dzia�a� jednoargumentowych do wykonania w
	 * konsoli JShell
	 * 
	 * @param numS Argument dzia�ania jako "String"
	 * @param op   Operator jako "String"
	 * @return Wynik wykonania operacji
	 * @exception IllegalArgumentException Zg�aszany w przypadku pr�by wykonania
	 *                                     nielegalnego dzia�ania lub gdy wynik
	 *                                     przekroczy maksymaln� dozwolon� warto��
	 */
	/*public static double calculate(String numS, String op) {
		var jshell = JShell.create();
		var sb = new StringBuilder();
		double numD = toDouble.apply(numS);
		if (op.equals("fact")) {
			if ((int) numD != numD)
				throw new IllegalArgumentException("Illegal operation!");
			if (numD > 10)
				throw new IllegalArgumentException("Too big number!");
			String fact = "public static int factorial(int num) {\r\n if (num == 0 || num == 1)\r\n return 1;\r\n else\r\n return num * factorial(num - 1);\r\n }";
			jshell.eval(fact);
			sb.append("factorial(" + (int) numD + ")");
		} else {
			sb.append("Math.sqrt(" + numD + ")");
		}

		var toJShell = sb.toString();
		return jshellFun(toJShell, jshell);
	}*/

}