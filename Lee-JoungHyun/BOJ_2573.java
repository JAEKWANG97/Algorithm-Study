import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {

    static class Poz {
        int y, x;
        public Poz(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int iceburgCnt = 0;
        int lastIceX = 0;
        int lastIceY = 0;

        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[][] map = new int[Y][X];
        int[][] mCnt = new int[Y][X];
        for (int y = 0; y < Y; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }


        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (map[y][x] != 0) {
                    iceburgCnt++;
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (0 <= ny && ny < Y && 0 <= nx && nx < X && map[ny][nx] == 0)
                            cnt++;
                    }
                    lastIceY = y;
                    lastIceX = x;
                    mCnt[y][x] = cnt;
                }

            }
        }

//        for (int i = 0; i < Y; i++)
//            System.out.println(Arrays.toString(map[i]));
//        System.out.println("시작: " + iceburgCnt);

        int time = 0;
        Queue<Poz> queue;
        boolean[][] visited;
        while (true) {
            int[][] nxtMCnt = new int[Y][X];
            int nowLastY = lastIceY, nowLastX = lastIceX;
            for (int y = 0; y < Y; y++)
                for (int x = 0; x < X; x++)
                    nxtMCnt[y][x] = mCnt[y][x];


            queue = new LinkedList<>();
            visited = new boolean[Y][X];

            queue.add(new Poz(lastIceY, lastIceX));
            visited[lastIceY][lastIceX] = true;
            Poz now = queue.peek();
            int leftIce = 0;
            // 시작점 녹이기
            if (map[now.y][now.x] - mCnt[now.y][now.x] <= 0) {
                map[now.y][now.x] = 0;

                for (int dir = 0; dir < 4; dir++) {
                    int ncx = now.x + dx[dir];
                    int ncy = now.y + dy[dir];
                    if (0 <= ncx && ncx < X && 0 <= ncy && ncy < Y) {
                        nxtMCnt[ncy][ncx]++;
                    }
                }
            }
            else {
                map[now.y][now.x] -= mCnt[now.y][now.x];
                leftIce++;
                lastIceY = now.y;
                lastIceX = now.x;
            }

            while (!queue.isEmpty()) {
                now = queue.poll();
                iceburgCnt--;
                for (int d = 0; d < 4; d++) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    if (0 <= ny && ny < Y && 0 <= nx && nx < X && !visited[ny][nx] && map[ny][nx] != 0) {
                        visited[ny][nx] = true;
                        if (map[ny][nx] - mCnt[ny][nx] <= 0) {
                            map[ny][nx] = 0;
                            for (int dir = 0; dir < 4; dir++) {
                                int ncx = nx + dx[dir];
                                int ncy = ny + dy[dir];
                                if (0 <= ncx && ncx < X && 0 <= ncy && ncy < Y) {
                                    nxtMCnt[ncy][ncx]++;
                                }
                            }
                        }
                        else {
                            map[ny][nx] -= mCnt[ny][nx];
                            leftIce++;
                            nowLastY = ny;
                            nowLastX = nx;

                        }
                        queue.add(new Poz(ny, nx));
                    }
                }
            }
            for (int i = 0; i < Y; i++)
                System.out.println(Arrays.toString(map[i]));
            System.out.println(time + "회차: " + iceburgCnt + "last: " + lastIceY + " " + lastIceX);
            if (iceburgCnt != 0) {
                System.out.println(time);
                return;
            }
            if (leftIce == 0 || (nowLastX == lastIceX && nowLastY == lastIceY)) {
                System.out.println(0);
                return;
            }
            for (int y = 0; y < Y; y++)
                for (int x = 0; x < X; x++)
                    mCnt[y][x] = nxtMCnt[y][x];
            iceburgCnt = leftIce;
            lastIceY = nowLastY;
            lastIceX = nowLastX;
            time++;


        }



    }
}
