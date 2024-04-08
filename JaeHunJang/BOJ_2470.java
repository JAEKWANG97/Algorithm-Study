import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 두 용액 - 2시간
public class BOJ_2470 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		solve(arr, N);
	}
	
	static void solve(int[] arr, int N){
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

		if(arr[N - 1] <= 0) {
			sb.append(arr[N - 2]).append(" ").append(arr[N - 1]);
			return;
		}
		if(arr[0] >= 0) {
			sb.append(arr[0]).append(" ").append(arr[1]);
			return;
		}

		int answerS = 0, answerE = N-1;
		int start = 0, end = N-1;
		int minGap = Integer.MAX_VALUE;
		while(start <= end) {
			int nowGap = arr[start] + arr[end];
			if (nowGap == 0) {
				answerS = start;
				answerE = end;
				break;
			} else if (nowGap > 0) {
				if (minGap > nowGap) {
					minGap = nowGap;
					answerS = start;
					answerE = end;
				}
				end--;
			} else if (nowGap < 0){
				if (minGap > Math.abs(nowGap)) {
					minGap = Math.abs(nowGap);
					answerS = start;
					answerE = end;
				}
				start++;
			}
			if (start == end) end--;
		}

		sb.append(arr[answerS]).append(" ").append(arr[answerE]);
	}
}