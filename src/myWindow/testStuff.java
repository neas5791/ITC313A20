package myWindow;

import java.text.NumberFormat;
import java.text.ParseException;

public class testStuff {

	public static void main (String[] args) {
		
		String result;
		int a = 1000000;
		int b = 2;
		int c = Integer.MAX_VALUE;
		int d;
		
		d = a * b;
		
		result = String.format("%d x %d = %d", a, b, d);
		System.out.println(result);
		
		d = a * a;
		
		result = String.format("%,d x %,d = %,d", a, a, d);
		System.out.println(result);
		
		
		if ( ((float) a * a) > Integer.MAX_VALUE)
			System.out.println("Result out of bounds");
		
		String number = "1,000,000";
		int num;
		num = Integer.parseInt(number.replaceAll(",",""));
		System.out.println(num);
		
		

		
		
		
	}
}
