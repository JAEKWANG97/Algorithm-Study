import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    private static class Node {
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static List<List<Node>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //무조건 방문하면서, 최단거리
        int n, m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b, c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        solution(n);
    }

    private static void solution(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] w = new int[n + 1];
        Arrays.fill(w, Integer.MAX_VALUE);

        w[1] = 0;
        queue.add(1);
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            for (int i = 0; i < list.get(poll).size(); i++) {
                Node next = list.get(poll).get(i);

                if (w[next.to] > w[poll] + next.weight) {
                    w[next.to] = w[poll] + next.weight;
                    queue.add(next.to);
                }
            }
        }

        findWay(n, w);
    }

    private static void findWay(int n, int[] w) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        int cnt = 0;
        queue.add(1);
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            for (int i = 0; i < list.get(poll).size(); i++) {
                Node next = list.get(poll).get(i);

                if (!visited[next.to] && w[next.to] == w[poll] + next.weight) {
                    sb.append(poll).append(" ").append(next.to).append("\n");
                    cnt++;
                    queue.add(next.to);
                    visited[next.to] = true;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(sb.toString());
    }
}
