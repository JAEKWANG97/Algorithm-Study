import java.io.*;
import java.util.*;

public class BOJ_15683 {
    private static final int BLANK = 0;
    private static final int WALL = 6;

    static int N, M, answer;
    static int[][] map;
    static List<Cctv> cctvs;
    static int[] rotateSize = new int[]{4, 2, 4, 4, 1};

    static class Cctv {
        int type, x, y;

        public Cctv(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    static void helper(int depth) {
        if (depth == cctvs.size()) {

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == BLANK) {
                        count++;
                    }
                }
            }

            answer = Math.min(answer, count);
            return;
        }

        int type = cctvs.get(depth).type;
        int[][] backUpMap = new int[N][N];

        for (int dir = 0; dir < rotateSize[type]; dir++) {
            copy(backUpMap, map);
            if (type == 0) {
                update(dir, cctvs.get(depth));
            } else if (type == 1) {
                update(dir, cctvs.get(depth));
                update(dir + 2, cctvs.get(depth));
            } else if (type == 2) {
                update(dir, cctvs.get(depth));
                update(dir + 1, cctvs.get(depth));
            } else if (type == 3) {
                update(dir, cctvs.get(depth));
                update(dir + 1, cctvs.get(depth));
                update(dir + 2, cctvs.get(depth));
            } else if (type == 4) {
                update(dir, cctvs.get(depth));
                update(dir + 1, cctvs.get(depth));
                update(dir + 2, cctvs.get(depth));
                update(dir + 3, cctvs.get(depth));
            }
            helper(depth + 1);
            copy(map, backUpMap);
        }
    }

    static void update(int dir, Cctv cctv) {
        dir %= 4;

        // 동
        if (dir == 0) {
            for (int y = cctv.y + 1; y < M; y++) {
                if (map[cctv.x][y] == WALL) {
                    break;
                }
                map[cctv.x][y] = -1;
            }
        }
        // 북
        else if (dir == 1) {
            for (int x = cctv.x - 1; x >= 0; x--) {
                if (map[x][cctv.y] == WALL) {
                    break;
                }
                map[x][cctv.y] = -1;
            }
        }
        // 서
        else if (dir == 2) {
            for (int y = cctv.y - 1; y >= 0; y--) {
                if (map[cctv.x][y] == WALL) {
                    break;
                }
                map[cctv.x][y] = -1;
            }
        }
        // 남
        else if (dir == 3) {
            for (int x = cctv.x + 1; x < N; x++) {
                if (map[x][cctv.y] == WALL) {
                    break;
                }
                map[x][cctv.y] = -1;
            }
        }
    }

    static void copy(int[][] dest, int[][] src) {
        for (int i = 0; i < N; i++) {
            dest[i] = src[i].clone();
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctvs = new ArrayList<>();
        map = new int[N][M];
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != BLANK && map[i][j] != WALL) {
                    cctvs.add(new Cctv(map[i][j] - 1, i, j));
                }
            }
        }
        helper(0);
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}