package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_7662 {

    class DualPriorityQueue {

        private HashMap<Integer, Integer> numCount = new HashMap<>();
        private PriorityQueue<Integer> ascendingPq = new PriorityQueue<>();
        private PriorityQueue<Integer> descendingPq = new PriorityQueue<>(Collections.reverseOrder());

        private int count;

        public void add(int num) {
            ascendingPq.add(num);
            descendingPq.add(num);
            if (numCount.containsKey(num)) {
                numCount.replace(num, numCount.get(num) + 1);
            } else {
                numCount.put(num, 1);
            }
            count++;
        }

        public void deleteMin() {
            if (isEmpty()) {
                return;
            }
            count--;
            int deleteNum = ascendingPq.poll();
            while (numCount.get(deleteNum) == 0) {
                deleteNum = ascendingPq.poll();
            }
            numCount.replace(deleteNum, numCount.get(deleteNum) - 1);
        }

        public void deleteMax() {
            if (isEmpty()) {
                return;
            }
            count--;
            int deleteNum = descendingPq.poll();
            while (numCount.get(deleteNum) == 0) {
                deleteNum = descendingPq.poll();
            }
            numCount.replace(deleteNum, numCount.get(deleteNum) - 1);
        }

        public int getMin() {
            int num = ascendingPq.poll();
            while (numCount.get(num) == 0) {
                num = ascendingPq.poll();
            }
            return num;
        }

        public int getMax() {
            int num = descendingPq.poll();
            while (numCount.get(num) == 0) {
                num = descendingPq.poll();
            }
            return num;
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }


    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int T, k;
    private static DualPriorityQueue dpq;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initT();
        for (int i = 0; i < T; i++) {
            dpq = new DualPriorityQueue();
            initK();
            for (int j = 0; j < k; j++) {
                readCommand();
            }
            printResult();
        }
        bw.flush();
        bw.close();
    }

    private void initT() throws IOException {
        T = Integer.parseInt(bf.readLine());
    }

    private void initK() throws IOException {
        k = Integer.parseInt(bf.readLine());
    }

    private void readCommand() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String command = st.nextToken();
        int num = Integer.parseInt(st.nextToken());
        if (command.equals("I")) {
            dpq.add(num);
        }
        if (command.equals("D")) {
            if (!dpq.isEmpty() && num == -1) {
                dpq.deleteMin();
            }
            if (!dpq.isEmpty() && num == 1) {
                dpq.deleteMax();
            }
        }
    }

    private void printResult() throws IOException {
        if (dpq.isEmpty()) {
            bw.write("EMPTY\n");
        } else {
            bw.write(dpq.getMax() + " " + dpq.getMin() + "\n");
        }
    }
}
