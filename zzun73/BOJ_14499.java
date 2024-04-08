import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14499 {
    static int[][] dice, map;
    static int[] dx = new int[]{0, 0, -1, 1}; // 동,서,북,남
    static int[] dy = new int[]{1, -1, 0, 0};

    static void rolling(int dir) {
        int temp;

        if (dir == 0) { // 동
            temp = dice[3][1];
            dice[3][1] = dice[1][2];
            for (int i = 2; i > 0; i--) {
                dice[1][i] = dice[1][i - 1];
            }
            dice[1][0] = temp;
        } else if (dir == 1) { // 서
            temp = dice[3][1];
            dice[3][1] = dice[1][0];
            for (int i = 0; i < 2; i++) {
                dice[1][i] = dice[1][i + 1];
            }
            dice[1][2] = temp;
        } else if (dir == 2) { // 북
            temp = dice[0][1];
            for (int i = 0; i < 3; i++) {
                dice[i][1] = dice[i + 1][1];
            }
            dice[3][1] = temp;
        } else { // 남
            temp = dice[3][1];
            for (int i = 3; i > 0; i--) {
                dice[i][1] = dice[i - 1][1];
            }
            dice[0][1] = temp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        dice = new int[4][3];

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) {
                continue;
            }

            rolling(dir);
            x = nx;
            y = ny;

            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[3][1];
            } else {
                dice[3][1] = map[nx][ny];
                map[nx][ny] = 0;
            }
            sb.append(dice[1][1]).append("\n");
        }
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}