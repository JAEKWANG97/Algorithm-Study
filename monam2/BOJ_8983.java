import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_8983 { //이분탐색으로 다시 풀기
	private static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}//Node
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		ArrayList<Node> shotLoca = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int t = Integer.parseInt(st.nextToken());
			shotLoca.add(new Node(t,0));
		}
		
		Node[] animals = new Node[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			animals[i] = new Node(a,b);
		}
		
		HashSet<Node> result = new HashSet<>();
		for (Node shot : shotLoca) {
			for (Node animal : animals) {
				if (getDist(shot.x, shot.y, animal.x, animal.y) <= l) {
					 result.add(animal);
				}
			}
		}
		System.out.println(result.size());
	}//main
	
	private static int getDist(int x1, int y1, int x2, int y2) {
		int dist = Math.abs(x2-x1) + Math.abs(y2-y1);
		return dist;
	}//getDist
}//class
