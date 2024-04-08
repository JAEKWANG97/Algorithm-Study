import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2660 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

	static int N;

	static int[][] map;

	static List<List<Integer>> adjList;

	static int minCount;
	static List<Integer> list;
	static int personCount;

	public static void main(String[] args) throws IOException {
		init();
		simulate();
		
        sb.append(minCount).append(" ").append(list.size()).append("\n");
		Collections.sort(list);
		for(Integer person : list) {
			sb.append(person).append(" ");
		}
        System.out.println(sb);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		adjList = new ArrayList<>();
		minCount = Integer.MAX_VALUE;
		
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(from == -1 && to == -1) {
				break;
			}
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
	}

	private static void simulate() {
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
	}

	private static void bfs(int start) {
		Queue<Integer> que = new ArrayDeque<>();
		
		boolean[] visited = new boolean[N + 1];
		visited[start] =true;
		for (Integer to : adjList.get(start)) {
			que.add(to);
			visited[to] = true;
		}

		int time = 0;

		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- > 0) {

				int cur = que.poll();

				for (Integer next : adjList.get(cur)) {
					if (!visited[next]) {
						que.add(next);
						visited[next] = true;
					}
				}
			}
			
			time+=1;
		}
		
		if(minCount > time) {
			minCount = time;
			list = new ArrayList<>();
			list.add(start);
		}else if(minCount == time){
			list.add(start);
		}
		
	}

}
