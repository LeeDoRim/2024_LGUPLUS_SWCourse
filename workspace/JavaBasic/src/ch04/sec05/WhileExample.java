package ch04.sec05;

import java.util.Scanner;

public class WhileExample {
	// for : 횟수가 예측 가능할 때 사용, while : 횟수가 예측 불가능할 때 사용
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	// ctrl+shift+o
		boolean run = true;
		int speed = 0;
		
		while(run) {
			// 작업 수행
			// 수행에 영향을 미치는 조건 처리
			System.out.println("-----------------------");
			System.out.println("1:증속  | 2:감속 | 3.중지 ");
			System.out.println("선택 : ");
			
			// 사용자 입력
			String strNum = sc.nextLine();
			
			if(strNum.equals("1")) {
				speed++;
				System.out.println("현재 속도 : " +  speed);
			} else if (strNum.equals("2")) {
				speed--;
				System.out.println("현재 속도 : " + speed);
			}else if(strNum.equals("3")) {
				run = false;
			}
		}
		
		System.out.println("종료");
	}

}
