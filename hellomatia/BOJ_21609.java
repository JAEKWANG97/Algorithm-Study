package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_21609 {

    class Point implements Comparable<Point> {
        int x;
        int y;
        int value;
        int cost;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int value, int cost) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            if(this.cost == o.cost) {
                if(this.value == o.value) {
                    if(this.x == o.x) {
                        return o.y - this.y;
                    }
                    return o.x - this.x;
                }
                return o.value - this.value;
            }
            return o.cost - this.cost;
        }
    }

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private final int[] dirX = {0, -1, 0, 1};
    private final int[] dirY = {-1, 0, 1, 0};

    private int N, M;
    private int[][] map;
    private boolean[][] visited;
    private List<Point> result;
    private int answer;

    public static void main(String[] args) throws IOException {
        new BOJ_21609().solution();
    }

    public void solution() throws IOException {
        init();
        calcResult();
        printAns();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new ArrayList<>();
    }

    private void calcResult() {
        while(true) {
            visited = new boolean[N][N];
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j< N ; j++) {
                    if(map[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j, true);
                    }
                }
            }
            if(result.isEmpty()) break;

            Collections.sort(result);
            visited = new boolean[N][N];
            bfs(result.get(0).x, result.get(0).y, false);
            cleanPoint();

            gravity();
            map = rotate();
            gravity();

            result.clear();
        }
    }

    private int[][] rotate() {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - j - 1][i] = map[i][j];
            }
        }

        return temp;
    }

    private void gravity() {
        for(int j = 0 ; j < N ; j++) {
            for(int i = N-1 ; i >= 0 ; i--) {
                for(int k = i ; k < N-1 ; k ++) {
                    if(map[k][j] == -1) continue;
                    if(map[k][j] != -2 && map[k+1][j] == -2) {
                        int temp = map[k][j];
                        map[k][j] = -2;
                        map[k+1][j] = temp;
                    }
                }
            }
        }
    }

    private void cleanPoint() {
        int count = 0;
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(visited[i][j]) {
                    count++;
                    map[i][j] = -2;
                }
            }
        }

        answer += (int) Math.pow(count, 2);
    }

    private void bfs(int x, int y, boolean flag) {
        int blockPoint = map[x][y];
        visited[x][y] = true;

        Queue<Point> queue = new ArrayDeque<>();

        queue.offer(new Point(x, y));
        int cost = 1;
        int value = 0;
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for(int i  = 0 ; i < 4 ; i++) {
                int nx = now.x + dirX[i];
                int ny = now.y + dirY[i];

                if(0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny]) continue;
                if(map[nx][ny] == blockPoint || map[nx][ny] == 0) {
                    if(map[nx][ny] == 0) {
                        value += 1;
                    }
                    cost += 1;
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }

        if(cost >= 2) result.add(new Point(x, y, value, cost));
        if(flag) {
            for(int i = 0; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(map[i][j] == 0) visited[i][j] = false;
                }
            }
        }
    }

    private void printAns() throws IOException {
        bw.write(answer + "\n");
        bw.flush();
    }
}
