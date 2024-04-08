import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

// 2023. 신기한 소수 / 40분
public class BOJ_2023 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] primeNum = {2, 3, 5, 7};
	static List<Integer> list;
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 자리수
		
		solve();
	}
	
	private static void solve() {
		for (int i = 0; i < primeNum.length; i++) {
			recursive(1, primeNum[i]);
		}
	}

	private static void recursive(int count, int num) {
		// 자리수마다 만든 숫자가 소수여야 하기 때문에 소수가 아니라면 더이상 진행할 필요 없음.
		if (!isPrime(num)) return; 

		// 자리수 만큼 만든 숫자는 소수로 저장
		if (count == N) {
			sb.append(num).append("\n");
		}
		
		// 1부터 시작 (0 으로 시작시 다음 값은 10만 곱해지는데 이 경우 2로 나눠질 수 있기 때문에 제외)
		for (int i = 1; i <= 9; i+=2) {
			recursive(count+1, num*10+i);
		}
	}

	private static boolean isPrime(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}