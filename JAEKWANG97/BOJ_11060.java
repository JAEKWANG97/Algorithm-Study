package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        bfs(arr, n);
    }

    private static void bfs(int[] arr, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        queue.add(0);
        visited[0] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll(); // index
                if (cur == n - 1) {
                    System.out.println(count);
                    return;
                }
                for (int i = 1; i <= arr[cur]; i++) {
                    int next = cur + i;
                    if (next >= n || visited[next]) {
                        continue;
                    }
                    queue.add(next);
                    visited[next] = true;
                }
            }
            count += 1;
        }
        System.out.println(-1);
        return;
    }
}
