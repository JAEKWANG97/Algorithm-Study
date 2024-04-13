import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int n,m,answer;
	private static List<List<Integer>> list = new ArrayList<>();
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		answer=0;
		for(int i=0;i<n;i++) {
			if(answer==1) break;
			
			visited = new boolean[n];
			visited[i] = true;
			dfs(i,1);
		}

		System.out.println(answer);
	}
	
	private static void dfs(int x,int depth) {
		if(depth==5) {
			answer=1;
			return;
		}
		
		for(int i=0;i<list.get(x).size();i++) {
			int num = list.get(x).get(i);
			if(!visited[num]) {
				visited[num] = true;
				dfs(num,depth+1);
				visited[num] = false;
			}
		}
	}
}

