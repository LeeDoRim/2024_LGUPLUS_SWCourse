package ch04.sec07;

public class BreakContinueExample {

	public static void main(String[] args) {
		// break => ex) 5를 만나면 종료
		for(int i = 0; i < 10; i++) {
			if(i == 5) break;
			System.out.println(i);
		}
		
		// contine => 5 만나면 skip
		for(int i = 0; i < 10; i++) {
			if(i == 5) continue;
			System.out.println(i);
		}
		
		// nested
		for(int i = 0; i < 10; i++) {
			if(i == 5) continue;
			for (int j = 0; j < 10; j++) {
				System.out.print(i + " " + j + "  ");
			}
			System.out.println();
		}

		for(int i = 0; i < 10; i++) {
			//if(i == 5) break;
			for (int j = 0; j < 10; j++) {
				if(j == 5) break;
				System.out.print(i + " " + j + "  ");
			}
			System.out.println();
		}
		// 중첩 for문에서 continue, break의 위치에 따라 결과가 달라짐.

		Outter : for(int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(j == 5) break Outter;
				System.out.print(i + " " + j + "  ");
			}
			System.out.println();
		}

		
	}

}
