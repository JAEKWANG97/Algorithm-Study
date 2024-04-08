
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static Queue<Integer> q;
	static int N, map[][];
	static boolean[] visit;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine()); 
		map = new int[N+1][N+1];
		for(int i = 1 ; i<=N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j<=N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] trip = new int[M];
		for(int i = 0; i<M; i++) {
			trip[i] = Integer.parseInt(st.nextToken());
		}
		boolean flag = true;
		for(int i = 0 ; i<M-1;i++) {
			q = new LinkedList<>();
			q.add(trip[i]);
			visit = new boolean[N+1];
			visit[trip[i]] = true;
			if(!bfs(trip[i+1])) {
				flag = false;
				break;
			}
		}
		if(flag==true) System.out.println("YES");
		else System.out.println("NO");

	}
	public static boolean bfs(int end) {
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == end) return true;
			for(int i = 1 ; i<=N;i++) {
				if(map[cur][i]==1 && !visit[i]) {
					q.add(i);
					visit[i] = true;
				}
			}
		}
		return false;
	}
}
