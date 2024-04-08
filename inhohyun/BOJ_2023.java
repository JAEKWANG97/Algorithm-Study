import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_2023 {
	 // n 자리수의 신기한 소수를 모두 출력
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		getResult(0,n);
		System.out.println(sb);
	}

	static void getResult(int output, int n) {
		//  이전 자리가 소수이고 본인도 소수인 수 -> n자리 까지 반복
		if (n == 0) {
			if (isPrime(output))
				sb.append(output + "\n");
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			int next = 10 * output + i;
			if (isPrime(next))
				getResult(next, n - 1);
		}
	}
	
	//해당 수가 소수인지 판별
	static boolean isPrime(int num) {
		if (num < 2)
			return false;

		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

}
