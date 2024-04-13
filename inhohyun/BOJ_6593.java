import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_6593 {
    static class lrc {
        int l, r, c, cnt;

        lrc(int l, int r, int c, int cnt) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int L, R, C;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) break;

            char[][][] build = new char[L][R][C];

            //입력 받기
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < C; k++) {
                        build[i][j][k] = s.charAt(k);
                    }
                }
                br.readLine();
            }
            //시작 위치 받기
            lrc start = null;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {

                    for (int k = 0; k < C; k++) {
                        if (build[i][j][k] == 'S') {
                            start = new lrc(i, j, k, 0);
                        }
                    }
                }

            }


            int answer = bfs(start, build);
            if (answer == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + answer + " minute(s).");

            }

        }
    }

    static int bfs(lrc start, char[][][] build) {
        Queue<lrc> q = new ArrayDeque<>();
        q.add(start);
        int[][][] visited = new int[L][R][C];
        visited[start.l][start.r][start.c] = 1;

        while (!q.isEmpty()) {
            lrc tmp = q.poll();

            for (int d = 0; d < 6; d++) {
                int nl = tmp.l + dz[d];
                int nx = tmp.r + dx[d];
                int ny = tmp.c + dy[d];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C || nl < 0 || nl >= L) continue;

                if ((visited[nl][nx][ny] == 0 && build[nl][nx][ny] == '.') || build[nl][nx][ny] == 'E') {
                    visited[nl][nx][ny] += 1;
                    if (build[nl][nx][ny] == 'E') {
                        return tmp.cnt + 1;
                    }
                    q.add(new lrc(nl, nx, ny, tmp.cnt + 1));

                }


            }

        }

        return -1;
    }

}
