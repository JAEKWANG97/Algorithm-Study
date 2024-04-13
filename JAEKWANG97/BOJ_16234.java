import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] A;
    static boolean[][] visited;
    static int N, L, R;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getDaysOfPopulationMovement());
    }

    static int getDaysOfPopulationMovement() {
        int days = 0;

        while (true) {
            boolean isMoved = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (movePopulation(i, j)) {
                            isMoved = true;
                        }
                    }
                }
            }

            if (!isMoved) {
                break;
            }
            days++;
        }

        return days;
    }

    static boolean movePopulation(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        LinkedList<int[]> union = new LinkedList<>();

        queue.offer(new int[]{x, y});
        union.offer(new int[]{x, y});
        visited[x][y] = true;

        int sum = A[x][y];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(A[current[0]][current[1]] - A[nx][ny]);
                    if (diff >= L && diff <= R) {
                        queue.offer(new int[]{nx, ny});
                        union.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        sum += A[nx][ny];
                    }
                }
            }
        }

        if (union.size() > 1) {
            int average = sum / union.size();
            for (int[] pos : union) {
                A[pos[0]][pos[1]] = average;
            }
            return true;
        }

        return false;
    }
}
