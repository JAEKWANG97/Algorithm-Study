package BOJ;

import java.io.*;
import java.util.*;

class BOJ_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] Mem = new int[N + 1][K + 1]; 
        int[] W = new int[N + 1]; 
        int[] V = new int[N + 1]; 
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
        	W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) { 
            for (int j = 1; j <= K; j++) { 
                Mem[i][j] = Mem[i - 1][j];
                if (j - W[i] >= 0) { 
                    Mem[i][j] = Math.max(Mem[i - 1][j], Mem[i - 1][j - W[i]] + V[i]);
                }
            }
        }
        System.out.println(Mem[N][K]);

    }
}