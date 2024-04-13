import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static List<List<Integer>> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n,m,k,x;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		for(int i=0; i<=n; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
		}
		
		for(int i : solution(x,k,n)) {
			System.out.println(i);
		}
	}
	
	private static List<Integer> solution(int start,int k,int n) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		List<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[n+1];
		
		visited[start] = true;
		int cnt=0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int j =0; j<size ;j++) {
				int poll = queue.poll();
				
				if(cnt==k) {
					result.add(poll);
					continue;
				}
				
				for(int i = 0; i<list.get(poll).size(); i++) {
					int num = list.get(poll).get(i);
					if(!visited[num]) {
						visited[num] = true;
						queue.add(num);
					}
				}
			}
			
			if(cnt==k) {
				break;
			}
			cnt++;
		}
		
		Collections.sort(result);
		if(result.size()==0) {
			result.add(-1);
		}
		return result;
	}
}

