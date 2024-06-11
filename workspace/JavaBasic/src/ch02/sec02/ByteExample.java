package ch02.sec02;

public class ByteExample {

	public static void main(String[] args) {
		byte var1 = -128;
		byte var2 = -30;
		byte var3 = 0;
		byte var4 = 30;
		byte var5 = 127;
		//byte var5 = 128;	// byte는 int 숫자를 담을 수 없다.
							// 숫자 리터럴(하드코딩한 숫자)는 int로 인식한다.
		
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
		System.out.println(var4);
		System.out.println(var5);
	}

}
