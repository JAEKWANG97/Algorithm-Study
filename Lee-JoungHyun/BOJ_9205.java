import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9205 {
    private static class Pos {
        int y, x, idx;
        public Pos(int y, int x, int idx) {
            this.y = y;
            this.x = x;
            this.idx = idx;
        }
        public boolean link(Pos other) {
            if ((Math.abs(this.y - other.y) + Math.abs(this.x - other.x)) <= 1000)
                return true;
            else
                return false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            // 그래프는 20 * 50 = 1000m 거리 까지만 가까우면 됨
            LinkedList<Integer>[] graph = new LinkedList[n + 2];
            for (int i = 0; i < n + 2; i++) {
                graph[i] = new LinkedList<>();
            }
            Pos[] arr = new Pos[n + 2];
            st = new StringTokenizer(br.readLine());
            // 출발점
            arr[0] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
                for (int j = 0; j < i; j++) {
                    if (arr[j].link(arr[i])) {
                        graph[i].add(j);
                        graph[j].add(i);
                    }
                }
            }
            st = new StringTokenizer(br.readLine());
            // 도착점
            arr[n + 1] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), n+1);
            for (int j = 0; j < n + 1; j++) {
                if (arr[j].link(arr[n + 1])) {
                    graph[n + 1].add(j);
                    graph[j].add(n + 1);
                }
            }
            // 그래프 만들기 끝!

//            for (int i = 0; i < n + 2; i++) {
//                System.out.println(graph[i]);
//            }

            boolean[] visited = new boolean[n + 2];
            visited[0] = true;
            Queue<Pos> queue = new LinkedList<>();
            queue.add(arr[0]);
            Pos now;
            boolean flag = true;
            while (!queue.isEmpty()) {
                now = queue.poll();
                for (int nxtIdx : graph[now.idx]) {
                    if (nxtIdx == n + 1) {
                        sb.append("happy").append("\n");
                        queue.clear();
                        flag = false;
                        break;
                    }
                    if (!visited[nxtIdx]) {
                        visited[nxtIdx] = true;
                        queue.add(arr[nxtIdx]);
                    }
                }
            }
            if (flag)
                sb.append("sad").append("\n");
        }
        System.out.println(sb);
    }
}