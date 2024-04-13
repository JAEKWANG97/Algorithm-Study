import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 빙산 - 90분
public class BOJ_2573 {
    static StringBuilder sb = new StringBuilder();
    static int N, M, map[][], deltas[][] = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Iceberg {
        int r, c, h;

        public Iceberg(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Queue<Iceberg> list = new ArrayDeque<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) list.add(new Iceberg(i, j, map[i][j]));
            }
        }

        solve(list);
    }

    private static void solve(Queue<Iceberg> list) throws Exception {
        int time = 0;
        while (bfs(list) < 2) {
            time++;
            list = melting(list);
            if (list.size() == 0) { // 빙산이 분리되지 않고 한번에 전부 녹으면 0처리
                time = 0;
                break;
            }
        }

        sb.append(time);
    }

    private static Queue<Iceberg> melting(Queue<Iceberg> icebergList) {
        Queue<Iceberg> nonMeltingList = new ArrayDeque<>(); // 아직 안녹은 빙산
        Queue<Iceberg> meltingList = new ArrayDeque<>(); // 녹은 빙산
        while (!icebergList.isEmpty()) {
            Iceberg iceberg = icebergList.poll();
            int cnt = 0;
            for (int d = 0; d < deltas.length; d++) {
                int nr = deltas[d][0] + iceberg.r;
                int nc = deltas[d][1] + iceberg.c;

                if (map[nr][nc] == 0) cnt++;
            }
            iceberg.h -= cnt;
            if (iceberg.h <= 0) {
                meltingList.offer(iceberg);
            } else {
                nonMeltingList.offer(iceberg);
            }
        }
        changMap(meltingList);

        return nonMeltingList;
    }

    private static void changMap(Queue<Iceberg> list) {
        while (!list.isEmpty()) {
            Iceberg iceberg = list.poll();
            map[iceberg.r][iceberg.c] = 0;
        }
    }

    private static int bfs(Queue<Iceberg> list) { // 빙산 덩어리 찾기
        int count = 0; // 빙산 덩어리 개수

        boolean visited[][] = new boolean[N][M];
        Queue<Iceberg> q = new ArrayDeque<>();
        for (Iceberg iceberg : list) {
            if (visited[iceberg.r][iceberg.c]) continue;
            q.offer(iceberg);
            visited[iceberg.r][iceberg.c] = true;
            count++;
            while (!q.isEmpty()) {
                Iceberg cur = q.poll();

                for (int d = 0; d < deltas.length; d++) {
                    int nr = deltas[d][0] + cur.r;
                    int nc = deltas[d][1] + cur.c;

                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == 0) continue;
                    q.offer(new Iceberg(nr, nc, map[nr][nc]));
                    visited[nr][nc] = true;
                }
            }
        }

        return count;
    }

    static void printArr() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}