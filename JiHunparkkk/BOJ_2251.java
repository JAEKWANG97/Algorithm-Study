import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static List<Integer> answer;
    private static boolean[][][] visited;
    private static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        answer = new ArrayList<>();
        visited = new boolean[201][201][201];

        visited[0][0][C] = true;
        dfs(0, 0, C);

        Collections.sort(answer);
        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }

    private static void dfs(int a, int b, int c) {
        if (a == 0) {
            answer.add(c);
        }

        int dif = 0;
        if (a != 0) {
            //a의 값을 b로
            if (a + b > B) {
                if (!visited[a - (B - b)][B][c]) {
                    visited[a - (B - b)][B][c] = true;
                    dfs(a - (B - b), B, c);
                }
            } else {
                if (!visited[0][a + b][c]) {
                    visited[0][a + b][c] = true;
                    dfs(0, a + b, c);
                }
            }

            //a->c
            if (a + c > C) {
                if (!visited[a - (C - c)][b][C]) {
                    visited[a - (C - c)][b][C] = true;
                    dfs(a - (C - c), b, C);
                }
            } else {
                if (!visited[0][b][a + c]) {
                    visited[0][b][a + c] = true;
                    dfs(0, b, a + c);
                }
            }
        }

        if (b != 0) {
            //b -> a
            if (b + a > A) {
                if (!visited[A][b - (A - a)][c]) {
                    visited[A][b - (A - a)][c] = true;
                    dfs(A, b - (A - a), c);
                }
            } else {
                if (!visited[b + a][0][c]) {
                    visited[b + a][0][c] = true;
                    dfs(b + a, 0, c);
                }
            }

            //b -> c
            if (b + c > C) {
                if (!visited[a][b - (C - c)][C]) {
                    visited[a][b - (C - c)][C] = true;
                    dfs(a, b - (C - c), C);
                }
            } else {
                if (!visited[a][0][b + c]) {
                    visited[a][0][b + c] = true;
                    dfs(a, 0, b + c);
                }
            }
        }

        if (c != 0) {
            //c -> a
            if (c + a > A) {
                if (!visited[A][b][c - (A - a)]) {
                    visited[A][b][c - (A - a)] = true;
                    dfs(A, b, c - (A - a));
                }
            } else {
                if (!visited[a + c][b][0]) {
                    visited[a + c][b][0] = true;
                    dfs(a + c, b, 0);
                }
            }

            //c -> b
            if (c + b > B) {
                if (!visited[a][B][c - (B - b)]) {
                    visited[a][B][c - (B - b)] = true;
                    dfs(a, B, c - (B - b));
                }
            } else {
                if (!visited[a][b + c][0]) {
                    visited[a][b + c][0] = true;
                    dfs(a, b + c, 0);
                }
            }
        }
    }
}
