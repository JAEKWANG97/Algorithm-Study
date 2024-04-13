import java.util.*;
import java.io.*;

public class BOJ_1107 {
    static int N;
    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                int brokenButton = Integer.parseInt(st.nextToken());
                broken[brokenButton] = true;
            }
        }

        // 시작점 100에서 +, -로만 이동하는 경우
        int minPress = Math.abs(N - 100);

        for (int i = 0; i <= 1000000; i++) { // 모든 채널을 시도
            int length = check(i);
            if (length > 0) {
                int press = Math.abs(i - N);
                minPress = Math.min(minPress, press + length);
            }
        }

        System.out.println(minPress);
    }

    // 채널 i를 누를 수 있는지 확인하고, 누를 수 있다면 해당 채널을 입력하기 위해 필요한 버튼 누름 횟수를 반환
    static int check(int channel) {
        if (channel == 0) {
            return broken[0] ? 0 : 1;
        }
        int length = 0;
        while (channel > 0) {
            if (broken[channel % 10]) return 0; // 고장난 버튼이 있다면 0 반환
            length += 1; // 고장나지 않은 버튼을 누른 횟수 증가
            channel /= 10;
        }
        return length;
    }
}