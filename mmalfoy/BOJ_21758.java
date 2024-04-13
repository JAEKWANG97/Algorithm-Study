
import java.io.*;
import java.util.StringTokenizer;

public class BOJ_21758 {
	static int n;					
	static int[] honeys;			
	static long maxCount;			

	static long total;				
	static long[] toRightTotal;		
	static long[] toLeftTotal;		


	static void case1() {
		long bee1, bee2;	

		for (int i = 1; i <= n - 2; i++) {
			bee1 = total - honeys[0] - honeys[i];
			bee2 = total - toRightTotal[i];
			maxCount = Math.max(maxCount, bee1 + bee2);
		}
	}

	static void case2() {
		long bee1, bee2;

		for (int i = n - 2; i >= 1; i--) {
			bee1 = total - honeys[n - 1] - honeys[i];
			bee2 = total - toLeftTotal[i];
			maxCount = Math.max(maxCount, bee1 + bee2);
		}
	}

	static void case3() {
		long bee1, bee2;

		for (int i = 1; i <= n - 2; i++) {
			bee1 = toRightTotal[i] - honeys[0];
			bee2 = toLeftTotal[i] - honeys[n - 1];
			maxCount = Math.max(maxCount, bee1 + bee2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		honeys = new int[n];
		toRightTotal = new long[n];		
		toLeftTotal = new long[n];			
		long temp = 0;
		for (int i = 0; i < n; i++) {
			honeys[i] = Integer.parseInt(st.nextToken());

			temp += honeys[i];
			toRightTotal[i] = temp;
		}

		temp = 0;
		for (int i = n - 1; i >= 0; i--) {
			temp += honeys[i];
			toLeftTotal[i] = temp;
		}

		total = toRightTotal[n - 1];

		case1();
		case2();
		case3();

		System.out.println(maxCount);
	}
}