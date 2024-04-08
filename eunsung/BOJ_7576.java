import java.io.*;
import java.util.*;

public class BOJ_7576 {

    static int M,N,H;
    static Queue<int[]> queue = new LinkedList<>();
    static int box[][];

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        box = new int[N][M];

        for (int n = 0; n < N; n++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                box[n][m] = Integer.parseInt(st1.nextToken());

                if(box[n][m] == 1) {
                    queue.add(new int[]{n,m});
                }
            }
        }

        System.out.println(bfs());

    } // end

    private static int bfs() {
        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int n = tmp[0];
            int m = tmp[1];

            for (int i = 0; i < dx.length; i++) { // 범위 내에 있고, 상하좌우위아래 안익은 토마토 있는지 검사
                if((m+dx[i])>=0 && (m+dx[i])<M && (n+dy[i])>=0 && (n+dy[i])<N ) {
                    if(box[n+dy[i]][m+dx[i]] == 0) { // 안익은 토마토가 있으면
                        box[n+dy[i]][m+dx[i]] = box[n][m] + 1; // 익은 토마토 값 = 이전에 익은 토마토 값 + 1
                        queue.add(new int[] {n+dy[i],m+dx[i]});
                    }
                }
            }

        } // while 끝

        int max = -1;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (box[n][m] == 0) {
                    return -1;
                }
                if (box[n][m] > max) {
                    max = box[n][m];
                }
            }
        }
        return max-1;

    } // bfs 끝
}

