package algorithm_study.day_03_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471 {
	
	// N: 노드의 수
	private static int N;
	private static List<int[]> graph = new ArrayList<>();
	private static boolean[] A;
	private static int[] population;
	private static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		A = new boolean[N + 1];
		graph.add(null);
		for (int line = 0; line < N; line++) {
			st = new StringTokenizer(br.readLine());
			int adjNum = Integer.parseInt(st.nextToken());
			int[] adjacent = new int[adjNum];
			for (int i = 0; i < adjNum; i++) {
				adjacent[i] = Integer.parseInt(st.nextToken());
			}
			graph.add(adjacent);
		}
		
		for (int i = 1; i <= N/2; i++) {
			comb(0, i, 1);
		}
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
		
	}

	// 조합을 boolean으로 표현 true는 집단 A, false는 집단 B
	public static void comb(int cnt, int i, int start) {
		if(cnt == i) {
			if(bfs() == true) calDiff();
			//System.out.println(Arrays.toString(A));

		return;
		}
		 
		 for (int j = start; j <= N; j++) {
			 A[j] = true;
			 comb(cnt + 1, i, j + 1);
			 A[j] = false;
		}
	}
	
	public static boolean bfs() {
		
		// A 선거구에서 start point(true) 선정
		int start = -1;
		boolean[] visited = new boolean[N + 1];
		for (int i = 1; i <= A.length; i++) {
			if(A[i] == true) {
				start = i;
				break;
			}
		}
		
		// A 선거구가 유효한 그래프인지 확인
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for (int i = 0; i < graph.get(curr).length; i++) {
				if(A[graph.get(curr)[i]] == true && !visited[graph.get(curr)[i]]) {
					visited[graph.get(curr)[i]] = true;
					queue.offer(graph.get(curr)[i]);
				}
			}
		}
		
		// A와 방문 배열이 같으면 유효한 그래프 
		boolean isGraphA = Arrays.equals(visited, A);
		
		
		for (int i = 1; i <= A.length; i++) {
			if(A[i] == false) {
				start = i;
				break;
			}
		}
		
		// B 선거구에서 start point(false) 선정
		queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;
		
		// A 선거구가 유효한 그래프인지 확인
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for (int i = 0; i < graph.get(curr).length; i++) {
				if(A[graph.get(curr)[i]] == false && !visited[graph.get(curr)[i]]) {
					visited[graph.get(curr)[i]] = true;
					queue.offer(graph.get(curr)[i]);
				}
			}
		}
		
		// 방문 배열이 모두 true이면 B는 유효한 선거구
		boolean isGraphB = true;
		for (int i = 1; i < visited.length; i++) {
			if(visited[i] == false) {
				isGraphB = false;
				break;
			}
		}
		
		// 둘 다 유효하면 true 반환 
		return (isGraphA && isGraphB);
	}
	
	// 인구수 계산
	public static void calDiff() {
		int sumA = 0;
		int sumB = 0;
		for (int i = 1; i < A.length; i++) {
			if(A[i] == true) sumA += population[i];
			else sumB += population[i];
		}
		
		int diff = Math.abs(sumA - sumB);
		
		if(min > diff) min = diff; 
	}
		
}
