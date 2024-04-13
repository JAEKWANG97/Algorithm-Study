import java.io.*;
import java.util.*;

public class BOJ_2531 {
	
	static int n, d, k, c, graph[], sol;
	static int visited[];
	static int s, e; //슬라이딩 윈도우 start지점, end지점
	//n : 회전 초밥 벨트에 놓인 접시 수, graph[]: 그래프
	//d : 초밥의 가짓수, visited[] : 선택된 초밥들 방문 처리
	//k : 연속해서 먹은 접시 수
	//c : 쿠폰 번호
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		graph = new int[n];
		visited = new int[d + 1];
		int cnt = 0;//초밥의 종류
		for (int i = 0; i < n; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < k; i++) {//슬라이딩 윈도우 init
			if (visited[graph[i]] == 0) { //초밥 없으면 가짓수 늘리기
				cnt++;
			}
			visited[graph[i]]++;
		}
		if (visited[c] == 0) { //초밥 종류가 없는거면
			sol = cnt + 1;
		} else {
			sol = cnt;
		}
		s = 0; e = k - 1; //시작 idx
		//init end
		for (int i = 1;  i < n; i++) {//슬라이딩 횟수(init 때 한 번 했으므로 1부터)
			if (visited[graph[s]] == 1) {//하나 남았으면
				cnt--;
			}
			visited[graph[s]]--; //s증가 시키고 이전 s 빼기
			s++;
			
			e = (e + 1) % n; //원형 큐와 비슷하게
			visited[graph[e]]++;//e 옮기기
			if (visited[graph[e]] == 1) {//하나 선택된 거면
				cnt++;
			}
			if (visited[c] == 0) { //쿠폰 초밥 체크
				sol = Math.max(sol, cnt + 1);
			} else {
				sol = Math.max(sol, cnt);
			}
		}
		System.out.println(sol);
	}
	
	
}