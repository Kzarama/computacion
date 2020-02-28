package co.icesi.edo.test;

/**
 * Hello world!
 *
 */
public class StringCalculator {
    //	“//;\n1;2”
	public static int add(String numbers) {
		if (numbers.equals("")) {
			return 0;
		}
		int answer = 0;
		int finishDelimiters = numbers.indexOf("]");
		String delimiters = numbers.substring(3, finishDelimiters);
		String[] numsString = numbers.substring(finishDelimiters+2).split(delimiters);
		System.out.println("ok");
		for (int i = 0; i < numsString.length; i++) {
			int number = Integer.parseInt(numsString[i]);
			if (number < 0) {
				throw new RuntimeException();
			}
			if (number > 1000) {
				answer += number;
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		StringCalculator calculator = new StringCalculator();
		System.out.println(calculator.add("//[,;]\n1,2;3"));
	}
	
}
