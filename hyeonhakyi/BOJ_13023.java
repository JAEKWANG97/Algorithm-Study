package ex0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
	private static int n,m,answer = 0;
	private static ArrayList<Integer>[] list;
	private static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		list = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i = 0; i < n; i++) {
			if(answer == 0) {
				dfs(0,i);
			}
		}
		
		System.out.println(answer);
	}//main end
	
	private static void dfs(int count,int idx) {
		if(count == 4) {
			answer = 1;
			return;
		}
		
		visited[idx] = true;
		for(int i : list[idx]) {
			if(!visited[i]) {
				dfs(count+1,i);
			}
		}
		visited[idx] = false;
	}
}//class end
