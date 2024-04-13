package hellomatia;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2531 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, d, k, c;
    private int[] sushiOrder;

    public static void main(String[] args) throws IOException {
        new BOJ_2531().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushiOrder = new int[N];

        for (int i = 0; i < N; i++) {
            int sushiNum = Integer.parseInt(bf.readLine());
            sushiOrder[i] = sushiNum;
        }
    }

    private int calcResult() {
        int count = 0;
        int result;

        int[] sushiCount = new int[d + 1];
        for (int i = 0; i < k; i++) {
            sushiCount[sushiOrder[i]]++;
        }

        for (int i = 1; i <= d; i++) {
            if (sushiCount[i] > 0) count++;
        }
        result = sushiCount[c] == 0 ? count + 1 : count;

        for (int i = 0; i < N; i++) {
            int removeIdx = i;
            int addIdx = i + k >= N ? (i + k) % N : i + k;

            int removeSushi = sushiOrder[removeIdx];
            int addSushi = sushiOrder[addIdx];

            sushiCount[removeSushi]--;
            if (sushiCount[removeSushi] == 0) count--;
            sushiCount[addSushi]++;
            if (sushiCount[addSushi] == 1) count++;
            if (sushiCount[c] == 0) {
                result = Math.max(result, count + 1);
            } else {
                result = Math.max(result, count);
            }
        }
        return result;
    }

    public void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}