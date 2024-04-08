import java.io.*;
import java.util.*;

public class BOJ_2573 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] dys = {1, 0, -1 , 0};
    static int[] dxs = {0, 1, 0, -1};

    public static void melt(int[][] board){
        int[][] zeroCount = new int[N][M];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                for (int k = 0; k < 4; k++) {
                    int ny = i + dys[k];
                    int nx = j + dxs[k];

                    if (inRange(ny, nx) && board[ny][nx] == 0) {
                        zeroCount[i][j] += 1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                board[i][j] = board[i][j] - zeroCount[i][j] > 0 ? board[i][j] - zeroCount[i][j] : 0;
            }
        }
    }

    public static boolean inRange(int y, int x){
        return 0 <= y && y < N && 0 <= x && x < M;
    }
    public static boolean bfs(boolean[][] visited, int[][] board, int sy, int sx){
        if (board[sy][sx] == 0 || visited[sy][sx]){
            return false;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (!queue.isEmpty()){
            int[] point = queue.poll();

            for (int i = 0; i < 4; i++){
                int ny = point[0] + dys[i];
                int nx = point[1] + dxs[i];

                if (inRange(ny, nx) && !visited[ny][nx] && board[ny][nx] > 0){
                    queue.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }

        return true;
    }

    public static int countMountain(int[][] board, int N, int M){
        int count = 0;

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (bfs(visited, board, i, j)){
                    count += 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int result = 1;
        while (result == 1){
            melt(board);
            result = countMountain(board, N, M);
            time++;
        }

        System.out.println(result == 0 ? 0 : time);
    }
}
