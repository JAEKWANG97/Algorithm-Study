import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, answer;
    private static int[][] board;
    private static int[] dir;
    private static final List<Cctv> cctv = new ArrayList<>();
    private static final int[][] camDir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (1 <= board[i][j] && board[i][j] <= 5) {
                    cctv.add(new Cctv(i, j, board[i][j]));
                }
            }
        }
        dir = new int[cctv.size()];
        answer = Integer.MAX_VALUE;
        solution(0);

        System.out.println(answer);
    }

    private static void solution(int depth) {
        if (depth == cctv.size()) {
            //정해진 방향으로 탐색
            check();
            return;
        }

        int size = 0;
        if (cctv.get(depth).num == 1) {
            size = 4;
        }
        if (cctv.get(depth).num == 2) {
            size = 2;
        }
        if (cctv.get(depth).num == 3) {
            size = 4;
        }
        if (cctv.get(depth).num == 4) {
            size = 4;
        }
        if (cctv.get(depth).num == 5) {
            size = 1;
        }

        for (int i = 0; i < size; i++) {
            dir[depth] = i;
            solution(depth + 1);
        }
    }

    private static void check() {
        int[][] copyBoard = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < cctv.size(); i++) {
            int camNum = cctv.get(i).num;
            int index = dir[i];

            if (camNum == 1) {
                watch(copyBoard, new int[]{index}, i);
            }
            if (camNum == 2) {
                watch(copyBoard, new int[]{index, index + 2}, i);
            }
            if (camNum == 3) {
                watch(copyBoard, new int[]{index % 4, (index + 1) % 4}, i);
            }
            if (camNum == 4) {
                watch(copyBoard, new int[]{index % 4, (index + 1) % 4, (index+2)%4}, i);
            }
            if (camNum == 5) {
                watch(copyBoard, new int[]{index,index+1,index+2,index+3}, i);
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyBoard[i][j] == 0) {
                    result++;
                }
            }
        }

        answer = Math.min(answer, result);
    }

    private static void watch(int[][] copy, int[] see, int index) {
        int x = cctv.get(index).x;
        int y = cctv.get(index).y;

        for (int i = 0; i < see.length; i++) {
            int dx = camDir[see[i]][0];
            int dy = camDir[see[i]][1];

            int nx = x + dx;
            int ny = y + dy;
            while (isValid(nx, ny) && copy[nx][ny] != 6) {
                if (copy[nx][ny] == 0) {
                    copy[nx][ny] = -1;
                }
                nx += dx;
                ny += dy;
            }
        }
    }

    private static boolean isValid(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m;
    }


    static class Cctv {
        int x, y, num;

        public Cctv(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
