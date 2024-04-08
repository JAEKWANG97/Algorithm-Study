import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1946 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T, N;

    private static List<Person> people;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            init();

            checkInterviewee();
        }

        printAnswer();
    }

    private static void checkInterviewee() {
        int answer = N;

        int interviewGrade = people.get(0).getInterviewGrade();
        for (int j = 0; j < people.size() - 1; j++) {
            if (interviewGrade > people.get(j + 1).getInterviewGrade()) {
                interviewGrade = people.get(j + 1).getInterviewGrade();
            } else {
                answer--;
            }
        }

        sb.append(answer).append('\n');
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        people = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            st = new StringTokenizer(br.readLine());

            people.add(new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        people.sort(Comparator.comparingInt(Person::getDocumentGrade));
    }

    private static void printAnswer() {
        System.out.println(sb);
    }

    static class Person {

        private int documentGrade;
        private int interviewGrade;

        public Person(int documentGrade, int interviewGrade) {
            this.documentGrade = documentGrade;
            this.interviewGrade = interviewGrade;
        }

        public int getDocumentGrade() {
            return documentGrade;
        }

        public int getInterviewGrade() {
            return interviewGrade;
        }
    }
}


