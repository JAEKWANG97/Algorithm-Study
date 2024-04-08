import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {
	static boolean[] visited;
	static int[] umap, dmap;
	static int[][] upper, lower;
	static int res=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 사다리수
		int m = Integer.parseInt(st.nextToken()); // 뱀의 수
		upper = new int[n][2];
		lower = new int[m][2];
		umap = new int[101];
		dmap = new int[101];
		visited = new boolean[101];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			upper[i][0] = Integer.parseInt(st.nextToken());
			upper[i][1] = Integer.parseInt(st.nextToken());
			umap[upper[i][0]] = i+1; 
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			lower[i][0] = Integer.parseInt(st.nextToken());
			lower[i][1] = Integer.parseInt(st.nextToken());
			dmap[lower[i][0]] = i+1;
		}
		
		bfs(1);
		System.out.println(res);
		
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			res++;
			for (int i = 0, qSize=queue.size(); i < qSize; i++) {
				int now = queue.poll();
				
				for (int j = 1; j <= 6; j++) {
					int move = now + j;
					if(move == 100) return;
					
					if(move > 100) continue;
					if(visited[move]) continue;
					
					visited[move] = true;
					if(umap[move]>0) {
						move = upper[umap[move]-1][1];
					}
					else if(dmap[move]>0) {
						move= lower[dmap[move]-1][1];
					}
					queue.add(move);
				}
			}
		}
	}
}
