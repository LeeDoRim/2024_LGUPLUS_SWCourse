package ch04.sec03;

public class SwitchExample {

	public static void main(String[] args) {
		int num = 2;
		
		switch(num) {
			case 1 :
				System.out.println("1번"); break;
			case 2 :
				System.out.println("2번"); break;
			case 3 :
				System.out.println("3번"); break;
			case 4 :
				System.out.println("4번"); break;
			case 5 :
				System.out.println("5번"); break;
			case 6 :
				System.out.println("6번"); break;
			default:
				System.out.println("default 번"); break;
		}
		
		// 문자열도 사용 가능
		String str = "2";
		
		switch(str) {
			case "1" :
				System.out.println("1번"); break;
			case "2" :
				System.out.println("2번"); break;
			case "3" :
				System.out.println("3번"); break;
			default:
				System.out.println("default 번"); break;
		}
		
		char ch = 'B';
		
		switch(ch) {
			case 'A' :
				System.out.println("A"); break;
			case 'B' :
				System.out.println("B"); break;
			case 'C' :
				System.out.println("C"); break;
			//case '65' :	// A(65)와 동일한 케이스이므로 오류 발생
				//System.out.println("65"); break;
		}
		
	}
	
}
