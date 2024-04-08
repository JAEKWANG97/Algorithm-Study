package ex0309;

public class PGS_150368 {
    private static int[] percent = {0,10,20,30,40};
    private static int total_count = 0 , total_price = 0, min = Integer.MAX_VALUE;
    public int[] solution(int[][] users,int[] emoticons){
        for(int[] user : users){
            min = Math.min(min,user[0]);
        }

        for(int i = 0; i < 5; i++){
            if(min <= percent[i]){
                min = i;
                break;
            }
        }

        int[] discount = new int[emoticons.length];
        dfs(discount,0,emoticons.length,users,emoticons);
        int[] result = {total_count,total_price};
        return result;
    }//solution end

    private static void dfs(int[] discount,int s,int n,int[][] users,int[] emoticons){
        if(s==n){
            check(discount,users,emoticons);
            return;
        }

        for(int i = s; i < n; i++){
            for(int j = min; j< 5; j++){
                discount[i] = percent[j];
                dfs(discount,i+1,n,users,emoticons);
            }
        }
    }//dfs end

    private static void check(int[] discount,int[][] users,int[] emoticons){
        int count = 0;
        int price = 0;

        for(int[] user : users){
            int userMinDiscount = user[0];
            int userPrice = user[1];
            int sum = 0;

            for(int i = 0; i < discount.length; i++){
                if(discount[i] < userMinDiscount)continue;
                sum += cal(discount[i],emoticons[i]);
            }

            if(sum >= userPrice){
                count++;
            }else{
                price += sum;
            }
        }

        if(total_count < count){
            total_count = count;
            total_price = price;
        }else if(total_count == count && price > total_price){
            total_price = price;
        }
    }//check end

    private static int cal(int dis,int price){
        return (price/100) * (100 - dis);
    }
}//class end
