import java.io.*;
import java.util.*;

public class BOJ_20056 {
    static int N, M, K;
    static Deque<Fireball> fireballs;
    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    static class Fireball {
        int x, y, m, s, d;

        public Fireball(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static void moveFireball() {
        Deque<Fireball> movedFireballs = new ArrayDeque<>();
        for (Fireball cur : fireballs) {
            int nx = (N + cur.x + dx[cur.d] * (cur.s % N)) % N;
            int ny = (N + cur.y + dy[cur.d] * (cur.s % N)) % N;
            movedFireballs.add(new Fireball(nx, ny, cur.m, cur.s, cur.d));
        }
        fireballs = movedFireballs;
    }

    static void mergeFireball() {
        Deque<Fireball>[][] mergedFireballs = new ArrayDeque[N][N];
        for (Deque<Fireball>[] row : mergedFireballs) {
            for (int j = 0; j < N; j++) {
                row[j] = new ArrayDeque<>();
            }
        }
        for (Fireball cur : fireballs) {
            mergedFireballs[cur.x][cur.y].add(cur);
        }
        Deque<Fireball> newFireballs = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size = mergedFireballs[i][j].size();
                if (size == 0) {
                    continue;
                }
                if (size == 1) {
                    newFireballs.addAll(mergedFireballs[i][j]);
                    continue;
                }
                int totalM = 0, totalS = 0, totalCount = 0;
                boolean isAllEven = true, isAllOdd = true;
                for (Fireball fireball : mergedFireballs[i][j]) {
                    totalM += fireball.m;
                    totalS += fireball.s;
                    totalCount++;
                    if (fireball.d % 2 == 0) {
                        isAllOdd = false;
                    } else {
                        isAllEven = false;
                    }
                }
                int newM = totalM / 5;
                if (newM == 0) {
                    continue;
                }
                int newS = totalS / totalCount;
                if (isAllEven || isAllOdd) {
                    for (int d = 0; d < 4; d++) {
                        newFireballs.add(new Fireball(i, j, newM, newS, d * 2));
                    }
                } else {
                    for (int d = 0; d < 4; d++) {
                        newFireballs.add(new Fireball(i, j, newM, newS, d * 2 + 1));
                    }
                }
            }
        }
        fireballs = newFireballs;
    }

    static int calcMassSum() {
        int sum = 0;
        for (Fireball fireball : fireballs) {
            sum += fireball.m;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireballs = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1; // x
            int y = Integer.parseInt(st.nextToken()) - 1; // y
            int m = Integer.parseInt(st.nextToken()); // 질량
            int s = Integer.parseInt(st.nextToken()); // 속도
            int d = Integer.parseInt(st.nextToken()); // 방향
            fireballs.add(new Fireball(x, y, m, s, d));
        }

        while (K-- > 0) {
            moveFireball();
            mergeFireball();
        }

        bw.write(String.valueOf(calcMassSum()));

        br.close();
        bw.close();
    }
}
