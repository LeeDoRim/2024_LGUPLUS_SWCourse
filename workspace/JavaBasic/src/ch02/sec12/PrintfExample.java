package ch02.sec12;

public class PrintfExample {
	public static void main(String[] args) {
		int value = 123;
		int value2 = 2024;
		String str = "Hello";
		double pi = 3.141592;
		
		System.out.printf("상품의 가격은 %-10d입니다. %d에 만들어 졌습니다."
				+ "%s 문자열이에요.\n", value, value2, str);
		System.out.printf("현재의 파이는 %10.5f", pi);
	}
}
