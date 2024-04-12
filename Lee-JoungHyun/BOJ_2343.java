import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 강의의 수, 블루레이의 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] lectures = new int[N];
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
        }

        int end = Arrays.stream(lectures).sum() + 1;
        int start = Arrays.stream(lectures).min().getAsInt();

        while (start < end) {
            int mid = (start + end) / 2;
            if (canSet(lectures, M, mid)) {
                //.out.println(mid + ": 가능!");
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    private static boolean canSet(int[] lectures, int M, int size) {
        int bucket = 0;
        for (int lecture : lectures) {
            if (lecture > size) return false;

            if (bucket + lecture > size) {
                M--;
                bucket = lecture;
            } else {
                bucket += lecture;
            }
        }

        return M > 0;
    }
}
