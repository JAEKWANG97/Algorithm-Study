import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] toGo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		toGo = new int[101];
		
		for(int i = 0; i <= 100; i++) {
			toGo[i] = i;
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			toGo[from] = to;
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			toGo[from] = to;
		}
		
		int answer = rollingDice();
		System.out.println(answer);
	}
	
	private static int rollingDice() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		
		boolean[] visited = new boolean[101];
		int cnt = 0;
		visited[1] = true;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {				
				int poll = queue.poll();
		
				for(int jump = 1; jump <= 6; jump++) {
					int next = poll + jump;
					
					if(next >= 100) {
						cnt += 1;
						return cnt;
					}
					
					if(!visited[next]) {
						visited[next] = true;
						queue.add(toGo[next]);
					}
				}
			}
			cnt++;
		}
		
		return cnt;
	}
}

