import java.util.Arrays;
import java.util.Scanner;

public class BOJ_3649 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			
		while(true) {
			int x = sc.nextInt() * 10000000;
			int n = sc.nextInt();
			int[] nano = new int[n];
			for (int i = 0; i < n; i++) {
				nano[i] = sc.nextInt();
			}

			Arrays.sort(nano);
			int flag = 0;
			int l = 0;
			int r = n - 1;
			while (true) {
				if (l >= r) {
//					System.out.println(l+" "+r);
					break;
				}

				int sum =  nano[l] + nano[r];
//				System.out.println("tmp : "+tmp );
//				System.out.println(x);
				if (sum == x) {
					flag = 1;
					break;
				}

				if (sum > x) {
					r--;
				} else {
					l++;
				}
			}

			if (flag == 1) {
				System.out.println("yes " + nano[l] + " " + nano[r]);
			} else {
				System.out.println("danger");
			}
		}
		
	}catch(Exception e) {
		System.out.println();
	}
		
		
	}
}
