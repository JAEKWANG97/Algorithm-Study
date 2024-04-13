import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_1976 {

	static int[] parents;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		for(int idx = 1; idx<=N;idx++) {
			parents[idx] = idx;
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int connect = Integer.parseInt(st.nextToken());
				if(connect==1) union(i,j);
			}
		}
		boolean flag = true;
		int root = -1;
		st = new StringTokenizer(br.readLine());
		for(int m=0;m<M;m++) {
			int num = Integer.parseInt(st.nextToken());
			if(root==-1) root = find(num);
			else {
				if(root!=find(num)) {
					flag = false;
					break;
				}
			}
		}
		System.out.println(flag?"YES":"NO");
	}	
	private static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static void union(int a, int b) {
		int aFind = find(a);
		int bFind = find(b);
		if(aFind==bFind) return;
		if(aFind>bFind) parents[aFind] = bFind;
		else parents[bFind] = aFind;
	}

}
