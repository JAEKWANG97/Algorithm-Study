package algo_sil;

import java.util.*;
import java.io.*;

public class BOJ_20055 {
    static int N, K;
    static int[] arr;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(move());

    }

    private static int move() {
        int cnt = 0;

        while (isAble()) {
            int tmp = arr[arr.length - 1];
            for (int i = arr.length - 1; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = tmp;

            for (int i = robot.length - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;
            robot[N - 1] = false;

            for (int i = robot.length - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && arr[i] >= 1) {
                    arr[i]--;
                    robot[i] = true;
                    robot[i - 1] = false;
                }
            }

            if (arr[0] > 0) {
                robot[0] = true;
                arr[0]--;
            }

            cnt++;
        }

        return cnt;
    }

    private static boolean isAble() {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                cnt++;
            }
            if (cnt >= K)
                return false;
        }
        return true;
    }

}