import java.util.*;
import java.io.*;


public class BOJ_1644 {
	
	static int n, sol;
	static boolean visited[];
	static List<Integer> list = new ArrayList<>();//소수 객체
	static void makePrime(int n) { //에라토스 소수 만들기
		for (int i = 2;  i <= n; i++) {
			if (!visited[i]) {
				list.add(i);
			}
			for (int j = i; j <= n; j += i) {
				visited[j] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		visited = new boolean[4000000 + 1];
		makePrime(4000000); //소수 구하기
		//투포인터로 구하기(s == e 허용)
		int s = 0;
		int e = 0;
		int sum = list.get(s);
		while (s <= e) { //s가 넘어가면 안됨
			if (sum > n) { //sum이 더 크면
				sum -= list.get(s++); //줄이기
			} else {//더 작거나 같으면
				if (sum == n) sol++; //같으면 sol++
				//idx넘어가면 탈출
				if (++e >= list.size()) break; 
				sum += list.get(e);
			}
		}
		System.out.println(sol);
	}
}
