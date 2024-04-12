import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2343 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int start = 0;
        int end = 100_000;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            start = Math.max(start, arr[i]);
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            int count = 0;

            for (int i = 0; i < N; i++) {
                if (sum + arr[i] > mid) {
                    sum = 0;
                    count++;
                }
                sum += arr[i];
            }

            if (sum != 0) {
                count++;
            }
            if (count <= M) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        bw.write(String.valueOf(start));

        br.close();
        bw.close();
    }
}