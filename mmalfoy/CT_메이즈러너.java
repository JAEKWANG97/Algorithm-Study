import java.io.*;
import java.util.*;

public class CT_메이즈러너 {
    private static int N, M, K;
    private static int[][] graph;
    private static List<int[]> playerList = new ArrayList<>();
    private static int ex, ey;
    private static int moveDistance = 0;
    private static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            playerList.add(new int[]{a, b});
        }

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        graph[ex][ey] = -5;

        for (int time = 1; time <= K; time++) {
            movePlayers();
            if (!playerList.isEmpty()) {
                rotateSquare();
            } else {
                break;
            }
        }

        System.out.println(moveDistance);
        System.out.println((ex + 1) + " " + (ey + 1));
    }

    private static void movePlayers() {
        List<int[]> newPlayerList = new ArrayList<>();
        for (int[] player : playerList) {
            boolean moved = false;
            for (int[] dir : DIR) {
                int nx = player[0] + dir[0], ny = player[1] + dir[1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (Math.abs(ex - player[0]) + Math.abs(ey - player[1]) == 
                        Math.abs(ex - nx) + Math.abs(ey - ny) + 1 && graph[nx][ny] < 1) {
                        newPlayerList.add(new int[]{nx, ny});
                        moveDistance++;
                        moved = true;
                        break;
                    }
                }
            }
            if (!moved) newPlayerList.add(player);
        }
        newPlayerList.removeIf(p -> p[0] == ex && p[1] == ey);
        playerList = new ArrayList<>(newPlayerList);
    }

    private static void rotateSquare() {
        // 가장 작은 사각형을 찾는 로직
        int minSize = N;
        int minX = 0;
        int minY = 0;
        boolean found = false;

        for (int size = 1; size <= N; size++) {
            for (int startX = 0; startX <= N - size; startX++) {
                for (int startY = 0; startY <= N - size; startY++) {
                    boolean includesExit = false;
                    boolean includesPlayer = false;

                    for (int[] player : playerList) {
                        if (player[0] >= startX && player[0] < startX + size && 
                            player[1] >= startY && player[1] < startY + size) {
                            includesPlayer = true;
                            break;
                        }
                    }

                    includesExit = (ex >= startX && ex < startX + size && 
                                    ey >= startY && ey < startY + size);

                    if (includesExit && includesPlayer) {
                        minX = startX;
                        minY = startY;
                        minSize = size;
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found) break;
        }

        // 회전 로직
        if (found) {
            int[][] temp = new int[minSize][minSize];
            for (int i = 0; i < minSize; i++) {
                for (int j = 0; j < minSize; j++) {
                    temp[j][minSize - 1 - i] = graph[minX + i][minY + j];
                }
            }

            for (int i = 0; i < minSize; i++) {
                for (int j = 0; j < minSize; j++) {
                    graph[minX + i][minY + j] = temp[i][j];
                }
            }

            // Update player positions
            List<int[]> newPlayerList = new ArrayList<>();
            for (int[] player : playerList) {
                if (player[0] >= minX && player[0] < minX + minSize && 
                    player[1] >= minY && player[1] < minY + minSize) {
                    int newPlayerX = minY + minSize - 1 - (player[1] - minY);
                    int newPlayerY = minX + (player[0] - minX);
                    newPlayerList.add(new int[]{newPlayerY, newPlayerX});
                } else {
                    newPlayerList.add(player);
                }
            }

            // Update exit position
            if (ex >= minX && ex < minX + minSize && 
                ey >= minY && ey < minY + minSize) {
                ex = minY + minSize - 1 - (ey - minY);
                ey = minX + (ex - minX);
            }

            playerList = new ArrayList<>(newPlayerList);
        }
    }
}
