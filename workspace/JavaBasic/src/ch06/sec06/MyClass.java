package ch06.sec06;

// compiler가 자동으로 해 주는 것
// 1. java.lang 자동 import
// 2. class에 생성자가 없으면 기본생성자를 추가
//    기본 생성자는 파라미터가 없는 생성자
public class MyClass {
	// field
	int n;		// 선언만 <= 0에 준하는 default value
	int n2 =10;	// 선언 + 초기화
	
	String s;
	String s2 = new String("Hello");
	
	// constructor
	// 무분별한 생성자를 여러개 만드는 것은 지양
	// pattern <= builder 패턴을 이용해서 필요하고 가독성이 높은 형태로 전환이 필요
	public MyClass() {}
	
	public MyClass(int n) {
		this.n = n;
	}

	public MyClass(int n, int n2, String s, String s2) {
		//this.n = n;
		this(n);	// MyClass(int n) 생성자가 호출되어 초기화됨.
		// 생성자 안에서 생성자를 호출할 때는 가장 윗줄에서만 호출 가능.
		this.n2 = n2;
		this.s = s;
		this.s2 = s2;
	}
	// this. <= this heap에 만들어진 instance를 지칭
	// this() <= 자신의 생성자를 호출
	
	public void m1() {
		System.out.println(this.n + this.n2);
	}

	// return이 없으면 void
	public void m1(String message) {
		if(message.equals("Quit")) return;
		System.out.println(message + this.s2);
	}
	
	public boolean m2() {
		return this.n > 4;
	}
	
}

// a.m()
// m(){
// 		for 반복문 { if() return }
// }