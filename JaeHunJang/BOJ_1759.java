import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
	static StringBuilder sb = new StringBuilder();
	static char input[], pw[];
	static final String VOWEL = "aeiou";
	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sb.toString());
	}
	
	private static void init() throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); // 암호 길이
		int C = Integer.parseInt(st.nextToken()); // 암호 만들 문자 개수
		pw = new char[L];
		
		input = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < input.length; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(input);
//		System.out.println(input);
		
		dfs(L, 0, 0, 0);
	}
	
	private static void dfs(int L, int cnt, int start, int vowelCnt) throws Exception {
		if (cnt == L) {
			if (vowelCnt >= 1 && (L - vowelCnt) >= 2) {
				sb.append(pw).append("\n");
			}
			return;
		}
		
		for (int i = start; i < input.length; i++) {
			pw[cnt] = input[i];
			if (VOWEL.contains(input[i]+"")) dfs(L, cnt+1, i+1, vowelCnt+1);
			else dfs(L, cnt+1, i+1, vowelCnt);
		}
	}
}

