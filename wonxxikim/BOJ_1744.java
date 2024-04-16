import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		for(int i = 0 ; i<N ; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(num);
    
		int result = 0;
		int start = 0;
		int end = 0;
		// 연속 두 수가 음수일 경우
		for(int i=0 ; i<N-1 ; i+=2) {
			if(i+1<N&&num[i]<0 && num[i+1]<0) { //N이 홀수일 상황을 고려해야함.
				result+=num[i]*num[i+1];
				start = i+2;
			}
			else {
				start = i;
				break;
			}
		}
    
		// 연속 두 수가 양수일 경우
		for(int i=N-1 ; i>=1 ; i-=2) {
			if(i-1>=0&&num[i]>1 && num[i-1]>1) {
				result+=num[i]*num[i-1];
				end = i-2;
			}
			else {
				end = i;
				break;
			}
		}
    
		int cnt_zero=0; //0의 개수 (가장 작은 음수 삭제할 수 있는 최대값)
		for(int i = start; i<=end; i++) {
			if(num[i]==0) cnt_zero++;
		}
    
    //위에서 처리하고 남은 수
		for(int i = start ; i<=end;i++) {
			if(num[i]<0&&cnt_zero>0) cnt_zero--;
			else if(num[i]<0 && cnt_zero==0) result+=num[i];
			else if (num[i]>0)result+=num[i];
		}
    
		System.out.println(result);

	}

}
