import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_4014 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T;

    private static int N;
    private static int X;

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            init();
            printAnswer(t);
        }
    }

    private static void printAnswer(int t) {
        System.out.println("#" + t + " " + (countHorizontalRunway() + countVerticalRunway()));
    }

    private static int countHorizontalRunway() {
        int count = 0;

        for (int y = 0; y < N; y++) {
            if (checkRunway(map[y])) {
                count++;
            }
        }

        return count;
    }

    private static int countVerticalRunway() {
        int count = 0;

        for (int x = 0; x < N; x++) {
            int[] terrain = new int[N];

            for (int y = 0; y < N; y++) {
                terrain[y] = map[y][x];
            }

            if (checkRunway(terrain)) {
                count++;
            }
        }

        return count;
    }

    private static boolean checkRunway(int[] land) {
        StringBuilder sb = new StringBuilder();
        sb.append(land[0]);

        for (int i = 1; i < N; i++) {
            if (land[i - 1] != land[i]) {
                sb.append(" ");
            }

            sb.append(land[i]);
        }

        String[] terrains = sb.toString().split("\\s");

        for (int i = 1; i < terrains.length; i++) {
            if (terrains[i - 1].charAt(0) > terrains[i].charAt(0)) {
                if (terrains[i].length() < X) {
                    return false;
                }
            }
        }

        for (int i = 0; i < terrains.length - 1; i++) {
            if (terrains[i + 1].charAt(0) > terrains[i].charAt(0)) {
                if (terrains[i].length() < X) {
                    return false;
                }
            }
        }

        for (int i = 1; i < terrains.length - 1; i++) {
            if (terrains[i - 1].charAt(0) > terrains[i].charAt(0) && terrains[i + 1].charAt(0) > terrains[i].charAt(0)) {
                if (terrains[i].length() < (2 * X)) {
                    return false;
                }
            }
        }

        for (int i = 1; i < terrains.length; i++) {
            if (Math.abs(terrains[i - 1].charAt(0) - terrains[i].charAt(0)) >= 2) {
                return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
