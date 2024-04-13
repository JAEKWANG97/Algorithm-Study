import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13549[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
[0, 0, 2, 2, 3, 3, 4, 4, 5, 5, 6]
[0, 0, 0, 0, 0, 4, 4, 6, 7, 8, 10] {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            return;
        }

        boolean visited[] = new boolean[100001];
        visited[N] = true;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o1[0] - o2[0];
                return Math.abs(o1[1] - K) - Math.abs(o2[1] - K);

            }
        });
        pq.add(new int[]{0, N});

        while (true) {
            int[] now = pq.poll();
            visited[now[1]] = true;
            if (now[1] == K) {
                System.out.println(now[0]);
                return;
            }
            //System.out.println(now[0] + " " + now[1]);

            if (now[1] * 2 <= 100000 && !visited[now[1] * 2]) {
                pq.add(new int[]{now[0], now[1] * 2});
            }
            if (now[1] - 1 >= 0 && !visited[now[1] - 1]) {
                pq.add(new int[]{now[0] + 1, now[1] - 1});
            }
            if (now[1] < K && !visited[now[1] + 1]) {
                pq.add(new int[]{now[0] + 1, now[1] + 1});
            }
        }
    }
}