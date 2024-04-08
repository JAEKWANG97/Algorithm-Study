package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3020 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, H;
    private int[] bot;
    private int[] top;

    public static void main(String[] args) throws IOException {
        new BOJ_3020().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        bot = new int[H + 2];
        top = new int[H + 2];
        for (int i = 0; i < N / 2; i++) {
            bot[Integer.parseInt(bf.readLine())]++;
            top[H - Integer.parseInt(bf.readLine()) + 1]++;
        }
    }

    private void calcResult() throws IOException {
        for (int i = 1; i <= H; i++) {
            bot[i] += bot[i - 1];
        }

        for (int i = H; i >= 1; i--) {
            top[i] += top[i + 1];
        }

        int min = N;
        int count = 0;

        for (int i = 1; i <= H; i++) {
            int obs = (bot[H] - bot[i - 1]) + (top[1] - top[i + 1]);

            if (obs < min) {
                min = obs;
                count = 1;
            } else if (obs == min)
                count++;
        }

        printResult(min, count);
    }

    private void printResult(int result1, int result2) throws IOException {
        bw.write(result1 + " " + result2);
    }
}
