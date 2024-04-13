
import java.io.*;
import java.util.*;

public class BOJ_2212 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solution() {

        Arrays.sort(arr);
        // 뛰어넘어도 되는 횟수 : K-1;

        int[] dist = new int[N - 1];

        for (int i = 0; i < N - 1; i++) {
            dist[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(dist);
        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += dist[i];
        }
        System.out.println(sum);
    }


}
