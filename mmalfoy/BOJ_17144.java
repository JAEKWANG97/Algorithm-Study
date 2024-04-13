import java.io.*;
import java.util.*;
public class BOJ_17144 {

    static int R, C, T, map[][];
    static int cleaner = -1;
    static Queue<Dust> dusts;
    static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
    static class Dust {
        int x, y, w;

        public Dust(int x, int y, int w) {
            super();
            this.x = x;
            this.y = y;
            this.w = w;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(cleaner == -1 && map[i][j] == -1) {
                    cleaner = i;
                }
            }
        }

        for (int time = 0; time < T; time++) {

            checkDust();

            spread();

            operate();

        }

        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == -1) continue;
                res += map[i][j];
            }
        }

        System.out.println(res);
    }

    private static void checkDust() {

        dusts = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == -1 || map[i][j] == 0) continue;
                dusts.add(new Dust(i, j, map[i][j]));
            }
        }

    }

    private static void spread() {

        while(!dusts.isEmpty()) {

            Dust now = dusts.poll();
            if(now.w < 5) continue;
            int amountOfSpread = now.w / 5;
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int xx = now.x + dx[d];
                int yy = now.y + dy[d];
                if(xx < 0 || xx >= R || yy < 0 || yy >= C) continue;
                if(map[xx][yy] == -1) continue;

                map[xx][yy] += amountOfSpread;
                ++cnt;
            }

            map[now.x][now.y] -= amountOfSpread * cnt;
        }

    }

    private static void operate() {

        int top = cleaner;
        int down = cleaner + 1;

        for (int i = top - 1; i > 0; i--) 
            map[i][0] = map[i-1][0];
        for (int i = 0; i < C - 1; i++) 
            map[0][i] = map[0][i+1];
        for (int i = 0; i < top; i++) 
            map[i][C - 1] = map[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) 
            map[top][i] = map[top][i-1];
        map[top][1] = 0;

        for (int i = down + 1; i < R - 1; i++) 
            map[i][0] = map[i + 1][0];
        for (int i = 0; i < C - 1; i++) 
            map[R - 1][i] = map[R - 1][i + 1]; 
    }
       
}