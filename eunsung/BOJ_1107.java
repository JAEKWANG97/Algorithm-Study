import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1107 {
	static int minClick = Integer.MAX_VALUE;
	static int N;
	static String sN;
	static int[] nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sN = br.readLine();
		N = Integer.parseInt(sN); // 이동하려는 채널
		int M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수
		
		if(M == 0) {
			int result = (Math.abs(N-100)>sN.length()) ? sN.length():Math.abs(N-100);
			System.out.println(result);
			return;
		}
		
		boolean[] check = new boolean[10];
		nums = new int[10-M];
		for (int i = 0; i < 10; i++) {
			check[i] = true;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int k =Integer.parseInt(st.nextToken());
			check[k] = false;
		}
		int k = 0;
		for (int i = 0; i < 10; i++) {
			if(check[i]) nums[k++] = i;
		}
		
		// 1. 하나 작은 자리수
		if(sN.length()>1 && nums.length>0) {
			String smalls = "";
			for (int i = 0; i < sN.length()-1; i++) {
				smalls += (nums[nums.length-1])+"";
			}
			int smallNum = Integer.parseInt(smalls);
			if(Math.abs(smallNum - N)+sN.length()-1 < minClick) minClick = Math.abs(smallNum - N)+sN.length()-1;	
		}
		
		// 2. 하나 큰 자리수
		if(nums.length > 1) {
			String bigs = "";
			if(nums[0]==0) bigs += nums[1]+"";
			else bigs += nums[0]+"";
			for (int i = 1; i < sN.length()+1; i++) {
				bigs += (nums[0])+"";
			}
			int bigNum = Integer.parseInt(bigs);
			if(Math.abs(bigNum - N)+sN.length()+1 < minClick) minClick = Math.abs(bigNum - N) + sN.length()+1;
		}
		
		// 3. 같은 자리수
		dfs(0,"");
		
		// 4. +-
		int plusMinus = Math.abs(N-100);
		if(plusMinus < minClick) minClick = plusMinus;
		
		System.out.println(minClick);
		
	}

	private static void dfs(int depth, String number) {
		if(depth == sN.length()) {
			int dfsV = Math.abs(Integer.parseInt(number)-N);
			if(dfsV+sN.length() < minClick) minClick = dfsV+sN.length();
			return;
		}
		int tmp = sN.charAt(depth) - '0';
		int[] tlist = new int[nums.length];
		int minV = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			tlist[i] = Math.abs(tmp - nums[i]);
			if(tlist[i] <= minV) {
				minV = tlist[i];
			}
		}
		
		for (int i = 0; i < tlist.length; i++) {
			if(tlist[i] == minV) {
				if(tmp == nums[i]) {
					dfs(depth+1,number+(nums[i]+""));
					if(i>0) {
						dfsUp(depth+1,number+(nums[i-1]+""));
					}
					if(i<nums.length-1) {
						dfsDown(depth+1,number+(nums[i+1]+""));	
					}
					
				}else if(tmp > nums[i]) { // 누를 수 있는 숫자가 더 작을 때 => 큰숫자만 누르기
					dfsUp(depth+1,number+(nums[i]+""));
				}else { // 누를 수 있는 숫자가 더 클 때 => 작은숫자만 누르기
					dfsDown(depth+1,number+(nums[i]+""));
				}
				
			}
		}
		
	}
	
	private static void dfsUp(int depth, String number) { // 가장 큰 숫자만 

		for (int i = depth; i < sN.length(); i++) {
			if(i<sN.length()) {
				number += (nums[nums.length-1])+"";	
			}
			
		}
		
		int smallNum = Integer.parseInt(number);
		if(Math.abs(smallNum - N)+sN.length() < minClick) minClick = Math.abs(smallNum - N)+sN.length();	
		
	}

	private static void dfsDown(int depth, String number) { // 가장 작은 숫자만
		for (int i = depth; i < sN.length(); i++) {
			if (i < sN.length()) { // 인덱스가 배열의 범위를 초과하지 않는지 확인
	            number += (nums[0]) + "";
	        }
		}
		int bigNum = Integer.parseInt(number);
		if(Math.abs(bigNum - N)+sN.length() < minClick) minClick = Math.abs(bigNum - N) + sN.length();
		
	}


}
