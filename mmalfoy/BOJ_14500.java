import java.io.*;
import java.util.*;

public class BOJ_14500 {
    // [도형번호][갯수][n, m]
    static int[][][] d = new int[][][] {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {-1, 1}, {-2, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 0}, {-1, 0}, {-1, 1}, {-1, 2}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {0, 1}, {-1, 1}, {1, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
            {{0, 0}, {0, 1}, {-1, 1}, {-1, 2}},
            {{0, 0}, {-1, 0}, {-1, 1}, {-2, 1}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
    };
    
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] G  = new int[N][M];
        
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int m = 0; m < M; m++) {
                G[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(dfs(G));
    }
    
    private static int dfs(int[][] G) {
        int max = Integer.MIN_VALUE;
        for (int n = 0; n < G.length; n++) {
            for (int m = 0; m < G[0].length; m++) {
                for (int[][] d1 : d) {
                    int cnt = 0;
                    for (int[] d2 : d1) {
                        int ny = n + d2[0];
                        int nx = m + d2[1];
                        if (ny < 0 || ny >= G.length || nx < 0 || nx >= G[0].length) {
                            break;
                        }
                        cnt += G[ny][nx];
                    }
                    if (cnt > max) {
                        max = cnt;
                    }
                }
            }
        }
        
        return max;
    }
}