package ch02.sec02;

public class LongExample {

	public static void main(String[] args) {
		long var1 = 10;		// 10은 int의 값
		long var2 = 20L;	// ~~L long 형의 값이다.
		long var3 = 100_000_000_000L;	// int value로 인식
		long var4 = 100_000_000_000l;	// int value로 인식
		// int보다 큰 수를 담을 때는 꼭 뒤에 L or l을 붙여서 long 타입임을 명시
		
	}

}
