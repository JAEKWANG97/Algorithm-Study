import java.io.*;
import java.util.*;

public class BOJ_3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] under = new int[H];
        int[] over = new int[H];
        int[] cave = new int[H];
        int overCount = N/2, underCount = 0, destroy = N, count = 0;
        for (int n = 0; n < N; n++) {
            int now = Integer.parseInt(br.readLine()) - 1;
            if (n % 2 == 0) under[now]++;
            else over[now]++;
        }
        for (int h = 0; h < H; h++) {
            underCount += under[H - 1 - h];
            cave[h] += overCount + underCount;
            overCount -= over[h];
            if (cave[h] < destroy) {
                destroy = cave[h];
                count = 1;
            } else if (cave[h] == destroy) count++;
        }
        System.out.println(destroy + " " + count);
    }
}
