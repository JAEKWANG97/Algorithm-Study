

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {
    static class xy {
        int x, y;

        xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    static Queue<xy> qJ;
    static Queue<xy> qF;
    static int r, c;
    static int[][] distJ, distF;
    static char[][] map;
    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();

        solution();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        qJ = new ArrayDeque<>();
        qF = new ArrayDeque<>();
        distJ = new int[r][c];
        distF = new int[r][c];

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                distJ[i][j] = -1;
                distF[i][j] = -1;
                map[i][j] = s.charAt(j);


                if (map[i][j] == 'J') {
                    distJ[i][j] = 0;
                    qJ.add(new xy(i, j));
                }
                if (map[i][j] == 'F') {
                    distF[i][j] = 0;
                    qF.add(new xy(i, j));
                }
            }

        }


    }

    static void solution() {


        //불이 전파되는 시간 미리 구하기
        while (!qF.isEmpty()) {
            xy cicj = qF.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cicj.x + dx[d];
                int ny = cicj.y + dy[d];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                if (distF[nx][ny] >= 0 || map[nx][ny] == '#') continue;

                distF[nx][ny] = distF[cicj.x][cicj.y] + 1;
                qF.add(new xy(nx, ny));
            }
        }

        //지훈이가 도망칠 수 있는지 확인
        while (!qJ.isEmpty()) {
            xy cicj = qJ.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cicj.x + dx[d];
                int ny = cicj.y + dy[d];

                //벗어났을 경우
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    System.out.println(distJ[cicj.x][cicj.y] + 1);
                    return;
                }

                if (distJ[nx][ny] >= 0 || map[nx][ny] == '#') continue;

                //불보다 해당 위치에 늦거나 같게 도착하면 건뛰
                if (distF[nx][ny] <= distJ[cicj.x][cicj.y] + 1) continue;


                distJ[nx][ny] = distJ[cicj.x][cicj.y] + 1;

                qJ.add(new xy(nx, ny));
            }
        }


        System.out.println("IMPOSSIBLE");
        return;
    }

}
