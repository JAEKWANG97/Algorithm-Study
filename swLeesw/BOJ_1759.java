import java.io.*;
import java.util.*;

public class BOJ_1759 {
	static int L, C, com[];
	static char arr[];
	static boolean visited[];//팀 나누는 배열
	
	static void comb(int r, int c, int mo, int ja) {
		if (c == L) {
			if (mo >= 1 && ja >= 2) {
				for (int i = 0; i < c; i++) {
					System.out.print(arr[com[i]]);
				}
				System.out.println();				
			}
			return;
		}
		
		for (int i = r; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true;
				com[c] = i;
				if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
					comb(i + 1, c + 1, mo + 1, ja);	
				}
				else {
					comb(i + 1, c + 1, mo, ja + 1);										
				}
				visited[i] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		visited = new boolean[C];
		com = new int[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		
		
		comb(0, 0, 0, 0);
		
	}
}
