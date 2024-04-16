import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_1744 {

    private static int N;
    private static List<Integer> zero;
    private static List<Integer> one;
    private static List<Integer> minus;
    private static List<Integer> plus;

    private static int answer;

    public static void main(String[] args) {
        init();

        calculate();

        printAnswer();
    }

    private static void calculate() {
        plus.sort((n1, n2) -> n2 - n1);
        for (int i = 0; i < plus.size(); i += 2) {
            if (i == plus.size() - 1) {
                answer += plus.get(i);
                break;
            }

            answer += (plus.get(i) * plus.get(i + 1));
        }

        answer += one.size();

        minus.sort(Integer::compare);
        for (int i = 0; i < minus.size(); i += 2) {
            if (i == minus.size() - 1) {
                if (!zero.isEmpty()) {
                    break;
                }

                answer += minus.get(i);
                break;
            }
            answer += (minus.get(i) * minus.get(i + 1));
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        zero = new ArrayList<>();
        one = new ArrayList<>();
        minus = new ArrayList<>();
        plus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();

            if (num > 1) {
                plus.add(num);
            } else if (num == 1) {
                one.add(num);
            } else if (num == 0) {
                zero.add(num);
            } else {
                minus.add(num);
            }
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
