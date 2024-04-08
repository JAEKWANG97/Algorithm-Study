import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17471 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] populations;
    private static List<Integer>[] graph;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        subSet(new boolean[N + 1], 1);
        printResult();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        populations = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int endCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < endCount; j++) {
                int end = Integer.parseInt(st.nextToken());
                graph[i].add(end);
            }
        }
    }

    private static void subSet(boolean[] isSelected, int count) {
        if (count == N + 1) {
            checkLink(isSelected);
            return;
        }

        isSelected[count] = true;
        subSet(isSelected, count + 1);
        isSelected[count] = false;
        subSet(isSelected, count + 1);
    }

    private static void checkLink(boolean[] isSelected) {
        List<Integer> selectedList = new ArrayList<>();
        List<Integer> unSelectedList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (isSelected[i]) {
                selectedList.add(i);
            } else {
                unSelectedList.add(i);
            }
        }

        boolean[] visited = new boolean[N + 1];
        for (int idx : unSelectedList) {
            visited[idx] = true;
        }
        boolean flag1 = checkLink(selectedList, visited);

        visited = new boolean[N + 1];
        for (int idx : selectedList) {
            visited[idx] = true;
        }
        boolean flag2 = checkLink(unSelectedList, visited);

        if (flag1 && flag2) {
            int weightSum1 = selectedList.stream().mapToInt(idx -> populations[idx]).sum();
            int weightSum2 = unSelectedList.stream().mapToInt(idx -> populations[idx]).sum();

            answer = Math.min(answer, Math.abs(weightSum1 - weightSum2));
        }
    }

    private static boolean checkLink(List<Integer> list, boolean[] visited) {
        if (list.isEmpty()) {
            return false;
        }

        if (list.size() == 1) {
            return true;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(list.get(0));

        while (!queue.isEmpty()) {
            int start = queue.poll();

            for (int idx : graph[start]) {
                if (visited[idx]) {
                    continue;
                }

                queue.offer(idx);
                visited[idx] = true;
            }
        }

        return isAllCheck(visited);
    }

    private static boolean isAllCheck(boolean[] visited) {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    private static void printResult() {
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
    }
}
