import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, k;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[100_001];
        Arrays.fill(visited, Integer.MAX_VALUE);

        visited[n] = 0;
        queue.add(n);
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (poll == k) {
                break;
            }

            if (poll + 1 <= 100000 && visited[poll + 1] > visited[poll] + 1) {
                visited[poll + 1] = visited[poll] + 1;
                queue.add(poll + 1);
            }
            if (poll - 1 <= 100000 && poll - 1 >= 0 && visited[poll - 1] > visited[poll] + 1) {
                visited[poll - 1] = visited[poll] + 1;
                queue.add(poll - 1);
            }
            if (poll * 2 <= 100000 && visited[poll * 2] > visited[poll]) {
                visited[poll * 2] = visited[poll];
                queue.add(poll * 2);
            }
        }
        System.out.println(visited[k]);

    }
}
