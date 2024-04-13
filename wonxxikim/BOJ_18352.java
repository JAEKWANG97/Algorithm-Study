
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for(int i = 1 ; i<=N ; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list[A].add(B);
		}
		int answer = 0;
		int[] min = new int[N+1];
		min[X] =  1;
		Queue<Integer> q = new LinkedList<>();
		q.add(X);
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(min[cur] == K+1) {
				answer++;
				continue;
			}
			for(int x : list[cur]) {
				if(min[x]==0) {
					min[x] = min[cur]+1;
					q.add(x);
				}
			}
		}
		if(answer == 0) System.out.println(-1);
		else {
			for(int i = 1 ; i<=N ; i++) {
				if(min[i] == K+1) System.out.println(i);
			}
		}

	}

}
