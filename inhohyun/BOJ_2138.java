import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2138{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] start = br.readLine().toCharArray();
        char[] end = br.readLine().toCharArray();

        // 1번 스위치를 켠 상태로 시작
        char[] startA = start.clone();

        //A의 1번 스위치 켜기
        for (int i = 0; i <= 1; i++) {
            if (startA[i] == '0') {
                startA[i] = '1';
            } else if (startA[i] == '1') {
                startA[i] = '0';
            }
        }

        // 1번 스위치를 끈 상태로 시작
        char[] startB = start.clone();

        int cntA = 1; // 켜놓고 시작하므로 1
        int cntB = 0;

        for (int i = 1; i < n; i++) {
            // 이전 전구가 만들고자하는 전구와 다를 경우 스위치를 누름
            if (startA[i - 1] != end[i - 1]) {
                cntA++;
                // 3가지 범위 바꾸기
                for (int j = -1; j <= 1; j++) {
                    if (i + j < 0 || i + j >= n)
                        continue;

                    if (startA[i + j] == '1') {
                        startA[i + j] = '0';
                    } else if (startA[i + j] == '0') {
                        startA[i + j] = '1';
                    }
                }
            }

            if (startB[i - 1] != end[i - 1]) {
                cntB++;
                // 3가지 범위 바꾸기
                for (int j = -1; j <= 1; j++) {
                    if (i + j < 0 || i + j >= n)
                        continue;

                    if (startB[i + j] == '1') {
                        startB[i + j] = '0';
                    } else if (startB[i + j] == '0') {
                        startB[i + j] = '1';
                    }
                }
            }
            if (Arrays.equals(startA, end)) {
                // 둘 다 같으면 더 적은 것 출력
                if (Arrays.equals(startA, startB)) {
                    System.out.println(Math.min(cntA, cntB));
                    System.exit(0);
                }

                System.out.println(cntA);
                System.exit(0);

            } else if (Arrays.equals(startB, end)) {
                System.out.println(cntB);
                System.exit(0);
            }

        }
        System.out.println(-1);
    }

}
