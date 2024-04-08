import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
	static int L,C,select[];
	static char[] alpha;
	static String check = "aeiou";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alpha = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i <C ; i++) {
			alpha[i] =  st.nextToken().charAt(0);
		}
		Arrays.sort(alpha);
		select = new int[L];
		combination(0,0);

	}
	public static void combination(int cnt, int start) {
		if(cnt == L) {
			int alpha_size = 0 ; //모음 수
			for(int i = 0 ; i<L; i++) {
				if(check.indexOf(alpha[select[i]])>=0) alpha_size++;
			}
			if(alpha_size>0 && (L-alpha_size)>1) {
				for(int i = 0 ;i<L ; i++) {
					System.out.print(alpha[select[i]]);
				}
                System.out.println();
			}
			return;
		}
		for(int i = start ; i<C ; i++) {
			select[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

}
