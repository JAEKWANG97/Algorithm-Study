import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_1644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 1. 에라토스테네스의 체
		int prime[] = makeChe(N);
		int answer = 0, left = 0, right = 0, subtotal = 2, length = prime.length;
		while (right < length) {
			System.out.println(subtotal + " - " + left + " " + right);
			if (subtotal < N) {
				subtotal += prime[++right == length ? 0 : right];
			}
			else if (subtotal > N) {
				subtotal -= prime[left++];
			}
			else {
				answer++;
				subtotal += prime[++right == length ? 0 : right];
				subtotal -= prime[left++];
			}
		}
		System.out.println(answer);

	}

	private static int[] makeChe(int n) {
		int prime[] = new int[n];
		boolean numbers[] = new boolean[n + 1];
		prime[0] = 2;
		// 소수는 false
		for (int i = 2; i < n + 1; i++) {
			if (!numbers[i]) {
				int j = 2;
				while (i * j < n + 1) {
					numbers[i * j] = true;
					j++;
				}
			}

		}
		int primeCnt = 1;
		for (int i = 3; i < n + 1; i++) {
			if (!numbers[i]) {
				prime[primeCnt++] = i;
			}

		}
		return Arrays.copyOf(prime, primeCnt);
	}

}
