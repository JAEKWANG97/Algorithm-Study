import java.util.Scanner;

public class CT_메이즈러너 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m, k;
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();

        int[][] board = new int[n + 1][n + 1];
        int[][] next_board = new int[n + 1][n + 1];
        int[][] traveler = new int[m + 1][2];
        int[] exits = new int[2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        for (int i = 1; i <= m; i++) {
            traveler[i][0] = scanner.nextInt();
            traveler[i][1] = scanner.nextInt();
        }

        exits[0] = scanner.nextInt();
        exits[1] = scanner.nextInt();

        int ans = 0;
        int sx = 0, sy = 0, square_size = 0;

        for (int i = 0; i < k; i++) {
            moveAllTraveler(n, m, traveler, exits, board);
            boolean isAllEscaped = true;
            for (int j = 1; j <= m; j++) {
                if (traveler[j][0] != exits[0] || traveler[j][1] != exits[1]) {
                    isAllEscaped = false;
                    break;
                }
            }
            if (isAllEscaped) {
                break;
            }
            findMinimumSquare(n, m, traveler, exits, board, next_board);
            rotateSquare(sx, sy, square_size, board, next_board);
            rotateTravelerAndExit(n, m, sx, sy, square_size, traveler, exits);
        }

        System.out.println(ans);
        System.out.println(exits[0] + " " + exits[1]);
    }

    public static void moveAllTraveler(int n, int m, int[][] traveler, int[] exits, int[][] board) {
        for (int i = 1; i <= m; i++) {
            if (traveler[i][0] == exits[0] && traveler[i][1] == exits[1]) {
                continue;
            }

            int tx = traveler[i][0];
            int ty = traveler[i][1];
            int ex = exits[0];
            int ey = exits[1];

            if (tx != ex) {
                int nx = tx;
                if (ex > nx) {
                    nx += 1;
                } else {
                    nx -= 1;
                }
                if (board[nx][ty] == 0) {
                    traveler[i][0] = nx;
                    ans++;
                    continue;
                }
            }

            if (ty != ey) {
                int ny = ty;
                if (ey > ny) {
                    ny += 1;
                } else {
                    ny -= 1;
                }
                if (board[tx][ny] == 0) {
                    traveler[i][1] = ny;
                    ans++;
                    continue;
                }
            }
        }
    }

    public static void findMinimumSquare(int n, int m, int[][] traveler, int[] exits, int[][] board, int[][] next_board) {
        int ex = exits[0];
        int ey = exits[1];

        for (int sz = 2; sz <= n; sz++) {
            for (int x1 = 1; x1 <= n - sz + 1; x1++) {
                for (int y1 = 1; y1 <= n - sz + 1; y1++) {
                    int x2 = x1 + sz - 1;
                    int y2 = y1 + sz - 1;

                    if (!(x1 <= ex && ex <= x2 && y1 <= ey && ey <= y2)) {
                        continue;
                    }

                    boolean isTravelerIn = false;
                    for (int l = 1; l <= m; l++) {
                        int tx = traveler[l][0];
                        int ty = traveler[l][1];
                        if (x1 <= tx && tx <= x2 && y1 <= ty && ty <= y2) {
                            if (!(tx == ex && ty == ey)) {
                                isTravelerIn = true;
                            }
                        }
                    }

                    if (isTravelerIn) {
                        sx = x1;
                        sy = y1;
                        square_size = sz;
                        return;
                    }
                }
            }
        }
    }

    public static void rotateSquare(int sx, int sy, int square_size, int[][] board, int[][] next_board) {
        for (int x = sx; x < sx + square_size; x++) {
            for (int y = sy; y < sy + square_size; y++) {
                if (board[x][y] > 0) {
                    board[x][y]--;
                }
            }
        }

        for (int x = sx; x < sx + square_size; x++) {
            for (int y = sy; y < sy + square_size; y++) {
                int ox = x - sx;
                int oy = y - sy;
                int rx = oy;
                int ry = square_size - ox - 1;
                next_board[rx + sx][ry + sy] = board[x][y];
            }
        }

        for (int x = sx; x < sx + square_size; x++) {
            for (int y = sy; y < sy + square_size; y++) {
                board[x][y] = next_board[x][y];
            }
        }
    }

    public static void rotateTravelerAndExit(int n, int m, int sx, int sy, int square_size, int[][] traveler, int[] exits) {
        for (int i = 1; i <= m; i++) {
            int tx = traveler[i][0];
            int ty = traveler[i][1];
            if (sx <= tx && tx < sx + square_size && sy <= ty && ty < sy + square_size) {
                int ox = tx - sx;
                int oy = ty - sy;
                int rx = oy;
                int ry = square_size - ox - 1;
                traveler[i][0] = rx + sx;
                traveler[i][1] = ry + sy;
            }
        }

        int ex = exits[0];
        int ey = exits[1];
        if (sx <= ex && ex < sx + square_size && sy <= ey && ey < sy + square_size) {
            int ox = ex - sx;
            int oy = ey - sy;
            int rx = oy;
            int ry = square_size - ox - 1;
            exits[0] = rx + sx;
            exits[1] = ry + sy;
        }
    }
}
