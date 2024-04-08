import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static class Person implements Comparable<Person> {
        int paper, meet;

        public Person(int paper, int meet) {
            this.paper = paper;
            this.meet = meet;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(paper, o.paper);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            Person[] person = new Person[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                person[i] = new Person(a, b);
            }

            Arrays.sort(person);
            int pass = person[0].meet;
            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (person[i].meet > pass) {
                    continue;
                }
                answer++;
                pass = person[i].meet;
            }

            System.out.println(answer);
        }
    }
}
