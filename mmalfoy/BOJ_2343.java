import java.io.*;
import java.util.*;

public class BOJ_2343 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = 0, end = 0, mid;
        int[] G = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            G[i] = Integer.parseInt(st.nextToken());
            if (start < G[i]) {
                start = G[i];
            }
            end += G[i];
        }
        
        while (start <= end) {
            mid = (start + end) / 2;
            int sum = 0, cnt = 0;
            for (int n : G) {
                if (sum + n > mid) {
                    cnt += 1;
                    sum = n;
                } else {
                    sum += n;
                }
            }
            
            if (sum != 0) {
                cnt += 1;
            }
            
            if (cnt > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            
        }
        System.out.println(start);
    }
    
}
