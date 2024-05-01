import java.util.*;

class PGS_72411 {
     static Map<String,Integer> map;
     static int max;

    public String[] solution(String[] orders, int[] course) {
        List<String> ans = new ArrayList<>();
        for(int c : course){
            map = new HashMap<>();
            max = 0;

            for(String order: orders){
                char[] str = order.toCharArray();
                Arrays.sort(str);
                order = new String(str);
                checkOrder(0,-1,order,"",c);
            }

            for(String key : map.keySet()){
                int value = map.get(key);
                if(value > 1 && max == value){
                    ans.add(key);
                }
            }
        }

        Collections.sort(ans);
        return  ans.toArray(new String[ans.size()]);
    }

    public void checkOrder(int stage, int pidx, String order,String cur, int end){
        if(stage == end){
            map.put(cur,map.getOrDefault(cur,0)+1);
            max = Math.max(max,map.get(cur));
        }

        for(int i = pidx+1; i < order.length(); i++){
            checkOrder(stage+1,i,order,cur + order.charAt(i),end);
        }
    }
}