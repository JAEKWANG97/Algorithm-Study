package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int homeCnt = Integer.parseInt(st.nextToken());
        int routerCnt = Integer.parseInt(st.nextToken());

        long homes[] = new long[homeCnt];
        for (int i = 0; i < homeCnt; i++) {
            homes[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(homes);


        long right = homes[homeCnt - 1] - homes[0] + 1;
        long left = 0;
        long mid;
        // upper bounce
        while (left < right) {
            mid = (left + right) / 2;
            if (check(mid, homes, routerCnt)) {
                left = mid + 1;
            }
            else {
                right = mid;
            }

        }
        System.out.println(right-1);

    }
    // 인접한 두 공유기 사이의 최대 거리가 length 이상으로 공유기를 모두 설치할 수 있는지 확인
    private static boolean check(long length, long[] homes, int routerCnt) {
        long lHome = homes[0];
        int cnt = 1;
        for (int i = 1; i < homes.length; i++) {
            if (homes[i] - lHome >= length) {
                cnt++;
                lHome = homes[i];
            }
            if (cnt >= routerCnt) return true;
        }
        return false;
    }
}
