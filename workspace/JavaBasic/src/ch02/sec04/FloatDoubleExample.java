package ch02.sec04;

public class FloatDoubleExample {

	public static void main(String[] args) {
		// 소숫점을 포함한 실수는 기본 타입이 double
		// byte b = 10;
		//float var1 = 0.1234;	// 간단한 실수 리터럴도 double로 인식하고 float
		float var1 = 0.123456789123456789f; // float는 F or f 붙여주야 함.
		double var2 = 0.123456789123456789f; 
		// double형에 저장하는 값이 이미 float형이므로 
		// 저장하기 전에 기본 double형에서 float형으로 리터럴 과정에서 이미 변환됨. 
		double var3 = 0.123456789123456789; 
		
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
	}

}

// int, long, float, double <= 범위를 벗어나는 더 큰 정수 계산 및 표현, 소수점 계산 표현????
// BigInteger, BigDecimal 클래스 <= 느리다.
