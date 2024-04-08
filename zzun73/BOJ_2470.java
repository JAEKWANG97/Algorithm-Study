import java.io.*;
import java.util.*;

public class BOJ_2470 {
    static int N, minSum, left, right, mid, sum, targetValue, targetIdx;
    static int[] arr, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = new int[2];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        minSum = Integer.MAX_VALUE;

        for (int curIdx = 0; curIdx < N - 1; curIdx++) {
            targetValue = -arr[curIdx];
            targetIdx = 0;

            left = curIdx + 1;
            right = N;
            while (left < right) {
                mid = (left + right) / 2;

                if (Math.abs(targetValue - arr[mid]) < minSum) {
                    minSum = Math.abs(targetValue - arr[mid]);
                    targetIdx = mid;
                }

                if (arr[mid] == targetValue) {
                    targetIdx = mid;
                    break;
                } else if (arr[mid] < targetValue) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            sum = Math.abs(arr[curIdx] + arr[targetIdx]);
            if (minSum >= sum) {
                minSum = sum;
                answer[0] = arr[curIdx];
                answer[1] = arr[targetIdx];
                if (sum == 0) {
                    break;
                }
            }
        }

        Arrays.sort(answer);
        sb.append(answer[0]).append(" ").append(answer[1]);

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}