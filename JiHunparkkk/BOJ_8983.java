import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int M, N, L, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Point[] shooter = new Point[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            shooter[i] = new Point(0, num);
        }

        List<Point> animals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x, y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            animals.add(new Point(y, x));
        }

        Arrays.sort(shooter, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y - o2.y;
            }
        });
        for (Point animal : animals) {
            findShooter(animal, shooter);
        }

        System.out.println(answer);
    }

    private static void findShooter(Point animal, Point[] shooter) {
        int left = 0;
        int right = M;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid >= M) {
                return;
            }

            int dis = Math.abs(shooter[mid].y - animal.y) + animal.x;
            if (dis > L && animal.y < shooter[mid].y) {
                right = mid - 1;
            } else if (dis > L && animal.y >= shooter[mid].y) {
                left = mid + 1;
            } else if (dis <= L) {
                answer++;
                return;
            }
        }
    }
}
