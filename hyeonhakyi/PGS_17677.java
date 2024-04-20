package ex0419;

import java.util.ArrayList;

public class PGS_17677 {
	class Solution {
	    public int solution(String str1, String str2) {
	        
	        str1 = str1.toLowerCase();
			str2 = str2.toLowerCase();
			
			ArrayList<String> list1 = new ArrayList<>();
			ArrayList<String> list2 = new ArrayList<>();
			
			ArrayList<String> union = new ArrayList<>();
			ArrayList<String> unionAll = new ArrayList<>();		
			
			for(int i = 0; i < str1.length()-1; i++) {
				char stt = str1.charAt(i);
				char stt1 = str1.charAt(i+1);
				
				if((stt >= 'a' && stt <= 'z')&&(stt1 >= 'a' && stt1 <= 'z')) {
					list1.add(String.valueOf(stt) + String.valueOf(stt1));
				}	
			}
			
			for(int i = 0; i < str2.length()-1; i++) {
				char stt = str2.charAt(i);
				char stt1 = str2.charAt(i+1);
				
				if((stt >= 'a' && stt <= 'z')&&(stt1 >= 'a' && stt1 <= 'z')) {
					list2.add(String.valueOf(stt) + String.valueOf(stt1));
				}	
			}
			
			
			for(String s : list1) {
				if(list2.remove(s)) {
					union.add(s);
				}
				unionAll.add(s);
			}
	        
	        unionAll.addAll(list2);
	        
	        
	        if(unionAll.size() == 0){
	            return 65536;
	        }
	        
	        
			
			double temp = (double)(union.size())/(double)(unionAll.size());
	        temp = temp * 65536;
	        
	        int answer = (int)temp;
			return answer;   
	    }
	}
}//class end
