import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_8983 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] guns = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            guns[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(guns);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());

            if (find(targetY, targetX, L, guns)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean find(int y, int x,  int L, int[] guns) {
        int start = 0;
        int end = guns.length;

        while (start < end) {

            int mid = (start + end) / 2;

            int lvalue = mid == 0 ? Integer.MAX_VALUE : y + Math.abs(x - guns[mid - 1]);
            int value = y + Math.abs(x - guns[mid]);
            int rvalue = mid == guns.length - 1 ? Integer.MAX_VALUE : y + Math.abs(x - guns[mid + 1]);

            if (lvalue <= L || value <= L || rvalue <= L)
                return true;
            if (lvalue > value && value < rvalue)
                return false;
            if (lvalue > value)
                start = mid + 1;
            else
                end = mid;
        }
        return false;
    }

}
