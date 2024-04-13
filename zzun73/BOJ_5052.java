import java.io.*;
import java.util.*;

public class BOJ_5052 {
    static boolean check(int n, String[] numbers) {
        for (int i = 0; i < n - 1; i++) {
            if (numbers[i + 1].startsWith(numbers[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            String[] numbers = new String[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
            }
            Arrays.sort(numbers);

            sb.append(check(n, numbers) ? "YES" : "NO").append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}