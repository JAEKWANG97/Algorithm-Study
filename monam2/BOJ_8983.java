import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_8983 {
	private static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		Node[] shotLoca = new Node[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			shotLoca[i] = new Node(Integer.parseInt(st.nextToken()), 0);
		}
		
		ArrayList<Node> animals = new ArrayList<Node>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (b > l) continue;
			animals.add(new Node(a, b));		
		}
		
		Arrays.sort(shotLoca, (a,b)->a.x - b.x);
		
		HashSet<Node> result = new HashSet<Node>();
		for (Node animal : animals) {
			
			int left = 0;
			int right = shotLoca.length-1;
			
			while (left<=right) {
				int mid = (left + right) / 2;
				
				if (getDist(animal, shotLoca[mid]) <= l) {
					result.add(animal);
					break;
				}
				
				if (animal.x < shotLoca[mid].x) {
					right--;
					continue;
				}
				
				left++;
			}
		}
		
		System.out.println(result.size());
	}//main
	
	private static int getDist(Node a, Node b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}//class
