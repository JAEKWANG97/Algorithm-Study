class Solution {
    static int plus = 0;
    static int sales = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];

        int[] arr = new int[emoticons.length];

        combi(arr,0,users,emoticons);

        answer[0] = plus;
        answer[1] = sales;

        return answer;
    }
    static void combi(int[] arr,int start,int[][] users,int[] emoticons)
    {
        if(start == arr.length){
            calculate(arr, users, emoticons);
            return;
        }
        //할인율 조합
        for(int i = 10; i <= 40; i += 10){
            arr[start] = i;
            combi(arr, start+1, users, emoticons);
        }
    }
    static void calculate(int[] arr,int[][] users,int[] emoticons){
        int cnt = 0;
        int sales_to = 0;

        for(int[] user : users){
            int discount = user[0];
            int price = user[1];
            int sum = 0;

            for(int i = 0; i < arr.length; i++){
                if(arr[i] >= discount){
                    sum += (emoticons[i] / 100) * (100 - arr[i]);
                }
            }

            if(sum >= price){
                cnt++;
            }
            else{
                sales_to += sum;
            }
        }

        if(cnt > plus){
            plus = cnt;
            sales = sales_to;
            return;
        }
        else if(cnt == plus){
            if(sales < sales_to){
                sales = sales_to;
            }
        }
    }
}