import java.io.*;
import java.util.*;

public class BOJ_2110 {
    static int N, C, left, right, front, count, answer;
    static int[] arr;

    static boolean isPossible(int value) {
        front = arr[0];
        count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - front >= value) {
                front = arr[i];
                count++;
            }
            if (count >= C) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        answer = 0;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        left = 0;
        right = arr[N - 1] - arr[0] + 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid;
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
