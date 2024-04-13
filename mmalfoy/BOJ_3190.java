
import java.io.*;
import java.util.*;

public class BOJ_3190 {
	static int[] dr = new int[] {0, 1, 0, -1};
	static int[] dc = new int[] {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		final int APPLE = 2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int [][] G = new int[N + 1][N + 1];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			G[r][c] = APPLE;
		}
		
		int L = Integer.parseInt(br.readLine());
		Queue<D> q = new ArrayDeque<>();
		for (int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			q.offer(new D(X, C));
		}
		
		List<Node> snake = new ArrayList<>();
		Node head = new Node(1, 1);
		snake.add(head);
		int time = 0;
		int tailR, tailC, didx = 0;
		while (true) {
			int nr = head.r + dr[didx];
			int nc = head.c + dc[didx];
			
			// 머리가 벽에 닿으면 종료
			if (nr < 1 || nr > N || nc < 1 || nc > N) {
				break;
			}
			
			//몸 길이를 늘리고, 사과 먹었는지 확인
			boolean isApple = false;
			if (G[nr][nc] == APPLE) {
				G[nr][nc] = 0;
				isApple = true;
			}
			
			
			// 일단 다 이동
			tailR = snake.get(snake.size() - 1).r;
			tailC = snake.get(snake.size() - 1).c;
			for (int i = snake.size() - 1; i > 0 ; i--) {
				//머리가 몸에 닿으면 종료
				if (nr == snake.get(i).r && nc == snake.get(i).c) {
					System.out.println(time + 1);
					return;
				}
				snake.get(i).r = snake.get(i - 1).r;
				snake.get(i).c = snake.get(i - 1).c;
			}
			
			// 사과 먹은경우 ? 그대로 : 마저 이동 
			if (isApple) {				
				snake.add(new Node(tailR, tailC));
			} 

			head.r = nr;
			head.c = nc;
			time += 1;
			
			// x초가 끝난 뒤, 이동 명령 확인
			if (!q.isEmpty() && q.peek().x == time) {
				D d = q.poll();
				didx = (d.c == 'D') ? (didx + 1) % 4 : (didx + 3) % 4; 
			}
		}
		System.out.println(time + 1);
		
		
	}
}
class Node {
	int r, c;
	public Node(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

class D {
	int x;
	char c;
	D (int x, char c) {
		this.x = x;
		this.c = c;
	}
}
