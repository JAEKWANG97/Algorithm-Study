import java.util.*;
import java.io.*;

public class BOJ_13023 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 친구 그래프 만들고
		// 4회 탐색 가능하면 pass 
		List<Node> graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new Node(i));
		}
		
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Node A = graph.get(a);
			Node B = graph.get(b);
			
			A.add(B);
			B.add(A);		
		}
		
		for (Node node : graph) {
			HashSet<Node> visited = new HashSet<>();
			
			// node next 이동
			
			try {
				dfs(node, 1, visited);
			} catch (EndOfProgramException e) {
				System.out.println(1);
				return;
			} 
		}
		
		// 불 가능 할 때
		System.out.println(0);
		
	}
	
	private static void dfs(Node node, int cnt, HashSet<Node> visited) throws EndOfProgramException {
		
		
		if (cnt == 5 && !visited.contains(node)) {
			
			throw new EndOfProgramException();
		}
		
		if (visited.contains(node)) {
			return;
		}
		
		visited.add(node);
		for (Node next : node.nexts) {
			dfs(next, cnt + 1, visited);
		}
		visited.remove(node);
	}

}

class Node {
	public List<Node> nexts;
	public int value;
	
	Node(int value) {
		this.nexts = new ArrayList<>();
		this.value = value;
	}
	
	public void add(Node node) {
		nexts.add(node);
	}
	
	public int edgeSize() {
		return nexts.size();
	}
}

 class EndOfProgramException extends Exception {
	 EndOfProgramException(){
		 super();
	 }	 
 }
