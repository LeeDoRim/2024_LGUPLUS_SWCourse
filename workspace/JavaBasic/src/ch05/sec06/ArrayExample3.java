package ch05.sec06;

import java.util.Scanner;

public class ArrayExample3 {

	public static void main(String[] args) {
		// 배열 + 반복문(for)
		// 사용자의 입력을 받은 문자열을 char 배열로 저장
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		int length = input.length();
		/*
		char[] chArr = new char[length];
		
		for (int i = 0; i < length; i++) {
			chArr[i] = input.charAt(i);
		}
		*/
		
		char[] chArr = input.toCharArray();	// 문자열의 내용을 배영로 변경
		
		// char[] chArr = new char[length];
		// chArr = input.toCharArray();
		// => 메모리 낭비됨. chArr과 input.toCharArray() 두개의 공간이 사용됨
		
		for (int i = 0; i < length; i++) {
			System.out.print(chArr[i]);
		}
		
	}

}
