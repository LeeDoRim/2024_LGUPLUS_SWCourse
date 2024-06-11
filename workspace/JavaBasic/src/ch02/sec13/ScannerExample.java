package ch02.sec13;

import java.util.Scanner;
// compiler가 자동으로 해주는 작업 #1 <- java.lang package는 자동으로 import 해준다.
public class ScannerExample {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // 자동import : ctrl + shift + o
		System.out.println("정수값 입력 : ");
		int input = sc.nextInt();
		System.out.println(input);
	}

}
