package class002;

import java.util.Arrays;

public class EconSim {
	
	static int [] money = new int [100];
	
	public static void main(String[] args) {
		
		initialize();
		runN(1000000);
		print();
		System.out.println(check());
		
	}
	
	public static void initialize () {
		
		for (int i = 0; i < money.length; i++) {
			
			money[i] = 100;
			
		}
		
	}
	
	public static void print () {
		
		System.out.println(Arrays.toString(money));		
	}
	
	public static void run () {
		
		int[] a = new int[money.length];
		
		for (int i = 0; i < money.length; i++) {
			
			if (money[i] != 0) {
				
				a[i] -= 1;
				a[(int)(Math.random()*100)] += 1;
				
			}
			
		}
		
		for (int i = 0; i < money.length; i++) {
			
			money[i] += a[i];
			
		}
		
	}
	
	public static void runN (int n) {
		
		for (int i = 0; i < n; i++) {
			
			run();
			
		}
		
	}
	
	public static boolean check () {
		
		int n = 0;
		
		for (int i = 0; i < money.length; i++) {
			
			n += money[i];
			
		}
		
		if (n == 10000)
			return true;
		else
			return false;
		
	}
	
}
