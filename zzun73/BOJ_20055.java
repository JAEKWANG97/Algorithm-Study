import java.io.*;
import java.util.*;

public class BOJ_20055 {
    static int N, K, brokenCount;
    static int[] map;
    static boolean[] robots;

    static void rotate() {
        int temp = map[map.length - 1];
        for (int i = map.length - 1; i > 0; i--) {
            map[i] = map[i - 1];
        }
        map[0] = temp;

        for (int i = N - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        robots[0] = false;
        robots[N - 1] = false;
    }

    static void moveRobot() {
        for (int i = N - 1; i > 0; i--) {
            if (robots[i - 1] && !robots[i] && map[i] > 0) {
                robots[i - 1] = false;
                robots[i] = true;
                if (--map[i] == 0) {
                    brokenCount++;
                }
            }
        }
    }

    static void addRobot() {
        if (map[0] >= 1 && !robots[0]) {
            if (--map[0] == 0) {
                brokenCount++;
            }
            robots[0] = true;
        }
    }

    static int helper() {
        int step = 0;
        while (brokenCount < K) {
            step++;

            rotate();
            moveRobot();
            addRobot();
        }

        return step;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[2 * N];
        robots = new boolean[2 * N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 2 * N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(helper()));
        br.close();
        bw.close();
    }
}