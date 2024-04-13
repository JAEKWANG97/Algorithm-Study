package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12865 {
    static int dp[], N, K, things[][];
    static boolean dpUse[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 무게, 가치 순
        things = new int[N][2];
        dp = new int[K+1]; // idx = 무게, value = 가치
        dpUse = new boolean[K+1][N]; // 값을 dp에 저장했다면 이를 만드는데 필요한 물건 True 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            things[i][0] = Integer.parseInt(st.nextToken());
            things[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(things, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o1[0] - o2[0];
                else
                    return o1[1] - o2[1];
            }
        });


        for (int i = 1; i <= K; i++) { // i는 무게
            //1. 1개만으로 2. 여러개 합쳐서
            for (int j = 0; j < N; j++) { // j는 물건 순회, things[j][0]이 무게, dp[0]이 가치
                if (i > things[j][0]) {
                    // 무게를 빼서 비교한 값의 가치가 본인의 가치보다 크고 && dp[뺀무게] 를 만드는데 j를 사용하지 않았다면
                    if (dp[i-things[j][0]] != 0 && things[j][1] + dp[i-things[j][0]] > dp[i] && !dpUse[i-things[j][0]][j]) {
                        dp[i] = things[j][1] + dp[i-things[j][0]];
                        dpUse[i] = Arrays.copyOf(dpUse[i-things[j][0]], N);
                        dpUse[i][j] = true;
                    }
                }
                else if (things[j][0] == i) {
                    if (dp[i] < things[j][1]) {
                        dp[i] = things[j][1];
                        dpUse[i] = new boolean[N];
                        dpUse[i][j] = true;
                    }
                }
                else
                    break;
            }
            // 결국 무게 별로 가장 큰 가치를 dp에 저장
        }
        int max = 0;
        for (int i = K; i >= 0; i--) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

}