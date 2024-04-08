import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_Main_9205 {
	static class Move{
		int x,y;
		public Move(int x,int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public String toString() {
			return "Move [x=" + x + ", y=" + y +"\n";
		}
		
	}
	static int R,C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int houseX = Integer.parseInt(st.nextToken());
			int houseY = Integer.parseInt(st.nextToken());
			List<int[]> stores = new ArrayList<>();
			for(int n=0;n<N;n++) {
				st = new StringTokenizer(br.readLine());
				stores.add(new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),n});
			}
			st = new StringTokenizer(br.readLine());
			int festivalX = Integer.parseInt(st.nextToken());
			int festivalY = Integer.parseInt(st.nextToken());
			R = festivalY;
			C = festivalX;
			Queue<Move> que = new ArrayDeque<>();
			que.offer(new Move(houseX,houseY));
			boolean[] visited = new boolean[N];
			boolean flag = false;
			while(!que.isEmpty()) {
				Move m = que.poll();
				for(int[] store:stores) {
					if(Math.abs(m.x-store[0])+Math.abs(m.y-store[1])<=1000&&!visited[store[2]]) {
						que.add(new Move(store[0],store[1]));
						visited[store[2]]=true;
					}
				}
				if(Math.abs(m.x-festivalX)+Math.abs(m.y-festivalY)<=1000) {
					flag = true;
					break;
				}
			}
			System.out.println(flag?"happy":"sad");
		}

	}

}
