import java.io.BufferedReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class BOJ_Main_2251 {

	static int[] buckets;
	static boolean[][][] visited;
	static Queue<Integer> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		pq = new PriorityQueue<>();
		buckets = new int[3];
		for(int i=0;i<3;i++) {
			buckets[i] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[buckets[0]+1][buckets[1]+1][buckets[2]+1];
		int[] arr = {0,0,buckets[2]};
		dfs(arr);
		while(!pq.isEmpty()) System.out.print(pq.poll()+" ");
	}
	private static void dfs(int[] arr) {
		if(visited[arr[0]][arr[1]][arr[2]]) return;
		visited[arr[0]][arr[1]][arr[2]] = true;
		if(arr[0]==0) {
			pq.add(arr[2]);
		}
		
		//물통에 물이 있는지 탐색	
		for(int i=0;i<3;i++) {
			//만약 물이 있다면
			if(arr[i]!=0) {
				//다른 물통에 옮기기
				for(int j = 1; j<=2; j++) {
					int ni = (i+j)%3;
					int tmp1 = arr[ni];
					int tmp2 = arr[i];
					//ni 물통이 다 꽉 찼을 때
					if(arr[i]+arr[ni]>buckets[ni]) {
						int water = buckets[ni]-arr[ni];
						arr[ni] = buckets[ni];
						arr[i] -= water;
						dfs(arr);
						arr[ni] = tmp1;
						arr[i] = tmp2;
					}else if(arr[i]+arr[ni]<=buckets[ni]){
						arr[ni] += arr[i];
						arr[i] = 0;
						dfs(arr);
						arr[ni] = tmp1;
						arr[i] = tmp2;
					}
				}
			}
		}
	}

}
