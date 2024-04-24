import java.util.Arrays; 
class Solution { 
	public String[] solution(int n, int[] arr1, int[] arr2) { 
		String[] answer = new String[n]; 

		for(int i=0; i<n; i++){ 
			// 10진수 -> 2진수
			String str1 = Integer.toBinaryString(arr1[i]);
			String str2 = Integer.toBinaryString(arr2[i]);

			int str1Len = str1.length();
			int str2Len = str2.length();
			String result = ""; 

			if(str1.length() < n) 
				for(int j=0; j<n-str1Len; j++) 
					str1 = "0" + str1; 

			if(str2.length() < n) 
				for(int j=0; j<n-str2Len; j++) 
					str2 = "0" + str2; 

			for(int j=0; j<str1.length(); j++){ 
				if(str1.charAt(j) == '1' || str2.charAt(j) == '1') 
					result += "#"; 
				else 
					result += " "; 
			} 
			answer[i] = result; 
		} 
		return answer; 
	} 
} 
