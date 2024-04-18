import java.io.*;
import java.util.*;

public class BOJ_13701 {
	
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		//int => 2 ^ 32 => 32개의 bit (8 byte)
		arr = new int[1 << 20]; //입력될 수 있는 수 2 ^ 25 - 1
		//int 1개 당 32개의 수 표현 가능
		/*
		 * 비트 마스킹 연산
		 * A = A | (1 << k) : 원소 추가
		 * A = A & ~(1 << k) : 원소 삭제
		 * (A & (1 << k)) == (1 << k) : 원소 포함 여부
		 * 
		 * 
		 */
		
		//
		while (st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			
			int idx = n / 32; //n / 2 ^ 5 
			int r = n % 32;
			
			if ((arr[idx] & (1 << r)) != (1 << r)) {
				arr[idx] = arr[idx] | (1 << r);
				sb.append(n + " ");
			}
			
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	
}
