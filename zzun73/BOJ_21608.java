import java.io.*;
import java.util.*;

public class BOJ_21608 {
    static int N;
    static int[][] map;
    static List<Integer>[] list;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static class Student implements Comparable<Student> {
        int x, y, blank, like;

        public Student(int x, int y, int blank, int like) {
            this.x = x;
            this.y = y;
            this.blank = blank;
            this.like = like;
        }

        @Override
        public int compareTo(Student o) {
            if (this.like == o.like) {
                if (this.blank == o.blank) {
                    if (this.x == o.x) {
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return o.blank - this.blank;
            }
            return o.like - this.like;
        }
    }

    static Student getPosition(int num) {
        PriorityQueue<Student> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    int[] info = calcBlankAndLikeCount(i, j, num);
                    pq.add(new Student(i, j, info[0], info[1]));
                }
            }
        }
        return pq.poll();
    }

    static int[] calcBlankAndLikeCount(int x, int y, int num) {
        int blankCount = 0, likeCount = 0;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
                continue;
            }
            for (int val : list[num]) {
                if (map[nx][ny] == val) {
                    likeCount++;
                    break;
                }
            }
            if (map[nx][ny] == 0) {
                blankCount++;
            }
        }
        return new int[]{blankCount, likeCount};
    }

    public static int calcAnswer() {
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = map[i][j];
                int likeCount = 0;
                for (int k = 0; k < dx.length; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
                        continue;
                    }
                    for (int val : list[num]) {
                        if (map[nx][ny] == val) {
                            likeCount++;
                        }
                    }
                }
                if (likeCount > 0) {
                    answer += (int) Math.pow(10, likeCount - 1);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        list = new ArrayList[N * N + 1];
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            list[num] = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                list[num].add(Integer.parseInt(st.nextToken()));
            }

            Student position = getPosition(num);
            map[position.x][position.y] = num;
        }
        bw.write(String.valueOf(calcAnswer()));

        br.close();
        bw.close();
    }
}