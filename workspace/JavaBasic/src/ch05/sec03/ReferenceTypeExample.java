package ch05.sec03;

public class ReferenceTypeExample {

	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 10;
		
		String str1 = new String("Hello");
		String str2 = new String("Hello");
		// new를 사용하면 무조건 heap 영역에 생성
		
		System.out.println(num1 == num2);	// heap 1000번지
		System.out.println(str1 == str2);	// heap 2000번지
		
		// JVM은 literal을 최대한 재사용하려고 한다.
		// 문자열 literal을 코드에서 처음 사용하면 재사용을 위해 별도의 공간에 저장.
		String str3 = "Hello";	// new(X) literal	// heap에 저장되지 않음.
		String str4 = "Hello";	// new(X) literal
		
		System.out.println(str3 == str4);
	}
	
}
