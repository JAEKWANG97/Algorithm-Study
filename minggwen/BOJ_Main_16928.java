import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_16928 {

	static int visited[],snake[],ladder[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		snake = new int[101];
		ladder = new int[101];
		visited = new int[101];
		Arrays.fill(visited,Integer.MAX_VALUE);
		for(int n=0; n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			ladder[start] = to;
		}
		for(int m=0; m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			snake[start] = to;
		}
		dfs(1,0);
		System.out.println(visited[100]);
	}
	private static void dfs(int dest, int cnt) {
		if(dest==100) return;
		for(int i =1;i<=6;i++) {
			if(dest+i>100||visited[dest+i]<cnt+1) break;
			if(snake[dest+i]!=0) {
				if(visited[snake[dest+i]]>cnt+1) {
					visited[dest+i] = cnt+1;
					visited[snake[dest+i]] = cnt+1;
					dfs(snake[dest+i],cnt+1);
				}
			}
			else if(ladder[dest+i]!=0) {
				if(visited[ladder[dest+i]]>cnt+1) {
					visited[dest+i] = cnt+1;
					visited[ladder[dest+i]] = cnt+1;
					dfs(ladder[dest+i],cnt+1);
				}
			}
			else{
				visited[dest+i] = cnt+1;
				dfs(dest+i,cnt+1);
			}
			
		}
	}
}
