import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3020 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, H;

    private static Integer[] up;
    private static Integer[] down;

    private static int answer;
    private static int[] counts;

    public static void main(String[] args) throws IOException {
        init();

        binarySearch();

        printAnswer();
    }

    private static void printAnswer() {
        int minCount = (int) Arrays.stream(counts)
                .skip(1)
                .filter(num -> num == answer)
                .count();

        System.out.println(answer + " " + minCount);
    }

    private static void binarySearch() {
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= H; i++) {
            int upIdx = lowerBound(up, H - i + 1);
            int upCount = N / 2 - upIdx;

            int downIdx = lowerBound(down, i);
            int downCount = N / 2 - downIdx;

            min = Math.min(min, upCount + downCount);
            counts[i] = upCount + downCount;
        }

        answer = min;
    }

    private static int lowerBound(Integer[] arr, int num) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] >= num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new Integer[N / 2];
        down = new Integer[N / 2];

        for (int i = 0; i < N / 2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        counts = new int[H + 1];
    }
}
