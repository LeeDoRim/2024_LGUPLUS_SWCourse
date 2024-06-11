package ch05.sec04;

public class NulExample {
	// NullPointerException <- RuntimeException (컴파일 타임에는 check하지 않는다.)
	public static void main(String[] args) {
		String str = null;
		System.out.println(str);
		//System.out.println(str.length());
		
		String str2 = new String("Hello");
		System.out.println(str2.length());
	}

}
