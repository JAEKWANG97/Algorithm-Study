import java.io.*;
import java.util.*;

public class BOJ_9205 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Point sanggeun;

    static class Point{
        int x;
        int y;
        int beer;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        Point(int x, int y, int beer){
            this.x = x;
            this.y = y;
            this.beer = beer;
        }

        public int getDistance(Point p){
            return Math.abs(p.y - this.y) + Math.abs(p.x - this.x);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++){
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            sanggeun = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 20);
            Point rockFestival = new Point(0, 0);

            ArrayList<Point> points = new ArrayList<>();

            for (int i = 0; i < N + 1; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                points.add(new Point(x, y));

                if (i == N){
                    rockFestival.x = x;
                    rockFestival.y = y;
                }
            }

            String answer = "sad";

            Queue<Point> queue = new LinkedList<>();
            Set<Point> visited = new HashSet<>();

            queue.add(sanggeun);
            visited.add(sanggeun);

            while(!queue.isEmpty()){
                Point current = queue.poll();

                if (current.x == rockFestival.x && current.y == rockFestival.y){
                    answer = "happy";
                    break;
                }

                for (Point point: points){
                    int distance = current.getDistance(point);
                    if(!visited.contains(point) && distance <= current.beer * 50){
                        queue.add(new Point(point.x, point.y, 20));
                        visited.add(point);
                    }
                }

            }

            System.out.println(answer);
        }
    }
}

