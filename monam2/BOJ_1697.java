import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_1697 { //백준 1697 숨바꼭질 - 15분 28초
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] dir = {-1,1};
		boolean[] visited = new boolean[100001];
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {n, 0});
		visited[n] = true;
		
		while(!q.isEmpty()) {
			int now = q.peek()[0];
			int time = q.poll()[1];
			
			if (now==k) {
				System.out.println(time);
				break;
			}
			
			int next;
			for (int d = 0; d < 3; d++) {
				if (d==2) {
					next = now*2;
				} else {
					next = now + dir[d];
				}
				
				if (next>=0 && next<100001 && !visited[next]) {
					q.offer(new int[] {next, time+1});
					visited[next] = true;
				}
			}
		}
	}//main
}//class
