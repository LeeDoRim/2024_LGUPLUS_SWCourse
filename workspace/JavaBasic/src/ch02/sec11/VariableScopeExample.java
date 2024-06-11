package ch02.sec11;

public class VariableScopeExample {

	public static void main(String[] args) {
		int v1 = 10;
		int v2;
		if( v1 > 10) {
			v2 = v1 - 10;
			System.out.println(v2);
		}else {
			v2 = 20;
		}
		
		System.out.println(v2);
	}

}
	
