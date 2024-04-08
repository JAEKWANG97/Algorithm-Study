
import java.util.*;
import java.io.*;

public class BOJ_1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				int result = Integer.compare(o1.to, o2.to);
				return result != 0 ? result : Integer.compare(o1.from, o2.from);
			}
		});
		
		Stack<Node> stk = new Stack<>();
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			pq.offer(new Node(from, to));
		}
        
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (!stk.isEmpty() && stk.peek().to > node.from) {
				continue;
			}
			stk.push(node);
		}
		System.out.println(stk.size());
	}
}

class Node {
	int from;
	int to;
	
	Node (int from, int to) {
		this.from = from;
		this.to = to;
	}
}
