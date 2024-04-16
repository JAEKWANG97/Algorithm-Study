import java.io.*;
import java.util.*;

public class BOJ_1253 {

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
        Arrays.sort(arr);

        int answer = 0;
        for (int t = 0; t < N; t++) {
            int cur = arr[t];
            int start = 0, end = N - 1;
            while (start < end) {
                int sum = arr[start] + arr[end];

                if (sum == cur) {
                    if (start == t) {
                        start++;
                    } else if (end == t) {
                        end--;
                    } else {
                        answer++;
                        break;
                    }
                } else if (sum < cur) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}