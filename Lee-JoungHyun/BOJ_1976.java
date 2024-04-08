import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
        for (int p = 1; p < N + 1; p++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c < N + 1; c++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(p, c, parents);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean flag = true;
        int p = find(Integer.parseInt(st.nextToken()), parents);
        for (int i = 0; i < M - 1; i++) {
            if (p != find(Integer.parseInt(st.nextToken()), parents))
                flag = false;
        }
        if (flag) System.out.println("YES");
        else System.out.println("NO");
    }
    static boolean union(int a, int b, int[] parents) {
        a = find(a, parents);
        b = find(b, parents);
        if (a == b) return false;
        parents[a] = b;
        return true;
    }
    static int find(int a, int[] parents) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a], parents);
    }
}
