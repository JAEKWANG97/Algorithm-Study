import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<bottle> q;
	static boolean[][][] visit;
	static class bottle{
		int a;
		int b;
		int c;
		public bottle(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> result = new ArrayList<>();
		q = new LinkedList<>();
		q.add(new bottle(0,0,C));
		visit = new boolean[A+1][B+1][C+1];
		while(!q.isEmpty()) {
			bottle cur = q.poll();
			if(visit[cur.a][cur.b][cur.c]) continue;
			if(cur.a==0) result.add(cur.c);
			visit[cur.a][cur.b][cur.c] = true;
			if(cur.c+cur.b<=B) { //C->B
				q.add(new bottle(cur.a,cur.b+cur.c,0));
			}else {
				q.add(new bottle(cur.a,B,cur.c-(B-cur.b)));
			}
			
			
			if(cur.c+cur.a<=A) { //C->A
				q.add(new bottle(cur.a+cur.c,cur.b,0));
			}else {
				q.add(new bottle(A,cur.b,cur.c-(A-cur.a)));
			}
			
			
			if(cur.c+cur.b<=C) { //B->C
				q.add(new bottle(cur.a,0,cur.b+cur.c));
			}else {
				q.add(new bottle(cur.a,cur.b-(C-cur.c),C));
			}
			
			
			if(cur.b+cur.a<=A) { //B->A
				q.add(new bottle(cur.a+cur.b,0,cur.c));
			}else {
				q.add(new bottle(A,cur.b-(A-cur.a),cur.c));
			}
			
			
			if(cur.c+cur.a<=C) { //A->C
				q.add(new bottle(0,cur.b,cur.a+cur.c));
			}else {
				q.add(new bottle(cur.a-(C-cur.c),cur.b,C));
			}
			
			
			if(cur.b+cur.a<=B) { //A->B
				q.add(new bottle(0,cur.a+cur.b,cur.c));
			}else {
				q.add(new bottle(cur.a-(B-cur.b),B,cur.c));
			}
		}
		
		Collections.sort(result);
		for(int i : result) {
			System.out.print(i +" ");
		}



	}
}
