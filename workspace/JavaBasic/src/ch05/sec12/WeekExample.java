package ch05.sec12;

import java.util.Calendar;

public class WeekExample {

	public static void main(String[] args) {
		// Week enum + Calendar
		Week today = null;
		
		Calendar cal = Calendar.getInstance();
		int week = cal.get(Calendar.DAY_OF_WEEK);
		// 1: 일, 2: 월, 3: 화, 4: 수, 5: 목, 6: 금, 7: 토
		System.out.println(week);
		
		switch(week) {
			case 1:
				today = Week.SUNDAY; break;
			case 2:
				today = Week.MONDAY; break;
			case 3:
				today = Week.TUESDAY; break;
			case 4:
				today = Week.WEDNESDAY; break;
			case 5:
				today = Week.THIRSDAY; break;
			case 6:
				today = Week.FRIDAY; break;
			case 7:
				today = Week.SATURDAY; break;
		}
		
		System.out.println(today);
	}

}
