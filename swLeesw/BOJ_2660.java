import java.util.*;

public class BOJ_2660 {
    static ArrayList<Integer>[] map;
    static int n;
    static int min;
    static int[] rank;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new ArrayList[n];
        rank = new int[n];
        min = 50;

        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
        }

        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == -1 && b == -1)
                break;

            map[a - 1].add(b - 1);
            map[b - 1].add(a - 1);
        }

        for (int i = 0; i < n; i++) {
            int c = bfs(i);

            rank[i] = c;
            min = Math.min(min, c);
        }

        int count = 0;

        for (int i = 0; i < rank.length; i++) {
            if (rank[i] == min)
                count++;
        }

        System.out.println(min + " " + count);

        for (int i = 0; i < rank.length; i++) {
            if (rank[i] == min)
                System.out.print(i + 1 + " ");
        }
    }

    private static int bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        boolean[] visit = new boolean[n];
        visit[start] = true;
        int count = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < map[node.x].size(); i++) {
                if (!visit[map[node.x].get(i)]) {
                    q.add(new Node(map[node.x].get(i), node.c + 1));
                    visit[map[node.x].get(i)] = true;
                }
            }
            count = node.c;
        }
        return count;
    }

    static class Node {
        int x;
        int c;

        public Node(int x, int c) {
            this.x = x;
            this.c = c;
        }
    }
}
