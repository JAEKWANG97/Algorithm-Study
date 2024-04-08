import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1931 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;

    private static List<Meeting> meetings;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        countMeeting();
        printAnswer();
    }

    private static void countMeeting() {
        meetings.sort(
                (t1, t2) -> t1.getEnd() == t2.getEnd() ? t1.getStart() - t2.getStart() : t1.getEnd() - t2.getEnd());

        int prev = meetings.get(0).getEnd();

        for (int i = 1; i < meetings.size(); i++) {
            if (meetings.get(i).getStart() >= prev) {
                prev = meetings.get(i).getEnd();
                answer++;
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings.add(new Meeting(start, end));
        }

        answer = 1;
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Meeting {

        private int start;
        private int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getTime() {
            return end - start;
        }
    }
}