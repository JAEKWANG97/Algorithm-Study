import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int nowX = N / 2;
        int nowY = N / 2;

        // 배열 돌리기
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // 죄 하 우 상 -> n, n, n+1, n+1 횟수 이동하며, 다음 시작은 n+2횟수 부터
        int goCnt = 1;
        int answer = 0;
        int dir = 0;
        int idx = 0;
        while (nowX >= 0) {
            // 좌로 이동
            for (int i = 0; i < goCnt; i++) {
                nowX += dx[dir];
                nowY += dy[dir];
                answer += spread(map, nowX, nowY, dir);
                //view(map);
                if (nowX == 0 && nowY == 0) {
                    System.out.println(answer);
                    return;
                }
            }
            dir = (dir+1) % 4;
            // 아래로 이동
            for (int i = 0; i < goCnt; i++) {
                nowX += dx[dir];
                nowY += dy[dir];
                answer += spread(map, nowX, nowY, dir);
                //view(map);
            }
            dir = (dir+1) % 4;
            // 우로 이동
            for (int i = 0; i < goCnt+1; i++) {
                nowX += dx[dir];
                nowY += dy[dir];
                answer += spread(map, nowX, nowY, dir);
                //view(map);
            }
            dir = (dir+1) % 4;
            // 위로 이동
            for (int i = 0; i < goCnt+1; i++) {
                nowX += dx[dir];
                nowY += dy[dir];
                answer += spread(map, nowX, nowY, dir);
                //view(map);
            }
            //System.out.println(++idx + "회차: " + answer);
            dir = (dir+1) % 4;
            goCnt += 2;
        }

        System.out.println(answer);
    }
    // 흩날리기 (이동한 방향임)
    private static int spread(int[][] map, int x, int y, int dir) {
        int out = 0;
        int move = 0;
        int N = map.length;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        //1. 왼쪽 끝 5% 위치
        move += map[y][x] * 5 / 100;
        if (isIn(y + dy[dir] * 2, x + dx[dir] * 2, N))
            map[y + dy[dir] * 2][x + dx[dir] * 2] += map[y][x] * 5 / 100;
        else
            out += map[y][x] * 5 / 100;

        //2. 그다음 10% 위치
        move += map[y][x] * 10 / 100 * 2;
        if (isIn(y + dy[dir] + dy[(dir + 1) % 4], x + dx[dir] + dx[(dir + 1) % 4], N))
            map[y + dy[dir] + dy[(dir + 1) % 4]][x + dx[dir] + dx[(dir + 1) % 4]] += map[y][x] * 10 / 100;
        else
            out += map[y][x] * 10 / 100;

        if (isIn(y + dy[dir] + dy[(dir + 3) % 4], x + dx[dir] + dx[(dir + 3) % 4], N))
            map[y + dy[dir] + dy[(dir + 3) % 4]][x + dx[dir] + dx[(dir + 3) % 4]] += map[y][x] * 10 / 100;
        else
            out += map[y][x] * 10 / 100;

        //3. 그다음 7% 위치
        move +=map[y][x] * 7 / 100 * 2;
        if (isIn(y + dy[(dir + 1) % 4], x + dx[(dir + 1) % 4], N))
            map[y + dy[(dir + 1) % 4]][x + dx[(dir + 1) % 4]] += map[y][x] * 7 / 100;
        else
            out += map[y][x] * 7 / 100;

        if (isIn(y + dy[(dir + 3) % 4], x + dx[(dir + 3) % 4], N))
            map[y + dy[(dir + 3) % 4]][x + dx[(dir + 3) % 4]] += map[y][x] * 7 / 100;
        else
            out += map[y][x] * 7 / 100;

        //4. 그담 2% 위치
        move += map[y][x] * 2 / 100 * 2;
        if (isIn(y + dy[(dir + 1) % 4] * 2, x + dx[(dir + 1) % 4] * 2, N))
            map[y + dy[(dir + 1) % 4] * 2][x + dx[(dir + 1) % 4] * 2] += map[y][x] * 2 / 100;
        else
            out += map[y][x] * 2 / 100;

        if (isIn(y + dy[(dir + 3) % 4] * 2, x + dx[(dir + 3) % 4] * 2, N))
            map[y + dy[(dir + 3) % 4] * 2][x + dx[(dir + 3) % 4] * 2] += map[y][x] * 2 / 100;
        else
            out += map[y][x] * 2 / 100;

        //5. 맨뒤 1% 위치
        move += map[y][x] / 100 * 2;
        if (isIn(y - dy[dir] + dy[(dir + 1) % 4], x - dx[dir] + dx[(dir + 1) % 4], N))
            map[y - dy[dir] + dy[(dir + 1) % 4]][x - dx[dir] + dx[(dir + 1) % 4]] += map[y][x] / 100;
        else
            out += map[y][x] / 100;

        //6. 마지막 이동
        if (isIn(y - dy[dir] + dy[(dir + 3) % 4], x - dx[dir] + dx[(dir + 3) % 4], N))
            map[y - dy[dir] + dy[(dir + 3) % 4]][x - dx[dir] + dx[(dir + 3) % 4]] += map[y][x] / 100;
        else
            out += map[y][x] / 100;

        //6. a 위치
        if (isIn(y + dy[dir], x + dx[dir], N))
            map[y + dy[dir]][x + dx[dir]] += map[y][x] - move;
        else
            out += map[y][x] - move;

        map[y][x] = 0;

        return out;
    }
    private static boolean isIn(int y, int x, int N) {
        if (y >= 0 && y < N && x >= 0 && x < N)
            return true;
        return false;
    }

    private static void view(int[][] map) {
        System.out.println("==========================");
        for (int y = 0; y < map.length; y++)
            System.out.println(Arrays.toString(map[y]));
        System.out.println("==========================");
    }
}