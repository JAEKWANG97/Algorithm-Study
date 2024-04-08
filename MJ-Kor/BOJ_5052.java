package algorithm_study.day_03_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052 {
	
	private static class TrieNode{
		int value;
		String data;
		TrieNode[] childNodes = new TrieNode[10];
		public TrieNode(int value, String data) {
			super();
			this.value = value;
			this.data = data;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			TrieNode[] root = new TrieNode[10];
			int N = Integer.parseInt(br.readLine());
			String result = "YES";
			String[] strs = new String[N];
			for (int i = 0; i < N; i++) {
				strs[i] = br.readLine();
			}
			
			Arrays.sort(strs);
			
			for (int n = 0; n < N; n++) {
				TrieNode[] parent = root;
				String numbers = strs[n];
				for (int i = 0; i < numbers.length(); i++) {
					int value = (int)(numbers.charAt(i) - '0');
					if(parent[value] == null) {						
						if(i == numbers.length() - 1) {
							TrieNode node = new TrieNode(value, numbers);
							parent[value] = node;
						}
						else {						
							parent[value] = new TrieNode(value, null);
							parent = parent[value].childNodes;
						}
					}
					else {
						if(parent[value].data != null) {
							result = "NO";
							break;
						}
						else {
							if(i == numbers.length() - 1) {
								parent[value].data = numbers;
							}
							else {						
								parent = parent[value].childNodes;
							}
						}
					}
				}
			}
			System.out.println(result);
		}
	}

}
