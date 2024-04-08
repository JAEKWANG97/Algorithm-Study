package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_3649 {

    class BlockPair implements Comparable<BlockPair> {
        int l1;
        int l2;
        int diff;

        BlockPair(int l1, int l2) {
            this.l1 = l1;
            this.l2 = l2;

            diff = l2 - l1;
        }

        @Override
        public int compareTo(BlockPair o) {
            return o.diff - diff;
        }

        @Override
        public String toString() {
            return "yes " + l1 + " " + l2;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int x, n;
    private int[] blocks;
    private PriorityQueue<BlockPair> pq;

    public static void main(String[] args) throws IOException {
        new BOJ_3649().solution();
    }

    private void solution() throws IOException {
        String str;
        while ((str = bf.readLine()) != null) {
            x = Integer.parseInt(str) * 10_000_000;
            init();
            calcResult();
            printResult();
        }
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        blocks = new int[n];
        for (int i = 0; i < n; i++) {
            blocks[i] = Integer.parseInt(bf.readLine());
        }
    }

    private void calcResult() {
        Arrays.sort(blocks);

        int start = 0;
        int end = n - 1;

        pq = new PriorityQueue<>();

        while (start < end) {
            int sum = blocks[start] + blocks[end];

            if (sum == x) {
                pq.offer(new BlockPair(blocks[start], blocks[end]));
                start++;
                continue;
            }

            if (sum < x) {
                start++;
            } else {
                end--;
            }
        }
    }

    private void printResult() throws IOException {
        if (pq.isEmpty()) {
            bw.write("danger\n");
        } else {
            bw.write(pq.peek().toString() + "\n");
        }
        bw.flush();
    }
}