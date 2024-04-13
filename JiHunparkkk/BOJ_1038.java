import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int n;
    static List<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        if (n <= 10) {
            System.out.println(n);
        } else if (n > 1022) {
            System.out.println("-1");
        } else {
            for (int i = 0; i < 10; i++) {
                solution(i, 1);
            }
            Collections.sort(list);
            System.out.println(list.get(n));
        }
    }

    static void solution(long num, int idx) {
        if (idx > 10) {
            return;
        }
        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            solution((num * 10) + i, idx + 1);
        }
    }
}
