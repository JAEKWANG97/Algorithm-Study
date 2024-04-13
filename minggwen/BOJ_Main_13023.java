import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_Main_13023{

	static int N,M;
	static List<List<Integer>> edges;
	static boolean[] visited;
	static boolean flag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();
		for(int n=0; n<N;n++) {
			edges.add(new ArrayList<>());
		}
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to =  Integer.parseInt(st.nextToken());
			edges.get(from).add(to);
			edges.get(to).add(from);
		}
		for(int n=0;n<N;n++) {
			visited = new boolean[N];
			visited[n]=true;
			dfs(n,0);
			if(flag) break;
			visited[n] = false;
		}
		System.out.println(flag?1:0);
	}
	private static void dfs(int start,int cnt) {
		if(cnt==4) {
			flag = true;
			return;
		}
		for(int i = 0; i<edges.get(start).size();i++) {
			int num = edges.get(start).get(i);
			if(visited[num])continue;
			visited[num] = true;
			dfs(num,cnt+1);
			visited[num] = false;
		}
	}
}