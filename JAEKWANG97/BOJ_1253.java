import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        init();
        solve();
    }

    private static void init() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
    }

    private static void solve() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isGood(int i) {
        // i번쨰 위치의 수를 만들 수 있는가?
        int left = 0;
        int right = N - 1;

        if (i == 0) {
            left = 1;
        }
        if (i == N - 1) {
            right = N - 2;
        }

        while (left < right) {

            if (left == i) {
                left += 1;
                continue;
            } else if (right == i) {
                right -= 1;
                continue;
            }

            int answer = arr[left] + arr[right];
            if (answer > arr[i]) {
                right -= 1;
                continue;
            } else if (answer < arr[i]) {
                left += 1;
                continue;
            } else {
                return true;
            }

        }
        return false;
    }

}
