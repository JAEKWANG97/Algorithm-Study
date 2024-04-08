package backjoon.algoStudy.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1107 {
    static int minValue = Integer.MAX_VALUE;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String targetStr = br.readLine();
        int target = Integer.parseInt(targetStr);

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean[] numbers = new boolean[10];
        Arrays.fill(numbers, true);
        for (int i = 0; i < n; i++) {
            int item = Integer.parseInt(st.nextToken());
            numbers[item] = false;
        }

        int temp = target;

        while (temp <= 1000000) {
            StringBuilder sb = new StringBuilder(temp++ + "");
            boolean flag = true;
            for (int i = 0; i < sb.toString().length(); i++) {
                boolean number = numbers[Integer.parseInt(Character.toString(sb.toString().charAt(i)))];
                if (!number) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer = Math.abs(target - Integer.parseInt(sb.toString())) + sb.toString().length();
                break;
            }
        }
        temp = target;
        while (temp >= 0) {
            StringBuilder sb = new StringBuilder(temp-- + "");

            boolean flag = true;
            for (int i = 0; i < sb.toString().length(); i++) {
                boolean number = numbers[Integer.parseInt(Character.toString(sb.toString().charAt(i)))];
                if (!number) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer = Math.min(Math.abs(target - Integer.parseInt(sb.toString())) + sb.toString().length(), answer);
                break;
            }
        }
        answer = Math.min(answer, Math.abs(100 - target));

        System.out.println(answer);

    }
}