import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_3649 {
    static int x;
    static int n;
    static int[] arr;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (br.ready()) { // EOF까지 반복
        init();
        simulate();
        }
    }

    private static void init() throws IOException {
        x = Integer.parseInt(br.readLine()) * 10000000;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
    }

    private static void simulate() {
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int item = arr[left] + arr[right];
            if (item < x) {
                left++;
            } else if (item > x) {
                right--;
            } else {
                System.out.println("yes" + " " + arr[left] + " " + arr[right]);
                return;
            }
        }

        System.out.println("danger");
    }
}
