import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int D;
    private static int K;
    private static int C;

    private static int[] sushiBelt;
    private static int[] sushiCheck;
    private static int sushiCount;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        slidingWindow();
        printAnswer();
    }

    private static void slidingWindow() {
        for (int i = 1; i < N; i++) {
            int previousSushi = sushiBelt[i - 1];
            int nextSushi = sushiBelt[(i + K - 1) % N];
            int couponSushi = C;

            if (--sushiCheck[previousSushi] == 0) {
                sushiCount--;
            }

            if (++sushiCheck[nextSushi] == 1) {
                sushiCount++;
            }

            if (sushiCheck[couponSushi] == 0) {
                answer = Math.max(answer, sushiCount + 1);
            } else {
                answer = Math.max(answer, sushiCount);
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        sushiBelt = new int[N];
        for (int i = 0; i < N; i++) {
            sushiBelt[i] = Integer.parseInt(br.readLine());
        }

        sushiCheck = new int[D + 1];
        for (int i = 0; i < K; i++) {
            if (sushiCheck[sushiBelt[i]] == 0) {
                sushiCount++;
            }

            sushiCheck[sushiBelt[i]]++;
        }

        if (sushiCheck[C] == 0) {
            answer = sushiCount + 1;
        } else {
            answer = sushiCount;
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
