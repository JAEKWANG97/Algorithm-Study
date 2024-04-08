package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11660 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private int[][] map;

    public static void main(String[] args) throws IOException {
        new BOJ_11660().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < M; i++) {
            printResult(calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                // 각 행의 누적합을 저장
                map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j - 1];
            }
        }
    }

    private int calcResult() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        int sum = 0;

        for (int i = x1; i <= x2; i++) {
            sum += map[i][y2] - map[i][y1 - 1];
        }

        return sum;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

