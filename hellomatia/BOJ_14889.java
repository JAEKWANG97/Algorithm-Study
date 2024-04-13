package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_14889 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[][] S;
    private boolean[] isStartTeam;
    private int result;

    public static void main(String[] args) throws IOException {
        new BOJ_14889().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void calcResult() {
        isStartTeam = new boolean[N];
        result = Integer.MAX_VALUE;
        pickStartTeam(0, 0);
    }

    private void pickStartTeam(int start, int count) {
        if (count == N / 2) {
            int diffScore = Math.abs(calcStartTeamScore() - calcLinkTeamScore());
            result = Math.min(result, diffScore);
        }

        for (int i = start; i < N; i++) {
            isStartTeam[i] = true;
            pickStartTeam(i + 1, count + 1);
            isStartTeam[i] = false;
        }
    }

    private int calcStartTeamScore() {
        int score = 0;
        for (int i = 0; i < N; i++) {
            if (!isStartTeam[i]) continue;
            for (int j = i + 1; j < N; j++) {
                if (!isStartTeam[j]) continue;
                score += S[i][j];
                score += S[j][i];
            }
        }
        return score;
    }

    private int calcLinkTeamScore() {
        int score = 0;
        for (int i = 0; i < N; i++) {
            if (isStartTeam[i]) continue;
            for (int j = i + 1; j < N; j++) {
                if (isStartTeam[j]) continue;
                score += S[i][j];
                score += S[j][i];
            }
        }
        return score;
    }

    private void printResult() throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
