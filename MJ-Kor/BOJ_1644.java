package algorithm_study.day_03_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1644 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N < 2) {
			System.out.println(0);
			System.exit(0);
		}
		
		boolean[] isPrimeNum = new boolean[N + 1];

		int[] primeNums = eratosthenes(isPrimeNum, N);
		
		int left = 0;
		int right = 0;
		int sum = primeNums[0];
		int answer = 0;
		
		while(left <= right && right < primeNums.length - 1) {
			
			if(sum < N) {
				right += 1;
				sum += primeNums[right];
			}
			else if(sum > N) {
				sum -= primeNums[left];
				left += 1;
				
			}
			else if(sum == N) {
				answer += 1;
				sum -= primeNums[left];
				left += 1;
			}
		}
		
		System.out.println(answer);
	}

	private static int[] eratosthenes(boolean[] isPrimeNum, int N) {
		int cnt = 0;
		
		isPrimeNum[0] = true;
		isPrimeNum[1] = true;
		
		for (int i = 2; i * i <= N; i++) {
			if(isPrimeNum[i] == true) { 
				continue;
			}
			for (int j = i + i; j <= N; j += i) {			
				isPrimeNum[j] = true;
			}
		}
		
		for (int i = 2; i <= N; i++) {
			if(!isPrimeNum[i]) cnt += 1;
		}
		
		int[] primeNums = new int[cnt + 1];

		int idx = 0;
		for (int i = 2; i <= N; i++) {
			if(isPrimeNum[i] == false) {
				primeNums[idx] = i;
				idx += 1;
			}

		}
		return primeNums;
	}
}
