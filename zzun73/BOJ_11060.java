import java.io.*;
import java.util.*;

public class BOJ_11060 {

    static int helper(int N, int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] count = new int[N];
        deque.add(0);
        count[0] = 0;

        while (!deque.isEmpty()) {
            int cur = deque.poll();

            if (cur == N - 1) {
                return count[cur];
            }

            int maxValue = cur + arr[cur];
            int curCount = count[cur];
            for (int i = cur; i <= maxValue && i < N; i++) {
                if (count[i] == 0) {
                    count[i] = curCount + 1;
                    deque.add(i);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = N == 1 ? 0 : helper(N, arr);
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}