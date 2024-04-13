import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 개똥벌레 / 90분
public class BOJ_3020 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 동굴의 크기
        int H = Integer.parseInt(st.nextToken()); // 동굴 높이
        int[] down = new int[H+2]; // 종유석
        int[] up = new int[H+2]; // 석순
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
				up[H - Integer.parseInt(br.readLine()) + 1]++;
			} else {
				down[Integer.parseInt(br.readLine())]++;
			}
        }

        for (int i = 1; i <= H; i++) {
            down[i] += down[i - 1];
        }

        for (int i = H; i >= 1; i--) {
            up[i] += up[i + 1];
        }

        int min = N;
        int cnt = 0;
        for (int i = 1; i < H + 1; i++) {
            int diff = (down[H] - down[i - 1]) + (up[1] - up[i + 1]);

            if (diff < min) {
                min = diff;
                cnt = 1;
            } else if (diff == min) cnt++;
        }

		sb.append(min).append(" ").append(cnt);
    }

}
