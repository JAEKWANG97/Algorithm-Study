import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int loc;
	int sec;
	public Node(int loc, int sec) {
		this.loc = loc;
		this.sec = sec;
	}
	
}
public class Main {
	static int N, K;
	static Queue<Node> q = new LinkedList<>();
	static int visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		q.add(new Node(N,1));
		visited = new int [100001];
		visited[N] = 1;
		if(N>=K) System.out.println(N-K);
		else System.out.println(move());

		
	}
	static int move() {
		while(!q.isEmpty()) {
			Node present = q.poll();

			if(present.loc*2<=100000) {
				if(visited[present.loc*2]== 0 || visited[present.loc*2]>present.sec) {
					q.add(new Node(present.loc*2, present.sec));
					visited[present.loc*2]= present.sec;

				}
			}
			if(present.loc-1<=100000 && present.loc-1>=0) {
				if(visited[present.loc-1]== 0 || visited[present.loc-1]>present.sec+1) {
					q.add(new Node(present.loc-1, present.sec+1));
					visited[present.loc-1]= present.sec+1;
					
				}
			}
			if(present.loc+1<=100000) {
				if(visited[present.loc+1]== 0 || visited[present.loc+1]>present.sec+1) {
					q.add(new Node(present.loc+1, present.sec+1));
					visited[present.loc+1]= present.sec+1;
					
				}
			}
		}
		return visited[K]-1;
	}



}
