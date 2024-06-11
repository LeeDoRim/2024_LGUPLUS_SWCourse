package ch04.sec02;

public class IfElseIfElseExample {

	public static void main(String[] args) {
		int score = 75;
		
		if(score >= 90) {
			if(score >= 95) {
				System.out.println("A+");
			}else {
				System.out.println("A");
			}
		}else if(score >= 80) {
			System.out.println("B");
		}else if(score >= 70) {
			System.out.println("C");
		}else if(score >= 60) {
			System.out.println("D");
		}else {
			System.out.println("F");
		}
		
	}

}
