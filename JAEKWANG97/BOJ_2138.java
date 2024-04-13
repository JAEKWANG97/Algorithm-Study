package backjoon;

import java.io.*;
import java.util.Arrays;

public class BOJ_2138 {
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String now = br.readLine();

        String target = br.readLine();

        boolean[] nowState = new boolean[n];
        boolean[] targetState = new boolean[n];

        //boolean 배열을 활용해서 풀이
        for (int i = 0; i < n; i++) {
            if (now.charAt(i) == '0') {
                nowState[i] = false;
            } else {
                nowState[i] = true;
            }
            if (target.charAt(i) == '0') {
                targetState[i] = false;
            } else {
                targetState[i] = true;
            }
        }

        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nowState[i - 1] != targetState[i - 1]) {
                flip(i, nowState, n);
                count += 1;
            }
        }

        //첫번째 스위치를 누른 경우

        if (Arrays.compare(nowState, targetState) == 0) {
            minCount = Math.min(count, minCount);
        }

        for (int i = 0; i < n; i++) {
            if (now.charAt(i) == '0') {
                nowState[i] = false;
            } else {
                nowState[i] = true;
            }
        }
        // 안 누르고 진행 한 경우

        flip(0, nowState, n);
        count = 1;
        for (int i = 1; i < n; i++) {
            if (nowState[i - 1] != targetState[i - 1]) {
                flip(i, nowState, n);
                count += 1;
            }
        }
        if (Arrays.compare(nowState, targetState) == 0) {
            minCount = Math.min(count, minCount);
        }

        System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
    }

    static void flip(int idx, boolean[] state, int n) {
        if (idx < 1) {
            state[idx] = !state[idx];
            state[idx + 1] = !state[idx + 1];
        } else if (idx == n - 1) {
            state[idx] = !state[idx];
            state[idx - 1] = !state[idx - 1];
        } else {
            state[idx - 1] = !state[idx - 1];
            state[idx] = !state[idx];
            state[idx + 1] = !state[idx + 1];
        }
    }


}
