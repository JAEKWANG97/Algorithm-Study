import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	private static class Node{
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	private static List<List<Node>> list;
	private static int[] time;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int n, d, c;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			time = new int[n + 1];
			list = new ArrayList<>();
			
			Arrays.fill(time, Integer.MAX_VALUE);
			for(int i = 0; i <= n; i++) {
				list.add(new ArrayList<>());
			}
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a, b, s;
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				
				list.get(b).add(new Node(a, s));
			}
			
		
			solution(c);
			
			int virusCnt = 0, virusTime = 0;
			for(int x : time) {
				if(x != Integer.MAX_VALUE) {
					virusCnt ++;
					virusTime = Math.max(virusTime, x);
				}
			}
			
			System.out.println(virusCnt + " " + virusTime);
		}
	}

	private static void solution(int c) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(c);
		time[c] = 0;
		
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			
			for(int i = 0; i < list.get(poll).size(); i++) {
				Node now = list.get(poll).get(i);
				
				if(time[now.idx] > time[poll] + now.cost) {
					time[now.idx] = time[poll] + now.cost;
					queue.add(now.idx);
				}
			}
		}

	}
}
