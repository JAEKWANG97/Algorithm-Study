package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_20057 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[][] map;

    static int nextDir[] = { 2, 3, 1, 0 };
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    private int[][] dsx = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 },
            { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } }; // 모래 퍼지는 x방향
    private int[][] dsy = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
            { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } }; // 모래 퍼지는 y방향
    private int[] rate = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };

    private int result;

    public static void main(String[] args) throws IOException {
        new BOJ_20057().solution();
    }

    public void solution() throws IOException {
        init();
        calcResult();
        printResult(result);
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void calcResult() {

        int cx = N / 2, cy = N / 2;
        int curDir = 2;
        int nx = 0, ny = 0;
        int d = 1;
        int cnt = 0;
        int check = 0;

        while (true) {
            if (cx == 0 && cy == 0) {
                break;
            }
            nx = cx + dx[curDir];
            ny = cy + dy[curDir];
            cnt++;
            sandBlow(cx, cy, nx, ny, curDir);

            if (d == cnt) {
                cnt = 0;
                curDir = nextDir[curDir];
                check++;
            }
            if (check == 2) {
                check = 0;
                d++;
            }
            cx = nx;
            cy = ny;
        }
    }

    private void sandBlow(int x, int y, int nextX, int nextY, int dir) {
        map[nextX][nextY] += map[x][y];
        map[x][y] = 0;
        int sand = map[nextX][nextY];
        int a = sand;
        int sandX;
        int sandY;
        for (int i = 0; i < 9; i++) {
            sandX = nextX + dsx[dir][i];
            sandY = nextY + dsy[dir][i];
            int amount = (int) (sand * (rate[i] * 0.01));

            if (isIn(sandX, sandY)) {
                map[sandX][sandY] += amount;
            } else {
                result += amount;
            }
            a -= amount;
        }
        int aX = nextX + dsx[dir][9];
        int aY = nextY + dsy[dir][9];
        if (isIn(aX, aY)) {
            map[aX][aY] += a;
        } else {
            result += a;
        }
        map[nextX][nextY] = 0;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
