package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_5052 {

    class TrieNode {

        boolean isLeaf;
        int value;
        List<TrieNode> childNode;

        TrieNode(int value) {
            this.isLeaf = true;
            this.value = value;
            this.childNode = new LinkedList<>();
        }

        void addChild(String num) {
            if (num.equals("")) {
                return;
            }
            isLeaf = false;
            int digit = num.charAt(0) - '0';
            for (TrieNode child : childNode) {
                if (child.value == digit) {
                    child.addChild(num.substring(1));
                    return;
                }
            }
            TrieNode newChild = new TrieNode(digit);
            childNode.add(newChild);
            newChild.addChild(num.substring(1));
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int count;
    private TrieNode root;

    public static void main(String[] args) throws IOException {
        new BOJ_5052().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int i = 0; i < testCase; i++) {
            init();
            printResult(calcResult());
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        count = Integer.parseInt(bf.readLine());
        root = new TrieNode(-1);
        for (int i = 0; i < count; i++) {
            String rowValue = bf.readLine();
            root.addChild(rowValue);
        }
    }

    private String calcResult() {
        int leafCount = 0;

        Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TrieNode now = queue.poll();
            if (now.isLeaf) {
                leafCount++;
            }
            for (TrieNode node : now.childNode) {
                queue.offer(node);
            }
        }

        if (count == leafCount) {
            return "YES";
        }
        return "NO";
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}
