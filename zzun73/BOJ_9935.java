import java.io.*;

public class BOJ_9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder answer = new StringBuilder();
        StringBuilder str = new StringBuilder(br.readLine());
        String bomb = br.readLine();

        int strLen = str.length();
        int bombLen = bomb.length();
        for (int i = 0; i < strLen; i++) {
            answer.append(str.charAt(i));
            int ansLen = answer.length();
            if (ansLen >= bombLen) {
                if (answer.substring(ansLen - bombLen, ansLen).equals(bomb)) {
                    answer.delete(ansLen - bombLen, ansLen);
                }
            }
        }

        bw.write(answer.length() == 0 ? "FRULA" : answer.toString());
        br.close();
        bw.close();
    }
}