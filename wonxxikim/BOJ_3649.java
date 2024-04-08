
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3649 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tc = "";
		while ((tc = br.readLine()) != null) {
			int x = Integer.parseInt(tc) * 10000000;
			int n = Integer.parseInt(br.readLine());
			int[] lego = new int[n];
			for (int i = 0; i < n; i++) {
				lego[i] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(lego);

			int min = 0;
			int max = n - 1;
			int result_min = 0;
			int result_max = 0;
			boolean flag = false;
			while (min < max) {
				int len = lego[min] + lego[max];
				if (len == x) {
					result_min = lego[min];
					result_max = lego[max];
					flag = true;
					break;
				} else if (len < x) {
					min++;
				} else {
					max--;
				}
			}
			if (flag)
				System.out.println("yes " + result_min + " " + result_max);
			else
				System.out.println("danger");
		}

	}
}
