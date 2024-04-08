package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int liquids[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquids);
        int answer1 = 0;
        int answer2 = 0;
        int value = Integer.MAX_VALUE;
        // 용액의 왼쪽 기준으로 오른쪽들 이진탐색 게속 진행
        // [기준(i)][ -- 더한값 작아짐 --> (lv) |최적의값(v)| (rv) <-- 더한값 작아짐 -- ]
        // 해당 그림처럼 기준을 더한 값의 최적값을 찾은 후 갱신해주기
        for (int i = 0; i < N-1; i++) {
            int left = i+1;
            int right = N-1;
            int mid, v, lv, rv;
            while (left < right) {
                mid = (left + right)/2;
                if (mid == N-1) {
                    rv = Integer.MAX_VALUE;
                }
                else {
                    rv = Math.abs(liquids[i] + liquids[mid + 1]);
                }
                if (mid == i+1) {
                    lv = Integer.MAX_VALUE;
                }
                else {
                    lv = Math.abs(liquids[i] + liquids[mid - 1]);
                }
                v = Math.abs(liquids[i] + liquids[mid]);
                if (lv > v && v > rv) { //
                    left = mid + 1;
                }
                else if (lv < v && v < rv) {
                    right = mid - 1;
                }
                else {
                    break;
                }
            }
            int tmp = Math.abs(liquids[i] + liquids[(left + right) / 2]);
            if (tmp < value) {
                value = tmp;
                answer1 = liquids[i];
                answer2 = liquids[(left + right) / 2];
            }
        }
        System.out.println(answer1 + " " + answer2);
    }
}
