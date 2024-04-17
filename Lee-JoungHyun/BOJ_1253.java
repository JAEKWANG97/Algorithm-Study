import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        if (N <= 2) {
            System.out.println(0);
            return;
        }
        int ans = 0;
        for (int idx = 0; idx < N; idx++) {
            // 0 ~ idx -1 까지 수 활용해서 arr[idx] 만들기!!
            //System.out.println(idx);
            if (findCnt(idx, arr))
                ans++;
        }
        System.out.println(ans);
    }
    private static boolean findCnt(int idx, long[] arr) {
        int left = 0, right = arr.length - 1;

        if (left == idx) left++;
        if (right == idx) right--;

        while (left < right) {
            if (arr[left] + arr[right] == arr[idx]) {
                return true;
            }
            else if (arr[left] + arr[right] < arr[idx]) {
                left++;
            } else {
                right--;
            }

            if (left == idx) left++;
            if (right == idx) right--;
        }
        return false;
    }
}