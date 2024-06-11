package ch05.sec05;

import java.util.Scanner;

public class StringExample {

	public static void main(String[] args) {
		//equals()
		String str1 = new String("Hello");
		String str2 = new String("Hello");
		
		System.out.println(str1 == str2); // 결과 : false => 서로 다른 객체임
		System.out.println(str1.equals(str2)); // 결과 : true => 같은 문자열
		
		//length()
		System.out.println(str1.length());
		
		// charAt()
		/*
		System.out.println(str2.charAt(4));
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		for (int i = 0; i < input.length(); i++) {
			System.out.println(input.charAt(i));
		}
		*/
		
		// replace
		String str = "Hello, Java!";
		String str3 = str.replace("ll", "LL");
		System.out.println(str);
		System.out.println(str3);
		
		// indexOf
		// 문자열이 없으면 -1을 반환
		String str4 = "abcdefghijk";
		int idx = str4.indexOf('c');
		System.out.println(idx);
		idx = str4.indexOf("def");
		System.out.println(idx);
		
		// 사용 방안
		if(idx == -1) {
			System.out.println("찾지 못했습니다.");
		}
		
		// 존재 유무 contains()
		boolean result = str4.contains("def");
		System.out.println(result);
		
		// 사용 방안
		if(str4.contains("def")) {
			
		}else {
			
		}
		
	}

}
