import java.util.*;
import java.io.*;

public class BOJ_2579 {
	static int[] G, Mem;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		G = new int[N+1];
		Mem = new int[N+1];
		for (int i = 1; i < N+1 ; i++) {
			G[i] = Integer.parseInt(br.readLine());
		}
		
		if (N <= 2) {
			System.out.println(G[G.length-1]);
			return;
		}
		
		
		Mem[1] = G[1];
		Mem[2] = Math.max(G[1]+G[2], G[2]);
		Mem[3] = Math.max(G[1]+G[3], G[2]+G[3]);
		
		System.out.println(find(N));
	}
	
	private static int find(int N) {
		if (Mem[N] == 0) {
			Mem[N] = Math.max(G[N] + find(N-2), G[N]+G[N-1]+find(N-3));
		}
		return Mem[N];
	}
}
