import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_Main_1038 {

	static List<Long> arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		for(int i=0;i<10;i++)comb(i);
		Collections.sort(arr);
		if(N>=arr.size())System.out.println(-1);
		else System.out.println(arr.get(N));
	}
	private static void comb(long num) {
		if(num > 9876543210L)return;
		arr.add(num);
		for(int i=0;i<10;i++) {
			if(num%10>i) {
				comb(num*10+i);
			}
		}
	}
}
