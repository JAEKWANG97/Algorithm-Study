package algorithm_study.day_04_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023 {

	public static int N;
	public static int max;
	public static int mul = 10;
	
	public static boolean isPrime(int n) {
		if(n<2) {
			return false;
		}
		for(int i = 2; i*i<=n; i++) {
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void magicPrime(int num) {
		
		int input = num * mul;

		if(input < max) {
			for (int i = 0; i < 10; i++) {
				if(isPrime(input+i)) {
					magicPrime(input+i);
				}
			}
		}
		else {
			System.out.println(num);;
		}


	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		max = (int)Math.pow(10, N);
		magicPrime(0);
	}
	
}
