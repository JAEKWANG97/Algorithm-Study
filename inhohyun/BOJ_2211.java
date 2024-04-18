
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_2211 {
	static class edge implements Comparable<edge>{
		int  end, value;
		
		public edge(int end, int value) {
			this.end = end;
			this.value = value;
		}

		@Override
		public int compareTo(edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(value, o.value);
		}
	}
	
	static int n, m;
	static List<edge>[] edgeList;
	static int[] path, dist; // 경로와 거리를 저장할 배열 두 개
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		edgeList = new ArrayList[n + 1];
		for (int i = 1; i <= n; ++i) {
			edgeList[i] = new ArrayList<>();
		}
		//가중치 값 입력 받기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edgeList[a].add(new edge(b,c));
			edgeList[b].add(new edge(a,c));	
		}
		
		Dijkstra();
		StringBuilder sb = new StringBuilder();
		int ans = 0;
		for(int i = 2; i <= n; i++) {
			if(path[i] == 0) continue;
			
			ans++;
			sb.append(i+" "+path[i]+"\n");
		}
		
		System.out.println(ans);
		System.out.println(sb);
		
	}
	
	
	static void Dijkstra() {
		dist = new int[n+1];
		path = new int[n+1];
		Arrays.fill(dist, (int)1e9);
		dist[1] = 0;
		Queue<edge> pq = new PriorityQueue<>();
		pq.offer(new edge(1, 0));

		pq.add(new edge(1, 0)); //  초기 1번경로
		

		while(!pq.isEmpty()) {
			edge e = pq.poll();

			if (e.value > dist[e.end]) continue;

			for (edge ne : edgeList[e.end]) {
				if (dist[ne.end] > e.value + ne.value) {
					dist[ne.end] = e.value + ne.value;
					path[ne.end] = e.end; //경로 저장
					pq.offer(new edge(ne.end, dist[ne.end]));
				}
			}
		}
		
		 
	}
	
	
	
}

