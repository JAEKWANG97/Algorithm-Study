import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16928 {

    private static final int GAME_SIZE = 100;
    private static final int START = 1;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;

    private static Map<Integer, Integer> ladders;
    private static Map<Integer, Integer> snakes;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        getDiceCount();
        printAnswer();
    }

    private static void getDiceCount() {
        Queue<Integer> queue = new LinkedList<>();

        int[] diceCount = new int[GAME_SIZE + 1];
        boolean[] visited = new boolean[GAME_SIZE + 1];

        queue.offer(START);
        visited[START] = true;

        while (!queue.isEmpty()) {
            int from = queue.poll();

            for (int dice = 1; dice <= 6; dice++) {
                int to = from + dice;

                if (to > GAME_SIZE || visited[to]) {
                    continue;
                }

                if (ladders.containsKey(to)) {
                    if (!visited[ladders.get(to)]) {
                        queue.offer(ladders.get(to));
                        diceCount[ladders.get(to)] = diceCount[from] + 1;
                        visited[ladders.get(to)] = true;
                    }
                } else if (snakes.containsKey(to)) {
                    if (!visited[snakes.get(to)]) {
                        queue.offer(snakes.get(to));
                        diceCount[snakes.get(to)] = diceCount[from] + 1;
                        visited[snakes.get(to)] = true;
                    }
                } else {
                    queue.offer(to);
                    diceCount[to] = diceCount[from] + 1;
                    visited[to] = true;
                }
            }
        }

        answer = diceCount[GAME_SIZE];
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ladders = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            ladders.put(from, to);
        }

        snakes = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            snakes.put(from, to);
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
