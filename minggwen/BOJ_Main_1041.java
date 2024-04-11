import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_1041 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		int dice[] = new int[6];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<6;i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		int pair[][] = {{0,5},{1,4},{2,3}};
		int pair2[] = {5,4,3,2,1,0};
		int a,b,c;
		a = Math.min(dice[pair[0][0]], dice[pair[0][1]]);
		b = Math.min(dice[pair[1][0]], dice[pair[1][1]]);
		c = Math.min(dice[pair[2][0]],dice[pair[2][1]]);
		int min = Integer.MAX_VALUE;
		if(N==1) {
			int sum=0;
			int max = 0;
			for(int i=0;i<6;i++) {
				sum+=dice[i];
				max = Math.max(max, dice[i]);
			}
			System.out.println(sum-max);
		
		}
		else {
			int minNum = Math.min(a,b);
			minNum = Math.min(minNum, c);
			long cnt_1 = (N-2)*(N-2)*5+(N-2)*4;
			long cnt_3 = 4;
			long cnt_2 = N*N*5-cnt_1-cnt_3*3;
			for(int i=0;i<6;i++) {
				for(int j=0;j<6;j++) {
					if(i==j||pair2[i]==j)continue;
					min = Math.min(min, dice[i]+dice[j]);
				}
			}
			long answer = cnt_1*minNum+cnt_3*(a+b+c)+cnt_2*min/2;
			System.out.println(answer);
		}  

	}

}
