import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2343 {

    private static int N, M;
    private static int[] videos;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        binarySearch();

        printAnswer();
    }

    private static void binarySearch() {
        int start = Arrays.stream(videos).max().getAsInt();
        int end = Arrays.stream(videos).sum();

        while (start <= end) {
            int mid = (start + end) / 2;

            int i = countBlueRay(mid);
            if (i <= M) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }

    private static int countBlueRay(int blueRaySize) {
        int count = 0;

        int temp = 0;
        for (int i = 0; i < N; i++) {
            temp += videos[i];

            if (temp > blueRaySize) {
                count++;
                temp = videos[i];
            }
        }

        if (temp > 0) {
            count++;
        }

        return count;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        videos = new int[N];
        for (int i = 0; i < N; i++) {
            videos[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
