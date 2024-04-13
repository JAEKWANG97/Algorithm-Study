package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {
    static class Cctv {
        int x, y, type;

        Cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static int n, m, minArea = Integer.MAX_VALUE;
    static int[][] map;
    static List<Cctv> cctvs = new ArrayList<>();
    static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // Right, Down, Left, Up

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int item = Integer.parseInt(st.nextToken());
                map[i][j] = item;
                if (item > 0 && item < 6) {
                    cctvs.add(new Cctv(i, j, item));
                }
            }
        }

        dfs(0, map);
        System.out.println(minArea);
    }

    private static void dfs(int cctvIndex, int[][] prevMap) {
        if (cctvIndex == cctvs.size()) {
            minArea = Math.min(minArea, calculateBlindSpot(prevMap));
            return;
        }

        Cctv cctv = cctvs.get(cctvIndex);
        int[][] copiedMap = new int[n][m];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                System.arraycopy(prevMap[j], 0, copiedMap[j], 0, m);
            }
            watch(cctv, i, copiedMap);
            dfs(cctvIndex + 1, copiedMap);
        }
    }

    private static void watch(Cctv cctv, int direction, int[][] map) {
        int[][] dirs = getDirections(cctv.type, direction);
        for (int[] dir : dirs) {
            int x = cctv.x;
            int y = cctv.y;
            while (true) {
                x += dir[0];
                y += dir[1];
                if (x < 0 || x >= n || y < 0 || y >= m || map[x][y] == 6) {
                    break;
                }
                if (map[x][y] == 0) {
                    map[x][y] = -1;
                }
            }
        }
    }

    private static int[][] getDirections(int type, int direction) {
        switch (type) {
            case 1:
                return new int[][]{directions[direction]};
            case 2:
                return new int[][]{directions[direction], directions[(direction + 2) % 4]};
            case 3:
                return new int[][]{directions[direction], directions[(direction + 1) % 4]};
            case 4:
                return new int[][]{directions[direction], directions[(direction + 1) % 4],
                        directions[(direction + 2) % 4]};
            case 5:
                return directions;
            default:
                return new int[][]{};
        }
    }

    private static int calculateBlindSpot(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
