import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class CODETREE_Main_destroytheturret {

    static int N, M, K;
    static int[][] arr;
    static int[][] lastAttack;
    static boolean[][] isAttacked;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] ddx = {0, 1, 0, -1, 1, 1, -1, -1};
    static int[] ddy = {1, 0, -1, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        lastAttack = new int[N][M];

        for (int round = 1; round <= K; round++) {

            if (isFinish())
                break;

            isAttacked = new boolean[N][M];

            int[] atk = searchAttacker();
            arr[atk[0]][atk[1]] += N + M;
            isAttacked[atk[0]][atk[1]] = true;
            lastAttack[atk[0]][atk[1]] = round;

            int[] tgt = searchTarget(atk);
            isAttacked[tgt[0]][tgt[1]] = true;

            if (!laser(atk, tgt)) {
                bomb(atk, tgt);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] < 0)
                        arr[i][j] = 0;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 0)        continue;
                    if (isAttacked[i][j])    continue;
                    arr[i][j] += 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        System.out.println(max);
    }

    static boolean isFinish() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0)    continue;
                count++;
            }
        }
        return count == 1;
    }

    static int[] searchAttacker() {
        int power = 5001;
        int ai = 0, aj = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0)    continue;

                if (arr[i][j] < power) {
                    power = arr[i][j];
                    ai = i;
                    aj = j;
                    continue;
                } else if (arr[i][j] > power)    continue;

                if (lastAttack[i][j] > lastAttack[ai][aj]) {
                    ai = i;
                    aj = j;
                    continue;
                } else if (lastAttack[i][j] < lastAttack[ai][aj])
                    continue;

                if (i + j > ai + aj) {
                    ai = i;
                    aj = j;
                    continue;
                } else if (i + j < ai + aj)    continue;

                if (j > aj) {
                    ai = i;
                    aj = j;
                }
            }
        }

        return new int[] { ai, aj };
    }

    static int[] searchTarget(int[] atk) {
        int power = -1;
        int ti = 0, tj = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0)    continue;
                if (i == atk[0] && j == atk[1])    continue;

                if (arr[i][j] > power) {
                    power = arr[i][j];
                    ti = i;
                    tj = j;
                    continue;
                } else if (arr[i][j] < power)    continue;

                if (lastAttack[i][j] < lastAttack[ti][tj]) {
                    ti = i;
                    tj = j;
                    continue;
                } else if (lastAttack[i][j] > lastAttack[ti][tj])
                    continue;

                if (i + j < ti + tj) {
                    ti = i;
                    tj = j;
                    continue;
                } else if (i + j > ti + tj)    continue;

                if (j < tj) {
                    ti = i;
                    tj = j;
                }
            }
        }
        return new int[] { ti, tj };
    }

    // 레이저 공격
    static boolean laser(int[] atk, int[] tgt) {
        boolean[][] visited = new boolean[N][M];
        Pair[][] come = new Pair[N][M];

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(atk[0], atk[1]));
        visited[atk[0]][atk[1]] = true;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = (pair.x + dx[d] + N) % N;
                int ny = (pair.y + dy[d] + M) % M;
                if (visited[nx][ny])    continue;
                if (arr[nx][ny] == 0)   continue;
                come[nx][ny] = new Pair(pair.x, pair.y);
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }

        if (!visited[tgt[0]][tgt[1]])
            return false;

        int x = tgt[0], y = tgt[1];
        while (x != atk[0] || y != atk[1]) {
            int power = arr[atk[0]][atk[1]] / 2;
            if (x == tgt[0] && y == tgt[1]) {
                power = arr[atk[0]][atk[1]];
            }
            arr[x][y] -= power;
            isAttacked[x][y] = true;
            Pair pair = come[x][y];
            x = pair.x;
            y = pair.y;
        }
        return true;
    }


    static void bomb(int[] atk, int[] tgt) {
        arr[tgt[0]][tgt[1]] -= arr[atk[0]][atk[1]];
        int halfPower = arr[atk[0]][atk[1]] / 2;
        for (int d = 0; d < 8; d++) {
            int nx = (tgt[0] + ddx[d] + N) % N;
            int ny = (tgt[1] + ddy[d] + M) % M;
            if (nx == atk[0] && ny == atk[1])    continue;
            arr[nx][ny] -= halfPower;
            isAttacked[nx][ny] = true;
        }
    }
}
