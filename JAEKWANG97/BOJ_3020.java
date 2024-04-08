import java.io.*;
import java.util.*;

public class BOJ_3020 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, H;
    static int[] up, down;

    public static void main(String[] args) throws IOException {
        init(); // 입력 받는 부분

        // 누적 합 배열 초기화
        int[] upSum = new int[H + 1]; // 천장에서의 누적 합
        int[] downSum = new int[H + 1]; // 바닥에서의 누적 합

        // 석순과 종유석에 대한 누적 합 계산
        for (int i = 0; i < N / 2; i++) {
            upSum[up[i]]++;
            downSum[down[i]]++;
        }
        //System.out.println(Arrays.toString(up));
        //System.out.println(Arrays.toString(upSum));

        // 석순과 종유석에 대해 누적 합을 계산 (높이별 장애물 수)
        for (int i = 1; i <= H; i++) {
            upSum[i] += upSum[i - 1];
            downSum[i] += downSum[i - 1];
        }

        //System.out.println(Arrays.toString(up));
        //System.out.println(Arrays.toString(upSum));

        int minObstacles = Integer.MAX_VALUE; // 최소 장애물 개수
        int count = 0; // 해당 최소값을 갖는 구간의 수

        // 각 높이에 대해 개똥벌레가 만나는 장애물의 총 개수 계산
        for (int height = 1; height <= H; height++) {
            // 현재 높이에서 부딪히는 장애물의 총 개수 = 바닥의 장애물 수 + 천장의 장애물 수
            int total = (N / 2 - upSum[H - height]) + (N / 2 - downSum[height - 1]);

            // 최소 장애물 개수 업데이트
            if (total < minObstacles) {
                minObstacles = total;
                count = 1;
            } else if (total == minObstacles) {
                count++;
            }
        }

        // 결과 출력
        System.out.println(minObstacles + " " + count);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[N / 2]; // 종유석
        down = new int[N / 2]; // 석순

        for (int i = 0; i < N / 2; i++) {
            down[i] = Integer.parseInt(br.readLine()); // 석순 입력 받기
            up[i] = Integer.parseInt(br.readLine()); // 종유석 입력 받기
        }
    }
}
