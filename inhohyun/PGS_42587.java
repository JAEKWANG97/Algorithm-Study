import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        for(int x : priorities) {
            list.add(x);
        }
        while(location >= 0) {
            System.out.println(list.get(0));
            int max = Collections.max(list);
            if(list.get(0) >= max) {
                list.remove(0);
                location--;
                answer++;
                if(location < 0) {
                    break;
                }
            } else {
                int tmp = list.get(0);
                list.remove(0);
                list.add(tmp);
                location--;
                if(location < 0) {
                    location = list.size()-1;
                }
            }
        }
        return answer;
    }
}
