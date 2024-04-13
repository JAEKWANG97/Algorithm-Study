package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_12865 {
    int N; // 물품의 수
    int K; // 준서가 버틸 수 있는 무게
    int[] dp;
    int[][] bags;

    public static void main (String[] args) throws IOException {
        new BOJ_12865().solution();
    }

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K+1];
        bags = new int[N+1][2];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(bf.readLine());
            // 각 물건의 무게
            bags[i][0] = Integer.parseInt(st.nextToken());
            // 해당 물건의 가치
            bags[i][1] = Integer.parseInt(st.nextToken());
        }

        bottomUp();

        bw.write(dp[K]+"\n");
        bw.flush();
        bw.close();
    }

    public void bottomUp() {
        for (int i=1; i<=N; i++) {
            for (int j=K; j-bags[i][0]>=0; j--) {
                dp[j] = Math.max(dp[j], dp[j-bags[i][0]]+bags[i][1]);
            }
        }
    }
}