import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_1107{

	static boolean[] nums;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		nums = new boolean[10];
		Arrays.fill(nums, true);
		if(M!=0)st = new StringTokenizer(br.readLine());
		for(int m=0;m<M;m++) {
			int num = Integer.parseInt(st.nextToken());
			nums[num] = false;
		}
		int diff2 = Math.abs(N-100);
		int diff1 = 0;
		for(;diff1<=diff2;diff1++) {
			int num = N+diff1;
			int move = canMove(num);
			if(move!=-1) {
				diff1 += move;
				break;
			}
		}
		for(int diff = 0; diff<=N;diff++) {
			int num = N-diff;
			int move = canMove(num);
			if(move!=-1) {
				if(diff1>diff+move) {
					diff1 = diff+move;
				}
			}
		}
		System.out.println(Math.min(diff2, diff1));
	}
	private static int canMove(int N) {
		String tmp = Integer.toString(N);
		for(int i=0;i<tmp.length();i++) {
			int num = tmp.charAt(i)-'0';
			if(!nums[num]) return -1;
		}
		return tmp.length();
	}
}
