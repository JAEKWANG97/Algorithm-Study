import java.util.*;

class PRO_150368 {
    int dis[] = {10,20,30,40};
    int userL, emotiL;
    ArrayList<int[]> ans;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer;
        ans = new ArrayList<>();
        userL= users.length;
        emotiL = emoticons.length;
        ans.add(new int[]{0,0});
        dfs(users,emoticons, new int[emotiL], 0 );
        Collections.sort(ans, (a,b)-> b[0]-a[0] == 0 ? b[1]-a[1] : b[0]-a[0]);

        return ans.get(0);
    }

    public void dfs(int[][] u, int[] e,int[] disInfo, int level){
        if(level == emotiL){
            int buyCnt = 0 ;
            int buySum = 0;

            for(int i = 0 ;i < userL ;i++){
                int sum = 0 ;
                for(int j =0 ; j< emotiL; j++){
                    if(disInfo[j] >= u[i][0])
                        sum+= e[j]/100*(100-disInfo[j]);
                }
                if(sum >= u[i][1]){
                    buyCnt++;
                }else{
                    buySum += sum;
                }
            }
            ans.add(new int[]{buyCnt,buySum});
            return;
        }else{
            for(int i = 0 ;i < 4 ;i++){
                disInfo[level] = dis[i];
                dfs(u,e,disInfo, level+1);
            }
        }
    }
}