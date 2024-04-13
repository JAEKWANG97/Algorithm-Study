package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2251 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private boolean[][] visited;
    private TreeSet<Integer> possibleAmounts = new TreeSet<>();

    private int A, B, C;

    public static void main(String[] args) throws IOException {
        new BOJ_2251().solution();
    }

    private void solution() throws IOException {
        init();
        printResult();
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[A + 1][B + 1];
        dfs(0, 0, C);
    }

    private void dfs(int a, int b, int c) {
        if (visited[a][b]) {
            return;
        }
        visited[a][b] = true;
        if (a == 0) {
            possibleAmounts.add(c);
        }

        // A -> B
        int move = Math.min(a, B - b);
        dfs(a - move, b + move, c); // A에서 B로 이동
        // B -> A
        move = Math.min(b, A - a);
        dfs(a + move, b - move, c); // B에서 A로 이동
        // C -> A
        move = Math.min(c, A - a);
        dfs(a + move, b, c - move); // C에서 A로 이동
        // C -> B
        move = Math.min(c, B - b);
        dfs(a, b + move, c - move); // C에서 B로 이동
        // A -> C
        move = Math.min(a, C - c);
        dfs(a - move, b, c + move);
        // B -> C
        move = Math.min(b, C - c);
        dfs(a, b - move, c + move);
    }

    private void printResult() throws IOException {
        for (int amount : possibleAmounts) {
            bw.write(amount + " ");
        }
        bw.flush();
    }
}
