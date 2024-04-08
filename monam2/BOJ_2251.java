import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_2251 { //백준 2251 물병 - 100분
    private static class Water {
        int from, to;

        public Water(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(); // A물 양
        int b = sc.nextInt(); // B물 양
        int c = sc.nextInt(); // C: 총 물의 양

        ArrayDeque<Water> q = new ArrayDeque<>();
        q.add(new Water(0, 0));

        ArrayList<Integer> arr = new ArrayList<>();
        boolean[][] visited = new boolean[a + 1][b + 1];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int x = q.peek().from;
            int y = q.poll().to;
            int z = c - x - y;

            if (x == 0) {
                arr.add(z);
            }

            int can;

            // x -> y
            if (x > 0) {
                can = Math.min(x, b - y);
                if (!visited[x - can][y + can]) {
                    q.add(new Water(x - can, y + can));
                    visited[x - can][y + can] = true;
                }

                // x -> z
                can = Math.min(x, c - x - z);
                if (!visited[0][c - x - z]) {
                    q.add(new Water(0, c - x - z));
                    visited[0][c - x - z] = true;
                }
            }

            // y -> x
            if (y > 0) {
                can = Math.min(y, a - x);
                if (!visited[x + can][y - can]) {
                    q.add(new Water(x + can, y - can));
                    visited[x + can][y - can] = true;
                }

                // y -> z
                if (!visited[x][0]) {
                    q.add(new Water(x, 0));
                    visited[x][0] = true;
                }
            }

            // z -> x
            if (z > 0) {
                can = Math.min(z, a - x);
                if (!visited[x + can][c - x - z]) {
                    q.add(new Water(x + can, c - x - z));
                    visited[x + can][c - x - z] = true;
                }

                // z -> y
                can = Math.min(z, b - y);
                if (!visited[x][y + can]) {
                    q.add(new Water(x, y + can));
                    visited[x][y + can] = true;
                }
            }
        }

        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }
}
