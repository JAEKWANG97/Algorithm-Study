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
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = solution(n, arr);
        if (answer == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(answer);
        }
    }

    private static int solution(int n, int[] arr) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[0] = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            for (int i = 1; i <= arr[poll]; i++) {
                if (poll + i < n && visited[poll + i] > visited[poll] + 1) {
                    visited[poll + i] = visited[poll] + 1;
                    queue.add(poll + i);
                }
            }
        }

        return visited[n - 1];
    }
}
