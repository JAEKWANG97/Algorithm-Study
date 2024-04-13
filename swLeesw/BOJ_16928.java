import java.io.*;
import java.util.*;

public class Main {
	
	static class Info {
		int pos, depth;

		public Info(int pos, int depth) {
			this.pos = pos;
			this.depth = depth;
		}
	}

	static int n, m, arr[];
	static boolean visited[];
	
	
	static void bfs() {
		Queue<Info> que = new ArrayDeque<>();
		que.offer(new Info(1, 0));
		visited[1] = true;
		while (!que.isEmpty()) {
			Info cur = que.poll();
			
			if (cur.pos == 100) {
				System.out.println(cur.depth);
				return;
			}
			
			for (int i = 1; i <= 6; i++) {
				int nextPos = cur.pos + i;
				int nextDepth = cur.depth + 1;
				if (nextPos == 100) {
					System.out.println(nextDepth);
					return;
				}
				if (nextPos > 100) break; //다음 위치가 100이상이면 멈추기
				if (!visited[nextPos]) {
					visited[nextPos] = true; //일단 방문체크
					if (arr[nextPos] != 0) { //사다리나 뱀이면?
						if (!visited[arr[nextPos]]) { //사다리나 뱀으로 이동한 부분이 방문하지 않은 경우에만 넣기
							visited[arr[nextPos]] = true;
							que.offer(new Info(arr[nextPos], nextDepth));
						}
					} else { //그냥 길이면
						que.offer(new Info(nextPos, nextDepth));
					}
				}
			}
			
			
		}
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[101]; //0 안 씀
		visited = new boolean[101];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for (int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());			
		}
		
		bfs();
		
		
		//init end
		
		
	}
	
}
