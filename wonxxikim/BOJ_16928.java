import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] map = new int[101];
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			int x=  Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x] = y;
		}
		for(int i = 0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			map[u] = v;
		}
		int[] result = new int[101];
		Arrays.fill(result, Integer.MAX_VALUE);
		Queue<Integer> q = new LinkedList<>();
		for(int i = 2 ; i<=7 ; i++) {
			if(map[i] == 0) {
				result[i] = 1;
				q.add(i);
			}
			else {
				result[map[i]] = 1;
				q.add(map[i]);
			}
		}
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 1 ; i<=6 ; i++) {
				int next = cur+i;
				if(next <101 && map[next] == 0 && result[cur]+1<result[next]) {
					result[next] = result[cur]+1;
					q.add(next);
				}
				if(next<101 && map[next]!=0 && result[cur]+1<result[map[next]]) {
					result[map[next]] = result[cur]+1;
					
					q.add(map[next]);
				}
			}
			
		}
		System.out.println(result[100]);

	}
}
