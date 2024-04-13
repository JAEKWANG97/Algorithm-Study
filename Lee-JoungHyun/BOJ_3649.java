import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3649 {
    public static void main(String[] args) throws IOException {
        // 100만 -> 투포인터?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String temp = null;

        while ((temp = br.readLine()) != null) {

            int x = Integer.parseInt(temp) * 10000000;

            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);
            int preIdx = 0;
            int postIdx = n - 1;
            boolean flag = false;
            while (preIdx < postIdx) {
                int tmp = arr[preIdx] + arr[postIdx];
                if (tmp == x){
                    sb.append("yes ").append(arr[preIdx]).append(" ").append(arr[postIdx]).append("\n");
                    flag = true;
                    break;
                }
                else if (tmp < x)
                    preIdx++;
                else
                    postIdx--;

            }
            if (!flag)
                sb.append("danger").append("\n");
        }
        System.out.println(sb);
    }
}
