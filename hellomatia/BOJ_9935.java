package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_9935 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new BOJ_9935().solution();
    }

    public void solution() throws IOException {

        String str = bf.readLine();
        String explosionStr = bf.readLine();

        Stack<Character> stack = new Stack<>();

        char[] charArr = str.toCharArray();
        char[] explosionCharArr = explosionStr.toCharArray();

        int explosionStrLength = explosionStr.length();

        for (char c : charArr) {

            stack.push(c);

            if (stack.size() >= explosionStrLength) {

                boolean canExplosion = true;
                int index = explosionStrLength - 1;

                for (int i = stack.size() - 1; i >= stack.size() - explosionStrLength; i--) {
                    if (stack.get(i) != explosionCharArr[index--]) {
                        canExplosion = false;
                        break;
                    }
                }

                if (canExplosion) {
                    for (int i = 0; i < explosionStrLength; i++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if (stack.isEmpty()) {

            bw.write("FRULA");

        } else {

            for (Character c : stack) {
                sb.append(c);
            }

            str = sb.toString();
            bw.write(str);
        }
        bw.flush();
        bw.close();
    }
}