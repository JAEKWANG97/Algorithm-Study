import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        BitSet set = new BitSet(33554432);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<input.length; i++) {
            int num = Integer.parseInt(input[i]);

            if(!set.get(num)) {
                set.set(num);
                sb.append(num).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}
