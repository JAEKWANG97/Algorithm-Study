import java.util.*;
class Solution {
    public int[] solution(String msg) {
        int NUMBER = 1;
        Map<String,Integer> dic = new HashMap<>();
        for(int i = 0 ; i < 'Z' - 'A'+1 ; i++){
            char now = (char)('A'+i);
            dic.put(now+"",NUMBER++);
        }
        List<Integer> result= new LinkedList<>();
        for(int i = 0 ; i < msg.length() ; i++){
            int index = 0;
            String sub = "";
            for(int j = i + 1 ; j <= msg.length() ;  j++){
                sub = msg.substring(i,j);
                if(dic.containsKey(sub)){
                    index = dic.get(sub);
                    if(j == msg.length()){
                        i = j;
                    }
                    continue;
                }else{
                    i = j-2;
                    break;
                }
            }
            dic.put(sub,NUMBER++);
            result.add(index);
        }
        // System.out.println(result);
        int[] answer = {};
        answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}