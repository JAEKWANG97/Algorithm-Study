package hellomatia;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SEA_1949 {

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, K;
    private int[][] map;
    private boolean[][] visited;
    private Queue<Point> starts;
    private int result;

    private final int[] dirX = {0, -1, 0, 1};
    private final int[] dirY = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        new SEA_1949().solution();
    }

    private void solution() throws IOException {
        int T = Integer.parseInt(bf.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            init();
            calcResult();
            printResult(testCase);
        }
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int max = 0;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        // 봉우리에서 출발
        starts = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == max) {
                    starts.add(new Point(i, j));
                }
            }
        }

        visited = new boolean[N][N];
    }

    private void calcResult() {
        result = 0;
        while (!starts.isEmpty()) {
            Point start = starts.poll();
            visited[start.x][start.y] = true;
            dfs(start.x, start.y, true, 1);
            visited[start.x][start.y] = false;
        }
    }

    private void dfs(int x, int y, boolean canConstruction, int count) {
        result = Math.max(result, count);

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (!isIn(nextX, nextY) || visited[nextX][nextY]) continue;

            if (map[nextX][nextY] < map[x][y]) {
                visited[nextX][nextY] = true;
                dfs(nextX, nextY, canConstruction, count + 1);
                visited[nextX][nextY] = false;
            }
            if (map[nextX][nextY] >= map[x][y] && canConstruction) {
                if (map[nextX][nextY] - map[x][y] < K) {
                    visited[nextX][nextY] = true;
                    int temp = map[nextX][nextY];
                    map[nextX][nextY] = map[x][y] - 1;
                    dfs(nextX, nextY, false, count + 1);
                    map[nextX][nextY] = temp;
                    visited[nextX][nextY] = false;
                }
            }
        }
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private void printResult(int testCase) throws IOException {
        bw.write("#" + testCase + " " + result + "\n");
        bw.flush();
    }
}

