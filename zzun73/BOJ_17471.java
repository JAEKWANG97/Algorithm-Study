import java.io.*;
import java.util.*;

public class BOJ_17471 {
    private static final int FAILURE = Integer.MAX_VALUE;
    static int N, answer;
    static int[] people;
    static boolean[] selected;
    static List<Integer>[] edges;

    static void helper(int count) {
        if (count == N) {
            List<Integer> teamA = new ArrayList<>();
            List<Integer> teamB = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (selected[i]) {
                    teamA.add(i);
                } else {
                    teamB.add(i);
                }
            }

            if (teamA.isEmpty() || teamB.isEmpty()) {
                return;
            }

            if (isConnect(teamA) && isConnect(teamB)) {
                answer = Math.min(answer, calcPopulationDiff());
            }
            return;
        }

        selected[count] = true;
        helper(count + 1);
        selected[count] = false;
        helper(count + 1);
    }


    static boolean isConnect(List<Integer> team) {
        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[team.get(0)] = true;
        deque.add(team.get(0));

        int count = 1;
        while (!deque.isEmpty()) {
            int cur = deque.poll();

            for (int next : edges[cur]) {
                if (!visited[next] && team.contains(next)) {
                    visited[next] = true;
                    count++;
                    deque.add(next);
                }
            }
        }
        return count == team.size();
    }

    static int calcPopulationDiff() {
        int teamASum = 0, teamBSum = 0;
        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                teamASum += people[i];
            } else {
                teamBSum += people[i];
            }
        }
        return Math.abs(teamASum - teamBSum);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        people = new int[N + 1];
        selected = new boolean[N + 1];
        edges = new ArrayList[N + 1];
        answer = FAILURE;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine(), " ");
            int size = Integer.parseInt(st.nextToken());
            while (size-- > 0) {
                int val = Integer.parseInt(st.nextToken());
                edges[i].add(val);
            }
        }

        helper(0);
        if (answer == FAILURE) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}