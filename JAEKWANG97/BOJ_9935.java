import java.util.*;
import java.io.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        String target = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.length() >= target.length() && input.charAt(i) == target.charAt(target.length() - 1)) {
                if (sb.substring(sb.length() - target.length()).equals(target)) {
                    sb.delete(sb.length() - target.length(), sb.length());
                }
            }
        }

        System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
    }
}
