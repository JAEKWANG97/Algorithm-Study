import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_21758 {

    private static int N;
    private static int[] honeys;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        calculateCase1();

        calculateCase2();

        calculateCase3();

        printAnswer();
    }

    private static void calculateCase1() {
        int bee1 = Arrays.stream(honeys, 2, N).sum();
        int bee2 = Arrays.stream(honeys, 2, N).sum();

        answer = Math.max(answer, bee1 + bee2);

        for (int i = 2; i < N - 1; i++) {
            bee1 -= honeys[i];
            bee1 += honeys[i - 1];

            bee2 -= honeys[i];

            answer = Math.max(answer, bee1 + bee2);
        }
    }

    private static void calculateCase2() {
        int bee1 = Arrays.stream(honeys, 0, N - 2).sum();
        int bee2 = Arrays.stream(honeys, 0, N - 2).sum();

        answer = Math.max(answer, bee1 + bee2);

        for (int i = N - 3; i >= 1; i--) {
            bee1 -= honeys[i];
            bee1 += honeys[i + 1];

            bee2 -= honeys[i];

            answer = Math.max(answer, bee1 + bee2);
        }
    }

    private static void calculateCase3() {
        int bee1 = honeys[1];
        int bee2 = Arrays.stream(honeys, 1, N - 1).sum();

        answer = Math.max(answer, bee1 + bee2);

        for (int i = 2; i < N - 1; i++) {
            bee1 += honeys[i];
            bee2 -= honeys[i - 1];

            answer = Math.max(answer, bee1 + bee2);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        honeys = new int[N];
        for (int i = 0; i < N; i++) {
            honeys[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
