import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class AirMachine {
        int x, y;

        public AirMachine(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int r, c, t;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[r][c];
        AirMachine top = null;
        AirMachine bottom = null;

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == -1) {
                    if (top == null) {
                        top = new AirMachine(i, j);
                    } else {
                        bottom = new AirMachine(i, j);
                    }
                }
            }
        }

        while (t > 0) {
            int[][] boardCopy = new int[r][c];    //확산된 먼지 저장
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (board[i][j] >= 0) {
                        spreadDust(i, j, boardCopy);
                    }
                }
            }

            board = boardCopy;

            //공기청정기 동작
            playMachineTop(top);
            playMachineBottom(bottom);
            board[top.x][top.y] = -1;
            board[bottom.x][bottom.y] = -1;

            t--;
        }

        int answer = sum();
        System.out.println(answer);
    }

    private static void spreadDust(int x, int y, int[][] boardCopy) {
        int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int cnt = 0;
        int spreadAmount = (int) Math.floor(board[x][y] / 5);
        for (int i = 0; i < 4; i++) {
            int nx = x + dxy[i][0];
            int ny = y + dxy[i][1];

            if (isIn(nx, ny) && board[nx][ny] >= 0) {
                cnt++;
                boardCopy[nx][ny] += spreadAmount;
            }
        }

        boardCopy[x][y] += (board[x][y] - spreadAmount * cnt);
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < r && ny < c;
    }

    private static void printTest() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playMachineTop(AirMachine top) {
        int nx, ny, x, y;

        //아래로
        x = top.x - 2;
        y = top.y;
        nx = x + 1;
        ny = y;
        while (!(nx == top.x && ny == top.y)) {
            if (!isIn(x, ny)) {
                break;
            }

            board[nx][ny] = board[x][y];
            nx = x;
            x -= 1;
        }

        //nx, ny는 그대로
        x = nx;
        y = ny + 1;
        while (!(nx == top.x && ny == top.y)) {
            if (!isIn(x, y)) {
                break;
            }

            board[nx][ny] = board[x][y];
            ny = y;
            y += 1;
        }

        x = nx + 1;
        y = ny;
        while (!(nx == top.x && ny == top.y)) {
            if (!isIn(x, y) || x == top.x + 1) {
                break;
            }

            board[nx][ny] = board[x][y];
            nx = x;
            x += 1;
        }

        x = nx;
        y = ny - 1;
        while (!(nx == top.x && ny == top.y)) {
            if (!isIn(x, y)) {
                break;
            }

            board[nx][ny] = board[x][y];
            ny = y;
            y -= 1;
        }
    }

    private static void playMachineBottom(AirMachine bottom) {
        int nx, ny, x, y;

        nx = bottom.x + 1;
        ny = bottom.y;
        x = nx + 1;
        y = ny;
        while (!(nx == bottom.x && ny == bottom.y)) {
            if (!isIn(x, y)) {
                break;
            }

            board[nx][ny] = board[x][y];
            nx = x;
            x += 1;
        }

        x = nx;
        y = ny + 1;
        while (!(nx == bottom.x && ny == bottom.y)) {
            if (!isIn(x, y)) {
                break;
            }

            board[nx][ny] = board[x][y];
            ny = y;
            y += 1;
        }

        x = nx - 1;
        y = ny;
        while (!(nx == bottom.x && ny == bottom.y)) {
            if (!isIn(x, ny) || nx == bottom.x) {
                break;
            }

            board[nx][ny] = board[x][y];
            nx = x;
            x -= 1;
        }

        x = nx;
        y = ny - 1;
        while (!(nx == bottom.x && ny == bottom.y)) {
            if (!isIn(x, y)) {
                break;
            }

            board[nx][ny] = board[x][y];
            ny = y;
            y -= 1;
        }
    }

    private static int sum() {
        int result = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] > 0) {
                    result += board[i][j];
                }
            }
        }
        return result;
    }
}


