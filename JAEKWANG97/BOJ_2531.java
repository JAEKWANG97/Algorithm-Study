package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(simulate(arr, n, d, k, c));

    }

    private static int simulate(int[] arr, int n, int d, int k, int c) {
        int maxCount = Integer.MIN_VALUE;

        // 초기 윈도우 설정(map 으로 관리)
        Map<Integer, Integer> window = new HashMap<>();
        for (int i = 0; i < k; i++) {
            window.put(arr[i], window.getOrDefault(arr[i], 0) + 1);
        }
        int count = window.size();
        maxCount = count;
        if (!window.containsKey(c)) {
            maxCount += 1;
        }
        // 다음 윈도우 실행
        int removeIndex = 0;
        int addIndex = k;
        while (removeIndex < n) {
            if (window.get(arr[removeIndex]) - 1 == 0) {
                window.remove(arr[removeIndex]);
            } else {
                window.put(arr[removeIndex], window.get(arr[removeIndex]) - 1);
            }

            window.put(arr[addIndex], window.getOrDefault(arr[addIndex], 0) + 1);

            count = window.size();
            if (maxCount <= count) {
                maxCount = count;
                if (!window.containsKey(c)) {
                    maxCount += 1;
                }
            }
            removeIndex += 1;
            addIndex = (addIndex + 1) % n;
        }
        return maxCount;
    }
}
