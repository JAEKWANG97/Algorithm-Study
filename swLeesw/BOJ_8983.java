import java.io.*;
import java.util.*;

public class BOJ_8983 {
	
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int m, n, l, sasu[], sol;
	static List<Pos> animals = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int x, y;		
		st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		sasu = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			sasu[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			animals.add(new Pos(y, x));
		}
		Arrays.sort(sasu);
		//init end
		
		for (int q = 0; q < n; q++) {
			Pos animal = animals.get(q);
			//do binary search and basis is position of x
			int start = 0;
			int end = m - 1;
			
			while (start <= end) {
				int mid = (start + end) / 2;
				
				int dist = Math.abs(animal.x - sasu[mid]) + animal.y; //사수와 동물 사이의 거리
				
				if (dist <= l) { //범위 내라면
					sol++;
					break;
				}
				
				if (sasu[mid] > animal.x) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}	
			}
		}
		
		
		System.out.println(sol);
	}
	
	
}