import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static long min2 = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long n = Long.parseLong(br.readLine());
        long min1 = Long.MAX_VALUE;
        int[] arr = new int[7];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min1 = Math.min(min1, arr[i]);
        }

        long answer = 0;
        if (n == 1) {
            Arrays.sort(arr);
            for (int i = 1; i <= 5; i++) {
                answer += arr[i];
            }
        } else {

            // 3면의 최소값 -> 무조건 4개 (n>1)
            // 2면의 최소값 -> (n-2) * 4 + (n-1) * 4 (개)
            // 1면의 최소값 -> (n-2)*(n-2) + (n-1)*(n-2)*4 (개)

            // 3면의 최소값
            long min3 = Long.MAX_VALUE;
            long tmp = Math.min(arr[1], arr[6]);
            long tmp2 = Math.min(arr[2], arr[5]);
            long tmp3 = Math.min(arr[3], arr[4]);
            min3 = tmp + tmp2 + tmp3;

            // 2면의 최소값
            find2nd(arr, new int[2], 0, 1);

            min3 *= 4;
            min2 *= ((n - 2) * 4 + (n - 1) * 4);
            min1 *= ((n - 2) * (n - 2) + (n - 1) * (n - 2) * 4);
            answer = min1 + min2 + min3;
        }
        System.out.println(answer);
    }

    private static void find2nd(int[] arr, int[] idx, int d, int start) {
        if (d == 2) {
            if (idx[0] + idx[1] == 7) {
                return;
            }
            min2 = Math.min(min2, arr[idx[0]] + arr[idx[1]]);
            return;
        }

        for (int i = start; i <= 6; i++) {
            idx[d] = i;
            find2nd(arr, idx, d + 1, i + 1);
        }
    }
}
