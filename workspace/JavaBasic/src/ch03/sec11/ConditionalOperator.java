package ch03.sec11;

public class ConditionalOperator {

	public static void main(String[] args) {
		// 90 초과 A
		// 81 ~ 90 B
		// 나머지는 C
		int score = 75;
		char grade = (score > 90)? 'A' : ( (score > 80)? 'B' : 'C' );
		System.out.println(grade);
	}

}
