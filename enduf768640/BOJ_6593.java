import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int L, R, C;
    private static String[][][] map;

    private static int[][][] count;
    private static boolean[][][] visited;

    private static Location start;
    private static Location exit;

    public static void main(String[] args) throws IOException {
        String input = "";

        while (!(input = br.readLine()).equals("0 0 0")) {
            init(input);
            searchExit();
            printAnswer();
        }
    }

    private static void printAnswer() {
        int minute = count[exit.getZ()][exit.getY()][exit.getX()];

        if (minute == 0) {
            System.out.println("Trapped!");
        } else {
            System.out.println("Escaped in " + minute + " minute(s).");
        }
    }

    private static void searchExit() {
        int[] dz = {0, 0, 0, 0, 1, -1};
        int[] dy = {-1, 0, 1, 0, 0, 0};
        int[] dx = {0, 1, 0, -1, 0, 0};

        Queue<Location> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.getZ()][start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            int z = location.getZ();
            int y = location.getY();
            int x = location.getX();

            for (int i = 0; i < 6; i++) {
                int Z = z + dz[i];
                int Y = y + dy[i];
                int X = x + dx[i];

                if (Z < 0 || Z >= L || Y < 0 || Y >= R || X < 0 || X >= C || visited[Z][Y][X] || map[Z][Y][X].equals("#")) {
                    continue;
                }

                queue.offer(new Location(Z, Y, X));
                visited[Z][Y][X] = true;
                count[Z][Y][X] = count[z][y][x] + 1;
            }
        }
    }

    private static void init(String input) throws IOException {
        st = new StringTokenizer(input);
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new String[L][R][C];
        for (int z = 0; z < L; z++) {
            for (int y = 0; y < R; y++) {
                String[] split = br.readLine().split("");
                for (int x = 0; x < C; x++) {
                    String s = split[x];

                    if (s.equals("S")) {
                        start = new Location(z, y, x);
                    }

                    if (s.equals("E")) {
                        exit = new Location(z, y, x);
                    }

                    map[z][y][x] = s;
                }
            }
            br.readLine();
        }

        count = new int[L][R][C];
        visited = new boolean[L][R][C];
    }

    private static class Location {

        private int z;
        private int y;
        private int x;

        public Location(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }

        public int getZ() {
            return z;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }
}
