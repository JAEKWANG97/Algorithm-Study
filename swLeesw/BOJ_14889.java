import java.util.*;
import java.io.*;

public class BOJ_14889 {
	
	static int n, arr[][];//n은 짝수
	static int sol = Integer.MAX_VALUE;
	static boolean visited[];
	
	static void solve() {
		ArrayList<Integer> start = new ArrayList<>();
		ArrayList<Integer> link = new ArrayList<>();
		int startScore = 0;
		int linkScore = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				start.add(i);
			} else {
				link.add(i);
			}
		}
		
		for (int i = 0; i < start.size() - 1; i++) {
			for (int j = i + 1; j < start.size(); j++) {
				startScore += arr[start.get(i)][start.get(j)];
				startScore += arr[start.get(j)][start.get(i)];
			}
		}
		for (int i = 0; i < link.size() - 1; i++) {
			for (int j = i + 1; j < link.size(); j++) {
				linkScore += arr[link.get(i)][link.get(j)];
				linkScore += arr[link.get(j)][link.get(i)];
			}
		}
		sol = Math.min(sol, Math.abs(startScore - linkScore));
		
	}
	
	static void combi(int r, int c) {
		
		if (c == n / 2) {
			solve();
			return;
		}
		
		for (int i = r; i < n; i++ ) {
			if (!visited[i]) {
				visited[i] = true;
				combi(i + 1, c + 1);
				visited[i] = false;
			}
			
		}	
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[n];
		
		combi(0, 0);
		System.out.println(sol);
	}
	
	
}
