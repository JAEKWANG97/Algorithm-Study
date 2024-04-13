package hellomatia;

import java.io.*;
import java.util.StringTokenizer;

public class SEA_4014 {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, X;
    private int[][] map;
    private boolean[] canRow;
    private boolean[] canCol;

    public static void main(String[] args) throws IOException {
        new SEA_4014().solution();
    }

    private void solution() throws IOException {
        int T = Integer.parseInt(bf.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            init();
            printResult(testCase, calcResult());
        }
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        canRow = new boolean[N];
        canCol = new boolean[N];
    }

    private int calcResult() {
        for (int i = 0; i < N; i++) {
            calcCanRow(i);
            calcCanCol(i);
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            if (canRow[i]) result++;
            if (canCol[i]) result++;
        }
        return result;
    }

    private void calcCanRow(int x) {
        boolean[] hasRunway = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int nextY = i + 1;
            int diff = map[x][nextY] - map[x][i];
            if (diff > 1 || diff < -1) return;
            if (diff == 0) continue;
            if (diff == 1) {
                for (int j = i; j > i - X; j--) {
                    if (j < 0) return;
                    if (map[x][i] != map[x][j] || hasRunway[j]) return;
                    hasRunway[j] = true;
                }
            } else {
                for (int j = i + 1; j < i + X + 1; j++) {
                    if (j >= N) return;
                    if (map[x][i + 1] != map[x][j] || hasRunway[j]) return;
                    hasRunway[j] = true;
                }
            }
        }
        canRow[x] = true;
    }

    private void calcCanCol(int y) {
        boolean[] hasRunway = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int nextX = i + 1;
            int diff = map[nextX][y] - map[i][y];
            if (diff > 1 || diff < -1) return;
            if (diff == 0) continue;
            if (diff == 1) {
                for (int j = i; j > i - X; j--) {
                    if (j < 0) return;
                    if (map[i][y] != map[j][y] || hasRunway[j]) return;
                    hasRunway[j] = true;
                }
            } else {
                for (int j = i + 1; j < i + X + 1; j++) {
                    if (j >= N) return;
                    if (map[i + 1][y] != map[j][y] || hasRunway[j]) return;
                    hasRunway[j] = true;
                }
            }
        }
        canCol[y] = true;
    }

    private void printResult(int testCase, int result) throws IOException {
        bw.write("#" + testCase + " " + result + "\n");
        bw.flush();
    }
}

