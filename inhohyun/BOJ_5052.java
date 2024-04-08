
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++) {

            int n = Integer.parseInt(br.readLine());
            String[] sArr = new String[n];
            for(int i = 0; i < n; i++) {
                sArr[i] = br.readLine();
            }
            Arrays.sort(sArr);

            if(isCheck(sArr, n)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }

    }

    static boolean isCheck(String[] tmp, int n) {
        for(int i = 0; i < n-1; i++) {
            if(tmp[i+1].startsWith(tmp[i])) {
                return false;
            }
        }

        return true;
    }
}
