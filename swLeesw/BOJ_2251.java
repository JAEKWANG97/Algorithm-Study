import java.util.*;
import java.io.*;

public class Main {
    static int A, B, C;
    static boolean[][][] check;
    static boolean[] visited;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        check = new boolean[A + 1][B + 1][C + 1];
        visited = new boolean[C + 1];
        list = new ArrayList<>();

        solve(0, 0, C);

        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    static void solve(int a, int b, int c) {
        if (check[a][b][c]) return;
        check[a][b][c] = true;
        if (a == 0 && !visited[c]) {
            list.add(c);
            visited[c] = true;
        }
        int capA = A - a;
        int capB = B - b;
        int capC = C - c;
        solve(Math.max(0, a - capC), b, c + Math.min(a, capC)); // a to c
        solve(Math.max(0, a - capB), b + Math.min(a, capB), c); // a to b
        solve(a, Math.max(0, b - capC), c + Math.min(b, capC)); // b to c
        solve(a + Math.min(b, capA), Math.max(0, b - capA), c); // b to a
        solve(a + Math.min(c, capA), b, Math.max(0, c - capA)); // c to a
        solve(a, b + Math.min(c, capB), Math.max(0, c - capB)); // c to b
    }
}
