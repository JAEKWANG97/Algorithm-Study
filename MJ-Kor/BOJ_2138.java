package algorithm_study.day_03_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2138 {

	private static int N;
	private static int result = 0;
	private static StringBuilder startStr, targetStr, startStr2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		startStr = new StringBuilder(br.readLine());
		startStr2 = new StringBuilder(startStr);
		targetStr = new StringBuilder(br.readLine());
		
		greedy();
		System.out.println(result);

	}
	
	public static void greedy() {
		for (int i = 0; i < N - 1; i++) {
			if(startStr.charAt(i) != targetStr.charAt(i)) {
				switchLight(startStr, i + 1);
				result++;
			}
		}
		
		if(startStr.charAt(N - 1) == targetStr.charAt(N - 1)) {
			return;
		}
		
		result = 1;
		switchLight(startStr2, 0);
		for (int i = 0; i < N - 1; i++) {
			if(startStr2.charAt(i) != targetStr.charAt(i)) {
				switchLight(startStr2, i + 1);
				result++;
			}
		}
		
		if(startStr2.charAt(N - 1) == targetStr.charAt(N - 1)) {
			return;
		}
		else {
			result = -1;
			return;
		}
	}
	
	public static void switchLight(StringBuilder state, int i) {
		if(i == 0) {
			if(state.charAt(i) == '0') {
				state.setCharAt(i, '1');
			}else {
				state.setCharAt(i, '0');
			}
			
			if(state.charAt(i + 1) == '0') {
				state.setCharAt(i + 1, '1');
			}else {
				state.setCharAt(i + 1, '0');
			}
		}
		else if(i == N - 1) {
			if(state.charAt(i) == '0') {
				state.setCharAt(i, '1');
			}else {
				state.setCharAt(i, '0');
			}
			
			if(state.charAt(i - 1) == '0') {
				state.setCharAt(i - 1, '1');
			}else {
				state.setCharAt(i - 1, '0');
			}
		}
		else {
			if(state.charAt(i) == '0') {
				state.setCharAt(i, '1');
			}else {
				state.setCharAt(i, '0');
			}
			
			if(state.charAt(i + 1) == '0') {
				state.setCharAt(i + 1, '1');
			}else {
				state.setCharAt(i + 1, '0');
			}
			
			if(state.charAt(i - 1) == '0') {
				state.setCharAt(i - 1, '1');
			}else {
				state.setCharAt(i - 1, '0');
			}
		}
	}
}
