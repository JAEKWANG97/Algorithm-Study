import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int K;

    private static Item[] items;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        dp();
        printAnswer();
    }

    private static void dp() {
        for (int W = 1; W <= K; W++) {
            Item item = items[1];

            if (item.getWeight() > W) {
                continue;
            }

            dp[1][W] = item.getValue();
        }

        for (int bagCount = 2; bagCount <= N; bagCount++) {
            for (int W = 1; W <= K; W++) {
                Item item = items[bagCount];

                if (item.getWeight() > W) {
                    dp[bagCount][W] = dp[bagCount - 1][W];
                } else {
                    dp[bagCount][W] = Math.max(dp[bagCount - 1][W], item.getValue() + dp[bagCount - 1][W - item.getWeight()]);
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            items[i] = new Item(W, V);
        }

        dp = new int[N + 1][K + 1];
    }

    private static void printAnswer() {
        System.out.println(dp[N][K]);
    }

    private static class Item {

        private int weight;
        private int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }
    }
}
