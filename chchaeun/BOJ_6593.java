import java.io.*;
import java.util.*;

public class BOJ_6593 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        while(true){
            int L, R, C;
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L==0 && R==0 && C==0){
                break;
            }

            char[][][] board = new char[L][R][C];
            int sl = 0, sr = 0, sc = 0;

            for (int i = 0; i < L; i++){
                for (int j = 0; j < R; j++){
                    board[i][j] = br.readLine().toCharArray();

                    for (int k = 0; k < C; k++){
                        if (board[i][j][k] == 'S'){
                            sl = i;
                            sr = j;
                            sc = k;
                        }
                    }
                }
                br.readLine();
            }

            int[] dls = { 0, 0, 0, 0, 1, -1 };
            int[] drs = { 1, 0, -1, 0, 0, 0 };
            int[] dcs = { 0, 1, 0, -1, 0, 0 };

            Queue<int[]> queue = new LinkedList<>();
            boolean[][][] visited = new boolean[L][R][C];

            queue.add(new int[]{sl, sr, sc, 0});
            visited[sl][sr][sc] = true;

            int answer = (int) 1e9;

            while(!queue.isEmpty()){
                int[] c = queue.poll();

                if (board[c[0]][c[1]][c[2]] == 'E'){
                    answer = answer > c[3] ? c[3] : answer;
                }

                for (int i = 0; i < 6; i++){
                    int nl = c[0] + dls[i];
                    int nr = c[1] + drs[i];
                    int nc = c[2] + dcs[i];

                    if (0 <= nl && nl < L && 0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nl][nr][nc] && board[nl][nr][nc] != '#'){
                        queue.add(new int[]{nl, nr, nc, c[3] + 1});
                        visited[nl][nr][nc] = true;
                    }
                }
            }

            if (answer == (int) 1e9){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in " + answer + " minute(s).");
            }
        }
    }
}
