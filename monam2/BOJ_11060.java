import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_11060 { //백준 11060 점프 점프 - 40분
	
	static int n, arr[], visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		visited = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		if (n==1) {
			System.out.println(0);
			return;
		}
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.offer(1);

		
		while (!q.isEmpty()) {
			int x = q.poll();
			
			if (x + arr[x] >= n) {
				System.out.println(visited[x]+1);
				return;
			}
			
			for (int i = 1; i <= arr[x]; i++) {
				int nx = x + i;
				if (visited[nx] == 0) {
					q.offer(nx);
					visited[nx] = visited[x]+1;
				}
			}
		}
		System.out.println(-1);
	}//main
}//class
