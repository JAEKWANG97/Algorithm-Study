import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
};

public class CT_메이즈러너  {
    public static final int MAX_N = 10;

    public static int n, m, k;
    public static int[][] board = new int[MAX_N + 1][MAX_N + 1];
    public static int[][] nextBoard = new int[MAX_N + 1][MAX_N + 1];
    public static Pair[] traveler = new Pair[MAX_N + 1];
    public static Pair exits;
    public static int ans;
    public static int sx, sy, squareSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            traveler[i] = new Pair(x, y);
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        exits = new Pair(x, y);

        while(k-- > 0) {
            moveAllTraveler();

            boolean isAllEscaped = true;
            for(int i = 1; i <= m; i++) {
                if(!(traveler[i].x == exits.x && traveler[i].y == exits.y)) {
                    isAllEscaped = false;
                }
            }

            if(isAllEscaped) break;

            findMinimumSquare();

            rotateSquare();
            rotateTravelerAndExit();
        }

        System.out.print(ans + "\n");
        System.out.print(exits.x + " " + exits.y);
    }
    
    public static void moveAllTraveler() {
        for(int i = 1; i <= m; i++) {
            if(traveler[i].x == exits.x && traveler[i].y == exits.y)
                continue;

            if(traveler[i].x != exits.x) {
                int nx = traveler[i].x;
                int ny = traveler[i].y;

                if(exits.x > nx) nx++;
                else nx--;

                if(board[nx][ny] == 0) {
                    traveler[i].x = nx;
                    traveler[i].y = ny;
                    ans++;
                    continue;
                }
            }

            if(traveler[i].y != exits.y) {
                int nx = traveler[i].x;
                int ny = traveler[i].y;

                if(exits.y > ny) ny++;
                else ny--;

                if(board[nx][ny] == 0) {
                    traveler[i].x = nx;
                    traveler[i].y = ny;
                    ans++;
                    continue;
                }
            }
        }
    }

    public static void findMinimumSquare() {
        for(int sz = 2; sz <= n; sz++) {
            for(int x1 = 1; x1 <= n - sz + 1; x1++) {
                for(int y1 = 1; y1 <= n - sz + 1; y1++) {
                    int x2 = x1 + sz - 1;
                    int y2 = y1 + sz - 1;

                    if(!(x1 <= exits.x && exits.x <= x2 && y1 <= exits.y && exits.y <= y2)) {
                        continue;
                    }

                    boolean isTravelerIn = false;
                    for(int l = 1; l <= m; l++) {
                        if(x1 <= traveler[l].x && traveler[l].x <= x2 && y1 <= traveler[l].y && traveler[l].y <= y2) {
                            if(!(traveler[l].x == exits.x && traveler[l].y == exits.y)) {
                                isTravelerIn = true;
                            }
                        }
                    }

                    if(isTravelerIn) {
                        sx = x1;
                        sy = y1;
                        squareSize = sz;

                        return;
                    }
                }
            }
        }
    }

    public static void rotateSquare() {
        for(int x = sx; x < sx + squareSize; x++)
            for(int y = sy; y < sy + squareSize; y++) {
                if(board[x][y] > 0) board[x][y]--;
            }

        for(int x = sx; x < sx + squareSize; x++)
            for(int y = sy; y < sy + squareSize; y++) {
                int ox = x - sx, oy = y - sy;
                int rx = oy, ry = squareSize - ox - 1;
                nextBoard[rx + sx][ry + sy] = board[x][y];
            }

        for(int x = sx; x < sx + squareSize; x++)
            for(int y = sy; y < sy + squareSize; y++) {
                board[x][y] = nextBoard[x][y];
            }
    }

    public static void rotateTravelerAndExit() {
        for(int i = 1; i <= m; i++) {
            int x = traveler[i].x;
            int y = traveler[i].y;

            if(sx <= x && x < sx + squareSize && sy <= y && y < sy + squareSize) {
                int ox = x - sx, oy = y - sy;
                int rx = oy, ry = squareSize - ox - 1;
                traveler[i].x = rx + sx;
                traveler[i].y = ry + sy;
            }
        }

        int x = exits.x;
        int y = exits.y;
        if(sx <= x && x < sx + squareSize && sy <= y && y < sy + squareSize) {
            int ox = x - sx, oy = y - sy;
            int rx = oy, ry = squareSize - ox - 1;
            exits.x = rx + sx;
            exits.y = ry + sy;
        }
    }

    
}
