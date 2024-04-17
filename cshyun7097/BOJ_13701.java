import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BOJ_13701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        
        int size = st.countTokens();
        for (int i = 0; i < size; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            set.add(tmp);
        }
        for (Integer i : set) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
