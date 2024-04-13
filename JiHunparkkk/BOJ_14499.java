import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, k;
    private static int[][] board;
    private static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int x, y;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] order = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        dice = new int[7];  //1부터 시작
        move(order, x, y);
    }

    private static void move(int[] order, int x, int y) {
        int[][] dxy = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};   //동서북남
        int nx = x, ny = y;
        for (int i = 0; i < k; i++) {
            int orderNum = order[i] - 1;
            nx += dxy[orderNum][0];
            ny += dxy[orderNum][1];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                nx -= dxy[orderNum][0];
                ny -= dxy[orderNum][1];
                continue;
            }

            int tmp = dice[3];
            if (orderNum == 0) {  //동쪽
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
            } else if (orderNum == 1) { //서쪽
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
            } else if (orderNum == 2) { //북쪽
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = tmp;
            } else { //남쪽
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
            }

            if (board[nx][ny] == 0) {
                board[nx][ny] = dice[6];
            } else {
                dice[6] = board[nx][ny];
                board[nx][ny] = 0;
            }
            System.out.println(dice[3]);
        }
    }

}


