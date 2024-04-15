package backjoon.implementation;

import java.util.*;
import java.io.*;

public class BOJ_1744 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Integer> positive;
    static PriorityQueue<Integer> negative;
    static boolean zero;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        positive = new PriorityQueue<>((o1, o2) -> o2 - o1);
        negative = new PriorityQueue<>();
        zero = false;
        for (int i = 0; i < N; i++) {
            int item = Integer.parseInt(br.readLine());
            if (item > 0) {
                positive.add(item);
            } else if (item < 0) {
                negative.add(item);
            } else {
                zero = true;
            }
        }
    }

    private static void solve() {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        while (!positive.isEmpty()) {
            int cur = positive.poll();

            if (stack.isEmpty()) {
                stack.push(cur);
            } else {
                int prev = stack.pop();
                if (cur != 1) {
                    sum += prev * cur;
                } else {
                    sum += cur + prev;
                }
            }
        }
        if (!stack.isEmpty()) {
            sum += stack.pop();
        }

        stack = new Stack<>();
        while (!negative.isEmpty()) {
            int cur = negative.poll();

            if (stack.isEmpty()) {
                stack.push(cur);
            } else {
                int prev = stack.pop();
                sum += prev * cur;
            }
        }

        if (!stack.isEmpty() && !zero) {
            sum += stack.pop();
        }

        System.out.println(sum);
    }
}
