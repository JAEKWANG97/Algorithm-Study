import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14889 {
	static int N, map[][], answer = Integer.MAX_VALUE;
	static boolean teamSelect[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		teamSelect = new boolean[N];
		map = new int[N][N];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		makeTeam(0, 0);
		System.out.println(answer);
	}
	// 팀 만들기
	static void makeTeam(int cnt, int start) {

		if (cnt == N/2) {
			answer = Math.min(answer, calc());
			return;
		}
		
		for(int i = start; i < N; i++) {		
			teamSelect[i] = true;
			makeTeam(cnt+1, i+1);
			teamSelect[i] = false;
		}		
	}
	// 값 계산
	static int calc() {
		int sumA = 0, sumB = 0;
		
		for (int pre = 0; pre < N-1; pre++) {
			for (int post = pre+1; post < N; post++) {
				if (teamSelect[pre] && teamSelect[post]) sumA += map[pre][post] + map[post][pre];
				else if (!teamSelect[pre] && !teamSelect[post]) sumB += map[pre][post] + map[post][pre];
			}
		}
		return Math.abs(sumA - sumB);
	}
}