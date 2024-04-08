import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.StringTokenizer;

public class BOJ_Main_10282 {
	static class Dpd implements Comparable<Dpd>{
		int to, s;
		public Dpd(int to,int s) {
			this.to = to;
			this.s = s;
		}
		
		public int compareTo(Dpd o) {
			return this.s-o.s;
		}

		@Override
		public String toString() {
			return "Dpd [to=" + to + ", s=" + s + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int C= Integer.parseInt(st.nextToken());
			List<List<Dpd>> arr = new ArrayList<>();
			for(int n=0;n<=N;n++) {
				arr.add(new ArrayList<>());
			}
			for(int d=0;d<D;d++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				arr.get(b).add(new Dpd(a,s));
			}
			int[] times = new int[N+1];
			boolean[] visited = new boolean[N+1];
			Arrays.fill(times,Integer.MAX_VALUE);
			int com = 0;
			int time = 0;
			times[C] = 0;
			for(int n=0;n<N;n++) {
				int min_idx = -1;
				int min = Integer.MAX_VALUE;
				for(int idx = 1; idx<=N;idx++) {
					if(min>times[idx]&&!visited[idx]) {
						min_idx = idx;
						min = times[idx];
					}
				}
				if(min_idx==-1)break;
				visited[min_idx] = true;
				com++;
				time+=min;
				for(int idx=0;idx<times.length;idx++) {
					if(times[idx] == Integer.MAX_VALUE)continue;
					times[idx]-=min;
				}
				for(int idx=0;idx<arr.get(min_idx).size();idx++) {
					Dpd tmp = arr.get(min_idx).get(idx);
					if(visited[tmp.to]||times[tmp.to]<tmp.s)continue;
					times[tmp.to] = tmp.s;
				}
			}
			System.out.println(com + " "+time);
		}
	}
	
}
