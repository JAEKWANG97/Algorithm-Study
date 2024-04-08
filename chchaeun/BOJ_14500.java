import java.io.*;
import java.util.*;

public class BOJ_14500 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[][] board;
    static int answer = 0;
    static boolean[][] visited;

    public static boolean inRange(int y, int x){
        return 0 <= y && y < N && 0 <= x && x < M;
    }
    public static void shape(boolean[][] visited, int y, int x, int count, int total){
        if (count == 4){
            answer = answer < total ? total : answer;
            return;
        }

        int[] dys = {1, 0, -1, 0};
        int[] dxs = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++){
            int ny = y + dys[i];
            int nx = x + dxs[i];

            if (!inRange(ny, nx) || visited[ny][nx]){
                continue;
            }

            visited[ny][nx] = true;
            shape(visited, ny, nx, count + 1, total + board[ny][nx]);
            visited[ny][nx] = false;
        }

        if (count == 2){
            if (inRange(y, x + 1) && !visited[y][x + 1]){
                visited[y][x + 1] = true;
                shape(visited, y, x, count + 1, total + board[y][x+1]);
                visited[y][x + 1] = false;
            }

            if (inRange(y + 1, x) && !visited[y + 1][x]){
                visited[y + 1][x] = true;
                shape(visited, y, x, count + 1, total + board[y+1][x]);
                visited[y + 1][x] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                visited[i][j] = true;
                shape(visited, i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }
}
