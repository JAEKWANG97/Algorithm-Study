package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    static int N, M, map[][], qsize, nxtX, nxtY, now[], answer, depth;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<int[]> queue;
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new LinkedList<>();
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        answer = N*M;
        depth = 0;
        visited = new boolean[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) {
                    queue.add(new int[]{y, x});
                    visited[y][x] = true;
                    answer--;
                }else if (tmp == -1) answer--;
                map[y][x] = tmp;
            }
        } ///////////// 입력

        while (queue.size() != 0) {
            qsize = queue.size();
            while (--qsize >= 0) {
                now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    nxtY = now[0]+dy[i];
                    nxtX = now[1]+dx[i];
                    if (0 <= nxtY && nxtY < N && 0 <= nxtX && nxtX < M && !visited[nxtY][nxtX] && map[nxtY][nxtX] == 0) {
                        visited[nxtY][nxtX] = true;
                        answer--;
                        queue.add(new int[]{nxtY, nxtX});
                    }
                }
            }
            depth++;
        }
        if (answer == 0)
            System.out.println(depth-1);
        else
            System.out.println(-1);

    }
}
