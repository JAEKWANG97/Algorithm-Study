import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1107 { //백준 1107 리모컨 - 100분

	static int n, m, result;
	static boolean broken[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		broken = new boolean[10];
		Arrays.fill(broken, true);
		if (m > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				int ipt = Integer.parseInt(st.nextToken());
				broken[ipt] = false;
			}
		}
		
		if (n == 100) { //목표가 초기 채널이라면 그냥 종료함
			System.out.println(0);
			return;
		}
		
		result = Math.abs(n - 100);
		
		dfs(0, 0);
		System.out.println(result);

		//6 7 8 -> 
		// [true, true, true, true, true, true, false, false, false, true]

	}//main
	
	private static void dfs(int idx, int click) {
		for (int i = 0; i < 10; i++) {
			if (broken[i]) {
				int btn = click*10 + i;
				int cnt = Math.abs(n - btn) + String.valueOf(btn).length();
				result = Math.min(cnt, result);
				
				if (idx < 6) {
					dfs(idx+1, btn); //이전에 누른 버튼
				}
			}
		}
	}//dfs
}//class
