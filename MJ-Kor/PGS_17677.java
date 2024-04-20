package algorithm_study.april.day_04_19;



import java.util.*;

public class PGS_17677 {
    
	/*
	 * 두 문자로 이루어진 문자열을 key로, 그것의 개수를 value로
	 */
	
    static Map<String, Integer> strMap1 = new HashMap<>();
    static Map<String, Integer> strMap2 = new HashMap<>();
    
    public int solution(String str1, String str2) {
        makeSet(str1.toLowerCase(), str2.toLowerCase());
        return jaccard();
    }
    
    private static int jaccard() {
        int inter = 0;
        int uni = 0;
        
        // 두 문장 다 집합을 만들 수 없을 때
        if(strMap1.size() == 0 && strMap2.size() == 0) return 65536;
        // 한 문장이 집합을 만들 수 없을 경우, 교집합이 0이므로
        if(strMap1.size() == 0 || strMap2.size() == 0) return 0;
        
        // 두 문장 다 집합을 만들 수 있을 때
        for(String key : strMap1.keySet()){
        	// 두 집합에 같은 원소가 있으면, 더 작은 개수를 교집합 개수에 저장
            if(strMap2.containsKey(key)){
                inter += Math.min(strMap1.get(key), strMap2.get(key));
            }
            // 집합1의 원소의 개수
            uni += strMap1.get(key);
        }
        // 집합2의 원소의 개수
        for(String key : strMap2.keySet()){
            uni += strMap2.get(key);
        }
        
        // 합집합의 원소의 개수는 두 집합의 원소의 개수 합 - 교집합 원소의 개수
        uni -= inter;
        return (int)((double)(inter)/(double)(uni) * 65536.0);
    }
    
    private static void makeSet(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        
        //문자열이 두 문자만 있을 경우
        if(l1 == 2) strMap1.put(str1, 1);
        if(l2 == 2) strMap2.put(str2, 1);
        
        // str1이 세 문자 이상일 경우
        if(strMap1.size() == 0) {
            for(int idx = 1; idx < l1; idx++) {
                char lChar = str1.charAt(idx);
                char rChar = str1.charAt(idx - 1);
                if(!Character.isAlphabetic(lChar)) {
                    idx += 1;
                    continue;
                }
                if(!Character.isAlphabetic(rChar)) {
                    continue;
                }
                String key = "" + lChar + rChar;
                if(strMap1.containsKey(key)){
                    strMap1.put(key, strMap1.get(key) + 1);
                } else {
                    strMap1.put(key, 1);
                }
            }
        }
        
        // str2가 세 문자 이상일 경우
        if(strMap2.size() == 0) {
            for(int idx = 1; idx < l2; idx++) {
                char rChar = str2.charAt(idx);
                char lChar = str2.charAt(idx - 1);
                
                // 오른쪽 문자가 영문자가 아닐 경우
                if(!Character.isAlphabetic(rChar)) {
                	// 두칸을 뛰어서 왼쪽 문자의 경우를 확인하지 않고 넘어감
                    idx += 1;
                    continue;
                }
                // 왼쪽 문자가 영문자가 아닐 경우
                if(!Character.isAlphabetic(lChar)) {
                	// 그냥 넘어감
                    continue;
                }
                String key = "" + rChar + lChar;
                
                // 그렇게 구한 영문자로 이루어진 두 문자가 이미 Map에 있는지 검사
                if(strMap2.containsKey(key)){
                	// 있으면 개수 추가
                    strMap2.put(key, strMap2.get(key) + 1);
                } else {
                    strMap2.put(key, 1);
                }
            }
        }
    }
}
