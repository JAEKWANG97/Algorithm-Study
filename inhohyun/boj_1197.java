import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1197 {
	static class Edge implements Comparable<Edge>{
		int from, to, weigt;
		
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weigt = weight;
		}
		
		//무게 중심으로 정렬
		@Override
		public int compareTo(Edge o) {
			
			return Integer.compare(weigt, o.weigt);
		}
	}
	
	static int V, E, parents[];
	static Edge[] edgeList;
	
	
	
	public static void main(String[] args) throws IOException {
		init(); // 초기화
		
		make();
		
		System.out.println(kruskal()); // 크루스칼 사용해서 mst의 가중치 중첩 리턴
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		parents = new int[V+1];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight); // 간선 정보 저장하기
			
		}	
	
		

	}
	
	static long kruskal() {
		Arrays.sort(edgeList); // 가중치 중심으로 정렬하기(가중치가 작은 것부터 가져올 것임)
		int cnt = 0;
		long weight = 0;
		for(Edge edge  : edgeList) {
			if(!union(edge.from, edge.to)) continue; // 같은 집합이면 건뛰, 이미 mst에 포함된 노드임
			weight += edge.weigt; // 가중치 중첩
			
			cnt++;
			if(cnt == V-1) {
				break;
			}
		}
		return weight;
	}
	
	//유니온 파인드 만들기
	static void make() {
		for(int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	//루트 노드 찾기
	static int find(int a) {
		
		if(parents[a] == a) return a;
		
			
		return parents[a] = find(parents[a]); // 경로 압축
			
	}
	
	//유니온
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; // 부모가 같으면 같은 집합이라 유니온 불가
		
		parents[aRoot] = bRoot; // 아니면 부모 동기화
		
		return true;
	}
	
	
}
