import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int n, l, r;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int openCnt = 0;
        int answer = -1;
        while (openCnt != n * n) {
            int[][] visited = new int[n][n];    //연합 별로 다른 숫자를 저장 (unionNum)
            int[] peopleCnt = new int[n * n + 1];   //연합 숫자에 따라 인구수를 저장
            int unionNum = 1;
            openCnt = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        boolean flag = bfs(i, j, visited, unionNum, peopleCnt);
                        if (flag) {
                            openCnt++;
                        }
                        unionNum++;
                        continue;
                    }
                    board[i][j] = peopleCnt[visited[i][j]];
                }
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static boolean bfs(int x, int y, int[][] visited, int unionNum, int[] peopleCnt) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        visited[x][y] = unionNum;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int size = 1;
        int sum = board[x][y];
        boolean flag = true;    //연합이 되는지 안되는지 확인
        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                if (validCond(poll.x, poll.y, nx, ny, visited)) {
                    size++;
                    flag = false;
                    sum += board[nx][ny];
                    visited[nx][ny] = unionNum;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        peopleCnt[unionNum] = (int) Math.floor(sum / size);
        board[x][y] = peopleCnt[unionNum];
        return flag;
    }

    private static boolean validCond(int x, int y, int nx, int ny, int[][] visited) {
        int diff = Math.abs(board[nx][ny] - board[x][y]);
        return visited[nx][ny] == 0 && l <= diff && diff <= r;
    }
}


