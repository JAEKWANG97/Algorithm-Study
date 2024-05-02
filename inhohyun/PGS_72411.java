import java.util.*;

public class Solution {
    public static HashMap<String, Integer> newOrder;
    public static int[] orderCnt;

    public static String[] solution(String[] orders, int[] course) {
        newOrder = new HashMap<>();
        orderCnt = new int[11];

        for(String item : orders){
            char[] temp = item.toCharArray();
            Arrays.sort(temp);
            String newItem = String.valueOf(temp);
            orderCombine(new boolean[newItem.length()], 0, newItem, course);
        }

        ArrayList<String> list = new ArrayList<>();

        for (String item : newOrder.keySet()) {
            int len = item.length();
            for (int i = 0; i < course.length; i++) {
                if(len == course[i]){
                    if(orderCnt[len] == newOrder.get(item)){
                        list.add(item);
                    }
                }
            }
        }

        Collections.sort(list);
        String[] answer = new String[list.size()];
        int idx = 0;

        for(String item : list){
            answer[idx++] = item;
        }

        return answer;
    }

    public static void orderCombine(boolean[] visited, int depth, String order, int[] course){
        if(depth == order.length()){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < order.length(); i++) {
                if(visited[i]){
                    sb.append(order.charAt(i));
                }
            }

            if(sb.length() < 2){
                return;
            }

            for(int n : course){
                if(sb.length() == n){
                    if(!newOrder.containsKey(sb.toString())){
                        newOrder.put(sb.toString(), 1);
                    }
                    else{72411
                        newOrder.put(sb.toString(), newOrder.get(sb.toString()) + 1);
                        orderCnt[sb.length()] = Math.max(orderCnt[sb.length()], newOrder.get(sb.toString()));
                    }
                }
            }

        } else{
            visited[depth] = true;
            orderCombine(visited, depth + 1, order, course);
            visited[depth] = false;
            orderCombine(visited, depth + 1, order, course);
        }
    }
}
