import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
 
    static class Point {
        int x, y;
 
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
    private static int N, K, answer;
    private static int[][] board;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            List<Point> starts = new ArrayList<>();
 
            int max = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (max < board[i][j]) {
                        max = board[i][j];
                    }
                }
            }
 
            setMaxHeight(starts, max);
            answer = 0;
            for (Point start : starts) {
                boolean[][] visited = new boolean[N][N];
                visited[start.x][start.y] = true;
                findLongWay(start.x, start.y, visited, 1, false);
                visited[start.x][start.y] = false;
            }
 
            System.out.println("#" + test_case + " " + answer);
        }
    }
 
    private static void setMaxHeight(List<Point> starts, int max) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max == board[i][j]) {
                    starts.add(new Point(i, j));
                }
            }
        }
    }
 
    private static void findLongWay(int x, int y, boolean[][] visited, int depth, boolean isCut) {
 
        answer = Math.max(answer, depth);
 
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if (isIn(nx, ny) && !visited[nx][ny]) {
                //낮으면 바로 갈 수 있음
                if (board[x][y] > board[nx][ny]) {
                    visited[nx][ny] = true;
                    findLongWay(nx, ny, visited, depth + 1, isCut);
                    visited[nx][ny] = false;
                } else if (!isCut) {
                    //높거나 같으면 깎아서 감
                    int cut = board[nx][ny] - board[x][y] + 1;
                    if (K - cut < 0) {
                        continue;
                    }
                    board[nx][ny] -= cut;
                    visited[nx][ny] = true;
                    findLongWay(nx, ny, visited, depth + 1, true);
                    board[nx][ny] += cut;
                    visited[nx][ny] = false;
                }
            }
        }
    }
 
    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < N;
    }
}
