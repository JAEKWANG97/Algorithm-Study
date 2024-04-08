import java.io.*;
import java.util.*;

public class Main {
	
	
	static ArrayList<Integer> graph[];//areaCnt : 노드 방문 횟수 체크 aC == n이면 다 방문 한 것
	static int n, m, peopleCnt[], sol, areaCnt; //n: 노드 수, peopleCnt : 노드당 인구 수, sol 답
	static boolean visited[], area[];//visited : 그래프
	//area : 구역
	
	static void dfs(int s, boolean check) {
		visited[s] = true;
		areaCnt++;
		for (int i = 0; i < graph[s].size(); i++) {
			int next = graph[s].get(i);
			
			if (!visited[next] && area[next] == check) {
				dfs(next, check);
			}
		}
		
	}
	
	
	
	static void partial(int s, int c, int zNum) {
		if (c == n) {
			if (zNum == 0 || zNum == n) return;
			areaCnt = 0; //초기화
			int a1 = -1; // false여야함
			int a2 = -1; // true여야함
			for (int i  =0; i < n; i++) {
				if (a1 != -1 && a2 != -1) break; //시작 노드 찾으면 뺴자
				if (a1 == -1 && !area[i]) { //a1 시작점 찾기
					a1 = i;
				}
				if (a2 == -1 && area[i]) { //a2 시작점 찾기
					a2 = i;
				}
			}
			visited = new boolean[n];
			dfs(a1, false);
			visited = new boolean[n];
			dfs(a2, true);
			
			if (areaCnt == n) { //다 방문 가능하면
				int sum1 = 0;
				int sum2 = 0;
				for (int i = 0; i < n; i++) {
					if (!area[i]) {
						sum1 += peopleCnt[i];
					} else {
						sum2 += peopleCnt[i];						
					}
				}
				sol = Math.min(sol, Math.abs(sum1 - sum2));
			}
			return;
		}
		area[s] = false;
		partial(s + 1, c + 1, zNum);
		area[s] = true;
		partial(s + 1, c + 1, zNum + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sol = 2100000000;
		n = Integer.parseInt(br.readLine());
		area = new boolean[n];
		peopleCnt = new int[n];
		graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		//get peopleNum
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			peopleCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()) - 1); //node is 0 ~ n - 1
			}
		}
		//end input
		partial(0, 0, 0);
		if (sol == 2100000000) {
			System.out.println(-1);
		} else {
			System.out.println(sol);
		}
	}
	
}
