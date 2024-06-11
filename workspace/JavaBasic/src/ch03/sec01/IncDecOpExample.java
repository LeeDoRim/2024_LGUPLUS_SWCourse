package ch03.sec01;

public class IncDecOpExample {

		public static void main(String[] args) {
			int x = 10;
			int y = 10;
			int z;
			
			//x++;
			//++x;
			
			//System.out.println(x++); // x++ : 출력 후 증가해서 다음라인에서 출력 시 13, ++x : 출력 전 증가해서 해당라인부터 13
			//System.out.println(x);
			
			if( ++x == 10 & ++y == 10) {
				System.out.println("1");
				System.out.printf("x = %d y =  %d", x, y);
			}else {
				System.out.println("2");
				System.out.printf("x = %d y =  %d", x, y);
			}
			// if의 조건이 &&이면 x = 11 y = 10으로 앞의 피연산자가 false이므로 뒤에는 검사하지 않음.
			// if의 조건이 &이면 x = 11 y = 11로 앞의 피연산자가 false여도 뒤의 조건도 확인.
		}
}
