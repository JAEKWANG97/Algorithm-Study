import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471 {
	static int n, answer;
	static List<Integer>[] graph;
	static List<Integer> A, B;

	static int[] population;

	public static void main(String[] args) throws IOException {
		init();

		combination(1, A, B);
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);	
		}
		
		
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		population = new int[n + 1];
		A = new LinkedList<>();
		B = new LinkedList<>();
		answer = Integer.MAX_VALUE;
		graph = new List[n + 1];
		
		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList();
		}
		// 각 인구수 저장
		for (int i = 1; i <= n; i++) {
			
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int linkN = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < linkN; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			
			}
			
		}

	}

	// 모든 지역구의 경우의 수 추출하기
	static void combination(int depth, List<Integer> A, List<Integer> B) {

		if (depth == n+1) { // 모든 경우의 수 추출
			
//			print(A, B);		
			if(A.size() == 0 || B.size() ==0) {
				return; // 한쪽으로 쏠리면 넘김
			}
			
			
			// A, B가 모두 연결되어있나 확인
			if(isLinked(A, B)){
//				System.out.println("TEST");
				answer = Math.min(answer, sumAnswer(A, B));
				return;	
			}
			
			
			
			return;
		}

		
//		System.out.println("test: "+depth);
		

		// A에 들어가는 경우
		A.add(depth);
		combination(depth + 1, A, B);
		A.remove(A.size() - 1);

		
		// B에 들어가는 경우
		B.add(depth);
		combination(depth + 1, A, B);
		B.remove(B.size() - 1);

	}

	// 두 리스트의 인구수 합의 차이 구하기
	static int sumAnswer(List<Integer> a, List<Integer> b) {
		int asum = 0;
		int bsum = 0;

		for (int i = 0; i < a.size(); i++) {
			asum += population[a.get(i)];
		}

		for (int i = 0; i < b.size(); i++) {
			bsum += population[b.get(i)];
		}

		return Math.abs(asum - bsum);

	}

	 
	// 두 노드가 연결되었는지 판별
	   static boolean isLinked(List<Integer> A, List<Integer> B) {

		   
		   
	        Queue<Integer> queue = new LinkedList<>();
	        int now;
	        boolean visited[] = new boolean[n+1];

	        
	        queue.add(A.get(0));
	        visited[A.get(0)] = true;
	        
	        while (!queue.isEmpty()) {
	            now = queue.poll();
	            for (int next : graph[now]) {
	                if (A.contains(next) && !visited[next]) {
	                    visited[next] = true;
	                    queue.add(next);
	                }
	            }
	        }

	        
	        queue.add(B.get(0));
	        visited[B.get(0)] = true;
	        while (!queue.isEmpty()) {
	            now = queue.poll();
	            for (int nxt : graph[now]) {
	                if (B.contains(nxt) && !visited[nxt]) {
	                    visited[nxt] = true;
	                    queue.add(nxt);
	                }
	            }
	        }
	        //a와 b를 탐색했을 때 모든 원소를 다 연결했으면 참

	        for (int i = 1; i <= n; i++) {
//	        	System.out.println("test");
	        	if(!visited[i]) return false;
	        }
	            
	        return true;
	    }

	
	//리스트 a와 b를 찍어보기
	static void print(List<Integer> A, List<Integer> B) {
		System.out.println("A");
		for(int i = 0; i<A.size(); i++) {
			System.out.print(A.get(i) +" ");
		}
		System.out.println();
		System.out.println("B");
		for(int i = 0; i<B.size(); i++) {
			System.out.print(B.get(i)+" ");
		}
		System.out.println();
	}
}
