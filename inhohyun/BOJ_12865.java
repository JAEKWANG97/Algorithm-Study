import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static class item {
        int w, v;

        item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    static int n, k;
    static item[] bag;
    static int[][] memo;

    public static void main(String[] args) throws Throwable {
        init();


        System.out.println(dfs(0, k));
    }

    static int dfs(int idx, int remainWeight) {
        if (idx == n || remainWeight == 0) // 모든 물품을 고려했거나 배낭의 용량이 0인 경우
            return 0;

        if (memo[idx][remainWeight] != -1) // 이미 계산된 값이 있는 경우
            return memo[idx][remainWeight];

        int result = 0;
        if (bag[idx].w > remainWeight) { // 현재 물품의 무게가 남은 용량보다 큰 경우, 현재 물품은 선택하지 않음
            result = dfs(idx + 1, remainWeight);
        } else { // 선택할 수 있는 경우, 선택하거나 선택하지 않는 경우 중 큰 값을 선택
            result = Math.max(dfs(idx + 1, remainWeight), dfs(idx + 1, remainWeight - bag[idx].w) + bag[idx].v);
        }

        // 계산된 값 메모이제이션
        memo[idx][remainWeight] = result;

        return result;
    }

    static void init() throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bag = new item[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bag[i] = new item(w, v);
        }

        memo = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                memo[i][j] = -1; // 초기값으로 -1 설정
            }
        }

    }
}