import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, S;
    static long[] prefixSum;

    public static void main(String[] args) throws IOException {
        init();
        searchSize();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        prefixSum = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            long item = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + item;
        }
    }

    private static void searchSize() {

        int start = 0;
        int end = 0;
        long currentSum = 0;
        int minLength = N + 1;
        while (end <= N) {
            currentSum = prefixSum[end] - prefixSum[start];
            if (currentSum >= S) {
                minLength = Math.min(minLength, end - start);
                start++;
            } else {
                end++;
            }
        }

        if (minLength == N + 1)
            System.out.println(0);
        else
            System.out.println(minLength);
    }
}
