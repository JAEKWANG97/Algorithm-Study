import java.util.*;
import java.io.*;
 
public class BOJ_1890{
 
    static int N;
    static int[][] map;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dp = new long[N+1][N+1];
        for(int i=1;i<=N;i++){
            String[] input = br.readLine().split(" ");
            for(int j=1;j<=N;j++)
                map[i][j] = Integer.parseInt(input[j-1]);
        }
        dp[1][1] = 1;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i==N&&j==N) //도착지점
                    continue;
                int next = map[i][j]; //map 값
                if(i+next <= N)
                    dp[i+next][j] += dp[i][j]; //세로 이동
                if(j+next <= N)
                    dp[i][j+next] += dp[i][j]; //가로 이동
            }
        }
        System.out.println(dp[N][N]);
    }
 
}
 
출처: https://katastrophe.tistory.com/12 [kimkata's Devlog:티스토리]
