import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1644 { // 백준 1644 소수의 연속합 - 60분
	/*
	 * 연속되는 소수의 합을 구하기
	 * 1. 에라토스테네스 체로 소수 찾기
	 * 2. 찾은 소수에서 투 포인터로 가능한지 확인
	 */
	static int n;
	static boolean primeNums[];
	static ArrayList<Integer> numList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		getPrimeNums();

		int left = 0;
		int right = 0;
		int result = 0;
		int sum;
		if (numList.size() < 0) {
			sum = 0;
		} else {
			sum = numList.get(0);
		}
		
		while (left<=right && right < numList.size()) {
			if (sum <= n) {
				if (sum == n) result++;
				right++;
				
				if (right < numList.size()) {
					sum += numList.get(right);
				}
			}
			else if (sum > n) {
				sum -= numList.get(left);
				left++;
				if (left < numList.size() && left > right) {
					left = right;
					sum = numList.get(right);
				}
			}
		}
		
		System.out.println(result);
	}//main
	
	private static void getPrimeNums() {		
		primeNums = new boolean[n+1];
		numList = new ArrayList<Integer>();
		
		primeNums[1] = true;
		for (int i = 2; i*i <= n; i++) {
			if (primeNums[i]) continue;
			for (int j = i*i; j <= n; j+=i) {
				primeNums[j] = true;
			}
		}

		for (int i = 0; i <= n; i++) {
			if (!primeNums[i]) {
				numList.add(i);
			}
		}
	}//getPrimeNums
}//class

//에라토스테네스의 체 핵심 : 어떤 수의 소수를 찾으려면 그 수의 제곱근까지만 보면 된다.
