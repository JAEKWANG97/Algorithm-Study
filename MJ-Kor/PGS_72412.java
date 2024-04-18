package algorithm_study.april.day_04_18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Solution {
	
	static HashMap<String, List<Integer>> table;
	
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        table = new HashMap<String, List<Integer>>();
        
        for (int i = 0; i < info.length; i++) {
			String[] strArr = info[i].split(" ");
			makeKey(strArr, 0, "");
		}
        
        for (String key : table.keySet()) {
        	Collections.sort(table.get(key));
        }
        
        for (int i = 0; i < query.length; i++) {
			query[i] = query[i].replaceAll(" and ", "");
			String[] queryKey = query[i].split(" ");
			if(table.containsKey(queryKey[0])) { 
				answer[i] = binarySearch(queryKey[0], Integer.parseInt(queryKey[1]));
			} else {
				answer[i] = 0;
			}
        }
        
        return answer;
    }
    
    private int binarySearch(String key, int target) {
		List<Integer> list = table.get(key);
		
		int start = 0;
		int end = list.size() - 1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			if (list.get(mid) < target) start = mid + 1;
			else end = mid - 1;
		}
		
		return list.size() - start;
	}

	private static void makeKey(String[] strArr, int cnt, String key) {
    	if(cnt == 4) {
    		if(!table.containsKey(key)) {
    			List<Integer> scores = new ArrayList<Integer>();
    			table.put(key, scores);
    		}
    		table.get(key).add(Integer.parseInt(strArr[cnt]));
    		return;
    	}
    	makeKey(strArr, cnt + 1, key + "-");
    	makeKey(strArr, cnt + 1, key + strArr[cnt]);
    }
}
public class PGS_72412 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String info = br.readLine();
		String query = br.readLine();		
		info = info.replaceAll("\"", "");
		query = query.replaceAll("\"", "");
		System.out.println(Arrays.toString(info.split(",")));
		System.out.println(Arrays.toString(query.split(",")));
		
		int[] result = new Solution().solution(info.split(","), query.split(","));
		System.out.println(Arrays.toString(result));
	}

}
