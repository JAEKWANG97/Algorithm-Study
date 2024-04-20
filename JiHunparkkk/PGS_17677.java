import java.util.*;

class Solution {
    public int solution(String str1, String str2) {        
        //List로 입력값 끊어서 저장 (소문자로 통일)
        //이중 반복문 돌려서 각 교집합 개수 저장 (boolean으로 이미 검사된 케이스 확인)
        //합집합은 교집합 먼저 구하고 빼기
        
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        splitInput(list1, str1);
        splitInput(list2, str2);
    
        int total = list1.size() + list2.size();
        int interCnt = findIntersection(list1, list2);
        int unionCnt = total - interCnt;      
        int answer = 0;
        
        try{
            if(interCnt == 0 && unionCnt == 0){
                answer = 65536;
            } else{
                answer = (int)Math.floor((interCnt * 1.0 / unionCnt) * 65536);
            }
        } catch(NumberFormatException e) { 
            answer = 65536;
        } finally {
            return answer;
        }
    }
    
    private static void splitInput(List<String> list, String str) {
        for(int i = 0; i < str.length() - 1; i++){
            String newStr = str.substring(i, i + 2).toLowerCase();
            if(isEnglish(newStr)){
                list.add(newStr);
            }
        }
    }
    
    private static boolean isEnglish(String str) {
        if(str.length() != 2){
            return false;
        }
        for(int i = 0; i < 2; i++){
            if('a' > str.charAt(i) || str.charAt(i) > 'z'){
                return false;
            }
        }
        return true;
    }
    
    private static int findIntersection(List<String> list1, List<String> list2) {
        boolean[] visited = new boolean[list2.size()];
        int cnt = 0;
        
        for(int i = 0; i < list1.size(); i++) {
            String now = list1.get(i);
            for(int j = 0; j < list2.size(); j++) {
                String target = list2.get(j);
                
                if(now.equals(target) && !visited[j]) {
                    cnt++;
                    visited[j] = true;
                    break;
                }
            }
        }
        return cnt;
    }
}
