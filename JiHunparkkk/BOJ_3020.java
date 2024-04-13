import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, h;
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[] down = new int[n / 2];
        int[] up = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(up);

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= h; i++) {
            int downSearch = binarySearch(0, n / 2, h - i + 1, down);
            int upSearch = binarySearch(0, n / 2, i, up);

            int sum = downSearch + upSearch;

            if (min == sum) {
                cnt++;
            }

            if (min > sum) {
                min = sum;
                cnt = 1;
            }
        }
        System.out.println(min + " " + cnt);
    }

    private static int binarySearch(int left, int right, int h, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return arr.length - right;
    }
}


