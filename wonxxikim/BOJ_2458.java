import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
	
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] list = new ArrayList[N+1];
			ArrayList<Integer>[] reverse = new ArrayList[N+1];
			for(int i = 1; i<=N ; i++) {
				list[i] = new ArrayList<>();
				reverse[i] = new ArrayList<>();
			}
			for(int i = 0 ; i<M ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				reverse[b].add(a);
			}
			int result = 0;
			for(int i = 1 ;i<=N;i++) {
				Queue<Integer> q = new LinkedList<>();
				q.add(i);
				boolean[] visit = new boolean[N+1];
				int cnt = 0;
				while(!q.isEmpty()) {
					int cur = q.poll();
					for(int x : list[cur]) {
						if(!visit[x]) {
							q.add(x);
							visit[x] = true;
							cnt++;
						}
					}
				}
				q.add(i);
				while(!q.isEmpty()) {
					int cur = q.poll();
					for(int x : reverse[cur]) {
						if(!visit[x]) {
							q.add(x);
							visit[x] = true;
							cnt++;
						}
					}
				}
				if(cnt==N-1) result++;
				
			}
			System.out.println(result);
		}

	

}
