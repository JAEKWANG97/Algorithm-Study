import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static class Point implements Comparable<Point> {
        int x, y;
        int like_cnt, empty_cnt;

        public Point(int x, int y, int like_cnt, int empty_cnt) {
            this.x = x;
            this.y = y;
            this.like_cnt = like_cnt;
            this.empty_cnt = empty_cnt;
        }

        @Override
        public int compareTo(Point o1) {
            if (like_cnt == o1.like_cnt) {
                return o1.empty_cnt - empty_cnt;
            }
            return o1.like_cnt - like_cnt;
        }
    }

    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        List<Set<Integer>> like = new ArrayList<>();

        for (int i = 0; i < n * n + 1; i++) {
            like.add(new HashSet<>());
        }

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                like.get(num).add(Integer.parseInt(st.nextToken()));
            }

            PriorityQueue<Point> pq = new PriorityQueue<>();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (board[j][k] == 0) {
                        pq.add(checkAround(like.get(num), j, k, n));
                    }
                }
            }

            Point result = pq.poll();
            board[result.x][result.y] = num;
        }

        System.out.println(sumSatisfy(n, like));
    }

    private static Point checkAround(Set<Integer> like, int x, int y, int n) {
        int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int like_cnt = 0;
        int empty_cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dxy[i][0];
            int ny = y + dxy[i][1];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }
            if (board[nx][ny] == 0) {
                empty_cnt++;
            } else if (like.contains(board[nx][ny])) {
                like_cnt++;
            }

        }

        return new Point(x, y, like_cnt, empty_cnt);
    }

    private static int sumSatisfy(int n, List<Set<Integer>> like) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Point point = checkAround(like.get(board[i][j]), i, j, n);
                int like_cnt = point.like_cnt;

                if (like_cnt == 0) {
                    sum += like_cnt;
                } else {
                    sum += (int) Math.pow(10, like_cnt - 1);
                }
            }
        }
        return sum;
    }
}


