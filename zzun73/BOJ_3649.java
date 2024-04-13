import java.io.*;
import java.util.Arrays;

public class BOJ_3649 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {
            int X = Integer.parseInt(input) * 10_000_000;
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            boolean flag = true;
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);

            int left = 0, right = N - 1;
            while (left < right) {
                int sum = arr[left] + arr[right];

                if (sum == X) {
                    sb.append("yes").append(" ").append(arr[left]).append(" ").append(arr[right]).append("\n");
                    flag = false;
                    break;
                } else if (sum > X) {
                    right--;
                } else {
                    left++;
                }
            }

            if (flag) {
                sb.append("danger").append("\n");
            }
        }
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}