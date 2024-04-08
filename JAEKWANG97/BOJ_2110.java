package backjoon.algoQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long[] houses = new long[n];

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        long left = 1; // 가능한 최소 거리
        long right = houses[n - 1] - houses[0]; // 가능한 최대 거리
        long maxDistance = 0;

        while (left <= right) {
            long mid = (left + right) / 2; // 중간값을 기준으로 설정
            long lastInstalled = houses[0]; // 첫 번째 집에 공유기 설치
            int count = 1;

            // 다음 공유기 설치 가능 여부 확인
            for (int i = 1; i < n; i++) {
                if (houses[i] - lastInstalled >= mid) {
                    count++;
                    lastInstalled = houses[i];
                }
            }

            // C개 이상의 공유기를 설치할 수 있는 경우, 거리 증가
            if (count >= c) {
                left = mid + 1;
                maxDistance = mid; // 최대 거리 갱신
            } else { // 그렇지 않은 경우, 거리 감소
                right = mid - 1;
            }
        }

        System.out.println(maxDistance);
    }

}
