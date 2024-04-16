import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7662 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T, K;

    private static DualPriorityQueue dpq;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            K = Integer.parseInt(br.readLine());
            dpq = new DualPriorityQueue();

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());

                String operator = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (operator.equals("I")) {
                    dpq.offer(num);
                } else {
                    if (num == 1) {
                        dpq.pollMax();
                    } else {
                        dpq.pollMin();
                    }
                }
            }

            if (dpq.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(dpq.peekMax() + " " + dpq.peekMin());
            }
        }
    }

    private static class DualPriorityQueue {

        private TreeMap<Integer, Integer> maxTreeMap;
        private TreeMap<Integer, Integer> minTreeMap;

        public DualPriorityQueue() {
            maxTreeMap = new TreeMap<>(Collections.reverseOrder());
            minTreeMap = new TreeMap<>(Comparator.comparingInt(n -> n));
        }

        public void offer(int num) {
            if (!maxTreeMap.containsKey(num)) {
                maxTreeMap.put(num, 1);
            } else {
                maxTreeMap.put(num, maxTreeMap.get(num) + 1);
            }

            if (!minTreeMap.containsKey(num)) {
                minTreeMap.put(num, 1);
            } else {
                minTreeMap.put(num, minTreeMap.get(num) + 1);
            }
        }

        public void pollMax() {
            if (isEmpty()) {
                return;
            }

            int max = maxTreeMap.firstKey();

            if (maxTreeMap.get(max) == 1) {
                maxTreeMap.remove(max);
                minTreeMap.remove(max);
            } else {
                maxTreeMap.put(max, maxTreeMap.get(max) - 1);
                minTreeMap.put(max, minTreeMap.get(max) - 1);
            }
        }

        public void pollMin() {
            if (isEmpty()) {
                return;
            }

            int min = minTreeMap.firstKey();

            if (minTreeMap.get(min) == 1) {
                maxTreeMap.remove(min);
                minTreeMap.remove(min);
            } else {
                maxTreeMap.put(min, maxTreeMap.get(min) - 1);
                minTreeMap.put(min, minTreeMap.get(min) - 1);
            }
        }

        public int peekMax() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            return maxTreeMap.firstKey();
        }

        public int peekMin() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            return minTreeMap.firstKey();
        }

        public boolean isEmpty() {
            return maxTreeMap.isEmpty() && minTreeMap.isEmpty();
        }
    }
}
