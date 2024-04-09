import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 2. DP
        int[] arr = new int[N + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < N + 1; i++) {
            // 본인자리 드갈 수 있는지 확인을 하는데... 본인 루트부터?
            for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                arr[i] = Math.min(arr[i], arr[i - j * j] + 1);
            }
        }

        System.out.println(arr[N]);

    }
}
