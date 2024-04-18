import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 중복 제거 / 30분
public class BOJ_13701 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		boolean visited[] = new boolean[33554433];
		while(st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			if (!visited[n]) {
				visited[n] = true;
				sb.append(n).append(" ");
			}
		}
	}
}
