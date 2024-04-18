import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

public class BOJ_Main_13701 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nums[] = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		BitSet s = new BitSet(1<<25);
		for(String num:nums) {
			int n = Integer.parseInt(num);
			if(!s.get(n)) {
				s.set(n);
				sb.append(n).append(" ");
			}
			
		}
		System.out.println(sb.toString());
	}

}
