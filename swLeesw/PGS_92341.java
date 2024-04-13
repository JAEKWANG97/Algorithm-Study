import java.util.*;

class Solution {
    static Map<String, Integer> parseMap;
    public int[] solution(int[] fees, String[] records) {
        // 출입시간
        Map<String, String> map = new HashMap<>();

        parseMap = new TreeMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] temp = records[i].split(" "); //스플릿으로 나누기
            // 입차
            if (temp[2].equals("IN")) {
                map.put(temp[1], temp[0]);
            } else {
                parse(map.get(temp[1]), temp[0], temp[1]); //입시, 출시, 차번호
                map.remove(temp[1]);
            }
        }

        // map에 남아있는 차량이 있다면
        if (!map.isEmpty()) {
            for (String s : map.keySet()) {
                parse(map.get(s), "23:59", s);
            }
        }

        // 계산 메서드 호출
        calculate(fees);
        
        // map에 value를 담아오기 위한 list
        List<Integer> list = new ArrayList<>();
        for (String key : parseMap.keySet()) {
            list.add(parseMap.get(key));
        }
        
        // list를 array로 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
    
    // 총 주차 시간을 계산
    static void parse(String in, String out, String carNum) {
        String[] outTemp = out.split(":");
        int outMin = Integer.parseInt(outTemp[0]) * 60 + Integer.parseInt(outTemp[1]);
        String[] inTemp = in.split(":");
        int inMin = Integer.parseInt(inTemp[0]) * 60 + Integer.parseInt(inTemp[1]);
        // 총 주차시간
        int dif = outMin - inMin;
        parseMap.put(carNum, parseMap.getOrDefault(carNum, 0) + dif);
    }
    
    //요금 계산
    static void calculate(int[] fees) {
        for (String key : parseMap.keySet()) {
            // 기본 < 주차
            if (parseMap.get(key) < fees[0]) {
                parseMap.put(key, fees[1]);
            } else { //기본 시간 이상
                double addTime = parseMap.get(key) - fees[0];
                addTime = Math.ceil(addTime / fees[2]);
                int result = (int) (addTime * fees[3] + fees[1]);
                parseMap.put(key, result);
            }
        }
    }
}
