import java.util.*;
import java.lang.*;
import java.io.*;
class Solution {
    static class Emoticon{
         int price;
         int margin;
         int sailPrice;
        public Emoticon(int price){
            this.price = price;
            this.margin = 0;
        }
        
        public void calculatePrice(){
            this.sailPrice = this.price * (100-this.margin) / 100;
        }
        
        @Override
        public String toString(){
            return String.valueOf(price);
        }
        
    }
    static class Person{
        int targetMargin;
        int money;
        int buyMoney;
        
        public Person(int targetMargin , int money){
            this.targetMargin = targetMargin;
            this.money = money;
            this.buyMoney = 0;
        }
    }
    static List<Person> personList = new ArrayList<>();
    static List<Emoticon> emoticonList = new ArrayList<>();
    static int[] marginPersent = new int[]{10,20,30,40};
    static int maxOnService = 0;
    static int maxTotalPrice = 0;
    
    // 1. 해당 아이템에 있는 마진 값 에 따라 구매 여부가 달라짐
    // 2. 구독을 하게 된다면 전체 마진에서 빠짐
    // 3. 아이템당 10~ 40 마진을 중복 조합해가면서 브루트 포스로 돌려야함
    // 
    public int[] solution(int[][] users, int[] emoticons) {
        // user 리스트입력
        for(int[] element : users){
            int targetMargin = element[0];
            int money = element[1];
            personList.add(new Person(targetMargin,  money));
        }
        // 이모티콘들 가격 입력 완료
        for(int i =0 ; i < emoticons.length ; i++){
            emoticonList.add(new Emoticon(emoticons[i]));
        }
        
        simulate(0,personList, emoticonList);
    
        int[] answer = {maxOnService , maxTotalPrice};
        return answer;
    }
    // 길이 만큼 가야함
    private static void simulate(int depth , List<Person> personList , List<Emoticon> emoticonList){
        
        if(depth == emoticonList.size()){
          
            int curTotalPrice = 0;
            int curTotalOnService = 0;
            for(Person p : personList){
                int curMoney = 0;
                for(Emoticon e: emoticonList){
                    if(e.margin >= p.targetMargin){
                        curMoney += e.sailPrice;
                    }
                    if(curMoney >= p.money){
                        curMoney = 0;
                        curTotalOnService+=1;
                        break;
                    }
                }
                curTotalPrice += curMoney;
            }
            
                if(maxOnService < curTotalOnService){
                    maxOnService = curTotalOnService;
                    maxTotalPrice = curTotalPrice;
                }
                else if(maxOnService == curTotalOnService){
                    maxTotalPrice = Math.max(maxTotalPrice , curTotalPrice);
                }
            return;
        }
        
            for(int j = 0 ; j < 4 ; j++){
            int margin = marginPersent[j];
            emoticonList.get(depth).margin = margin;
            emoticonList.get(depth).calculatePrice();
            simulate(depth + 1,  personList, emoticonList);
                }
        
    }
}
// 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것
// 2. 이모티콘 판매액을 최대한 늘리는 것
// 우선순위가 1, 2 임

// n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매
// 이모티콘 마다 할인율은 다를 수 있음 10%, 20%, 30%, 40% 중 하나

// 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매함
// 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입함

