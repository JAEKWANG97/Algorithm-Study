import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2138 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 전구 개수
		
		int now[] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray(); // 현재 전구 상태
		int answer[] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray(); // 완성된 전구 상태
		
		int cnt = solve(N, now.clone(), answer, 0); // 첫번째 스위치 안 킨 경우

		now[0] = (now[0]+1) % 2;
		now[1] = (now[1]+1) % 2;
		int cnt2 = solve(N, now, answer, 1); // 첫번째 스위치 킨 경우
		int minCount;
		if (cnt == -1 && cnt2 == -1) minCount = -1; // 둘다 불가능하면 -1
		else if (cnt >= 0 && cnt2 >= 0) minCount = Math.min(cnt, cnt2); // 둘다 가능하면 최소 횟수
		else minCount = Math.max(cnt, cnt2); // 한쪽만 가능하면 -1이 아닌 값
		sb.append(minCount);
	}
	
	private static int solve(int N, int now[], int answer[], int cnt) throws Exception {
		for (int i = 1; i < N-1; i++) {
			if (now[i-1] != answer[i-1]) {
				now[i-1] = (now[i-1]+1) % 2;
				now[i] = (now[i]+1) % 2;
				now[i+1] = (now[i+1]+1) % 2;
				cnt++;
			}
		}
		
		if (now[N-1] != answer[N-1]) {
			now[N-2] = (now[N-2]+1) % 2;
			now[N-1] = (now[N-1]+1) % 2;
			cnt++;
		}
		
		for (int i = 0; i < N; i++) {
			if (now[i] != answer[i]) {
				cnt = -1;
				break;
			}
		}
		
		return cnt;
//		System.out.println(Arrays.toString(now));
//		System.out.println(Arrays.toString(answer));
		
	}
}

