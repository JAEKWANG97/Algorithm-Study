import java.io.*;
import java.util.Arrays;

public class BOJ_1744 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int minCount = 0, answer = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] <= 0) {
                minCount++;
            }
        }
        Arrays.sort(arr);
        for (int i = 1; i < minCount; i += 2) {
            answer += arr[i - 1] * arr[i];
        }

        if (minCount % 2 == 1) {
            answer += arr[minCount - 1];
        }
        if ((N - minCount) % 2 == 1) {
            answer += arr[minCount++];
        }
        for (int i = N - 1; i > minCount; i -= 2) {
            int mul = arr[i - 1] * arr[i];
            int sum = arr[i - 1] + arr[i];
            answer += Math.max(mul, sum);
        }
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}