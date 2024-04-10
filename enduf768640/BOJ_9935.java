import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {

    private static String str;
    private static String bomb;
    
    private static String answer;

    public static void main(String[] args) throws IOException {
        init();

        process();

        printAnswer();
    }

    private static void process() {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= bomb.length() && str.charAt(i) == bomb.charAt(bomb.length() - 1)) {
                check(stack);
            }
        }

        if (stack.isEmpty()) {
            answer = "FRULA";
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        answer = sb.reverse().toString();
    }

    private static void check(Stack<Character> stack) {
        StringBuilder check = new StringBuilder();

        for (int i = bomb.length() - 1; i >= 0; i--) {
            check.append(stack.pop());
        }

        if (!bomb.equals(check.reverse().toString())) {
            for (int i = 0; i < bomb.length(); i++) {
                stack.push(check.charAt(i));
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        bomb = br.readLine();
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}
