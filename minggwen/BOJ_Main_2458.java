import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Main_2458 {

	static class Student{
		List<Integer> shorter;
		List<Integer> longer;
		public Student() {
			this.shorter = new ArrayList<>();
			this.longer = new ArrayList<>();
		}
		@Override
		public String toString() {
			return "Student [shorter=" + shorter.toString() + ", longer=" + longer.toString() + "]";
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Student> students = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			students.add(new Student());
		}
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			students.get(a).longer.add(b);
			students.get(b).shorter.add(a);
		}
		
		int cnt = 0;
		for(int idx = 1; idx<=N;idx++) {
			Queue<Integer> que = new ArrayDeque<>();
			boolean[] visited = new boolean[N+1];
			visited[idx] = true;
			
			//나보다 키가 큰 경우
			for(int i:students.get(idx).longer) {
				que.add(i);
				visited[i] = true;
			}
			while(!que.isEmpty()) {
				int tmp = que.poll();
				Student q = students.get(tmp);
				for(int qi=0;qi<q.longer.size();qi++) {
					int num = q.longer.get(qi);
					if(!visited[num]) {
						visited[num]=true;
						que.add(num);
					}
				}
			}
			
			//나보다 키가 작은 경우
			for(int i:students.get(idx).shorter) {
				que.add(i);
				visited[i] = true;
			}
			
			while(!que.isEmpty()) {
				int tmp = que.poll();
				Student q = students.get(tmp);
				for(int qi=0;qi<q.shorter.size();qi++) {
					int num = q.shorter.get(qi);
					if(!visited[num]) {
						visited[num]=true;
						que.add(num);
					}
				}
			}
			boolean flag = true;
			
			for(int k=1;k<=N;k++) {
				if(!visited[k]) {
					flag=false;
					break;
				}
			}
			if(flag)cnt++;
		}
		System.out.println(cnt);
	}

}
