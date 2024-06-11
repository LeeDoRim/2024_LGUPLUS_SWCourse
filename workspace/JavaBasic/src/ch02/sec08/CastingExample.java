package ch02.sec08;

public class CastingExample {
	// 자동형변환
	public static void main(String[] args) {
		int var1 = 10000;
		byte var2 = (byte) var1;
		
		System.out.println(var2);
		
		
		int var3 = 65;
		char var4 = (char) var3;
		
		System.out.println(var4);
		
		double var5 = 3.141592;
		int var6 = (int) var5;
		
		System.out.println(var6);
		
		
		byte b1 = 10;
		byte b2 = 20;
		byte b3 = 10 +  20;
		//byte b4 = b1 + b2; // byte, short는 피연산자(오른쪽) 처리가 되면 int로 자동형변환이 발생
		
		
	}

}
	
// Primitive Type으로 형변환 개념을 익히고 있지만, 실제로는 Primitive Type의 형변환은 거의 사용 X
// reference Type의 형변환이 중요!