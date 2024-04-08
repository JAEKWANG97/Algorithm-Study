import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static List<List<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(from).add(new Node(to, cost));
            list.get(to).add(new Node(from, cost));
        }

        int answer = solution(n);
        System.out.println(answer);
    }

    private static int solution(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] costs = new int[n + 1];

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[1] = 0;
        queue.add(1);   //1번 부터 시작

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            for (int i = 0; i < list.get(poll).size(); i++) {
                Node next = list.get(poll).get(i);

                if (costs[next.to] > next.cost + costs[poll]) {
                    costs[next.to] = next.cost + costs[poll];
                    queue.add(next.to);
                }
            }
        }

        return costs[n];
    }
}
