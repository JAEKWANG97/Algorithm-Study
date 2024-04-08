import java.io.*;
import java.util.*;


public class BOJ_17144 {
    private static final int AIR_CLEANER = -1;
    private static final int MOD = 5;
    static int R, C, T, top, bottom;
    static int[][] map;
    static List<Pos> fineDust, spreadDust, remainingDust;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static class Pos {
        int x, y, value;

        public Pos(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    static void diffusion() {
        remainingDust = new ArrayList<>();
        spreadDust = new ArrayList<>();
        for (Pos pos : fineDust) {
            int x = pos.x;
            int y = pos.y;
            int v = pos.value / MOD;
            int count = 0; // 확산된 방향 개수
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || map[nx][ny] == AIR_CLEANER) {
                    continue;
                }
                count++;
                spreadDust.add(new Pos(nx, ny, v));
            }
            remainingDust.add(new Pos(x, y, pos.value - (v * count)));
        }
    }

    static void updateMap() {
        // 남은 먼지로 갱신
        for (Pos pos : remainingDust) {
            map[pos.x][pos.y] = Math.max(pos.value, 0);
        }

        // 확산된 먼지 반영
        for (Pos pos : spreadDust) {
            map[pos.x][pos.y] += pos.value;
        }
    }

    static void updateFineDust() {
        fineDust = new ArrayList<>();

        // 미세먼지 최신화
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    fineDust.add(new Pos(i, j, map[i][j]));
                }
            }
        }
    }

    static void operate() {

        // 상단부 공기청정기 작동
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }
        map[top][1] = 0;


        // 하단부 공기청정기 작동
        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }
        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }
        map[bottom][1] = 0;
    }

    static int calcFineDustSum() {
        int sum = 2; // 공기청정기 값 -1 * 2

        for (int[] row : map) {
            for (int val : row) {
                sum += val;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        fineDust = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    fineDust.add(new Pos(i, j, map[i][j])); // 미세먼지
                }
            }
        }

        for (int i = 0; i < R; i++) {
            if (map[i][0] == AIR_CLEANER) {
                top = i;
                bottom = i + 1;
                break;
            }
        }

        while (T-- > 0) {
            diffusion(); // 확산
            updateMap(); // 확산 결과 반영
            operate(); // 공기 청정기 작동
            updateFineDust(); // 확산할 미세먼지 추가
        }

        bw.write(String.valueOf(calcFineDustSum()));

        br.close();
        bw.close();
    }
}