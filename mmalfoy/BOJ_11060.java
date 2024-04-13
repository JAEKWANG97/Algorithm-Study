import java.io.*;
import java.util.*;

public class BOJ_11060 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] nums = new int[N];		
		boolean[] visited = new boolean[N];		
		for (int n = 0; n < N; n++) {
			nums[n] = Integer.parseInt(st.nextToken());
		}

		if (N == 1) {
			System.out.println(0);
			return;
		}
		
		Queue<Node> q = new ArrayDeque<>();
		visited[0] = true;
		q.offer(new Node(0, 0));
	
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.value == N-1) {
				System.out.println(cur.depth);
				return;
			}
			
			for (int i = 1; i <= nums[cur.value]; i++) {
				int next = i + cur.value;				
				if (next < N && !visited[next]) {
					visited[next] = true;
					q.offer(new Node(next, cur.depth + 1));
				}
			}
		}
		System.out.println(-1);
	}
}

class Node {
	int value;
	int depth;
	
	Node (int value, int depth) {
		this.value = value;
		this.depth = depth;
	}
}
