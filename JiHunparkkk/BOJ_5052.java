import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static class Node {
        Map<Character, Node> child;
        boolean isLast;

        public Node() {
            this.child = new HashMap<>();
            this.isLast = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());

            String answer = "YES";
            Node root = new Node();

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                if (!insert(root, input)) {
                    answer = "NO";
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean insert(Node root, String str) {
        Node node = root;

        for (int i = 0; i < str.length(); i++) {
            //이미 숫자가 존재하면 해당 노드를 반환
            //새로운 숫자면 새로 추가
            node = node.child.computeIfAbsent(str.charAt(i), key -> new Node());
            if (node.isLast) {
                return false;
            }
        }
        if (node.child.size() != 0) {
            return false;
        }

        node.isLast = true;
        return true;
    }

}


