import java.util.*;
import java.io.*;

public class BOJ_2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), INF = 100;
        int[][] D = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(D[i], INF);
            D[i][i] = 0;
        }
        
        StringTokenizer st;
        int a, b;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (a == -1) {
                break;
            }
            D[a][b] = 1;
            D[b][a] = 1;
        }
        
        
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    D[i][j] = Integer.min(D[i][j], D[i][k] + D[k][j]);
                }
            }        
        }
        
        int min = 100;
        List<Integer> mins = new ArrayList<>();
        
        for (int i = 1; i < N + 1; i++) {
            int tmp = 0;
            for (int j = 1; j < N + 1; j++) {
                tmp = Integer.max(tmp, D[i][j]);
            }
            if (tmp < min) {
                mins = new ArrayList<>();
                mins.add(i);
                min = tmp;
            } else if (tmp == min) {
                mins.add(i);
            }
        }
        
        System.out.println(min + " " + mins.size());
        for (int m : mins) {
            System.out.print(m + " ");
        }
        
    }
}
