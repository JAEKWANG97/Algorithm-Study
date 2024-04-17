import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {

    private static int N;
    private static int[] arr;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        countGoodNumber();

        printAnswer();
    }

    private static void countGoodNumber() {
        for (int i = 0; i < N; i++) {
            int start = 0;
            int end = N - 1;

            while (start < end) {
                if (i == start) {
                    start++;
                    continue;
                }

                if (i == end) {
                    end--;
                    continue;
                }

                int sum = arr[start] + arr[end];

                if (sum > arr[i]) {
                    end--;
                } else if (sum < arr[i]) {
                    start++;
                } else {
                    answer++;
                    break;
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
