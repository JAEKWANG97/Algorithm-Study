import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {
    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, L, R, A[][];
    static int[] dy = {0, 0, 1, -1}, dx = {1, -1, 0, 0};

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();

        int answer = solution();

        System.out.println(answer);

    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 인구 이동 시키기
    static int solution() {
        int result = 0;
        while (true) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) { // 아직 방문하지 않은 칸에 대해서만 확인
                        if (bfs(new XY(j, i))) { // 인구 이동이 발생하면 true 반환
                            isMove = true;
                        }
                    }
                }
            }
            if (!isMove) return result;

            result++; // 인구 이동이 일어났으면 시간 증가
        }
    }

    // 연합 만들기
    static boolean bfs(XY start) {
        Queue<XY> q = new ArrayDeque<>();
        List<XY> union = new ArrayList<>(); // 연합을 관리
        int sum = 0; // 연합의 인구 합

        q.add(start);
        visited[start.y][start.x] = true;
        union.add(start);
        sum += A[start.y][start.x];

        while (!q.isEmpty()) {
            XY current = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (!visited[ny][nx]) {
                    int diff = Math.abs(A[current.y][current.x] - A[ny][nx]);
                    if (L <= diff && diff <= R) {
                        q.add(new XY(nx, ny));
                        visited[ny][nx] = true;
                        union.add(new XY(nx, ny));
                        sum += A[ny][nx];
                    }
                }
            }
        }

        // 연합의 크기가 1보다 크면 인구 이동 발생
        if (union.size() > 1) {
            int avg = sum / union.size();
            for (XY xy : union) {
                A[xy.y][xy.x] = avg;
            }
            return true;
        }

        return false; // 인구 이동이 발생하지 않음
    }
}
