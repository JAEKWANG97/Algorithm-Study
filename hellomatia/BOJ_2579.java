package hellomatia;

import java.io.*;

public class BOJ_2579 {

    public static void main (String[] args) throws IOException {
        new BOJ_2579().solution();
    }

    private void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfStair = Integer.parseInt(bf.readLine());
        int[] stairScores = new int[numOfStair+1];

        for(int i=1; i<=numOfStair; i++) {
            stairScores[i] = Integer.parseInt(bf.readLine());
        }

        /*
         * 조건
         * 1. 계단을 오를 때 한 계단, 또는 두 계단을 오를 수 있다.
         * 2. 연속된 3개의 계단을 밟으면 안된다. (즉, 한 계단씩 올라갈 때 최대 연속으로 2번만 한계단 씩 오를 수 있다는 의미다.)
         * 3. 마지막 계단을 '반드시' 밟아야 한다.
         */

        /*
         * Top-Down 방식, 즉 큰 문제부터 작은 문제로 들어가는 방식
         * 이 때 재귀호출을 통해 작은문제로 쪼개서 들어가는 것 이게 Top-Down 방식이다.
         * Top-Down 방식이 있다면 반대로 Bottom-Up 방식도 있다.
         * Bottom-Up 방식은 작은 문제부터 풀어가며 전체 문제를 풀어가는 방식이다.
         * 이 방법은 대개 반복문을 통해 구현된다.
         */

        int[] dp = new int[numOfStair+1];

        dp[1] = stairScores[1];

        if(numOfStair>=2) {
            dp[2] = stairScores[1] + stairScores[2];
        }

        for(int i=3; i<=numOfStair; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3]+stairScores[i-1]) + stairScores[i];
        }

        bw.write(dp[numOfStair]+"\n");
        bw.flush();
        bw.close();
    }
}