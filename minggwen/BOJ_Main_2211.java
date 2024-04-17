import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_Main_2211 {
	
	static class Edge{
		int from;
		int to;
		int w;
		public Edge(int from,int to, int w) {
			super();
			this.from=from;
			this.to = to;
			this.w = w;
		}
		@Override
		public String toString() {
			return "Edge [to=" + to + ", w=" + w + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Edge>> edges = new ArrayList<>();
		for(int n=0;n<=N;n++)edges.add(new ArrayList<>());
		for(int m=0;m<M;m++) {
			st=new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.get(start).add(new Edge(start,end,weight));
			edges.get(end).add(new Edge(end,start,weight));
		}
		
		boolean visited[] = new boolean[N+1];
		int[] dp = new int[N+1];
		int[] path = new int[N+1];
		
		Arrays.fill(dp, 100000000);
		dp[1]=0;
		visited[0] = true;
		
		for(int n=1;n<=N;n++) {
			int minIdx=-1;
			int min = Integer.MAX_VALUE;
			for(int i=1;i<=N;i++) {
				if(min>dp[i]&&!visited[i]) {
					min = dp[i];
					minIdx = i;
				}
			}
			if(minIdx==-1)break;
			visited[minIdx]=true;
			for(int i=0;i<edges.get(minIdx).size();i++) {
				Edge e = edges.get(minIdx).get(i);
				if(!visited[e.to]&&dp[e.to]>min+e.w) {
					dp[e.to] = min+e.w;
					path[e.to] = minIdx;
				}
			}
		}
		int cnt=0;
		StringBuilder sb=new StringBuilder();
		for(int idx=2;idx<=N;idx++) {
			if(path[idx]!=0) {
				cnt++;
				sb.append(idx+" "+path[idx]).append("\n");
			}
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
