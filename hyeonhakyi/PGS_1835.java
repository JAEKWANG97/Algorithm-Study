package ex0428;

public class PGS_1835 {
    class Solution {
        static int answer;
        static String[] arr = {"A","C","F","J","M","N","R","T"};
        public int solution(int n, String[] data) {
            answer = 0;
            boolean[] visited = new boolean[8];
            dfs("",visited,data);
            return answer;
        }

        private static void dfs(String orders, boolean[] visited, String[] data){
            if(orders.length() == 8){
                if(check(orders, data)){
                    answer++;
                }
                return;
            }

            for(int i = 0; i < arr.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    String order = orders + arr[i];
                    dfs(order, visited, data);
                    visited[i] = false;
                }
            }
        }

        private static boolean check(String orders, String[] data){
            for(String i : data){
                int fir = orders.indexOf(i.charAt(0));
                int sec = orders.indexOf(i.charAt(2));
                char op = i.charAt(3);
                int length = Integer.parseInt(String.valueOf(i.charAt(4)));
                if(op == '='){
                    if(!(Math.abs(fir - sec) - 1 == length)){
                        return false;
                    }
                }else if(op == '>'){
                    if(!(Math.abs(fir - sec) - 1 > length)){
                        return false;
                    }
                }else{
                    if(!(Math.abs(fir - sec) - 1 < length)){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}//class end
