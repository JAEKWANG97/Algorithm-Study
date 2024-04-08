import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int low = 1; // 최소 간격
        int high = arr[n - 1]; // 최대 간격

        while(low <= high) {
            int mid = (low + high) / 2;

            int position = 0;

            int cnt = 1;

            for(int i = 1; i < n; i++) {
                if(arr[i] - arr[position] >= mid) {
                    position= i;
                    cnt++;
                }
            }
            //더 적으면
            if(cnt < c) {
                high = mid - 1;
                continue;
            }
            //더 많으면
            low = mid + 1;
        }
        System.out.println(low - 1);



    }

}
