import java.io.*;
import java.util.*;

public class BOJ_21609 {
    static int N, M, EMPTY = 10, cnt, rainbow;
    static int[][] G, d = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = new int[N][N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int m = 0; m < N; m++) {
                G[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        int total = 0;
        while (true) {
            visited = new boolean[M + 1][N][N];
            int[] result = find();
            if (result[2] < 2) {
                break;
            }
            total +=  result[2] * result[2];
            
            boolean[][] visit = new boolean[N][N];
            visit[result[0]][result[1]] = true;
            remove(result[0], result[1], G[result[0]][result[1]], visit);
            G[result[0]][result[1]] = EMPTY;
            

            gravity();
            rotate();
            gravity();
        }
        
        System.out.println(total);

    }

    static int[] find() {
        int maxY = 0, maxX = 0, max = 0, maxRainbow = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (1 <= G[i][j] && G[i][j] <= M) {
                    cnt = 0;
                    rainbow = 0;
                    if (!visited[G[i][j]][i][j]) {
                        visited[G[i][j]][i][j] = true;
                        dfs(i, j, G[i][j]);
                        if (cnt > max) {
                            maxY = i;
                            maxX = j;
                            maxRainbow = rainbow;
                            max = cnt;
                            continue;
                        } 
                        
                        if (cnt == max) {
                            if (rainbow > maxRainbow) {
                                maxY = i;
                                maxX = j;
                                maxRainbow = rainbow;
                                continue;
                            } 
                            
                            if (rainbow == maxRainbow) {                                
                                if (maxY < i) {
                                    maxY = i;
                                    maxX = j;
                                    continue;
                                }
                                
                                if (maxY == i && maxX < j) {                        
                                    maxY = i;
                                    maxX = j;
                                    continue;                                    
                                }                                
                            }                                                        
                        } 
                    }
                }
            }
        }
        return new int[] { maxY, maxX, max + 1 };
    }

    static void dfs(int i, int j, int idx) {
        for (int k = 0; k < 4; k++) {
            int ni = i + d[k][0];
            int nj = j + d[k][1];

            if (ni < 0 || ni > N - 1 || nj < 0 || nj > N - 1 || visited[idx][ni][nj]) {
                continue;
            }

            if (G[ni][nj] == 0) {
                cnt += 1;
                rainbow += 1;
                visited[idx][ni][nj] = true;
                dfs(ni, nj, idx);
            } else if (G[ni][nj] == idx) {
                cnt += 1;
                visited[idx][ni][nj] = true;
                dfs(ni, nj, idx);
            }
        }
    }

    static void remove(int i, int j, int idx, boolean[][] visited) {

            for (int k = 0; k < 4; k++) {
                int ni = i + d[k][0];
                int nj = j + d[k][1];
                
                if (ni < 0 || ni > N - 1 || nj < 0 || nj > N - 1 ||
                        visited[ni][nj] ) {
                    continue;
                }
                
                if (G[ni][nj] == 0 || G[ni][nj] == idx) {
                    G[ni][nj] = EMPTY;
                    visited[ni][nj] = true;
                    remove(ni, nj, idx, visited);
                } 
            }    
        
    }

    static void rotate() {
        int[][] newG = new int[N][N];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < N; m++) {
                newG[N - 1 - m][n] = G[n][m];
            }
        }
        G = newG;
    }

    static void gravity() {
        for (int m = 0; m < N; m++) {
            int cnt = 0;
            for (int n = N - 1; n >= 0; n--) {
                if (G[n][m] == -1) {
                    cnt = 0;
                    continue;
                } else if (G[n][m] == EMPTY) {
                    cnt += 1;
                    continue;
                } else if (cnt != 0) {
                    G[n + Integer.max(0, cnt)][m] = G[n][m];
                    G[n][m] = EMPTY;
                }

            }
        }
    }
}
